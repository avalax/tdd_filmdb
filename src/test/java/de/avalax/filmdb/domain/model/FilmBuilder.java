package de.avalax.filmdb.domain.model;

public class FilmBuilder {
    private String name;

    public static FilmBuilder aFilm() {
        return new FilmBuilder();
    }

    public FilmBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Film build() {
        return new Film(name);
    }
}
