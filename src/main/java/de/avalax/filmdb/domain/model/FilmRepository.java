package de.avalax.filmdb.domain.model;

import java.util.Collection;

public interface FilmRepository {
    void save(Film film);

    Collection<Film> loadAll();
}
