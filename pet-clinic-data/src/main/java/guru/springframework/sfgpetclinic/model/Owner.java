package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner extends Person{

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Owner() {

    }

    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    public Owner(OwnerBuilder ownerBuilder) {
        if (ownerBuilder.id != null) this.setId(ownerBuilder.id);
        this.setFirstName(ownerBuilder.firstName);
        this.setLastName(ownerBuilder.lastName);
        this.address = ownerBuilder.address;
        this.city = ownerBuilder.city;
        this.telephone = ownerBuilder.telephone;
        this.pets = ownerBuilder.pets;
    }

    public static class OwnerBuilder implements Builder<Owner> {

        private Long id;
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String telephone;
        private Set<Pet> pets = new HashSet<>();

        public OwnerBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public OwnerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public OwnerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public OwnerBuilder address(String address) {
            this.address = address;
            return this;
        }
        public OwnerBuilder city(String city) {
            this.city = city;
            return this;
        }
        public OwnerBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }
        public OwnerBuilder pets(Set<Pet> pets) {
            this.pets = pets;
            return this;
        }

        @Override
        public Owner build() {
            return new Owner(this);
        }
    }

    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
