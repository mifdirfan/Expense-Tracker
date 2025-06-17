package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.expense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface expenseRepository extends CrudRepository<expense, Long> {
    List<expense> findByCategoryId(Long accountId);
}
