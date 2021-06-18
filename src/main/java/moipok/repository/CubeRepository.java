package moipok.repository;

import moipok.models.Cube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CubeRepository extends JpaRepository<Cube, Long> {
    List<Cube> findByOrderByIdDesc();
}
