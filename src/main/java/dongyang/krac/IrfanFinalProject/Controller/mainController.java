package dongyang.krac.IrfanFinalProject.Controller;


import dongyang.krac.IrfanFinalProject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private subscriptionRepository subscriptionRepository;
    @Autowired
    private expenseRepository expenseRepository;
    @Autowired
    private incomeRepository incomeRepository;
    @Autowired
    private transferRepository transferRepository;
    @Autowired
    private categoryRepository categoryRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("accounts", accountRepository.findTop3ByOrderByIdDesc());
        model.addAttribute("subscriptions", subscriptionRepository.findTop3ByOrderByStartdateDesc());
        model.addAttribute("expenses", expenseRepository.findTop3ByOrderByDateDesc());
        model.addAttribute("incomes", incomeRepository.findTop3ByOrderByDateDesc());
        model.addAttribute("transfers", transferRepository.findTop3ByOrderByDateDesc());
        model.addAttribute("categories", categoryRepository.findTop3ByOrderByIdDesc());
        return "home";
    }



}
