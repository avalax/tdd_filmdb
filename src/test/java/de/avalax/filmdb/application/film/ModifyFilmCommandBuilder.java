package de.avalax.filmdb.application.film;

import lombok.Data;

@Data
public class ModifyFilmCommandBuilder {

    private long id;
    private String name;
    private String genre;
    private Integer year;
    private Integer rating;

    public static ModifyFilmCommandBuilder aModifyFilmCommand() {
        return new ModifyFilmCommandBuilder();
    }

    public ModifyFilmCommandBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ModifyFilmCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ModifyFilmCommandBuilder withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public ModifyFilmCommandBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public ModifyFilmCommandBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }

    public ModifyFilmCommand build() {
        ModifyFilmCommand modifyFilmCommand = new ModifyFilmCommand();
        modifyFilmCommand.setId(id);
        modifyFilmCommand.setName(name);
        modifyFilmCommand.setGenre(genre);
        modifyFilmCommand.setYear(year);
        modifyFilmCommand.setRating(rating);
        return modifyFilmCommand;
    }
}
