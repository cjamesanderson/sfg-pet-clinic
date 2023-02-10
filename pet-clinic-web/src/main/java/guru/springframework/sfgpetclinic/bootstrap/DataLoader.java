package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiologySpecialty = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgerySpecialty = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistrySpecialty = specialtyService.save(dentistry);

        Owner owner1 = Owner.builder()
                .firstName("Michael").lastName("Weston")
                .address("123 Brickerel").city("Miami")
                .telephone("1231231234").build();

        Pet mikesPet = Pet.builder()
                .petType(savedDogPetType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Wolfie").build();
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona").lastName("Glenanne")
                .address("123 Brickerel").city("Miami")
                .telephone("1231231234").build();

        Pet fionasCat = Pet.builder().name("Just Cat")
                .owner(owner2)
                .birthDate(LocalDate.now())
                .petType(savedCatPetType).build();
        owner2.getPets().add(fionasCat);
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiologySpecialty);
        vet1.getSpecialties().add(savedDentistrySpecialty);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgerySpecialty);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
