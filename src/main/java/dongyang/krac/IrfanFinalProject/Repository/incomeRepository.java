package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.income;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface incomeRepository extends CrudRepository<income, Long> {
    List<income> findByCategoryId(Long accountId);
    List<income> findTop3ByOrderByDateDesc();
    List<income> findByDescriptionContainingIgnoreCase(String keyword);
}
