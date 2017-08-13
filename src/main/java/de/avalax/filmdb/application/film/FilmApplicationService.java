package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmNotFoundException;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmApplicationService {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> loadAllFilms() {
        return filmRepository.loadAll();
    }

    public Film loadFilm(ShowFilmCommand showFilmCommand) throws FilmNotFoundException {
        return filmRepository.load(showFilmCommand.getId());
    }

    public FilmId addFilm(AddFilmCommand addFilmCommand) {
        Film film = Film.builder()
                .name(addFilmCommand.getName())
                .genre(addFilmCommand.getGenre())
                .rating(addFilmCommand.getRating())
                .year(addFilmCommand.getYear())
                .build();
        return filmRepository.save(film);
    }

    public void modifyFilm(ModifyFilmCommand modifyFilmCommand) throws FilmNotFoundException {
        Film film = filmRepository.load(modifyFilmCommand.getId());
        film.setName(modifyFilmCommand.getName());
        film.setGenre(modifyFilmCommand.getGenre());
        film.setYear(modifyFilmCommand.getYear());
        film.setRating(modifyFilmCommand.getRating());
        filmRepository.save(film);
    }

    public void deleteFilmFromRepository(DeleteFilmCommand deleteFilmCommand) throws FilmNotFoundException {
        FilmId filmId = FilmId.builder().id(deleteFilmCommand.getId()).build();
        filmRepository.delete(filmId);
    }
}
