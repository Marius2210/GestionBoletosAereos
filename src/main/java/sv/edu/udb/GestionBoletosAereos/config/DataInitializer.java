package sv.edu.udb.GestionBoletosAereos.config;

import sv.edu.udb.GestionBoletosAereos.model.*;
import sv.edu.udb.GestionBoletosAereos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private AvionRepository avionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuario ADMIN si no existe
        if (!usuarioRepository.existsByEmail("admin@aerolinea.com")) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@aerolinea.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol("ADMIN");
            admin.setActivo(true);
            usuarioRepository.save(admin);
            System.out.println("Usuario ADMIN creado: admin@aerolinea.com / admin123");
        }

        // Crear aerolínea de prueba
        if (aerolineaRepository.count() == 0) {
            Aerolinea ava = new Aerolinea();
            ava.setNombreAerolinea("Avianca");
            ava.setCodigoIata("AVA");
            aerolineaRepository.save(ava);
        }
    }
}
