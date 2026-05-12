package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VueloCreationDTO {
    private String numeroVuelo;
    private String origen;
    private String destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private String estado; // P, V, A, C
    private Integer idAvion;
    private List<TarifaCreationDTO> tarifas;

    @Data
    public static class TarifaCreationDTO {
        private String clase;
        private BigDecimal precio;
    }
}
