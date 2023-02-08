package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "index","index.html","index.htm"})
    public String index() {

        return "index";
    }

    @GetMapping("/oups")
    public String oupsHandler() {
        return "notimplemented";
    }
}
