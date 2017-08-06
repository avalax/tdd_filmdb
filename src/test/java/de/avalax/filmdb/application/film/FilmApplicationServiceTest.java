package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.FilmBuilder;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static de.avalax.filmdb.application.film.AddFilmToRepositoryCommandBuilder.anAddFilmToRepositoryCommand;
import static de.avalax.filmdb.domain.model.FilmBuilder.*;
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

        verify(filmRepository).save(aFilm().withName("aFilmName").build());
    }
}