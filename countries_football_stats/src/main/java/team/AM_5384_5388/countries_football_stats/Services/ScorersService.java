package team.AM_5384_5388.countries_football_stats.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import team.AM_5384_5388.countries_football_stats.Datamodel.GoalsPerYearDTO;
import team.AM_5384_5388.countries_football_stats.Datamodel.ScorerTeamDTO;
import team.AM_5384_5388.countries_football_stats.Datamodel.YearsDTO;



@Service
public class ScorersService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void getAllScorersService(Model model){
		List<ScorerTeamDTO> scorerNames = jdbcTemplate.query(
			    "SELECT scorer_name, team, country.Display_Name FROM scorers JOIN countries country ON scorers.team = country.ISO_Code GROUP BY scorer_name, team",
			    (rs, rowNum) -> new ScorerTeamDTO(
			    		rs.getString("scorer_name"),
			    		rs.getInt("team"),
			    		rs.getString("country.Display_Name")));
		model.addAttribute("scorers", scorerNames);
	}
	
	public void showScorerInformationService(Model model, String scorerName, int scorerTeam){
		model.addAttribute("scorer_name", scorerName);
		
		String teamName = jdbcTemplate.queryForObject(
				"SELECT Display_Name FROM countries WHERE ISO_Code = ? ",
				new Object[]{scorerTeam},
			    (rs, rowNum) -> rs.getString("Display_Name"));
		model.addAttribute("scorer_team", teamName);
		
		int goals = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) AS goals FROM scorers WHERE scorer_name = ? ",
				new Object[]{scorerName},
			    (rs, rowNum) -> rs.getInt("goals"));
		model.addAttribute("goals", goals);
		
		int maxMatchGoals = jdbcTemplate.queryForObject(
				"SELECT MAX(goals_per_match) AS max_goals "
				+ "FROM "
				+ "( SELECT COUNT(*) AS goals_per_match FROM scorers"
				+ " WHERE scorer_name = ?"
				+ " GROUP BY match_date, team)"
				+ "AS match_goals",
				new Object[]{scorerName},
			    (rs, rowNum) -> rs.getInt("max_goals"));
		model.addAttribute("max_match_goals", maxMatchGoals);
		
		double goalsPerMatch = jdbcTemplate.queryForObject(
				"SELECT "
				+ "(SELECT COUNT(*) FROM scorers WHERE scorer_name = ? ) "
				+ "/ "
				+ "(SELECT COUNT(DISTINCT match_date) FROM scorers WHERE scorer_name = ?) "
				+ "AS goals_per_match",
				new Object[]{scorerName, scorerName},
			    (rs, rowNum) -> rs.getDouble("goals_per_match"));
		model.addAttribute("goals_per_match", goalsPerMatch);
		
		YearsDTO yearsInfo = jdbcTemplate.queryForObject(
			    "SELECT "
			    + " MIN(YEAR(s.match_date)) AS firstYear, "
			    + " MAX(YEAR(s.match_date)) AS lastYear, "
			    + " COUNT(DISTINCT YEAR(s.match_date)) AS totalYears "
			    + "FROM scorers s "
			    + "WHERE s.scorer_name = ?",
			    new Object[]{scorerName},
			    (rs, rowNum) -> new YearsDTO(
			        rs.getInt("firstYear"),
			        rs.getInt("lastYear"),
			        rs.getInt("totalYears")
			    )
			);
		model.addAttribute("yearsInfo", yearsInfo);
		
		List<GoalsPerYearDTO> statsAtYears = jdbcTemplate.query(
				"SELECT YEAR(match_date) AS year, "
				+ "COUNT(*) AS goals "
	            + "FROM scorers "
	            + "WHERE scorer_name = ? "
	            + "GROUP BY YEAR(match_date) "
	            + "ORDER BY YEAR(match_date)",
			    new Object[]{scorerName},
			    (rs, rowNum) -> new GoalsPerYearDTO(
			        rs.getInt("goals"),
			        rs.getInt("year")
			    )
			);
		model.addAttribute("stats_at_years", statsAtYears);
	}
}