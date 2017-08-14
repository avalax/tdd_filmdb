package de.avalax.filmdb.application.film;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddFilmCommand {
    @NotNull(message = "Please provide a name")
    @Size(min = 1, max = 254, message = "Please provide a name with max 254 chars")
    private String name;
    @Size(max = 254, message = "Please provide a genre with max 254 chars")
    private String genre;
    @Min(value = 1, message = "Please provide a rating between 1 - 3")
    @Max(value = 3, message = "Please provide a rating between 1 - 3")
    private Integer rating;
    @Digits(integer = 4, fraction = 0, message = "Please provide a year with 4 digits long")
    private Integer year;
}
