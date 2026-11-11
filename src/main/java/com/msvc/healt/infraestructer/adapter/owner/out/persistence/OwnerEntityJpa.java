package com.msvc.healt.infraestructer.adapter.owner.out.persistence;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owners")
public class OwnerEntityJpa {

    @Id
    @Column(name = "id_owner")
    private Long id;

    @Column(name = "own_name", nullable = false, length = 50)
    private String name;

    @Column(name = "own_lastname", nullable = false, length = 50)
    private String lastName;

    @Column(name = "own_document_type", nullable = false, length = 4)
    private String documentType;

    @Column(name = "own_document_number", nullable = false, length = 20, unique = true)
    private String documentNumer;

    @Column(name = "own_email", nullable = false, length = 100)
    private String email;

    @Column(name = "own_phone", nullable = false, length = 10)
    private String numberPhone;

    @Column(name = "own_status", nullable = false)
    private boolean status;

    // private Set<PetJpa> pets;

    @Column(name = "own_registration_date")
    private LocalDate registrationDate;
}
