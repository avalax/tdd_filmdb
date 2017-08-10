package de.avalax.filmdb.application.film;

import lombok.Data;

@Data
public class DeleteFilmToRepositoryCommand {
    private String id;
}
