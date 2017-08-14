package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static de.avalax.filmdb.application.film.ShowFilmCommandBuilder.aShowFilmCommand;
import static java.lang.String.join;
import static java.util.Collections.nCopies;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmApplicationService filmApplicationService;

    @Test
    public void addFilmCommandWithInvalidDataShouldNotValidate() throws Exception {
        mockMvc.perform(post("/film")
                .param("year", "invalidYear")
                .param("rating", "4"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("film", "year", "rating", "name"));
    }

    @Test
    public void addFilmCommandWithLongInvalidDataShouldNotValidate() throws Exception {
        mockMvc.perform(post("/film")
                .param("name", join("", nCopies(255, "n")))
                .param("genre", join("", nCopies(255, "g"))))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("film", "name", "genre"));
    }

    @Test
    public void modifyFilmCommandWithInvalidDataShouldNotValidate() throws Exception {
        ShowFilmCommand showFilmCommand = aShowFilmCommand().withId(1L).build();
        doReturn(Film.builder().name("oldFilmName").build()).when(filmApplicationService)
                .loadFilm(showFilmCommand);

        mockMvc.perform(post("/film/1")
                .param("year", "invalidYear")
                .param("rating", "0"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("film", "year", "rating", "name"));
    }

    @Test
    public void modifyFilmCommandWithLongInvalidDataShouldNotValidate() throws Exception {
        mockMvc.perform(post("/film/1")
                .param("name", join("", nCopies(255, "n")))
                .param("genre", join("", nCopies(255, "g"))))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("film", "name", "genre"));
    }
}