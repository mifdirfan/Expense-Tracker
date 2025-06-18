package dongyang.krac.IrfanFinalProject.Service;

import dongyang.krac.IrfanFinalProject.Entity.*;
import dongyang.krac.IrfanFinalProject.Repository.*;
import dongyang.krac.IrfanFinalProject.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class transactionService {
    @Autowired
    expenseRepository expenseRepository;
    @Autowired
    incomeRepository incomeRepository;
    @Autowired
    transferRepository transferRepository;
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private categoryRepository categoryRepository;


    // expense service
    @Transactional
    public expenseDto createExpense(expenseDto target){
        account account = accountRepository.findById((target.getAccounts()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Account ID"));
        category category = categoryRepository.findById((target.getCategory()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid category"));

        if (!category.isActive()) {
            throw new IllegalArgumentException("Cannot use an inactive category");
        }
        if (!account.isActive()) {
            throw new IllegalArgumentException("Cannot assign to an inactive account.");
        }

        expense newExpense = new expense();
        newExpense.setDescription(target.getDescription());
        newExpense.setAmount(target.getAmount());
        newExpense.setDate(target.getDate());
        newExpense.setCategory(category);
        newExpense.setAccounts(account);

        // deduct amount from account
        account.setBalance(account.getBalance() - target.getAmount());

        expense saved = expenseRepository.save(newExpense);
        accountRepository.save(account);

        return expenseDto.createExpenseDto(saved);
    }

    public expenseDto updateExpense(Long id, expenseDto dto) {
        expense originalExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("expense not found"));

        // refund the amount to account
        account oldAccount = originalExpense.getAccounts();
        double oldAmount = originalExpense.getAmount();
        oldAccount.setBalance(oldAccount.getBalance() + oldAmount);
        accountRepository.save(oldAccount);


        account newAccount = accountRepository.findById(dto.getAccounts())
                .orElseThrow(() -> new IllegalArgumentException("account not found"));

        category category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("category not found"));

        if (!category.isActive()) {
            throw new IllegalArgumentException("Cannot use an inactive category");
        }
        if (!newAccount.isActive()) {
            throw new IllegalArgumentException("Cannot assign to an inactive account");
        }

        // Deduct new amount from new account
        newAccount.setBalance(newAccount.getBalance() - dto.getAmount());

        originalExpense.setDescription(dto.getDescription());
        originalExpense.setAmount(dto.getAmount());
        originalExpense.setDate(dto.getDate());
        originalExpense.setCategory(category);
        originalExpense.setAccounts(newAccount);

        expense updated = expenseRepository.save(originalExpense);
        accountRepository.save(newAccount);
        return expenseDto.createExpenseDto(updated);
    }


    @Transactional
    public expenseDto deleteExpense(Long id) {
        // 1. 댓글 조회 및 예외 발생
        expense target = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! " +
                        "대상이 없습니다."));

        // Refund the amount back to the account
        account account = target.getAccounts();
        account.setBalance(account.getBalance() + target.getAmount());
        accountRepository.save(account);

        // 2. 댓글 삭제
        expenseRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return expenseDto.createExpenseDto(target);
    }

    // income service
    @Transactional
    public incomeDto createIncome(incomeDto target){
        account account = accountRepository.findById((target.getAccounts()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Account ID"));
        category category = categoryRepository.findById((target.getCategory()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid category"));

        if (!category.isActive()) {
            throw new IllegalArgumentException("Cannot use an inactive category");
        }
        if (!account.isActive()) {
            throw new IllegalArgumentException("Cannot assign to an inactive account.");
        }


        income newIncome = new income();
        newIncome.setDescription(target.getDescription());
        newIncome.setAmount(target.getAmount());
        newIncome.setDate(target.getDate());
        newIncome.setCategory(category);
        newIncome.setAccounts(account);

        // add amount from account
        account.setBalance(account.getBalance() + target.getAmount());

        income saved = incomeRepository.save(newIncome);
        accountRepository.save(account);

        return incomeDto.createIncomeDto(saved);
    }

    public incomeDto updateIncome(Long id, incomeDto dto) {
        income originalIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("income not found"));

        // refund the amount to account
        account oldAccount = originalIncome.getAccounts();
        double oldAmount = originalIncome.getAmount();
        oldAccount.setBalance(oldAccount.getBalance() - oldAmount);
        accountRepository.save(oldAccount);


        account newAccount = accountRepository.findById(dto.getAccounts())
                .orElseThrow(() -> new IllegalArgumentException("account not found"));

        category category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("category not found"));

        if (!category.isActive()) {
            throw new IllegalArgumentException("Cannot use an inactive category");
        }
        if (!newAccount.isActive()) {
            throw new IllegalArgumentException("Cannot assign to an inactive account");
        }

        // Add new amount from new account
        newAccount.setBalance(newAccount.getBalance() + dto.getAmount());

        originalIncome.setAmount(dto.getAmount());
        originalIncome.setDescription(dto.getDescription());
        originalIncome.setDate(dto.getDate());
        originalIncome.setCategory(category);
        originalIncome.setAccounts(newAccount);

        income updated = incomeRepository.save(originalIncome);
        accountRepository.save(newAccount);
        return incomeDto.createIncomeDto(updated);
    }


    @Transactional
    public incomeDto deleteIncome(Long id) {
        // 1. 댓글 조회 및 예외 발생
        income target = incomeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! " +
                        "대상이 없습니다."));

        // Deduct the amount back to the account
        account account = target.getAccounts();
        account.setBalance(account.getBalance() - target.getAmount());
        accountRepository.save(account);

        // 2. 댓글 삭제
        incomeRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return incomeDto.createIncomeDto(target);
    }

    // transfer service
    @Transactional
    public transferDto createTransfer(transferDto target){
        account fromAccount = accountRepository.findById(target.getFromAccount())
                .orElseThrow(() -> new IllegalArgumentException("From Account not found"));
        account toAccount = accountRepository.findById(target.getToAccount())
                .orElseThrow(() -> new IllegalArgumentException("From Account not found"));
        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (fromAccount.getBalance() < target.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in fromAccount");
        }

        //update balances
        fromAccount.setBalance(fromAccount.getBalance() - target.getAmount());
        toAccount.setBalance(toAccount.getBalance() + target.getAmount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        transfer newTransfer = new transfer();
        newTransfer.setFromAccount(fromAccount);
        newTransfer.setToAccount(toAccount);
        newTransfer.setAmount(target.getAmount());
        newTransfer.setDate(target.getDate());


        transfer saved = transferRepository.save(newTransfer);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        // transferRepository.save(newTransfer);

        return transferDto.createTransferDto(saved);
    }

    public transferDto updateTransfer(Long id, transferDto dto) {
        transfer target = transferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        //reverse old transfer
        account oldFromAccount = target.getFromAccount();
        account oldtoAccount = target.getToAccount();
        double oldAmount = target.getAmount();
        oldFromAccount.setBalance(oldFromAccount.getBalance() + oldAmount);
        oldtoAccount.setBalance(oldtoAccount.getBalance() - oldAmount);

        //apply new transfer
        account newFromAccount = accountRepository.findById(dto.getFromAccount())
                .orElseThrow(() -> new IllegalArgumentException("From Account not found"));
        account newToAccount = accountRepository.findById(dto.getToAccount())
                .orElseThrow(() -> new IllegalArgumentException("To Account not found"));

        newFromAccount.setBalance(newFromAccount.getBalance() - dto.getAmount());
        newToAccount.setBalance(newToAccount.getBalance() + dto.getAmount());

        target.setFromAccount(newFromAccount);
        target.setToAccount(newToAccount);
        target.setAmount(dto.getAmount());
        target.setDate(dto.getDate());


        accountRepository.saveAll(List.of(oldFromAccount, oldtoAccount, newFromAccount, newToAccount));
        return transferDto.createTransferDto(transferRepository.save(target));

    }

    @Transactional
    public transferDto deleteTransfer(Long id) {
        // 1. 댓글 조회 및 예외 발생
        transfer target = transferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! " +
                        "대상이 없습니다."));


        account fromAccount = target.getFromAccount();
        account toAccount = target.getToAccount();

        fromAccount.setBalance(fromAccount.getBalance() + target.getAmount());
        toAccount.setBalance(toAccount.getBalance() - target.getAmount());
        accountRepository.saveAll(List.of(fromAccount, toAccount));

        // 2. 댓글 삭제
        transferRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return transferDto.createTransferDto(target);
    }
}
