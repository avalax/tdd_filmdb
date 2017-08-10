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
        model.addAttribute("films", filmApplicationService.loadFilms());
        return new ModelAndView("index");
    }

    @RequestMapping(method = POST)
    public RedirectView addFilmToRepository(AddFilmToRepositoryCommand addFilmToRepositoryCommand) {
        filmApplicationService.addFilmToRepository(addFilmToRepositoryCommand);
        return new RedirectView("/");
    }

    @RequestMapping(method = DELETE)
    public RedirectView deleteFilmFromRepository(DeleteFilmToRepositoryCommand deleteFilmToRepositoryCommand) {
        filmApplicationService.deleteFilmFromRepository(deleteFilmToRepositoryCommand);
        return new RedirectView("/");
    }
}
