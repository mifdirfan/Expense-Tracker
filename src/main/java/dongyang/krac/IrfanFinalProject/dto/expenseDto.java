package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class expenseDto {
    private Long id;
    private LocalDate date;
    private String category;
    private double amount;
    private Long accountId;



    @Override
    public String toString() {
        return "expenseDto{" + "id=" + id +
                ", date=" + date +
                ", category=" + category +
                ", amount=" + amount +
                ", accountId=" + accountId + '}';
    }
}
