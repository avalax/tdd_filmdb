package de.avalax.filmdb.application.film;

public class DeleteFilmToRepositoryCommandBuilder {
    private String id;

    public static DeleteFilmToRepositoryCommandBuilder aDeleteFilmToRepositoryCommand() {
        return new DeleteFilmToRepositoryCommandBuilder();
    }

    public DeleteFilmToRepositoryCommandBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public DeleteFilmToRepositoryCommand build() {
        DeleteFilmToRepositoryCommand deleteFilmToRepositoryCommand = new DeleteFilmToRepositoryCommand();
        deleteFilmToRepositoryCommand.setId(id);
        return deleteFilmToRepositoryCommand;
    }
}
