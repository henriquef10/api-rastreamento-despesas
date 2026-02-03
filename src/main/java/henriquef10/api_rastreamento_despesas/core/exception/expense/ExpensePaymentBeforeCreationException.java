package henriquef10.api_rastreamento_despesas.core.exception.expense;

public class ExpensePaymentBeforeCreationException extends RuntimeException {
    public ExpensePaymentBeforeCreationException(String message) {
        super(message);
    }
}
