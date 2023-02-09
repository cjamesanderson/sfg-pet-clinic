package guru.springframework.sfgpetclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

    public PetType() {
    }

    public PetType(Long id, String name) {
        super(id, name);
    }

    public PetType(PetTypeBuilder builder) {
        if (builder.id != null) this.setId(builder.id);
        this.setName(builder.name);
    }

    public static class PetTypeBuilder implements Builder<PetType> {

        private Long id;
        private String name;

        public PetTypeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PetTypeBuilder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public PetType build() {
            return new PetType(this);
        }
    }

    public static PetTypeBuilder builder() {
        return new PetTypeBuilder();
    }

    public String toString() {
        return this.getName();
    }

}
