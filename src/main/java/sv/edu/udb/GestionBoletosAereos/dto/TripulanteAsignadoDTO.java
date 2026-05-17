package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

@Data
public class TripulanteAsignadoDTO {
    private Integer idTripulante;
    private String nombre;
    private String cargo;
    private String aerolineaNombre;
}