package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.FilmId;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ModifyFilmCommand {
    private Long id;
    @NotBlank(message = "Please provide a name")
    private String name;
    private String genre;
    @Min(value = 1, message = "Please provide a rating between 1 - 3")
    @Max(value = 3, message = "Please provide a rating between 1 - 3")
    private Integer rating;
    @Digits(integer = 4, fraction = 0, message = "Please provide a year with 4 digits long")
    private Integer year;

    public FilmId getId() {
        return FilmId.builder().id(id).build();
    }
}
