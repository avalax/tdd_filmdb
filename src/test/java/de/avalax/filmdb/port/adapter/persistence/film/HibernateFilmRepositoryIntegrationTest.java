package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class HibernateFilmRepositoryIntegrationTest {

    @Autowired
    private HibernateFilmRepository filmRepository;

    @Test
    public void shouldLoadAllFilms() throws Exception {
        Film film = Film.builder().name("aFilmName").build();
        FilmId filmId = filmRepository.save(film);

        List<Film> films = filmRepository.loadAll();

        assertThat(films).extracting(Film::getId).containsExactly(filmId);
    }

    @Test
    public void shouldSaveFilmToRepository() throws Exception {
        Film film = Film.builder().name("aFilmName").build();

        FilmId filmId = filmRepository.save(film);

        assertThat(filmRepository.load(filmId)).isEqualTo(Film.builder().name("aFilmName").id(filmId.getId()).build());
    }

    @Test
    public void shouldDeleteFilmFromRepository() throws Exception {
        Film film = Film.builder().name("aFilmName").build();

        FilmId filmId = filmRepository.save(film);
        filmRepository.delete(filmId);

        assertThat(filmRepository.loadAll()).isEmpty();
    }

    @Test(expected = FilmNotFoundException.class)
    public void loadUnknownFilmShouldThrowException() throws Exception {
        filmRepository.load(FilmId.builder().id(-1L).build());
    }

    @Test(expected = FilmNotFoundException.class)
    public void deleteUnknownFilmShouldThrowException() throws Exception {
        filmRepository.delete(FilmId.builder().id(-1L).build());
    }
}