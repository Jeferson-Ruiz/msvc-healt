package com.msvc.healt.domain.models;

import java.time.LocalDate;
import com.msvc.healt.domain.NormalizedText;

public class Pet {

    private Long idPet;
    private NormalizedText name;
    private NormalizedText species;
    private NormalizedText breed;
    private Sex sex;
    private LocalDate dateBorn;
    private Owner owner;
    private LocalDate registrationDate;

    public Pet() {
    }

    public Pet(Long idPet, NormalizedText name, NormalizedText species, NormalizedText breed, Sex sex,
            LocalDate dateBorn, Owner owner, LocalDate registrationDate) {
        this.idPet = idPet;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.dateBorn = dateBorn;
        this.owner = owner;
        this.registrationDate = registrationDate;
    }

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public NormalizedText getName() {
        return name;
    }

    public void setName(NormalizedText name) {
        this.name = name;
    }

    public NormalizedText getSpecies() {
        return species;
    }

    public void setSpecies(NormalizedText species) {
        this.species = species;
    }

    public NormalizedText getBreed() {
        return breed;
    }

    public void setBreed(NormalizedText breed) {
        this.breed = breed;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(LocalDate dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}