package dongyang.krac.IrfanFinalProject.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class transfer {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private account toAccount;

    @Column
    private double amount;
}
