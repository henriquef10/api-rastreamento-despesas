package henriquef10.api_rastreamento_despesas.core.exception.expense;

public class ExpenseAlreadyPaidException extends RuntimeException {
    public ExpenseAlreadyPaidException(String message) {
        super(message);
    }
}
