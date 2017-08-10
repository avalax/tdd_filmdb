package de.avalax.filmdb.domain.model;

import java.util.List;

public interface FilmRepository {
    FilmId save(Film film);

    Film load(FilmId id);

    List<Film> loadAll();

    void delete(FilmId filmId);
}
