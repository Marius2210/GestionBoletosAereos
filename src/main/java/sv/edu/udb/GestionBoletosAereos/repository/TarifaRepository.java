package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Tarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {
    void deleteByVueloIdVuelo(Integer idVuelo);
}