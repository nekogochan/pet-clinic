package nekogochan.petclinic.repository;

import nekogochan.petclinic.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
