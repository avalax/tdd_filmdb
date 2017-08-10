package de.avalax.filmdb.application.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static de.avalax.filmdb.application.film.AddFilmToRepositoryCommandBuilder.anAddFilmToRepositoryCommand;
import static de.avalax.filmdb.application.film.DeleteFilmToRepositoryCommandBuilder.aDeleteFilmToRepositoryCommand;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void shouldShowNothing() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}