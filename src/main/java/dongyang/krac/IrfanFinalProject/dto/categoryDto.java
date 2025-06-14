package dongyang.krac.IrfanFinalProject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class categoryDto {
    private Long id;
    private String name;
    private String type;
    private Double budget;
}
