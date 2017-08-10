package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static de.avalax.filmdb.application.film.AddFilmCommandBuilder.anAddFilmToRepositoryCommand;
import static de.avalax.filmdb.application.film.DeleteFilmCommandBuilder.aDeleteFilmToRepositoryCommand;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FilmApplicationService filmApplicationService;

    @Test
    public void addNewFilmShouldDelegateCommandToApplicationService() throws Exception {
        mvc.perform(post("/").param("name", "aFilmName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(filmApplicationService).addFilm(anAddFilmToRepositoryCommand().withName("aFilmName").build());
    }

    @Test
    public void deleteFilmFromRepository() throws Exception {
        mvc.perform(delete("/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(filmApplicationService).deleteFilmFromRepository(aDeleteFilmToRepositoryCommand().withId(1L).build());
    }

    @Test
    public void showFilmFromRepository() throws Exception {
        Film expectedFilm = Film.builder().name("aFilmToShow").build();
        doReturn(expectedFilm).when(filmApplicationService).loadFilm(ShowFilmCommandBuilder.aShowFilmCommand().withId(1L).build());

        ModelAndView modelAndView = mvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();

        Film film = (Film) modelAndView.getModelMap().get("film");
        FilmAssert.assertThat(film).hasName("aFilmToShow");
    }

    @Test
    public void allFilmsFromRepositoryShouldBeListed() throws Exception {
        Film expectedFilm = Film.builder().name("aFilmToShow").build();
        doReturn(singletonList(expectedFilm)).when(filmApplicationService).loadAllFilms();

        ModelAndView modelAndView = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();

        @SuppressWarnings("unchecked")
        List<Film> films = (List<Film>) modelAndView.getModelMap().get("films");
        Assertions.assertThat(films).extracting(Film::getName).contains("aFilmToShow");
    }
}