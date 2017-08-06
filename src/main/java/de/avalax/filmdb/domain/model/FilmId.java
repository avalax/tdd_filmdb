package de.avalax.filmdb.domain.model;

import java.io.Serializable;

public class FilmId implements Serializable {
    private Long id;

    public FilmId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
