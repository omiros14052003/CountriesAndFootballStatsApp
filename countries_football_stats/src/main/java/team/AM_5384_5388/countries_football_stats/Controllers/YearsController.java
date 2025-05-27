package team.AM_5384_5388.countries_football_stats.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import team.AM_5384_5388.countries_football_stats.Services.YearsService;
import team.AM_5384_5388.countries_football_stats.Datamodel.*;

@Controller
@RequestMapping("/years")
public class YearsController {
    
    @Autowired
    private YearsService yearsService;
    
    @GetMapping("/all_years")
    public String getAllCountries(Model model) {
        List<Integer> years = yearsService.getAllYears();
        model.addAttribute("years", years);
        return "years/select_year";
    }
    
    @GetMapping("/select_one")
    public String selectYear(@RequestParam("year") int year, Model model) {
        List<ResultsDTO> matches = yearsService.getResultsByYear(year);
        int numberOfMatches = matches.size();
        int numberOfDraws = yearsService.getDrawCountByYear(year);
        int numberOfPenalties = yearsService.getPenaltyShootoutsCountByYear(year);
        int totalGoals = yearsService.getTotalGoalsByYear(year);
        int decisiveMatches = yearsService.getDecisiveMatchesByYear(year);

        List<CountryWinDrawLossDTO> stats = yearsService.getTopCountriesWinDrawLossByYear(year);
        List<String> statCountryNames = stats.stream().map(CountryWinDrawLossDTO::getCountryName).toList();
        List<Integer> wins = stats.stream().map(CountryWinDrawLossDTO::getWins).toList();
        List<Integer> draws = stats.stream().map(CountryWinDrawLossDTO::getDraws).toList();
        List<Integer> losses = stats.stream().map(CountryWinDrawLossDTO::getLosses).toList();

        model.addAttribute("statCountryNames", statCountryNames);
        model.addAttribute("wins", wins);
        model.addAttribute("draws", draws);
        model.addAttribute("losses", losses);
        model.addAttribute("decisiveMatches", decisiveMatches);
        model.addAttribute("totalGoals", totalGoals);
        model.addAttribute("numberOfPenalties", numberOfPenalties);
        model.addAttribute("numberOfDraws", numberOfDraws);
        model.addAttribute("year", year);
        model.addAttribute("numberOfMatches", numberOfMatches);

        List<CountryGoalsDTO> topCountriesByGoals = yearsService.getTopCountriesByGoals(year);
        List<String> goalCountryNames = topCountriesByGoals.stream()
            .map(CountryGoalsDTO::getCountryName)
            .toList();
        List<Integer> goalCounts = topCountriesByGoals.stream()
            .map(CountryGoalsDTO::getTotalGoals)
            .toList();

        model.addAttribute("goalCountryNames", goalCountryNames);
        model.addAttribute("goalCounts", goalCounts);

        return "years/year_profile";
    }
    
    @GetMapping("/results")
    public String selectYearResult(
            @RequestParam("year") int year,
            @RequestParam(value = "tournament", required = false) String tournament,
            @RequestParam(value = "continent", required = false) String continent,
            @RequestParam(value = "homeDevelopedOrDeveloping", required = false) String homeDevelopedOrDeveloping,
            @RequestParam(value = "awayDevelopedOrDeveloping", required = false) String awayDevelopedOrDeveloping,
            Model model) {

        List<SimpleMatchDTO> matches;
        if (tournament != null && !tournament.isEmpty()) {
            matches = yearsService.getSimpleResultsByYearAndTournament(year, tournament);
        } else {
            matches = yearsService.getSimpleResultsByYear(year);
        }
        if (continent != null && !continent.isEmpty()) {
            matches = matches.stream()
                .filter(m -> continent.equals(m.getHomeContinent()) || continent.equals(m.getAwayContinent()))
                .toList();
        }
        if (homeDevelopedOrDeveloping != null && !homeDevelopedOrDeveloping.isEmpty()) {
            matches = matches.stream()
                .filter(m -> homeDevelopedOrDeveloping.equals(m.getHomeDeveloped_Or_Developing()))
                .toList();
        }
        
        if (awayDevelopedOrDeveloping != null && !awayDevelopedOrDeveloping.isEmpty()) {
            matches = matches.stream()
                .filter(m -> awayDevelopedOrDeveloping.equals(m.getAwayDeveloped_Or_Developing()))
                .toList();
        }
        
        List<String> tournaments = yearsService.getTournamentsByYear(year);

        model.addAttribute("matches", matches);
        model.addAttribute("year", year);
        model.addAttribute("tournaments", tournaments);
        model.addAttribute("selectedTournament", tournament);
        model.addAttribute("selectedContinent", continent);
        model.addAttribute("selectedHomeDevelopedOrDeveloping", homeDevelopedOrDeveloping);
        model.addAttribute("selectedAwayDevelopedOrDeveloping", awayDevelopedOrDeveloping);

        return "years/results";
    }

}
