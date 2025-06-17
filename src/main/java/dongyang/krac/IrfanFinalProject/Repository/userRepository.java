package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.user;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<user, Long> {
    user findByUsername(String username);
}
