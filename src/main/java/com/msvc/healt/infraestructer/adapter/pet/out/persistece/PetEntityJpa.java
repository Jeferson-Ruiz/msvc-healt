package com.msvc.healt.infraestructer.adapter.pet.out.persistece;

import java.time.LocalDate;
import com.msvc.healt.infraestructer.adapter.owner.out.persistence.OwnerEntityJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class PetEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idPet;

    @Column(name = "pet_name", nullable = false, length = 50)
    private String name;

    @Column(name = "pet_species", nullable = false, length = 50)
    private String specie;

    @Column(name = "pet_breed", length = 50)
    private String breed;

    @Column(name = "pet_sex", length = 1)
    private String sex;

    @Column(name = "pet_dateBorn", nullable = false)
    private LocalDate dateBorn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_owner", nullable = false)
    private OwnerEntityJpa owner;

    @Column(name = "pet_registration_date", nullable = false)
    private LocalDate registrationDate;
}
