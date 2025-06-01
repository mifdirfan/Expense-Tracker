package dongyang.krac.IrfanFinalProject.dto;

import dongyang.krac.IrfanFinalProject.Entity.subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class subscriptionDto {
    private Long id;
    private String name;
    private float amount;
    private String date;

    public subscription toEntity(){
        return new subscription(id, name, amount, date);
    }

    @Override
    public String toString() {
        return "subscriptionDto{" + "id=" + id +
                ", name=" + name +
                ", amount=" + amount +
                ", date=" + date + '}';
    }
}
