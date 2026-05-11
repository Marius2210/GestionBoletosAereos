package sv.edu.udb.GestionBoletosAereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.GestionBoletosAereos.model.Avion;

public interface AvionRepository extends JpaRepository<Avion, Integer> {}
