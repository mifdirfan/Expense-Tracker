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
    private double amount;
    private String startdate;
    private String reccurrent;
    private Long accounts;
    private String lastChargedDate;



    public static subscriptionDto createsubscripiotnDto(subscription target) {



        return new subscriptionDto(
                target.getId(),
                target.getName(),
                target.getAmount(),
                target.getStartdate().toString(),
                target.getReccurrent(),
                target.getAccounts().getId(),
                target.getLastChargedDate() != null ? target.getLastChargedDate().toString() : "");
    }


    @Override
    public String toString() {
        return "subscriptionDto{" + "id=" + id +
                ", name=" + name +
                ", amount=" + amount +
                ", startdate=" + startdate +
                ", reccurrent=" + reccurrent +
                ", accounts=" + accounts + '}';
    }

}
