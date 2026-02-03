package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.Category.CategoryEntity;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "expenses")
public class ExpenseEntity {

    @Id()
    private Long id;
    private String name;
    private String description;
    private BigDecimal amount;

    private ExpenseStatus status;

    private LocalDate dueDate;
    private LocalDate paymentDate;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToOne()
    private UserEntity user;

    @OneToOne()
    private CategoryEntity category;


}
