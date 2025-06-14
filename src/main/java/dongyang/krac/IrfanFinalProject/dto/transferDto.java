package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class transferDto {
    private Long id;
    private LocalDate date;
    private Long fromAccount;
    private Long toAccount;
    private double amount;



    @Override
    public String toString() {
        return "transferDto{" + "id=" + id +
                ", date=" + date +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount + '}';
    }
}
