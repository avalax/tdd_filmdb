package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static de.avalax.filmdb.domain.model.FilmBuilder.aFilm;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FakeFilmRepositoryTest {

    @InjectMocks
    private FakeFilmRepository fakeFilmRepository;

    @Test
    public void shouldSaveFilmToRepository() throws Exception {
        Film film = aFilm().withName("aFilmName").build();

        fakeFilmRepository.save(film);

        assertThat(fakeFilmRepository.loadAll()).contains(aFilm().withName("aFilmName").build());
    }
}