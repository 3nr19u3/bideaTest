package com.bideafactory.booking.entity;

import com.bideafactory.booking.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> booking = new ArrayList<>();
}
