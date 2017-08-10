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

    public void addFilmToRepository(AddFilmToRepositoryCommand addFilmToRepositoryCommand) {
        Film film = Film.builder()
                .name(addFilmToRepositoryCommand.getName())
                .build();
        filmRepository.save(film);
    }

    public void deleteFilmFromRepository(DeleteFilmToRepositoryCommand deleteFilmToRepositoryCommand) {
        String id = deleteFilmToRepositoryCommand.getId();
        filmRepository.delete(new FilmId(Long.valueOf(id)));
    }

    public List<Film> loadFilms() {
        return filmRepository.loadAll();
    }
}
