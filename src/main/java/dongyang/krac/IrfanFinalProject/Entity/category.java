package dongyang.krac.IrfanFinalProject.Entity;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name; // e.g., "Food & Drinks"
    @Column
    private String type; // "EXPENSE" or "INCOME"
    @Column(nullable = false)
    private boolean active = true;
    @Transient
    private Double totalAmount;


}
