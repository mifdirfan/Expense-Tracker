package dongyang.krac.IrfanFinalProject.Controller;


import dongyang.krac.IrfanFinalProject.Entity.user;
import dongyang.krac.IrfanFinalProject.Repository.userRepository;
import dongyang.krac.IrfanFinalProject.Service.authService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authController {
    @Autowired
    private authService authService;
    @Autowired
    private userRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        // Check if user already exists
        if (userRepository.findByUsername(username) != null) {
            if (userRepository.findByUsername(username).isPresent()) {
                return "redirect:/register?error";
            }
            user newUser = new user();
            newUser.setUsername(username);
            newUser.setPassword(new BCryptPasswordEncoder().encode(password));
            userRepository.save(newUser);
            return "redirect:/";
        }

        user newUser = new user();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
        model.addAttribute("success", "Registration successful. Please log in.");
        return "login";
    }

}
