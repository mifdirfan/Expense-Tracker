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
public class income {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String date;
    @Column
    private String accounts;
    @Column
    private String source;
    @Column
    private float amount;



}
