package dongyang.krac.IrfanFinalProject.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class category {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name; // e.g., "Food & Drinks"
    @Column
    private String type; // "EXPENSE" or "INCOME"
    @Column
    private Double budget; // optional
}
