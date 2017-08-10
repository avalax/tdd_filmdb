package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateFilmRepositoryIntegrationTest {

    @Autowired
    private HibernateFilmRepository filmRepository;

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
}