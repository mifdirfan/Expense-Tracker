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
public class expense {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String date;
    @Column
    private String category;
    @Column
    private String method;
    @Column
    private float amount;


}
