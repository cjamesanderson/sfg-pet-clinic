package guru.springframework.sfgpetclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Pet() {
    }

    public Pet(PetBuilder petBuilder) {
        if (petBuilder.id != null) this.setId(petBuilder.id);
        this.setName(petBuilder.name);
        this.setBirthDate(petBuilder.birthDate);
        this.setPetType(petBuilder.petType);
        this.setOwner(petBuilder.owner);

        if (visits == null || visits.size() > 0) this.setVisits(petBuilder.visits);
    }

    public static class PetBuilder implements Builder<Pet> {

        private Long id;
        private String name;
        private LocalDate birthDate;
        private PetType petType;
        private Owner owner;
        private Set<Visit> visits = new HashSet<>();

        public PetBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }
        public PetBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }
        public PetBuilder petType(PetType petType) {
            this.petType = petType;
            return this;
        }
        public PetBuilder owner(Owner owner) {
            this.owner = owner;
            return this;
        }
        public PetBuilder visits(Set<Visit> visits) {
            this.visits = visits;
            return this;
        }

        @Override
        public Pet build() {
            return new Pet(this);
        }
    }

    public static PetBuilder builder() {
        return new PetBuilder();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
