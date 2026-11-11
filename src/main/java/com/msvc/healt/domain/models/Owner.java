package com.msvc.healt.domain.models;

import java.time.LocalDate;
import java.util.Set;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;

public class Owner {

    private Long id;
    private NormalizedText name;
    private NormalizedText lastName;
    private DocumentType documentType;
    private NormalizedNumber documentNumer;
    private Email email;
    private NormalizedNumber numberPhone;
    private boolean status;
    private Set<Pet> pets;
    private LocalDate registrationDate;

    public Owner() {
    }

    public Owner(Long id, NormalizedText name, NormalizedText lastName, DocumentType documentType,
            NormalizedNumber documentNumer, Email email, NormalizedNumber numberPhone, boolean status, Set<Pet> pets,
            LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumer = documentNumer;
        this.email = email;
        this.numberPhone = numberPhone;
        this.status = status;
        this.pets = pets;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idOwner) {
        this.id = idOwner;
    }

    public NormalizedText getName() {
        return name;
    }

    public void setName(NormalizedText name) {
        this.name = name;
    }

    public NormalizedText getLastName() {
        return lastName;
    }

    public void setLastName(NormalizedText lastName) {
        this.lastName = lastName;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public NormalizedNumber getDocumentNumer() {
        return documentNumer;
    }

    public void setDocumentNumer(NormalizedNumber documentNumer) {
        this.documentNumer = documentNumer;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public NormalizedNumber getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(NormalizedNumber numberPhone) {
        this.numberPhone = numberPhone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

}
