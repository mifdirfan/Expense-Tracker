package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.income;
import org.springframework.beans.PropertyValues;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface incomeRepository extends CrudRepository<income, Long> {
    List<income> findByCategoryId(Long categoryId);
}
