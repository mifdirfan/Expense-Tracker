package dongyang.krac.IrfanFinalProject.Service;

import dongyang.krac.IrfanFinalProject.Entity.*;
import dongyang.krac.IrfanFinalProject.Repository.*;
import dongyang.krac.IrfanFinalProject.dto.searchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class searchService {
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    categoryRepository categoryRepository;
    @Autowired
    incomeRepository incomeRepository;
    @Autowired
    expenseRepository expenseRepository;
    @Autowired
    subscriptionRepository subscriptionRepository;


    public List<searchResultDto> searchAll(String keyword)  {
        List<searchResultDto> results = new ArrayList<>();

        accountRepository.findByNameContainingIgnoreCase(keyword)
                .forEach(acc -> results.add(new searchResultDto("Account", acc.getName(), "/account")));

        categoryRepository.findByNameContainingIgnoreCase(keyword)
                .forEach(cat -> results.add(new searchResultDto("Category", cat.getName(), "/categories")));

        incomeRepository.findByDescriptionContainingIgnoreCase(keyword)
                .forEach(income -> results.add(new searchResultDto("Income", income.getDescription(), "/incomes")));

        expenseRepository.findByDescriptionContainingIgnoreCase(keyword)
                .forEach(exp -> results.add(new searchResultDto("Expense", exp.getDescription(), "/expense")));

        subscriptionRepository.findByNameContainingIgnoreCase(keyword)
                .forEach(sub -> results.add(new searchResultDto("Subscription", sub.getName(), "/subscriptions")));

        return results;
    }
}
