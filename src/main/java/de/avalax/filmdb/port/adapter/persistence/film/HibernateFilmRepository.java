package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateFilmRepository implements FilmRepository {

    @Autowired
    private FilmDAO filmDAO;

    @Override
    public FilmId save(Film film) {
        Film persistedFilm = filmDAO.save(film);
        return persistedFilm.getId();
    }

    @Override
    public Film load(FilmId id) {
        return filmDAO.findOne(id.getId());
    }

    @Override
    public List<Film> loadAll() {
        List<Film> films = new ArrayList<>();
        filmDAO.findAll().forEach(films::add);
        return films;
    }

    @Override
    public void delete(FilmId filmId) {
        filmDAO.delete(filmId.getId());
    }
}
