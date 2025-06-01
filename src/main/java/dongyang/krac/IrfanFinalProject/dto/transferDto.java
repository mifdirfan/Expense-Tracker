package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class transferDto {
    private Long id;
    private String date;
    private String recipientAccounts;
    private String senderAccounts;
    private float amount;

    public transfer toEntity(){
        return new transfer(id, date, recipientAccounts, senderAccounts, amount);
    }

    @Override
    public String toString() {
        return "transferDto{" + "id=" + id +
                ", date=" + date +
                ", recipientAccounts=" + recipientAccounts +
                ", senderAccounts=" + senderAccounts +
                ", amount=" + amount + '}';
    }
}
