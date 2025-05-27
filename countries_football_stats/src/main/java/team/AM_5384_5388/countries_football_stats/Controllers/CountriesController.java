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

@Controller
@RequestMapping("/countries")
public class CountriesController {
	
	@Autowired
	CountriesService countriesService;
	
	@GetMapping("/all_countries")
    public String getAllCountries(Model model) {
		model.addAttribute("countries", countriesService.getAllCountriesService());
        return "countries/all_countries";
    }
	
	@GetMapping("/select_one")
	public String processField(@RequestParam int isoCode, Model model) {
	    countriesService.showCountryInformationService(model, isoCode);
	    return "countries/country_information";
	}
}