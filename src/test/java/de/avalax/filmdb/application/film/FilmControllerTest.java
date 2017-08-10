package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmAssert;
import de.avalax.filmdb.domain.model.FilmId;
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

import static de.avalax.filmdb.application.film.AddFilmCommandBuilder.anAddFilmCommand;
import static de.avalax.filmdb.application.film.DeleteFilmCommandBuilder.aDeleteFilmToRepositoryCommand;
import static de.avalax.filmdb.application.film.ModifyFilmCommandBuilder.aModifyFilmCommand;
import static de.avalax.filmdb.application.film.ShowFilmCommandBuilder.aShowFilmCommand;
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
    private MockMvc mockMvc;

    @MockBean
    private FilmApplicationService filmApplicationService;

    @Test
    public void showFilm() throws Exception {
        Film expectedFilm = Film.builder().name("aFilmToShow").build();
        doReturn(expectedFilm).when(filmApplicationService).loadFilm(aShowFilmCommand().withId(1L).build());

        ModelAndView modelAndView = mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();

        Film film = (Film) modelAndView.getModelMap().get("film");
        FilmAssert.assertThat(film).hasName("aFilmToShow");
    }

    @Test
    public void addFilm() throws Exception {
        FilmId filmId = FilmId.builder().id(1L).build();
        doReturn(filmId).when(filmApplicationService).addFilm(anAddFilmCommand().withName("aFilmName").build());

        mockMvc.perform(post("/").param("name", "aFilmName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/film/1"));
    }

    @Test
    public void modifyFilm() throws Exception {
        mockMvc.perform(post("/1").param("name", "newFilmName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/film/1"));

        verify(filmApplicationService).modifyFilm(aModifyFilmCommand().withId(1L).withName("newFilmName").build());
    }

    @Test
    public void deleteFilm() throws Exception {
        mockMvc.perform(delete("/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(filmApplicationService).deleteFilmFromRepository(aDeleteFilmToRepositoryCommand().withId(1L).build());
    }

    @Test
    public void showAllFilms() throws Exception {
        Film expectedFilm = Film.builder().name("aFilmToShow").build();
        doReturn(singletonList(expectedFilm)).when(filmApplicationService).loadAllFilms();

        ModelAndView modelAndView = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();

        @SuppressWarnings("unchecked")
        List<Film> films = (List<Film>) modelAndView.getModelMap().get("films");
        Assertions.assertThat(films).extracting(Film::getName).contains("aFilmToShow");
    }
}