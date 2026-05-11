package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class PasajeroBasicoDTO {
    private Integer idPasajero;
    private String nombreCompleto;
    private String numPasaporte;
}