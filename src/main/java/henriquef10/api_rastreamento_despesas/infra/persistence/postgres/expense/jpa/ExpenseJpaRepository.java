package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.jpa;

import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseJpaRepository extends JpaRepository<ExpenseEntity, Long> {

    @Query("SELECT e FROM ExpenseEntity e WHERE e.user.id = :userId")
    List<ExpenseEntity> findAllByUserId(Long userId);

    void deleteByIdAndUser_Id(Long id, Long userId);

}
