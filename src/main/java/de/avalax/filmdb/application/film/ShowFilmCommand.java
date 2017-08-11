package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.FilmId;
import lombok.Data;

@Data
public class ShowFilmCommand {
    private Long id;

    public FilmId getId() {
        return FilmId.builder().id(id).build();
    }
}
