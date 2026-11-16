package com.msvc.healt.infraestructer.adapter.consultation.out.persistence;

import java.time.LocalDateTime;
import com.msvc.healt.infraestructer.adapter.owner.out.persistence.OwnerEntityJpa;
import com.msvc.healt.infraestructer.adapter.pet.out.persistece.PetEntityJpa;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consultations")
public class ConsultationEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultation")
    private Long idConsultation;

    @Column(name = "con_registration_date", nullable = false)
    private LocalDateTime registratioDate;

    @Column(name = "con_doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "con_person_charge", nullable = false, length = 100)
    private String personCharge;

    @Column(name = "con_document_number", nullable = false, length = 20)
    private String documentNumber;

    @Column(name = "con_pet_name", nullable = false, length = 50)
    private String petName;

    @Column(name = "con_age", nullable = false)
    private Integer age;

    @Column(name = "con_sex", nullable = false, length = 1)
    private String sex;

    @Column(name = "con_reason_inquiry", nullable = false, length = 255)
    private String reasonInquiry;

    @Column(name = "con_diagnosis", length = 255)
    private String diagnosis;

    @Column(name = "con_observations", length = 255)
    private String observations;

    @ManyToOne
    @JoinColumn(name = "con_pet", nullable = true)
    private PetEntityJpa pet;

    @ManyToOne
    @JoinColumn(name = "con_owner", nullable = true)
    private OwnerEntityJpa owner;
}
