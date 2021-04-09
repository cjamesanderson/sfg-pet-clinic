package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {

    private PetType petType;
    private Owner owner;
    private LocalDate birthDare;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDare() {
        return birthDare;
    }

    public void setBirthDare(LocalDate birthDare) {
        this.birthDare = birthDare;
    }
}
