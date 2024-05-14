package ru.ooozakirov.miracle.workers.peristence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ooozakirov.miracle.workers.peristence.model.Floor;
import ru.ooozakirov.miracle.workers.peristence.model.Student;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    Optional<Floor> findByNumber(String number);
}
