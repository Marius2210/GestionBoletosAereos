package sv.edu.udb.GestionBoletosAereos.service;

import sv.edu.udb.GestionBoletosAereos.dto.*;
import sv.edu.udb.GestionBoletosAereos.model.*;
import sv.edu.udb.GestionBoletosAereos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Autowired
    private TarifaRepository tarifaRepository;

    @Transactional
    public ReservaResponseDTO crearReserva(ReservaRequest request) {
        Vuelo vuelo = vueloRepository.findById(request.getIdVuelo())
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        Pasajero pasajero = pasajeroRepository.findById(request.getIdPasajero())
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));

        Tarifa tarifa = tarifaRepository.findById(request.getIdTarifa())
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));

        // Verificar disponibilidad
        long reservasExistentes = reservaRepository.findAll().stream()
                .filter(r -> r.getVuelo().getIdVuelo().equals(vuelo.getIdVuelo()) &&
                        !r.getEstadoReserva().equals("CANCELADA"))
                .count();

        if (reservasExistentes >= vuelo.getAvion().getCapacidad()) {
            throw new RuntimeException("No hay asientos disponibles en este vuelo");
        }

        Reserva reserva = new Reserva();
        reserva.setCodigoReserva(generarCodigoReserva());
        reserva.setVuelo(vuelo);
        reserva.setPasajero(pasajero);
        reserva.setAsientoPreferencia(request.getAsientoPreferencia());
        reserva.setPrecioTotal(tarifa.getPrecio());
        reserva.setEstadoReserva("PEN");
        reserva.setFechaReserva(LocalDateTime.now());

        Reserva savedReserva = reservaRepository.save(reserva);

        // Convertir a DTO
        return convertToResponseDTO(savedReserva);
    }

    public ReservaResponseDTO obtenerReserva(String codigoReserva) {
        Reserva reserva = reservaRepository.findByCodigoReserva(codigoReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return convertToResponseDTO(reserva);
    }

    @Transactional
    public ReservaResponseDTO cancelarReserva(String codigoReserva) {
        Reserva reserva = obtenerReservaEntity(codigoReserva);
        if (reserva.getEstadoReserva().equals("CONF")) {
            throw new RuntimeException("No se puede cancelar una reserva confirmada");
        }
        reserva.setEstadoReserva("CANCELADA");
        Reserva savedReserva = reservaRepository.save(reserva);
        return convertToResponseDTO(savedReserva);
    }

    private Reserva obtenerReservaEntity(String codigoReserva) {
        return reservaRepository.findByCodigoReserva(codigoReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    private String generarCodigoReserva() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private ReservaResponseDTO convertToResponseDTO(Reserva reserva) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setCodigoReserva(reserva.getCodigoReserva());
        dto.setEstadoReserva(reserva.getEstadoReserva());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setAsientoPreferencia(reserva.getAsientoPreferencia());
        dto.setPrecioTotal(reserva.getPrecioTotal());

        // Información del vuelo (solo campos necesarios)
        VueloInfoDTO vueloInfo = new VueloInfoDTO();
        vueloInfo.setIdVuelo(reserva.getVuelo().getIdVuelo());
        vueloInfo.setNumeroVuelo(reserva.getVuelo().getNumeroVuelo());
        vueloInfo.setOrigen(reserva.getVuelo().getOrigen());
        vueloInfo.setDestino(reserva.getVuelo().getDestino());
        vueloInfo.setFechaSalida(reserva.getVuelo().getFechaSalida());
        vueloInfo.setFechaLlegada(reserva.getVuelo().getFechaLlegada());
        vueloInfo.setAerolineaNombre(reserva.getVuelo().getAvion().getAerolinea().getNombreAerolinea());
        dto.setVuelo(vueloInfo);

        // Información del pasajero (solo campos necesarios)
        PasajeroInfoDTO pasajeroInfo = new PasajeroInfoDTO();
        pasajeroInfo.setIdPasajero(reserva.getPasajero().getIdPasajero());
        pasajeroInfo.setNombreCompleto(reserva.getPasajero().getNombreCompleto());
        pasajeroInfo.setNumPasaporte(reserva.getPasajero().getNumPasaporte());
        pasajeroInfo.setNacionalidad(reserva.getPasajero().getNacionalidad());
        dto.setPasajero(pasajeroInfo);

        return dto;
    }
}