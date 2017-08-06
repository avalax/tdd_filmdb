package de.avalax.filmdb.application.film;

import org.junit.Test;

import static de.avalax.filmdb.application.film.AddFilmToRepositoryCommandBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AddFilmToRepositoryCommandTest {
    @Test
    public void testEquals() throws Exception {
        assertThat(anAddFilmToRepositoryCommand().build()).isNotEqualTo(null);
        assertThat(anAddFilmToRepositoryCommand().build()).isNotEqualTo("aFilm");
        assertThat(anAddFilmToRepositoryCommand().withName("aGoodFilm").build()).isNotEqualTo(anAddFilmToRepositoryCommand().withName("aBadFilm").build());
        assertThat(anAddFilmToRepositoryCommand().withName("theSameFilm").build()).isEqualTo(anAddFilmToRepositoryCommand().withName("theSameFilm").build());
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(anAddFilmToRepositoryCommand().withName("aGoodFilm").build().hashCode()).isNotEqualTo(anAddFilmToRepositoryCommand().withName("aBadFilm").build().hashCode());
        assertThat(anAddFilmToRepositoryCommand().withName("theSameFilm").build().hashCode()).isEqualTo(anAddFilmToRepositoryCommand().withName("theSameFilm").build().hashCode());
    }

}