package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;
import sv.edu.udb.GestionBoletosAereos.model.AsientoEstado;

@Data
public class AsientoDTO {
    private Integer idAsientoVuelo;
    private String numeroAsiento;
    private Integer fila;
    private String letra;
    private String tipo;
    private AsientoEstado estado;
    private String reservaCodigo; // Si está ocupado, qué reserva lo ocupa
}