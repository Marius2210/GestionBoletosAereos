package sv.edu.udb.GestionBoletosAereos.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagoResponseDTO {
    private Integer idPago;
    private BigDecimal monto;
    private String metodoPago;
    private LocalDateTime fechaPago;
    private ReservaInfoBasicaDTO reserva;
}