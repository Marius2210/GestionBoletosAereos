package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tipo = "Bearer";
    private String email;
    private String rol;

    public AuthResponse(String token, String email, String rol) {
        this.token = token;
        this.email = email;
        this.rol = rol;
    }
}
