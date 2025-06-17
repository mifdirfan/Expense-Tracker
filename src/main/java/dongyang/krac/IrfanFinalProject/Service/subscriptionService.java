package dongyang.krac.IrfanFinalProject.Service;


import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.subscription;
import dongyang.krac.IrfanFinalProject.Repository.accountRepository;
import dongyang.krac.IrfanFinalProject.Repository.subscriptionRepository;
import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class subscriptionService {
    @Autowired
    private subscriptionRepository subscriptionRepository;
    @Autowired
    private accountRepository accountRepository;

    @Transactional
    public subscriptionDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        subscription target = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! " +
                        "대상이 없습니다."));

        // 2. 댓글 삭제
        subscriptionRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return subscriptionDto.createsubscripiotnDto(target);
    }

    @Transactional
    public subscriptionDto create(subscriptionDto target){
        account account = accountRepository.findById((target.getAccounts()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Account ID"));

        subscription newSubscription = new subscription();
        newSubscription.setName(target.getName());
        newSubscription.setAmount(target.getAmount());
        newSubscription.setStartdate(LocalDate.parse(target.getStartdate()));
        newSubscription.setReccurrent(target.getReccurrent());
        newSubscription.setAccounts(account);

        subscription saved = subscriptionRepository.save(newSubscription);

        return subscriptionDto.createsubscripiotnDto(saved);
    }

    public subscriptionDto update(Long id, subscriptionDto dto) {
        subscription target = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        account acc = accountRepository.findById(dto.getAccounts())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        target.setName(dto.getName());
        target.setAmount(dto.getAmount());
        target.setStartdate(LocalDate.parse(dto.getStartdate()));
        target.setReccurrent(dto.getReccurrent());
        target.setAccounts(acc);

        subscription updated = subscriptionRepository.save(target);
        return subscriptionDto.createsubscripiotnDto(updated);
    }

    @Transactional
    @Scheduled(cron = "0 0 2 * * *") // Runs daily at 2AM
    public void autoChargeSubscriptions() {
        LocalDate today = LocalDate.now();

        List<subscription> all = (List<subscription>) subscriptionRepository.findAll();

        for (subscription s : all) {
            LocalDate lastDate = s.getLastChargedDate();
            LocalDate nextDue;

            switch (s.getReccurrent().toUpperCase()) {
                case "DAILY" -> nextDue = lastDate.plusDays(1);
                case "WEEKLY" -> nextDue = lastDate.plusWeeks(1);
                case "MONTHLY" -> nextDue = lastDate.plusMonths(1);
                default -> {
                    continue; // Skip if frequency is unknown
                }
            }

            if (!today.isBefore(nextDue)) {
                account acc = s.getAccounts();

                // Prevent overdraft (optional)
                if (acc.getBalance() >= s.getAmount()) {
                    acc.setBalance(acc.getBalance() - s.getAmount());
                    s.setLastChargedDate(today);
                    accountRepository.save(acc);
                    subscriptionRepository.save(s);
                    System.out.println("Charged " + s.getName() + ": ₩" + s.getAmount() + " on " + LocalDate.now());

                }
            }
        }
    }


}
