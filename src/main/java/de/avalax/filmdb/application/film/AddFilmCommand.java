package de.avalax.filmdb.application.film;

import lombok.Data;

@Data
public class AddFilmCommand {
    private String name;
    private String genre;
    private Integer rating;
    private Integer year;
}
