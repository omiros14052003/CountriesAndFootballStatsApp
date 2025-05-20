package team.AM_5384_5388.countries_football_stats.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.AM_5384_5388.countries_football_stats.Datamodel.CountryIsoNameDTO;
import team.AM_5384_5388.countries_football_stats.Services.CountriesService;
import team.AM_5384_5388.countries_football_stats.Services.ScorersService;

@Controller
@RequestMapping("/scorers")
public class ScorersController {
	
	@Autowired
	ScorersService scorersService;
	
	@GetMapping("/all_scorers")
    public String getAllScorers(Model model) {
		System.err.println("in");
		System.out.println("Loading template: scorers/all_scorers");
		scorersService.getAllScorersService(model);
        return "/scorers/all_scorers";
    }
	
	@GetMapping("/select_one")
	public String selectScorer(@RequestParam String scorerInfo, Model model) {
		String[] parts = scorerInfo.split(",");
	    String scorerName = parts[0];
	    int teamId = Integer.parseInt(parts[1]);
	    System.err.println(teamId);
	    scorersService.showScorerInformationService(model, scorerName, teamId);
	    return "/scorers/scorer_information";
	}
}