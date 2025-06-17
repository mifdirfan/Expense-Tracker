package dongyang.krac.IrfanFinalProject.Service;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.category;
import dongyang.krac.IrfanFinalProject.Entity.expense;
import dongyang.krac.IrfanFinalProject.Entity.income;
import dongyang.krac.IrfanFinalProject.Repository.categoryRepository;
import dongyang.krac.IrfanFinalProject.Repository.expenseRepository;
import dongyang.krac.IrfanFinalProject.Repository.incomeRepository;
import dongyang.krac.IrfanFinalProject.dto.accountDto;
import dongyang.krac.IrfanFinalProject.dto.categoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class categoryService {
    @Autowired
    private expenseRepository expenseRepository;

    @Autowired
    private incomeRepository incomeRepository;
    @Autowired
    private categoryRepository categoryRepository;

    public double calculateTotalExpenseForCategory(Long categoryId) {
        return expenseRepository.findByCategoryId(categoryId)
                .stream()
                .mapToDouble(expense::getAmount)
                .sum();
    }

    public double calculateTotalIncomeForCategory(Long categoryId) {
        return incomeRepository.findByCategoryId(categoryId)
                .stream()
                .mapToDouble(income::getAmount)
                .sum();
    }

    @Transactional
    public categoryDto create(categoryDto target){

        category newCategory = new category();
        newCategory.setName(target.getName());
        newCategory.setType(target.getType());

        category saved = categoryRepository.save(newCategory);

        return categoryDto.createCategoryDto(saved);
    }

//    public categoryDto update(Long id, categoryDto dto) {
//        category target = categoryRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
//
//        target.setName(dto.getName());
//        target.setType(dto.getType());
//
//        category updated = categoryRepository.save(target);
//        return categoryDto.createCategoryDto(updated);
//    }


    @Transactional
    public categoryDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        category target = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! " +
                        "대상이 없습니다."));

        // 2. 댓글 삭제
        categoryRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return categoryDto.createCategoryDto(target);
    }
}
