package de.avalax.filmdb.application.film;

public class AddFilmToRepositoryCommandBuilder {
    private String name;

    public static AddFilmToRepositoryCommandBuilder anAddFilmToRepositoryCommand() {
        return new AddFilmToRepositoryCommandBuilder();
    }

    public AddFilmToRepositoryCommand build() {
        AddFilmToRepositoryCommand addFilmToRepositoryCommand = new AddFilmToRepositoryCommand();
        addFilmToRepositoryCommand.setName(name);
        return addFilmToRepositoryCommand;
    }

    public AddFilmToRepositoryCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
