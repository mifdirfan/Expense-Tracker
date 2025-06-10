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
public class account {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private double balance;
}
