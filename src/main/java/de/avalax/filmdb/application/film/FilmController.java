package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.FilmId;
import de.avalax.filmdb.domain.model.FilmNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/")
public class FilmController {
    private static final String MODEL_ATTRIBUTE_FILM = "film";

    @Autowired
    private FilmApplicationService filmApplicationService;

    @RequestMapping(method = GET)
    public ModelAndView showAllFilms(ModelMap model) {
        model.addAttribute("films", filmApplicationService.loadAllFilms());
        return new ModelAndView("index");
    }

    @RequestMapping(path = "/film/{id}", method = GET)
    public ModelAndView showFilm(ShowFilmCommand showFilmCommand, ModelMap model) throws FilmNotFoundException {
        model.addAttribute(MODEL_ATTRIBUTE_FILM, filmApplicationService.loadFilm(showFilmCommand));
        return new ModelAndView("film");
    }

    @RequestMapping(path = "/film", method = POST)
    public ModelAndView addFilm(
            @ModelAttribute(MODEL_ATTRIBUTE_FILM)
            @Valid AddFilmCommand addFilmCommand,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            return showAllFilms(model);
        }
        FilmId filmId = filmApplicationService.addFilm(addFilmCommand);
        return new ModelAndView("redirect:/film/" + filmId.getId());
    }

    @RequestMapping(path = "/film/{id}", method = POST)
    public ModelAndView modifyFilm(
            @ModelAttribute(MODEL_ATTRIBUTE_FILM)
            @Valid ModifyFilmCommand modifyFilmCommand,
            BindingResult result,
            ModelMap model) throws FilmNotFoundException {
        if (result.hasErrors()) {
            return new ModelAndView("film");
        }
        filmApplicationService.modifyFilm(modifyFilmCommand);
        return new ModelAndView("redirect:/film/" + modifyFilmCommand.getId().getId());
    }

    @RequestMapping(path = "film/{id}", method = DELETE)
    @ResponseBody
    public String deleteFilm(DeleteFilmCommand deleteFilmCommand) throws FilmNotFoundException {
        filmApplicationService.deleteFilmFromRepository(deleteFilmCommand);
        return "";
    }
}
