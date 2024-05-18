package ru.ooozakirov.miracle.workers.peristence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ooozakirov.miracle.workers.peristence.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(String studentId);

    Optional<List<Student>> findByRoomId(Long roomId);

    Optional<Student> findByPhone(String phone);

    Optional<Student> findByEmail(String email);
}
