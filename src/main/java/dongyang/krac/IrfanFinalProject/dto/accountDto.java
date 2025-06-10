package dongyang.krac.IrfanFinalProject.dto;

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
}
