package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.ExceptionFactory;
import de.avalax.filmdb.domain.model.FilmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class ResponseStatusExceptionFactory implements ExceptionFactory {
    @Override
    public FilmNotFoundException createFilmNotFoundException() {
        return new ResponseStatusFilmNotFoundException();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResponseStatusFilmNotFoundException extends FilmNotFoundException {

    }
}
