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

public class subscription {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String amount;
    @Column
    private String date;

    public subscription(Long id, String name, float amount, String date) {
    }
}
