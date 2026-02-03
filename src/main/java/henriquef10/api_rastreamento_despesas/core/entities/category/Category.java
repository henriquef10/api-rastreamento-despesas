package henriquef10.api_rastreamento_despesas.core.entities.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    private Long id;
    private String name;
    private String description;

}
