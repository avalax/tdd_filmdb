package de.avalax.filmdb.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmDbController {

    @RequestMapping("/")
    public String index() {
        return "nothing";
    }

}
