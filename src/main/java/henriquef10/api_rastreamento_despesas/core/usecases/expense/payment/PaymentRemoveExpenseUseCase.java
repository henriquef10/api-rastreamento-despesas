package henriquef10.api_rastreamento_despesas.core.usecases.expense.payment;

import java.time.LocalDate;

public interface PaymentRemoveExpenseUseCase {

    PaymentRemoveExpenseOutput execute(Long id);

}
