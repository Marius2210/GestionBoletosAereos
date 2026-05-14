package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tipo = "Bearer";
    private String email;
    private String rol;
    private Integer idPasajero;
    private String nombrePasajero;

    public AuthResponse(String token, String email, String rol, Integer idPasajero, String nombrePasajero) {
        this.token = token;
        this.email = email;
        this.rol = rol;
        this.idPasajero = idPasajero;
        this.nombrePasajero = nombrePasajero;
    }
}
