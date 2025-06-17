package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface accountRepository extends CrudRepository<account, Long> {
    List<account> findByActiveTrue();
}
