package sv.edu.udb.GestionBoletosAereos.controller;

import sv.edu.udb.GestionBoletosAereos.dto.*;
import sv.edu.udb.GestionBoletosAereos.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Gestión de reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/crear")
    @Operation(summary = "Crear reserva", description = "Genera una nueva reserva de vuelo")
    public ResponseEntity<?> crearReserva(@Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.ok(reservaService.crearReserva(request));
    }

    @GetMapping("/{codigoReserva}")
    @Operation(summary = "Obtener reserva", description = "Consulta una reserva por su código")
    public ResponseEntity<?> obtenerReserva(@PathVariable String codigoReserva) {
        return ResponseEntity.ok(reservaService.obtenerReserva(codigoReserva));
    }

    @PutMapping("/cancelar/{codigoReserva}")
    @Operation(summary = "Cancelar reserva", description = "Cancela una reserva existente")
    public ResponseEntity<?> cancelarReserva(@PathVariable String codigoReserva) {
        return ResponseEntity.ok(reservaService.cancelarReserva(codigoReserva));
    }
}
