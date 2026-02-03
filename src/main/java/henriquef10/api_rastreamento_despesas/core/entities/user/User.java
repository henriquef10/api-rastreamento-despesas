package henriquef10.api_rastreamento_despesas.core.entities.user;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String login;
    private String password;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private List<Expense> expenses;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(Long id, String name, String login, String password, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.login = login;
    }

}
