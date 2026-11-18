package com.msvc.healt.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Treatment {
    private Long idTreatment;
    private LocalDateTime registrationDate;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private String observations;
    private Consultation consultation;

    public Treatment(Long idTreatment, LocalDateTime registrationDate, LocalDate dateInit, LocalDate dateEnd,
            String observations, Consultation consultation) {
        this.idTreatment = idTreatment;
        this.registrationDate = registrationDate;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.observations = observations;
        this.consultation = consultation;
    }

    public Treatment() {
    }

    public Long getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Long idTreatment) {
        this.idTreatment = idTreatment;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getDateInit() {
        return dateInit;
    }

    public void setDateInit(LocalDate dateInit) {
        this.dateInit = dateInit;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
}