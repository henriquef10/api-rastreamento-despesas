package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category;


import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.ExpenseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "category")
    private List<ExpenseEntity> expenses;

}
