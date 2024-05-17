package ru.ooozakirov.miracle.workers.peristence.mapper;

import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ooozakirov.miracle.workers.peristence.dto.student.*;
import ru.ooozakirov.miracle.workers.peristence.model.Gender;
import ru.ooozakirov.miracle.workers.peristence.model.InventoryType;
import ru.ooozakirov.miracle.workers.peristence.model.Student;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS)
public interface StudentMapper {
    Student mapCreateStudentRequestToStudent(CreateStudentRequest request);

    @Mapping(target = "inventories", expression = "java(getInventories(student))")
    GetStudentResponse mapStudentToGetStudentResponse(Student student);

    List<ru.ooozakirov.miracle.workers.peristence.dto.floor.Student> mapModelStudentsToStudents(List<Student> students);

    void updateStudentFromDto(@MappingTarget Student student, UpdateStudentRequest request) throws IOException;

    default List<String> getInventories(Student student){
        return student.getInventories().stream().map(inventory -> inventory.getType().getLabel()).toList();
    }
}
