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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void newFilmShouldBeAddedToRepository() throws Exception {
        mockMvc.perform(post("/")
                .param("name", "aFilmName")
                .param("genre", "aFilmGenre")
                .param("year", "2017")
                .param("rating", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/film/1"));

        FilmAssert.assertThat(filmRepository.load(FilmId.builder().id(1L).build()))
                .hasName("aFilmName")
                .hasGenre("aFilmGenre")
                .hasYear(2017)
                .hasRating(3);
    }

    @Test
    public void showExistingFilmFromRepository() throws Exception {
        filmRepository.save(Film.builder()
                .name("aFilmName")
                .genre("aFilmGenre")
                .year(2017)
                .rating(2)
                .build());

        ModelAndView modelAndView = mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("film"))
                .andReturn().getModelAndView();

        Film film = (Film) modelAndView.getModelMap().get("film");
        FilmAssert.assertThat(film)
                .hasName("aFilmName")
                .hasGenre("aFilmGenre")
                .hasYear(2017)
                .hasRating(2);
    }

    @Test
    public void modifyExistingFilmInRepository() throws Exception {
        filmRepository.save(Film.builder().name("oldFilmName").build());

        mockMvc.perform(post("/1")
                .param("name", "newFilmName")
                .param("genre", "newFilmGenre")
                .param("year", "1999")
                .param("rating", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/film/1"));

        FilmAssert.assertThat(filmRepository.load(FilmId.builder().id(1L).build()))
                .hasName("newFilmName")
                .hasGenre("newFilmGenre")
                .hasYear(1999)
                .hasRating(1);
    }

    @Test
    public void deleteExistingFilmFromRepository() throws Exception {
        filmRepository.save(Film.builder().name("anyFilmName").build());

        mockMvc.perform(delete("/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Assertions.assertThat(filmRepository.loadAll()).isEmpty();
    }

    @Test
    public void showAllFilmsFromRepository() throws Exception {
        filmRepository.save(Film.builder().name("Film A").build());
        filmRepository.save(Film.builder().name("Film B").build());

        ModelAndView modelAndView = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andReturn().getModelAndView();

        @SuppressWarnings("unchecked")
        List<Film> films = (List<Film>) modelAndView.getModelMap().get("films");
        Assertions.assertThat(films).extracting(Film::getName).contains("Film A", "Film B");
    }
}