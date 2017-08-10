package de.avalax.filmdb.application.film;

public class AddFilmCommandBuilder {
    private String name;

    public static AddFilmCommandBuilder anAddFilmToRepositoryCommand() {
        return new AddFilmCommandBuilder();
    }

    public AddFilmCommand build() {
        AddFilmCommand addFilmCommand = new AddFilmCommand();
        addFilmCommand.setName(name);
        return addFilmCommand;
    }

    public AddFilmCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
