package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.Category;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id()
    private Long id;

    private String name;

    private String description;

}
