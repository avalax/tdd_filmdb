package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class FakeFilmRepository implements FilmRepository {

    private List<Film> films;

    public FakeFilmRepository() {
        films = new ArrayList<>();
    }

    @Override
    public void save(Film film) {
        films.add(film);
    }

    @Override
    public Collection<Film> loadAll() {
        return films;
    }
}
