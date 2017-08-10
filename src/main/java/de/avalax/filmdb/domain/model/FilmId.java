package de.avalax.filmdb.domain.model;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class FilmId implements Serializable {
    private Long id;
}
