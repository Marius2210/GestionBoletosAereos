package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class TripulacionCreationDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "El ID de la aerolínea es obligatorio")
    private Integer idAerolinea;
}
