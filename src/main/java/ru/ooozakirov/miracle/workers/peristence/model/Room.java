package ru.ooozakirov.miracle.workers.peristence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "room")
@Data
@Accessors(chain = true)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "room")
    private List<Student> students;
    @OneToMany(mappedBy = "room")
    private List<RoomInventory> inventories;
    private String number;
    @ManyToOne
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private Floor floor;
    @Column(name = "max_count_student")
    private Integer maxCountStudent;
}
