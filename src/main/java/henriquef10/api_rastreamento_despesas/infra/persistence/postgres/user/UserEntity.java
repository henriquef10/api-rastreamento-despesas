package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.ExpenseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id()
    private Long id;

    private String name;

    private String login;

    private String password;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @OneToMany()
    private List<ExpenseEntity> expenses;


}
