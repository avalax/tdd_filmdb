package de.avalax.filmdb;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmAssert;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class ApplicationAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void postNewFilmShouldBeAddedToDatabase() throws Exception {
        mockMvc.perform(post("/").param("name", "aFilmName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        FilmAssert.assertThat(filmRepository.load(new FilmId(1L))).hasName("aFilmName");
    }

    @Test
    public void deleteExistingFilmShouldBeRemovedFromDatabase() throws Exception {
        filmRepository.save(Film.builder().name("anyFilmName").build());

        mockMvc.perform(delete("/").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Assertions.assertThat(filmRepository.loadAll()).isEmpty();
    }
}