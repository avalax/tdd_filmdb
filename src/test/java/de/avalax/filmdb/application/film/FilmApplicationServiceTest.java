package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static de.avalax.filmdb.application.film.AddFilmCommandBuilder.anAddFilmToRepositoryCommand;
import static de.avalax.filmdb.application.film.DeleteFilmCommandBuilder.aDeleteFilmToRepositoryCommand;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilmApplicationServiceTest {

    @InjectMocks
    private FilmApplicationService filmApplicationService;

    @Mock
    private FilmRepository filmRepository;

    @Test
    public void shouldAddFilmToRepository() throws Exception {
        AddFilmCommand addFilmCommand = anAddFilmToRepositoryCommand()
                .withName("aFilmName")
                .build();

        filmApplicationService.addFilm(addFilmCommand);

        verify(filmRepository).save(Film.builder().name("aFilmName").build());
    }

    @Test
    public void shouldDeleteFilmFromRepository() throws Exception {
        DeleteFilmCommand deleteFilmCommand = aDeleteFilmToRepositoryCommand()
                .withId("1")
                .build();

        filmApplicationService.deleteFilmFromRepository(deleteFilmCommand);

        verify(filmRepository).delete(FilmId.builder().id(1L).build());
    }

    @Test
    public void shouldLoadFilmFromRepository() throws Exception {
        ShowFilmCommand showFilmCommand = ShowFilmCommandBuilder.aShowFilmCommand()
                .withId("1")
                .build();

        filmApplicationService.loadFilm(showFilmCommand);

        verify(filmRepository).load(FilmId.builder().id(1L).build());
    }

    @Test
    public void shouldLoadFilmsFromRepository() throws Exception {
        doReturn(singletonList(Film.builder().name("aFilmName").build())).when(filmRepository).loadAll();

        List<Film> films = filmApplicationService.loadAllFilms();

        assertThat(films).extracting(Film::getName).containsExactly("aFilmName");
    }
}