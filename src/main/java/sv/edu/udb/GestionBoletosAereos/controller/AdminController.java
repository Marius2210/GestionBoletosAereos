package sv.edu.udb.GestionBoletosAereos.controller;

import sv.edu.udb.GestionBoletosAereos.dto.AerolineaResponseDTO;
import sv.edu.udb.GestionBoletosAereos.dto.UsuarioResponseDTO;
import sv.edu.udb.GestionBoletosAereos.service.*;
import sv.edu.udb.GestionBoletosAereos.model.*;
import sv.edu.udb.GestionBoletosAereos.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Administración", description = "Endpoints exclusivos para administradores")
public class AdminController {

    @Autowired
    private EstadisticaService estadisticaService;

    @Autowired
    private AdminUsuarioService adminUsuarioService; // ← NUEVO SERVICE

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private AvionRepository avionRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private TarifaRepository tarifaRepository;

    @Autowired
    private AdminAerolineaService adminAerolineaService;

    @GetMapping("/estadisticas")
    @Operation(summary = "Estadísticas del sistema")
    public ResponseEntity<?> getEstadisticas() {
        return ResponseEntity.ok(estadisticaService.obtenerEstadisticas());
    }

    //USUARIOS
    @GetMapping("/usuarios")
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(adminUsuarioService.listarTodosLosUsuarios());
    }

    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Obtener usuario por ID")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(adminUsuarioService.obtenerUsuarioPorId(id));
    }

    @PutMapping("/usuarios/{id}/estado")
    @Operation(summary = "Activar/Desactivar usuario")
    public ResponseEntity<UsuarioResponseDTO> toggleUsuarioEstado(@PathVariable Integer id) {
        return ResponseEntity.ok(adminUsuarioService.toggleUsuarioEstado(id));
    }

    @DeleteMapping("/usuarios/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario y su pasajero asociado")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable Integer id) {
        adminUsuarioService.eliminarUsuario(id);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Usuario eliminado exitosamente");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    //AEROLINEAS
    @GetMapping("/aerolineas")
    @Operation(summary = "Listar todas las aerolíneas")
    public ResponseEntity<List<AerolineaResponseDTO>> listarAerolineas() {
        return ResponseEntity.ok(adminAerolineaService.listarTodasAerolineas());
    }

    @PostMapping("/aerolineas")
    @Operation(summary = "Crear aerolínea")
    public ResponseEntity<AerolineaResponseDTO> crearAerolinea(@RequestBody Aerolinea aerolinea) {
        return ResponseEntity.ok(adminAerolineaService.crearAerolinea(aerolinea));
    }

    @PutMapping("/aerolineas/{id}")
    @Operation(summary = "Actualizar aerolínea")
    public ResponseEntity<AerolineaResponseDTO> actualizarAerolinea(@PathVariable Integer id,
                                                                    @RequestBody Aerolinea aerolinea) {
        return ResponseEntity.ok(adminAerolineaService.actualizarAerolinea(id, aerolinea));
    }

    @DeleteMapping("/aerolineas/{id}")
    @Operation(summary = "Eliminar aerolínea")
    public ResponseEntity<Map<String, String>> eliminarAerolinea(@PathVariable Integer id) {
        adminAerolineaService.eliminarAerolinea(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Aerolínea eliminada exitosamente");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vuelos")
    @Operation(summary = "Crear vuelo")
    public ResponseEntity<Vuelo> crearVuelo(@RequestBody Vuelo vuelo) {
        return ResponseEntity.ok(vueloRepository.save(vuelo));
    }

    @PostMapping("/vuelos/{idVuelo}/tarifas")
    @Operation(summary = "Agregar tarifa a un vuelo")
    public ResponseEntity<?> agregarTarifa(@PathVariable Integer idVuelo, @RequestBody Tarifa tarifa) {
        Vuelo vuelo = vueloRepository.findById(idVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        tarifa.setVuelo(vuelo);
        return ResponseEntity.ok(tarifaRepository.save(tarifa));
    }
}