package de.avalax.filmdb.application.film;

import de.avalax.filmdb.domain.model.FilmId;
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

    @RequestMapping(path = "/film", method = POST)
    public RedirectView addFilm(AddFilmCommand addFilmCommand) {
        FilmId filmId = filmApplicationService.addFilm(addFilmCommand);
        return new RedirectView("/film/" + filmId.getId());
    }

    @RequestMapping(path = "/film/{id}", method = GET)
    public ModelAndView showFilm(ShowFilmCommand showFilmCommand, ModelMap model) {
        model.addAttribute("film", filmApplicationService.loadFilm(showFilmCommand));
        return new ModelAndView("film");
    }

    @RequestMapping(path = "/film/{id}", method = POST)
    public RedirectView modifyFilm(ModifyFilmCommand modifyFilmCommand) {
        filmApplicationService.modifyFilm(modifyFilmCommand);
        return new RedirectView("/film/" + modifyFilmCommand.getId());
    }

    @RequestMapping(path = "film/{id}", method = DELETE)
    public void deleteFilm(DeleteFilmCommand deleteFilmCommand) {
        filmApplicationService.deleteFilmFromRepository(deleteFilmCommand);
    }
}
