package sv.edu.udb.GestionBoletosAereos.controller;

import sv.edu.udb.GestionBoletosAereos.dto.*;
import sv.edu.udb.GestionBoletosAereos.service.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vuelos")
@Tag(name = "Vuelos", description = "Gestión de vuelos y búsqueda")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @GetMapping("/disponibles")
    @Operation(summary = "Buscar vuelos disponibles", description = "Lista vuelos según origen, destino y fecha")
    public ResponseEntity<?> buscarVuelos(@Valid @RequestBody BusquedaVueloRequest request) {
        return ResponseEntity.ok(vueloService.buscarVuelosDisponibles(request));
    }
}
