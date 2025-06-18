package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class expenseDto {
    private Long id;
    private String description;
    private LocalDate date;
    private Long category;
    private String categoryName; // for template display
    private double amount;
    private Long accounts;

    public static expenseDto createExpenseDto(expense target) {
        return new expenseDto(
                target.getId(),
                target.getDescription(),
                target.getDate(),
                target.getCategory().getId(),
                target.getCategory().getName(),
                target.getAmount(),
                target.getAccounts().getId()
        );
    }
}
