package de.avalax.filmdb.port.adapter.persistence.film;

import de.avalax.filmdb.domain.model.Film;
import org.springframework.data.repository.CrudRepository;

interface FilmDAO extends CrudRepository<Film, Long> {
}
