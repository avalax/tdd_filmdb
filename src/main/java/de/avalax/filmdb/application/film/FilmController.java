package de.avalax.filmdb.application.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/")
public class FilmController {

    @Autowired
    private FilmApplicationService filmApplicationService;

    @RequestMapping(method = GET)
    public String nothing() {
        return "nothing";
    }

    @RequestMapping(method = POST)
    public RedirectView addFilmToRepository(AddFilmToRepositoryCommand addFilmToRepositoryCommand) {
        filmApplicationService.addFilmToRepository(addFilmToRepositoryCommand);
        return new RedirectView("/");
    }

}