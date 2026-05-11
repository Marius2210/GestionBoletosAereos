package sv.edu.udb.GestionBoletosAereos.service;

import sv.edu.udb.GestionBoletosAereos.dto.*;
import sv.edu.udb.GestionBoletosAereos.model.*;
import sv.edu.udb.GestionBoletosAereos.repository.*;
import sv.edu.udb.GestionBoletosAereos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());

        return new AuthResponse(token, usuario.getEmail(), usuario.getRol());
    }

    @Transactional
    public String registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol("USER");
        usuario.setActivo(true);
        usuario = usuarioRepository.save(usuario);

        // Crear pasajero
        Pasajero pasajero = new Pasajero();
        pasajero.setNombreCompleto(request.getNombreCompleto());
        pasajero.setNumPasaporte(request.getNumPasaporte());
        pasajero.setFechaNac(request.getFechaNac().toLocalDate());
        pasajero.setNacionalidad(request.getNacionalidad());
        pasajero.setEmail(request.getEmail());
        pasajero.setNumTelefono(request.getNumTelefono());
        pasajero.setUsuario(usuario);
        pasajeroRepository.save(pasajero);

        return "Usuario registrado exitosamente";
    }
}
