package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.income;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class incomeDto {
    private Long id;
    private String description;
    private LocalDate date;
    private double amount;
    private Long category;
    private Long accounts;

    public static incomeDto createIncomeDto(income target) {
        return new incomeDto(
                target.getId(),
                target.getDescription(),
                target.getDate(),
                target.getAmount(),
                target.getCategory().getId(),
                target.getAccounts().getId()
        );
    }



    @Override
    public String toString() {
        return "incomeDto{" + "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", source=" + category +
                ", accounts=" + accounts + '}';
    }

}
