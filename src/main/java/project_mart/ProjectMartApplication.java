package project_mart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import project_mart.model.Product;
import project_mart.model.User;
import project_mart.repository.UserRepository;
import project_mart.service.ProductService;

import java.util.List;

@SpringBootApplication
public class ProjectMartApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjectMartApplication.class, args);
    }
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        User user = new User();
//        user.setUsername("namvu");
//        user.setPassword(passwordEncoder.encode("123"));
//        userRepository.save(user);
//        System.out.println(user);
//    }
}
