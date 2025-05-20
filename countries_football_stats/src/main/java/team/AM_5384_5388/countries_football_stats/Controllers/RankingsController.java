package team.AM_5384_5388.countries_football_stats.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.AM_5384_5388.countries_football_stats.Services.*;
import team.AM_5384_5388.countries_football_stats.Datamodel.*;
import java.util.List;

@Controller
@RequestMapping("/rankings")
public class RankingsController {

	@Autowired
    private RankingsService rankingsService;

    @GetMapping("/stats")
    public String showRankings(@RequestParam(defaultValue = "points") String sort, Model model) {
        model.addAttribute("rankings", rankingsService.getCountryRankings(sort));
        model.addAttribute("sort", sort);
        return "rankings/rankings_stats";
    }
    
    @GetMapping("/charts")
    public String showRankingCharts(Model model) {
        List<CountryRankingDTO> topPoints = rankingsService.getTop10ByPoints();
        model.addAttribute("topPoints", topPoints);
        List<CountryRankingDTO> topWins = rankingsService.getTop10ByWins();
        model.addAttribute("topWins", topWins);
        List<CountryRankingDTO> winsPerYearData = rankingsService.getTop10WinsPerYearByCountry();
        model.addAttribute("winsPerYearData", winsPerYearData);
        
        List<CountryRankingDTO> pointsPerYearData = rankingsService.getTop10PointsPerYearByCountry();
        model.addAttribute("pointsPerYearData", pointsPerYearData);
        System.out.println("Top 10 Wins:");
        for (CountryRankingDTO country : pointsPerYearData) {
            System.out.println(country.getCountryName() + " - Wins: " + country.getPointsPerYear());
        }
        
        return "rankings/ranking-charts";
    }
    
    @GetMapping("/scatterplot")
    public String showScatterplot(Model model) {
        List<CountryPoPointsDTO> countryPoPoints = rankingsService.getCountryPoPoints();
        System.out.println("Data from service: " + countryPoPoints);
        model.addAttribute("countryPoPoints", countryPoPoints);
        List<String> continents = rankingsService.getContinents();
        model.addAttribute("continents", continents);
        return "rankings/scatterplot";
    }
}

