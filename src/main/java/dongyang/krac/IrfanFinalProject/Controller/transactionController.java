package dongyang.krac.IrfanFinalProject.Controller;


import dongyang.krac.IrfanFinalProject.Entity.*;
import dongyang.krac.IrfanFinalProject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/expense")
    public String expense(Model mo) {
        List<expense> expenseList = (List<expense>)expenseRepository.findAll();
        List<category> categoryList = (List<category>)categoryRepository.findAll();
        List<account> accountList = (List<account>)accountRepository.findAll();
        mo.addAttribute("expenseList", expenseList);
        mo.addAttribute("categoryList", categoryList);
        mo.addAttribute("accountList", accountList);
        return "transaction/expense";
    }
    @GetMapping("/incomes")
    public String income(Model mo) {
        List<income> incomeList = (List<income>)incomeRepository.findAll();
        mo.addAttribute("incomeList", incomeList);
        return "transaction/incomes";
    }
    @GetMapping("/transfer")
    public String transfer(Model mo) {
        List<transfer> transferList = (List<transfer>)transferRepository.findAll();
        mo.addAttribute("transferList", transferList);
        return "transaction/transfer";
    }
}
