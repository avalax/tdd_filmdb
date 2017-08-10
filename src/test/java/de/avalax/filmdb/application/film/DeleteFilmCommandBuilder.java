package de.avalax.filmdb.application.film;

public class DeleteFilmCommandBuilder {
    private String id;

    public static DeleteFilmCommandBuilder aDeleteFilmToRepositoryCommand() {
        return new DeleteFilmCommandBuilder();
    }

    public DeleteFilmCommandBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public DeleteFilmCommand build() {
        DeleteFilmCommand deleteFilmCommand = new DeleteFilmCommand();
        deleteFilmCommand.setId(id);
        return deleteFilmCommand;
    }
}
