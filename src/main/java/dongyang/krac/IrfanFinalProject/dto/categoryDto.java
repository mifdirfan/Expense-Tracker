package dongyang.krac.IrfanFinalProject.dto;


import dongyang.krac.IrfanFinalProject.Entity.category;
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

    public static categoryDto createCategoryDto(category saved) {
        return new categoryDto(saved.getId(), saved.getName(), saved.getType());
    }

}
