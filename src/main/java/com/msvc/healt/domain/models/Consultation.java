package com.msvc.healt.domain.models;

import java.time.LocalDateTime;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;

public class Consultation {
    private Long idConsultation;
    private LocalDateTime registratioDate;
    private Long doctorId;
    private NormalizedText personCharge;
    private NormalizedNumber documentNumber;
    private NormalizedText petName;
    private Integer age;
    private Sex sex;
    private String ReasonInquiry;
    private String diagnosis;
    private String observations;
    private Pet pet;
    private Owner owner;

    public Consultation() {
    }

    public Consultation(Long idConsultation, LocalDateTime registratioDate, Long doctorId,
            NormalizedText personCharge, NormalizedNumber documentNumber, NormalizedText petName, Integer age,
            Sex sex, String reasonInquiry, String diagnosis, String observations, Pet pet, Owner owner) {
        this.idConsultation = idConsultation;
        this.registratioDate = registratioDate;
        this.doctorId = doctorId;
        this.personCharge = personCharge;
        this.documentNumber = documentNumber;
        this.petName = petName;
        this.age = age;
        this.sex = sex;
        ReasonInquiry = reasonInquiry;
        this.diagnosis = diagnosis;
        this.observations = observations;
        this.pet = pet;
        this.owner = owner;
    }

    public Long getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(Long idConsultation) {
        this.idConsultation = idConsultation;
    }

    public LocalDateTime getRegistratioDate() {
        return registratioDate;
    }

    public void setRegistratioDate(LocalDateTime registratioDate) {
        this.registratioDate = registratioDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public NormalizedText getPersonCharge() {
        return personCharge;
    }

    public void setPersonCharge(NormalizedText personCharge) {
        this.personCharge = personCharge;
    }

    public NormalizedNumber getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(NormalizedNumber documentNumber) {
        this.documentNumber = documentNumber;
    }

    public NormalizedText getPetName() {
        return petName;
    }

    public void setPetName(NormalizedText petName) {
        this.petName = petName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getReasonInquiry() {
        return ReasonInquiry;
    }

    public void setReasonInquiry(String reasonInquiry) {
        ReasonInquiry = reasonInquiry;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
