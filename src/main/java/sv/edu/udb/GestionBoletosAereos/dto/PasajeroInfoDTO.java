package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class PasajeroInfoDTO {
    private Integer idPasajero;
    private String nombreCompleto;
    private String numPasaporte;
    private String nacionalidad;
}
