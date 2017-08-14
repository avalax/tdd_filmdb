package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmNotFoundException;
import de.avalax.filmdb.domain.model.ExceptionFactory;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateFilmRepository implements FilmRepository {

    @Autowired
    private ExceptionFactory exceptionFactory;

    @Autowired
    private FilmDAO filmDAO;

    @Override
    public FilmId save(Film film) {
        Film persistedFilm = filmDAO.save(film);
        return persistedFilm.getId();
    }

    @Override
    public Film load(FilmId id) throws FilmNotFoundException {
        Film film = filmDAO.findOne(id.getId());
        if (film == null) {
            throw exceptionFactory.createFilmNotFoundException();
        }
        return film;
    }

    @Override
    public List<Film> loadAll() {
        List<Film> films = new ArrayList<>();
        filmDAO.findAll().forEach(films::add);
        return films;
    }

    @Override
    public void delete(FilmId filmId) throws FilmNotFoundException {
        Film film = filmDAO.findOne(filmId.getId());
        if (film == null) {
            throw exceptionFactory.createFilmNotFoundException();
        }
        filmDAO.delete(film);
    }
}
