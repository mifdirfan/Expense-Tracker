package dongyang.krac.IrfanFinalProject.Service;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.subscription;
import dongyang.krac.IrfanFinalProject.Entity.user;
import dongyang.krac.IrfanFinalProject.Repository.accountRepository;
import dongyang.krac.IrfanFinalProject.Repository.userRepository;
import dongyang.krac.IrfanFinalProject.dto.accountDto;
import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class accountService {

    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private userRepository userRepository;

    @Transactional
    public accountDto create(accountDto target){

        account newAccount = new account();
        newAccount.setName(target.getName());
        newAccount.setBalance(target.getBalance());
        newAccount.setType(target.getType());

        // 👇 Associate with logged-in user
        newAccount.setUser(getCurrentUser());

        account saved = accountRepository.save(newAccount);
        return accountDto.createAccountDto(saved);
    }

    public accountDto update(Long id, accountDto dto) {
        account target = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        target.setName(dto.getName());
        target.setBalance(dto.getBalance());
        target.setType(dto.getType());

        account updated = accountRepository.save(target);
        return accountDto.createAccountDto(updated);
    }


    @Transactional
    public accountDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        account target = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! " +
                        "대상이 없습니다."));

        // 2. 댓글 삭제
        accountRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return accountDto.createAccountDto(target);
    }

    public user getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }
}
