package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.income;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class incomeDto {
    private Long id;
    private String date;
    private String accounts;
    private String source;
    private float amount;

    public income toEntity(){
        return new income(id, date, accounts, source, amount);
    }

    @Override
    public String toString() {
        return "incomeDto{" + "id=" + id +
                ", date=" + date +
                ", accounts=" + accounts +
                ", source=" + source +
                ", amount=" + amount + '}';
    }

}
