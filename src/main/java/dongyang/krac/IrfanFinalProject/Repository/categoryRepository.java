package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface categoryRepository extends CrudRepository<category, Long> {
    List<category> findByType(String type);

}
