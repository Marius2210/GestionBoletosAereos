package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class ReservaInfoBasicaDTO {
    private Integer idReserva;
    private String codigoReserva;
    private String estadoReserva;
    private String pasajeroNombre;
    private String vueloInfo; // Ej: "AV402 - San Salvador a Guatemala"
}