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
        return filmRepository.load(showFilmCommand.getId());
    }

    public void modifyFilm(ModifyFilmCommand modifyFilmCommand) {
        Film film = filmRepository.load(modifyFilmCommand.getId());
        film.setName(modifyFilmCommand.getName());
        film.setGenre(modifyFilmCommand.getGenre());
        film.setYear(modifyFilmCommand.getYear());
        film.setRating(modifyFilmCommand.getRating());
        filmRepository.save(film);
    }
}
