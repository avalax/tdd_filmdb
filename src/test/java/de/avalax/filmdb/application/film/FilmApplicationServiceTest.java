package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmIdBuilder;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static de.avalax.filmdb.application.film.AddFilmToRepositoryCommandBuilder.anAddFilmToRepositoryCommand;
import static de.avalax.filmdb.application.film.DeleteFilmToRepositoryCommandBuilder.aDeleteFilmToRepositoryCommand;
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
        AddFilmToRepositoryCommand addFilmToRepositoryCommand = anAddFilmToRepositoryCommand()
                .withName("aFilmName")
                .build();

        filmApplicationService.addFilmToRepository(addFilmToRepositoryCommand);

        verify(filmRepository).save(Film.builder().name("aFilmName").build());
    }

    @Test
    public void shouldDeleteFilmFromRepository() throws Exception {
        DeleteFilmToRepositoryCommand deleteFilmToRepositoryCommand = aDeleteFilmToRepositoryCommand()
                .withId("1")
                .build();

        filmApplicationService.deleteFilmFromRepository(deleteFilmToRepositoryCommand);

        verify(filmRepository).delete(FilmIdBuilder.aFilmId().withId(1L).build());
    }

    @Test
    public void shouldLoadFilmsFromRepository() throws Exception {
        doReturn(singletonList(Film.builder().name("aFilmName").build())).when(filmRepository).loadAll();

        List<Film> films = filmApplicationService.loadFilms();

        assertThat(films).extracting(Film::getName).containsExactly("aFilmName");
    }
}