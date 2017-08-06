package de.avalax.filmdb.domain.model;

public interface FilmRepository {
    FilmId save(Film film);

    Film load(FilmId id);
}
