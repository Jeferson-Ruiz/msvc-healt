package com.msvc.healt.infraestructer.adapter.treatment.out.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.msvc.healt.infraestructer.adapter.consultation.out.persistence.ConsultationEntityJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "treatments")
public class TreatmentEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_treatment")
    private Long idTreatment;

    @Column(name = "tre_registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "tre_date_init", nullable = false)
    private LocalDate dateInit;

    @Column(name = "tre_date_end", nullable = false)
    private LocalDate dateEnd;

    @Column(name = "tre_observations", nullable = false)
    private String observations;

    @OneToOne(optional = false)
    @JoinColumn(name = "tre_consultation", nullable = false, unique = true)
    private ConsultationEntityJpa consultation;
}
