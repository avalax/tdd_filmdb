package de.avalax.filmdb.application.film;

public class AddFilmCommandBuilder {
    private String name;
    private String genre;
    private Integer year;
    private Integer rating;

    public static AddFilmCommandBuilder anAddFilmCommand() {
        return new AddFilmCommandBuilder();
    }

    public AddFilmCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AddFilmCommandBuilder withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public AddFilmCommandBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public AddFilmCommandBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }

    public AddFilmCommand build() {
        AddFilmCommand addFilmCommand = new AddFilmCommand();
        addFilmCommand.setName(name);
        addFilmCommand.setGenre(genre);
        addFilmCommand.setYear(year);
        addFilmCommand.setRating(rating);
        return addFilmCommand;
    }
}
