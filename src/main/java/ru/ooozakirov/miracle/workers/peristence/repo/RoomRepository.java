package ru.ooozakirov.miracle.workers.peristence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ooozakirov.miracle.workers.peristence.model.Room;
import ru.ooozakirov.miracle.workers.peristence.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByNumber(String number);
}
