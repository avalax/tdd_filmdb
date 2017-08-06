package de.avalax.filmdb.domain.model;

import org.junit.Test;

import static de.avalax.filmdb.domain.model.FilmBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FilmTest {

    @Test
    public void testEquals() throws Exception {
        assertThat(aFilm().build()).isNotEqualTo(null);
        assertThat(aFilm().build()).isNotEqualTo("aFilm");
        assertThat(aFilm().withName("aGoodFilm").build()).isNotEqualTo(aFilm().withName("aBadFilm").build());
        assertThat(aFilm().withName("theSameFilm").build()).isEqualTo(aFilm().withName("theSameFilm").build());
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(aFilm().withName("aGoodFilm").build().hashCode()).isNotEqualTo(aFilm().withName("aBadFilm").build().hashCode());
        assertThat(aFilm().withName("theSameFilm").build().hashCode()).isEqualTo(aFilm().withName("theSameFilm").build().hashCode());
    }
}