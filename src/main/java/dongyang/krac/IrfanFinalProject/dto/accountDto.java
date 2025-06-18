package dongyang.krac.IrfanFinalProject.dto;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.subscription;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class accountDto {
    private Long id;
    private String name;
    private String type;
    private double balance;
    private Long userId; // optional for future use

    public accountDto(Long id, String name, double balance, String type, Long userId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.userId = userId;
    }

    public static accountDto createAccountDto(account target) {
        return new accountDto(
                target.getId(),
                target.getName(),
                target.getBalance(),
                target.getType(),
                target.getUser().getId());
    }
}


