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
public class income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate date;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private category category;
    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private account accounts;



}
