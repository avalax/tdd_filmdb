package de.avalax.filmdb.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@ToString
public class FilmId implements Serializable {
    private Long id;

    public FilmId(Long id) {
        this.id = id;
    }
}
