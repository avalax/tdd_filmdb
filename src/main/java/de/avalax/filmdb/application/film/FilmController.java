package de.avalax.filmdb.application.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/")
public class FilmController {

    @Autowired
    private FilmApplicationService filmApplicationService;

    @RequestMapping(method = GET)
    public ModelAndView listFilms(ModelMap model) {
        model.addAttribute("films", filmApplicationService.loadAllFilms());
        return new ModelAndView("index");
    }

    @RequestMapping(method = POST)
    public RedirectView addFilm(AddFilmCommand addFilmCommand) {
        filmApplicationService.addFilm(addFilmCommand);
        return new RedirectView("/");
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ModelAndView showFilm(ShowFilmCommand showFilmCommand, ModelMap model) {
        model.addAttribute("film", filmApplicationService.loadFilm(showFilmCommand));
        return new ModelAndView("film");
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public RedirectView deleteFilm(DeleteFilmCommand deleteFilmCommand) {
        filmApplicationService.deleteFilmFromRepository(deleteFilmCommand);
        return new RedirectView("/");
    }
}
