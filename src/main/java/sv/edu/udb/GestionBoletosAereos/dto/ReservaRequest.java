package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ReservaRequest {
    @NotNull(message = "ID de vuelo obligatorio")
    private Integer idVuelo;

    @NotNull(message = "ID del pasajero obligatorio")
    private Integer idPasajero;

    private String asientoPreferencia;

    @NotNull(message = "ID de tarifa obligatorio")
    private Integer idTarifa;
}
