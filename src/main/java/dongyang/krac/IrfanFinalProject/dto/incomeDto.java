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
    private LocalDate date;
    private double amount;
    private String source;
    private Long accounts;



    @Override
    public String toString() {
        return "incomeDto{" + "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", source=" + source +
                ", accounts=" + accounts + '}';
    }

}
