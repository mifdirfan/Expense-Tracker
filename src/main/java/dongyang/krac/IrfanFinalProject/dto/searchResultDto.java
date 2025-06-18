package dongyang.krac.IrfanFinalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class searchResultDto {
    private String type;       // "Account", "Category", "Subscription", etc.
    private String name;       // Display name
    private String redirectUrl; // URL to redirect when clicked

    public searchResultDto(String type, String name, String redirectUrl) {
        this.type = type;
        this.name = name;
        this.redirectUrl = redirectUrl;
    }
}
