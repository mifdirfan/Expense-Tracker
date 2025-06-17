package dongyang.krac.IrfanFinalProject.Service;

import dongyang.krac.IrfanFinalProject.Entity.user;
import dongyang.krac.IrfanFinalProject.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class authService {
    @Autowired
    private userRepository userRepository;

    public user authenticate(String username, String password) {
        user user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
