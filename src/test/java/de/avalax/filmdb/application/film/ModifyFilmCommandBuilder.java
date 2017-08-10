package de.avalax.filmdb.application.film;

import lombok.Data;

@Data
public class ModifyFilmCommandBuilder {

    private long id;
    private String name;

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

    public ModifyFilmCommand build() {
        ModifyFilmCommand modifyFilmCommand = new ModifyFilmCommand();
        modifyFilmCommand.setId(id);
        modifyFilmCommand.setName(name);
        return modifyFilmCommand;
    }
}
