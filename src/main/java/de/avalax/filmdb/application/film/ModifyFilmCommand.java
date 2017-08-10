package de.avalax.filmdb.application.film;

import lombok.Data;

@Data
public class ModifyFilmCommand {
    private Long id;
    private String name;
}
