package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmApplicationService {

    @Autowired
    private FilmRepository filmRepository;

    public FilmId addFilm(AddFilmCommand addFilmCommand) {
        Film film = Film.builder()
                .name(addFilmCommand.getName())
                .genre(addFilmCommand.getGenre())
                .rating(addFilmCommand.getRating())
                .year(addFilmCommand.getYear())
                .build();
        return filmRepository.save(film);
    }

    public void deleteFilmFromRepository(DeleteFilmCommand deleteFilmCommand) {
        FilmId filmId = FilmId.builder().id(deleteFilmCommand.getId()).build();
        filmRepository.delete(filmId);
    }

    public List<Film> loadAllFilms() {
        return filmRepository.loadAll();
    }

    public Film loadFilm(ShowFilmCommand showFilmCommand) {
        FilmId filmId = FilmId.builder().id(showFilmCommand.getId()).build();
        return filmRepository.load(filmId);
    }

    public void modifyFilm(ModifyFilmCommand modifyFilmCommand) {
        FilmId id = FilmId.builder().id(modifyFilmCommand.getId()).build();
        Film film = filmRepository.load(id);
        film.setName(modifyFilmCommand.getName());
        film.setGenre(modifyFilmCommand.getGenre());
        film.setYear(modifyFilmCommand.getYear());
        film.setRating(modifyFilmCommand.getRating());
        filmRepository.save(film);
    }
}
