package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String email;
    private String rol;
    private Boolean activo;
    private PasajeroInfoBasicaDTO pasajero;
}
