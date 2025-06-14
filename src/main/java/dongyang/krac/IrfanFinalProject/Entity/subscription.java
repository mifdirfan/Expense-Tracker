package dongyang.krac.IrfanFinalProject.Entity;

import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity

public class subscription {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private double amount;
    @Column
    private LocalDate startdate;
    @Column
    private String reccurrent;

    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private account accounts;

}
