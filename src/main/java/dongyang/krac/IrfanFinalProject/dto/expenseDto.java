package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class expenseDto {
    private Long id;
    private String date;
    private String category;
    private String method;
    private float amount;

    public expense toEntity(){
        return new expense(id, date, category, method, amount);
    }

    @Override
    public String toString() {
        return "expenseDto{" + "id=" + id +
                ", date=" + date +
                ", category=" + category +
                ", method=" + method +
                ", amount=" + amount + '}';
    }
}
