package ru.ooozakirov.miracle.workers.peristence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ooozakirov.miracle.workers.peristence.dto.auth.UserDto;
import ru.ooozakirov.miracle.workers.peristence.dto.student.CreateStudentRequest;
import ru.ooozakirov.miracle.workers.peristence.model.Student;
import ru.ooozakirov.miracle.workers.peristence.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "role", constant = "STUDENT")
    @Mapping(target = "login", source = "studentId")
    @Mapping(target = "password", constant = "1111")
    User mapStudentRequestToUser(CreateStudentRequest request);
}
