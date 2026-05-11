package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TarifaInfo {
    private Integer idTarifa;
    private String clase;
    private BigDecimal precio;
}
