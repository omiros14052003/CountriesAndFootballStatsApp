package team.AM_5384_5388.countries_football_stats.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}