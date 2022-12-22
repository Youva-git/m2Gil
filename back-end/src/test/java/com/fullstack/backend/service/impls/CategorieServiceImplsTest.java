package com.fullstack.backend.service.impls;

import com.fullstack.backend.dto.CategorieDto;
import com.fullstack.backend.service.CategorieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorieServiceImplsTest {

    private CategorieService vService;
    @Test
    void shouldCreateCategorieWithSuccess() {
        CategorieDto vExpectedCategorie =
                CategorieDto.builder()
                        .nom("test")
                        .codeCategorie("C001")
                        .description("Test categorie")
                        .idBoutique(1)
                        .build();
        CategorieDto createCategorie = vService.create(vExpectedCategorie);
        assertNotNull(createCategorie);
        assertNotNull(createCategorie.getId());
        assertEquals(vExpectedCategorie.getId(), createCategorie.getId());
        assertEquals(vExpectedCategorie.getNom(), createCategorie.getNom());
        assertEquals(vExpectedCategorie.getCodeCategorie(), createCategorie.getCodeCategorie());
        assertEquals(vExpectedCategorie.getDescription(), createCategorie.getDescription());
        assertEquals(vExpectedCategorie.getIdBoutique(), createCategorie.getIdBoutique());
    }

    @Test
    void read() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByCodeCategorie() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}