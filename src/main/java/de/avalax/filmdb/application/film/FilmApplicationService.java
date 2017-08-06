package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.Film;
import de.avalax.filmdb.domain.model.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmApplicationService {

    @Autowired
    private FilmRepository filmRepository;

    public void addFilmToRepository(AddFilmToRepositoryCommand addFilmToRepositoryCommand) {
        filmRepository.save(new Film(addFilmToRepositoryCommand.getName()));
    }
}
