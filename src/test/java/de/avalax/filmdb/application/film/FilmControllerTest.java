package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
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

import static de.avalax.filmdb.application.film.AddFilmToRepositoryCommandBuilder.anAddFilmToRepositoryCommand;
import static de.avalax.filmdb.application.film.DeleteFilmToRepositoryCommandBuilder.aDeleteFilmToRepositoryCommand;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
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

        verify(filmApplicationService).addFilmToRepository(anAddFilmToRepositoryCommand().withName("aFilmName").build());
    }

    @Test
    public void deleteFilmShouldDelegateCommandToApplicationService() throws Exception {
        mvc.perform(delete("/").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(filmApplicationService).deleteFilmFromRepository(aDeleteFilmToRepositoryCommand().withId("1").build());
    }

    @Test
    public void allFilmsFromRepositoryShouldBeListed() throws Exception {
        Film expectedFilm = Film.builder().name("aFilmToShow").build();
        doReturn(singletonList(expectedFilm)).when(filmApplicationService).loadFilms();

        ModelAndView modelAndView = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();

        @SuppressWarnings("unchecked")
        List<Film> films = (List<Film>) modelAndView.getModelMap().get("films");
        Assertions.assertThat(films).extracting(Film::getName).contains("aFilmToShow");
    }
}