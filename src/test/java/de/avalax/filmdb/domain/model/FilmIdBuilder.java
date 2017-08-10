package de.avalax.filmdb.domain.model;

public class FilmIdBuilder {
    private long id;

    public static FilmIdBuilder aFilmId() {
        return new FilmIdBuilder();
    }

    public FilmIdBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public FilmId build() {
        return new FilmId(id);
    }
}
