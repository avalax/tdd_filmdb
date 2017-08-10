package de.avalax.filmdb.application.film;

public class DeleteFilmCommandBuilder {
    private Long id;

    public static DeleteFilmCommandBuilder aDeleteFilmToRepositoryCommand() {
        return new DeleteFilmCommandBuilder();
    }

    public DeleteFilmCommandBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DeleteFilmCommand build() {
        DeleteFilmCommand deleteFilmCommand = new DeleteFilmCommand();
        deleteFilmCommand.setId(id);
        return deleteFilmCommand;
    }
}
