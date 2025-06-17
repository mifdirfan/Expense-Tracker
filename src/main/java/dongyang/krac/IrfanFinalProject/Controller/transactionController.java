package dongyang.krac.IrfanFinalProject.Controller;


import dongyang.krac.IrfanFinalProject.Entity.*;
import dongyang.krac.IrfanFinalProject.Repository.*;
import dongyang.krac.IrfanFinalProject.Service.transactionService;
import dongyang.krac.IrfanFinalProject.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class transactionController {
    @Autowired
    private expenseRepository expenseRepository;
    @Autowired
    private incomeRepository incomeRepository;
    @Autowired
    private transferRepository transferRepository;
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private categoryRepository categoryRepository;
    @Autowired
    private transactionService transactionService;



    @GetMapping("/expense")
    public String expense(Model mo) {
        List<expense> expenseList = (List<expense>)expenseRepository.findAll();
        List<category> categoryList = (List<category>)categoryRepository.findByTypeAndActiveTrue("EXPENSE");
        List<account> accountList = (List<account>)accountRepository.findByActiveTrue();

        mo.addAttribute("expenseList", expenseList);
        mo.addAttribute("categoryList", categoryList);
        mo.addAttribute("accountList", accountList);
        return "transaction/expense";
    }
    @GetMapping("/incomes")
    public String income(Model mo) {
        List<income> incomeList = (List<income>)incomeRepository.findAll();
        List<category> categoryList = (List<category>)categoryRepository.findByTypeAndActiveTrue("INCOME");
        List<account> accountList = (List<account>)accountRepository.findByActiveTrue();

        mo.addAttribute("categoryList", categoryList);
        mo.addAttribute("accountList", accountList);
        mo.addAttribute("incomeList", incomeList);
        return "transaction/incomes";
    }
    @GetMapping("/transfer")
    public String transfer(Model mo) {
        List<transfer> transferList = (List<transfer>)transferRepository.findAll();
        mo.addAttribute("transferList", transferList);
        return "transaction/transfer";
    }

    // expense controller
    @PostMapping("/expense/add")
    public ResponseEntity<expenseDto> createExpense(@RequestBody expenseDto target) {
        expenseDto created = transactionService.createExpense(target);

        return ResponseEntity.ok(created);
    }

    @PatchMapping("/expense/edit/{id}")
    public ResponseEntity<expenseDto> updateExpense(@PathVariable Long id,
                                                  @RequestBody expenseDto dto) {

        expenseDto updatedDto = transactionService.updateExpense(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<expenseDto> deleteExpense(@PathVariable Long id) {
        expenseDto deletedDto = transactionService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

    // income controller
    @PostMapping("/income/add")
    public ResponseEntity<incomeDto> createExpense(@RequestBody incomeDto target) {
        incomeDto created = transactionService.createIncome(target);

        return ResponseEntity.ok(created);
    }

    @PatchMapping("/income/edit/{id}")
    public ResponseEntity<incomeDto> updateIncome(@PathVariable Long id,
                                                    @RequestBody incomeDto dto) {

        incomeDto updatedDto = transactionService.updateIncome(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/income/{id}")
    public ResponseEntity<incomeDto> deleteIncome(@PathVariable Long id) {
        incomeDto deletedDto = transactionService.deleteIncome(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
