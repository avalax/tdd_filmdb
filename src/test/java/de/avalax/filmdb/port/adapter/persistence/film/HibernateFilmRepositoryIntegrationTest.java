package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static de.avalax.filmdb.domain.model.FilmBuilder.aFilm;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateFilmRepositoryIntegrationTest {

    @Autowired
    private HibernateFilmRepository hibernateFilmRepository;

    @Test
    public void shouldSaveFilmToRepository() throws Exception {
        Film film = aFilm().withName("aFilmName").build();

        FilmId filmId = hibernateFilmRepository.save(film);

        assertThat(hibernateFilmRepository.load(filmId)).isEqualTo(aFilm().withName("aFilmName").build());
    }
}