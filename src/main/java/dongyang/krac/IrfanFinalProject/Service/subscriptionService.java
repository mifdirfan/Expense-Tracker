package dongyang.krac.IrfanFinalProject.Service;


import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.subscription;
import dongyang.krac.IrfanFinalProject.Repository.accountRepository;
import dongyang.krac.IrfanFinalProject.Repository.subscriptionRepository;
import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " +
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

}
