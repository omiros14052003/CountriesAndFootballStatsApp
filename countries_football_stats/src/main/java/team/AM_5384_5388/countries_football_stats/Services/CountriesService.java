package team.AM_5384_5388.countries_football_stats.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import team.AM_5384_5388.countries_football_stats.Datamodel.Country;
import team.AM_5384_5388.countries_football_stats.Datamodel.CountryDTO;
import team.AM_5384_5388.countries_football_stats.Datamodel.CountryIsoNameDTO;
import team.AM_5384_5388.countries_football_stats.Datamodel.CountryStatsAtYearDTO;
import team.AM_5384_5388.countries_football_stats.Datamodel.ResultsDTO;
import team.AM_5384_5388.countries_football_stats.Datamodel.YearsDTO;



@Service
public class CountriesService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CountryIsoNameDTO> getAllCountriesService(){
		return jdbcTemplate.query(
		        "SELECT Display_Name, ISO_Code FROM countries",
		        (rs, rowNum) -> new CountryIsoNameDTO(
		            rs.getString("Display_Name"),
		            rs.getInt("ISO_Code")
		        )
		    );
	}
	
	public void showCountryInformationService(Model model, int isoCode){
		CountryDTO country = jdbcTemplate.queryForObject(
				"SELECT * FROM countries WHERE ISO_Code = ?",
			    new Object[]{isoCode},
			    (rs, rowNum) -> new CountryDTO(
			            rs.getString("ISO"),
			            rs.getString("ISO3"),
			            rs.getInt("ISO_Code"),
			            rs.getString("FIPS"),
			            rs.getString("Display_Name"),
			            rs.getString("Official_Name"),
			            rs.getString("Capital"),
			            rs.getString("Continent"),
			            rs.getString("CurrencyCode"),
			            rs.getString("CurrencyName"),
			            rs.getString("Phone"),
			            rs.getInt("Region_Code"),
			            rs.getString("Region_Name"),
			            rs.getInt("Subregion_Code"),
			            rs.getString("Subregion_Name"),
			            rs.getInt("Intermediate_Region_Code"),
			            rs.getString("Intermediate_Region_Name"),
			            rs.getString("Status_of_Country"),
			            rs.getString("Developed_or_Developing"),
			            rs.getBoolean("SIDS"),
			            rs.getBoolean("LLDC"),
			            rs.getBoolean("LDC"),
			            rs.getInt("Area_SqKm"),
			            rs.getLong("Population")
			            )
			    );
		model.addAttribute("country", country);
		
		YearsDTO yearsInfo = jdbcTemplate.queryForObject(
			    "SELECT " +
			    "    MIN(YEAR(m.match_date)) AS firstYear, " +
			    "    MAX(YEAR(m.match_date)) AS lastYear, " +
			    "    COUNT(DISTINCT YEAR(m.match_date)) AS totalYears " +
			    "FROM results m " +
			    "WHERE m.home_team = ? OR m.away_team = ?",
			    new Object[]{isoCode, isoCode},
			    (rs, rowNum) -> new YearsDTO(
			        rs.getInt("firstYear"),
			        rs.getInt("lastYear"),
			        rs.getInt("totalYears")
			    )
			);
		model.addAttribute("yearsInfo", yearsInfo);
		
		int wins = jdbcTemplate.queryForObject(
			    "SELECT "
			    + "(SELECT COUNT(*) FROM results WHERE home_team = ? AND home_score > away_score) "
			    + "+ "
			    + "(SELECT COUNT(*) FROM results WHERE away_team = ? AND away_score > home_score) "
			    + "AS total_wins;",
			    new Object[]{isoCode, isoCode},
			    (rs, rowNum) -> rs.getInt("total_wins")
			);
		model.addAttribute("wins", wins);
		
		int losses = jdbcTemplate.queryForObject(
			    "SELECT "
			    + "(SELECT COUNT(*) FROM results WHERE home_team = ? AND home_score < away_score) "
			    + "+ "
			    + "(SELECT COUNT(*) FROM results WHERE away_team = ? AND away_score < home_score) "
			    + "AS total_losses;",
			    new Object[]{isoCode, isoCode},
			    (rs, rowNum) -> rs.getInt("total_losses")
			);
		model.addAttribute("losses", losses);
		
		int draws = jdbcTemplate.queryForObject(
			    "SELECT "
			    + "(SELECT COUNT(*) FROM results WHERE home_team = ? AND home_score = away_score) "
			    + "+ "
			    + "(SELECT COUNT(*) FROM results WHERE away_team = ? AND away_score = home_score) "
			    + "AS total_draws;",
			    new Object[]{isoCode, isoCode},
			    (rs, rowNum) -> rs.getInt("total_draws")
			);
		model.addAttribute("draws", draws);
		
		int matches = jdbcTemplate.queryForObject(
			    "SELECT "
			    + "(SELECT COUNT(*) FROM results WHERE home_team = ?) "
			    + "+ "
			    + "(SELECT COUNT(*) FROM results WHERE away_team = ?) "
			    + "AS total_matches;",
			    new Object[]{isoCode, isoCode},
			    (rs, rowNum) -> rs.getInt("total_matches")
			);
		model.addAttribute("matches", matches);
		
		int home_matches = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) "
				+ "FROM results WHERE home_team = ?",
			    new Object[]{isoCode},
			    (rs, rowNum) -> rs.getInt(1)
			);
		model.addAttribute("home_matches", home_matches);
		
		int away_matches = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) "
				+ "FROM results WHERE away_team = ?",
			    new Object[]{isoCode},
			    (rs, rowNum) -> rs.getInt(1)
			);
		model.addAttribute("away_matches", away_matches);
		
		List<CountryStatsAtYearDTO> statsAtYears = jdbcTemplate.query(
				"SELECT YEAR(match_date) AS year, "
				+ "COUNT(*) AS total_matches,"
	            + "SUM(CASE WHEN (home_team = ? AND home_score > away_score) "
	            + "OR (away_team = ? AND away_score > home_score) THEN 1 ELSE 0 END) AS wins, "
	            + "SUM(CASE WHEN (home_team = ? AND home_score < away_score) "
	            + "OR (away_team = ? AND away_score < home_score) THEN 1 ELSE 0 END) AS losses, "
	            + "SUM(CASE WHEN home_score = away_score THEN 1 ELSE 0 END) AS draws "
	            + "FROM results "
	            + "WHERE home_team = ? OR away_team = ? "
	            + "GROUP BY YEAR(match_date) "
	            + "ORDER BY YEAR(match_date);",
			    new Object[]{isoCode, isoCode, isoCode, isoCode, isoCode, isoCode},
			    (rs, rowNum) -> new CountryStatsAtYearDTO(
			        rs.getInt("wins"),
			        rs.getInt("losses"),
			        rs.getInt("draws"),
			        rs.getInt("total_matches"),
			        rs.getInt("year")
			    )
			);
		model.addAttribute("stats_at_years", statsAtYears);
		
		List<ResultsDTO> countryMatches = jdbcTemplate.query(
				"SELECT r.match_date, "
				+ "r.home_team, "
				+ "r.away_team, "
				+ "home.Display_Name AS home_team_name, "
				+ "away.Display_Name AS away_team_name, "
				+ "r.home_score, r.away_score, "
				+ "r.tournament, r.city, "
				+ "r.country, "
				+ "host.Display_Name AS host_country_name, "
				+ "r.neutral "
				+ "FROM results r "
				+ "JOIN countries home ON r.home_team = home.ISO_Code "
				+ "JOIN countries away ON r.away_team = away.ISO_Code "
				+ "JOIN countries host ON r.country = host.ISO_Code "
				+ "WHERE r.home_team = ? OR r.away_team = ? "
				+ "ORDER BY r.match_date",
			    new Object[]{isoCode, isoCode},
			    (rs, rowNum) -> new ResultsDTO(
			        rs.getDate("r.match_date"),
			        rs.getInt("r.home_team"),
			        rs.getInt("r.away_team"),
			        rs.getString("home_team_name"),
			        rs.getString("away_team_name"),
			        rs.getInt("home_score"),
			        rs.getInt("away_score"),
			        rs.getString("r.tournament"),
			        rs.getString("r.city"),
			        rs.getInt("r.country"),
			        rs.getString("host_country_name"),
			        rs.getString("neutral")
			    )
			);
		model.addAttribute("country_matches", countryMatches);
	}
}