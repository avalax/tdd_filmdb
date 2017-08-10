package de.avalax.filmdb.application.film;

public class ShowFilmCommandBuilder {
    private String id;

    public static ShowFilmCommandBuilder aShowFilmCommand() {
        return new ShowFilmCommandBuilder();
    }

    public ShowFilmCommandBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ShowFilmCommand build() {
        ShowFilmCommand showFilmCommand = new ShowFilmCommand();
        showFilmCommand.setId(id);
        return showFilmCommand;
    }
}
