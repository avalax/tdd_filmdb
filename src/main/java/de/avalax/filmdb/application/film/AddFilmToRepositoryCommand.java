package de.avalax.filmdb.application.film;

import java.util.Objects;

public class AddFilmToRepositoryCommand {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddFilmToRepositoryCommand that = (AddFilmToRepositoryCommand) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}