package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmRepository;

public class FilmApplicationService {

    private FilmRepository filmRepository;

    public void addFilmToRepository(AddFilmToRepositoryCommand addFilmToRepositoryCommand) {
        filmRepository.save(new Film(addFilmToRepositoryCommand.getName()));
    }
}
