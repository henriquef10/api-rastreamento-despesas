package henriquef10.api_rastreamento_despesas.core.entities.expense;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseAlreadyPaidException;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpensePaymentBeforeCreationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {

    private Long id;
    private String name;
    private String description;
    private BigDecimal amount;
    private Category category;
    private ExpenseStatus status;

    private LocalDate dueDate;
    private LocalDate paymentDate;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private User user;

    public void pay(LocalDate paymentDate) {

        if(paymentDate.isBefore(this.createdAt)){
            throw new ExpensePaymentBeforeCreationException("Payment date cannot be before expense creation date");
        }

        if(this.status.equals(ExpenseStatus.PAID)){
            throw new ExpenseAlreadyPaidException("Expense already paid");
        }

        this.status = ExpenseStatus.PAID;
        this.paymentDate = paymentDate;

    }

}
