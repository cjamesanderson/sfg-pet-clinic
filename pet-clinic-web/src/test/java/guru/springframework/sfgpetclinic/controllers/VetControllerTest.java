package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetController controller;

    private Set<Vet> vetSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vetSet = new HashSet<>();
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vetSet.add(vet1);
        Vet vet2 = new Vet();
        vet1.setId(2L);
        vetSet.add(vet2);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listVets() throws Exception {
        when(vetService.findAll()).thenReturn(vetSet);

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attributeExists("vets"));

        verify(vetService, times(1)).findAll();
    }

    @Test
    void getVetsJson() throws Exception {
        when(vetService.findAll()).thenReturn(vetSet);

        mockMvc.perform(get("/api/vets"))
                .andExpect(status().isOk()); //todo: test for expected JSON output

        verify(vetService, times(1)).findAll();
    }
}