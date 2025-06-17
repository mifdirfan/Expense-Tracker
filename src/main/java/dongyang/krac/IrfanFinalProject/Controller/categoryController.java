package dongyang.krac.IrfanFinalProject.Controller;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.category;
import dongyang.krac.IrfanFinalProject.Entity.subscription;
import dongyang.krac.IrfanFinalProject.Repository.categoryRepository;
import dongyang.krac.IrfanFinalProject.Service.categoryService;
import dongyang.krac.IrfanFinalProject.dto.categoryDto;
import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class categoryController {
    @Autowired
    private categoryRepository categoryRepository;
    @Autowired
    private categoryService categoryService;

    @GetMapping("/categories")
    public String category(Model mo) {
        List<category> catList = (List<category>)categoryRepository.findAll();
        List<category> incomeList = (List<category>)categoryRepository.findByType("INCOME");
        List<category> expenseList = (List<category>)categoryRepository.findByType("EXPENSE");



        for (category c : incomeList) {
            double total = categoryService.calculateTotalIncomeForCategory(c.getId());
            c.setTotalAmount(total);
        }

        for (category c : expenseList) {
            double total = categoryService.calculateTotalExpenseForCategory(c.getId());
            c.setTotalAmount(total);
        }


        mo.addAttribute("catList", catList);
        mo.addAttribute("incomeList", incomeList);
        mo.addAttribute("expenseList", expenseList);
        return "board/categories";
    }
    @PostMapping("/categories/add")
    public ResponseEntity<categoryDto> create(@RequestBody categoryDto target) {
        categoryDto created = categoryService.create(target);
        return ResponseEntity.ok(created);
    }

//    @PatchMapping("/categories/edit/{id}")
//    public ResponseEntity<categoryDto> update(@PathVariable Long id,
//                                                  @RequestBody categoryDto dto) {
//
//        categoryDto updatedDto = categoryService.update(id, dto);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
//    }
    @PatchMapping("/categories/toggle-active/{id}")
    public ResponseEntity<?> toggleActive(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        category target = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        target.setActive(body.get("active"));
        categoryRepository.save(target);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity<categoryDto> delete(@PathVariable Long id) {
        categoryDto deletedDto = categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
