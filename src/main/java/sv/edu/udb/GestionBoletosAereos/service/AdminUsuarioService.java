package sv.edu.udb.GestionBoletosAereos.service;

import sv.edu.udb.GestionBoletosAereos.dto.PasajeroInfoBasicaDTO;
import sv.edu.udb.GestionBoletosAereos.dto.UsuarioResponseDTO;
import sv.edu.udb.GestionBoletosAereos.model.Usuario;
import sv.edu.udb.GestionBoletosAereos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> listarTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO toggleUsuarioEstado(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(!usuario.getActivo());
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return convertToResponseDTO(updatedUsuario);
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertToResponseDTO(usuario);
    }

    private UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.getActivo());

        // Si el usuario tiene un pasajero asociado, incluir su información básica
        if (usuario.getPasajero() != null) {
            PasajeroInfoBasicaDTO pasajeroInfo = new PasajeroInfoBasicaDTO();
            pasajeroInfo.setIdPasajero(usuario.getPasajero().getIdPasajero());
            pasajeroInfo.setNombreCompleto(usuario.getPasajero().getNombreCompleto());
            pasajeroInfo.setNumPasaporte(usuario.getPasajero().getNumPasaporte());
            pasajeroInfo.setNacionalidad(usuario.getPasajero().getNacionalidad());
            pasajeroInfo.setNumTelefono(usuario.getPasajero().getNumTelefono());
            dto.setPasajero(pasajeroInfo);
        }

        return dto;
    }
}
