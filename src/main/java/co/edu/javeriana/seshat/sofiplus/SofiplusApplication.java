package co.edu.javeriana.seshat.sofiplus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Usuario;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Methods.CrearUsuario;

@SpringBootApplication
@EnableMongoRepositories
public class SofiplusApplication {

    @Autowired
    private CrearUsuario crearUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SofiplusApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            // Set up test user data
            Usuario testUser = new Usuario();
            testUser.setId("test-user-id");
            testUser.setNombre("Test User");
            testUser.setEmail("testuser@example.com");
            testUser.setPassword("testPassword123");  // Plain password; will be encoded
            testUser.setFamiempresaID("testFamiempresaID");
            testUser.setRol("USER");

            // Prepare the RequestMessage with the test user data
            RequestMessage requestMessage = new RequestMessage(args);
            requestMessage.setParams(testUser);

            // Run the CrearUsuario process to create the test user
            try {
                crearUsuario.run(requestMessage);
                System.out.println("Test user created successfully at startup.");
            } catch (Exception e) {
                System.err.println("Test user creation failed: " + e.getMessage());
            }
        };
    }
}