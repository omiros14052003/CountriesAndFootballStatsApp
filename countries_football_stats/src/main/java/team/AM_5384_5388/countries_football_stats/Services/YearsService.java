package team.AM_5384_5388.countries_football_stats.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.AM_5384_5388.countries_football_stats.Datamodel.*;

@Service
public class YearsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Integer> getAllYears() {
        return jdbcTemplate.query(
            "SELECT DISTINCT YEAR(match_date) AS year FROM results ORDER BY year",
            (rs, rowNum) -> rs.getInt("year")
        );
    }
    
    public List<ResultsDTO> getResultsByYear(int year) {
        String sql = "SELECT r.match_date, "
        		+ "r.home_team, "
        		+ "r.away_team, "
        		+ "home.Display_Name AS home_team_name, "
        		+ "away.Display_Name AS away_team_name, "
        		+ "r.home_score, r.away_score, "
        		+ "r.tournament, r.city, "
        		+ "r.country, "
        		+ "host.Display_Name AS host_country_name, "
        		+ "r.neutral, "
        		+ "home.continent AS continent_home, "
        		+ "away.continent AS continent_away, "
        		+ "home.Region_Name AS region_home, "
        		+ "away.Region_Name AS region_away, "
        		+ "home.Status_of_Country AS status_home, "
        		+ "away.Status_of_Country AS status_away, "
        		+ "home.Developed_Or_Developing AS developed_home, "
        		+ "away.Developed_Or_Developing AS developed_away, "
        		+ "home.Population AS population_home, "
        		+ "away.Population AS population_away "
        		+ "FROM results r "
        		+ "JOIN countries home ON r.home_team = home.ISO_Code "
        		+ "JOIN countries away ON r.away_team = away.ISO_Code "
        		+ "JOIN countries host ON r.country = host.ISO_Code "
        		+ "WHERE YEAR(r.match_date) = ? "
        		+ "ORDER BY r.match_date";

        return jdbcTemplate.query(sql, new Object[]{year}, (rs, rowNum) -> {
            return new ResultsDTO(
                rs.getDate("r.match_date"),
                rs.getInt("r.home_team"),
                rs.getInt("r.away_team"),
                rs.getString("home_team_name"),
                rs.getString("away_team_name"),
                rs.getInt("r.home_score"),
                rs.getInt("r.away_score"),
                rs.getString("r.tournament"),
                rs.getString("r.city"),
                rs.getInt("r.country"),
                rs.getString("host_country_name"),
                rs.getString("r.neutral"),
                rs.getString("continent_home"),
    	        rs.getString("continent_away"),
    	        rs.getString("region_home"),
    	        rs.getString("region_away"),
    	        rs.getString("status_home"),
    	        rs.getString("status_away"),
    	        rs.getString("developed_home"),
    	        rs.getString("developed_away"),
    	        rs.getInt("population_home"),
    	        rs.getInt("population_away")
            );
        });
    }
    
    public int getDrawCountByYear(int year) {
        String sql = "SELECT COUNT(*) FROM results " +
                     "WHERE YEAR(match_date) = ? AND home_score = away_score";
        return jdbcTemplate.queryForObject(sql, new Object[]{year}, Integer.class);
    }
    
    public int getPenaltyShootoutsCountByYear(int year) {
        String sql = "SELECT COUNT(*) FROM penaltyshootouts WHERE YEAR(match_date) = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{year}, Integer.class);
    }
    
    public int getTotalGoalsByYear(int year) {
        return jdbcTemplate.queryForObject(
            "SELECT SUM(home_score + away_score) FROM results WHERE YEAR(match_date) = ?",
            new Object[]{year},
            Integer.class
        );
    }
    
    public int getDecisiveMatchesByYear(int year) {
        String sql = "SELECT COUNT(*) FROM results "
        		+ "WHERE YEAR(match_date) = ? AND home_score <> away_score";
        return jdbcTemplate.queryForObject(sql, new Object[]{year}, Integer.class);
    }
    
    public List<CountryWinDrawLossDTO> getTopCountriesWinDrawLossByYear(int year) {
        String sql = """
            SELECT 
            c.Display_Name AS countryName,
                SUM(CASE
                    WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score)
                      OR (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                    THEN 1 ELSE 0 END) AS wins,
                SUM(CASE WHEN r.home_score = r.away_score THEN 1 ELSE 0 END) AS draws,
                SUM(CASE
                    WHEN (r.home_team = c.ISO_Code AND r.home_score < r.away_score)
                      OR (r.away_team = c.ISO_Code AND r.away_score < r.home_score)
                    THEN 1 ELSE 0 END) AS losses
            FROM results r
            JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
            WHERE YEAR(r.match_date) = ?
            GROUP BY c.Display_Name
            ORDER BY (wins + draws + losses) DESC
            LIMIT 20
        """;

        return jdbcTemplate.query(sql, new Object[]{year}, (rs, rowNum) -> 
            new CountryWinDrawLossDTO(
                rs.getString("countryName"),
                rs.getInt("wins"),
                rs.getInt("draws"),
                rs.getInt("losses")
            )
        );
    }
    
    public List<CountryGoalsDTO> getTopCountriesByGoals(int year) {
        String sql = """
            SELECT c.Display_Name AS countryName, SUM(
                CASE 
                    WHEN r.home_team = c.ISO_Code THEN r.home_score
                    WHEN r.away_team = c.ISO_Code THEN r.away_score
                    ELSE 0
                END
            ) AS totalGoals
            FROM results r
            JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
            WHERE EXTRACT(YEAR FROM r.match_date) = ?
            GROUP BY c.Display_Name
            ORDER BY totalGoals DESC
            LIMIT 20
        """;

        return jdbcTemplate.query(sql, new Object[]{year}, (rs, rowNum) -> 
            new CountryGoalsDTO(
                rs.getString("countryName"),
                rs.getInt("totalGoals")
            )
        );
    }
    
    public List<MatchWithFiltersDTO> getMatchesWithContinentsByYear(int year) {
    	String sql = """
    	        SELECT r.home_team, r.away_team, r.match_date,
    	               home.Display_Name AS home_team_name,
    	               away.Display_Name AS away_team_name,
    	               home.continent AS continent_home,
    	               away.continent AS continent_away,
    	               home.Region_Name AS region_home,
    	               away.Region_Name AS region_away,
    	               home.Status_of_Country AS status_home,
    	               away.Status_of_Country AS status_away,
    	               home.Developed_Or_Developing AS developed_home,
    	               away.Developed_Or_Developing AS developed_away,
    	               home.Population AS population_home,
    	               away.Population AS population_away
    	        FROM results r
    	        JOIN countries home ON r.home_team = home.ISO_Code
    	        JOIN countries away ON r.away_team = away.ISO_Code
    	        WHERE YEAR(r.match_date) = ?
    	        ORDER BY r.match_date
    	    """;

    	    return jdbcTemplate.query(sql, new Object[]{year}, (rs, rowNum) -> {
    	        String homeTeam = rs.getString("home_team_name");
    	        String awayTeam = rs.getString("away_team_name");
    	        String matchDate = rs.getString("match_date");
    	        String continentHome = rs.getString("continent_home");
    	        String continentAway = rs.getString("continent_away");
    	        String regionHome = rs.getString("region_home");
    	        String regionAway = rs.getString("region_away");
    	        String statusHome = rs.getString("status_home");
    	        String statusAway = rs.getString("status_away");
    	        String developedHome = rs.getString("developed_home");
    	        String developedAway = rs.getString("developed_away");
    	        int populationHome = rs.getInt("population_home");
    	        int populationAway = rs.getInt("population_away");

            return new MatchWithFiltersDTO(homeTeam, awayTeam, matchDate, continentHome, continentAway, regionHome, regionAway, statusHome, statusAway, developedHome, developedAway, populationHome, populationAway);
        });
    }
    
    public List<SimpleMatchDTO> getSimpleResultsByYear(int year) {
        String sql = """
            SELECT 
                r.match_date, 
                ch.Display_Name AS home_name,
                ca.Display_Name AS away_name,
                r.home_score, 
                r.away_score, 
                r.tournament, 
                r.city, 
                cc.Display_Name AS country_name,
                ch.Continent AS home_continent,
                ca.Continent AS away_continent,
                ch.Developed_Or_Developing AS HomeDeveloped_Or_Developing,
                ca.Developed_Or_Developing AS AwayDeveloped_Or_Developing
            FROM results r
            JOIN countries ch ON r.home_team = ch.ISO_Code
            JOIN countries ca ON r.away_team = ca.ISO_Code
            JOIN countries cc ON r.country = cc.ISO_Code
            WHERE YEAR(r.match_date) = ?
            ORDER BY r.match_date
        """;

        return jdbcTemplate.query(sql, new Object[]{year}, (rs, rowNum) -> {
            SimpleMatchDTO dto = new SimpleMatchDTO();
            dto.setDate(rs.getString("match_date"));
            dto.setHomeTeam(rs.getString("home_name"));
            dto.setAwayTeam(rs.getString("away_name"));
            dto.setHomeScore(rs.getInt("home_score"));
            dto.setAwayScore(rs.getInt("away_score"));
            dto.setTournament(rs.getString("tournament"));
            dto.setCity(rs.getString("city"));
            dto.setCountryName(rs.getString("country_name"));
            dto.setHomeContinent(rs.getString("home_continent"));
            dto.setAwayContinent(rs.getString("away_continent"));
            dto.setHomeDeveloped_Or_Developing(rs.getString("HomeDeveloped_Or_Developing"));
            dto.setAwayDeveloped_Or_Developing(rs.getString("AwayDeveloped_Or_Developing"));
            return dto;
        });
    }


    
    public List<SimpleMatchDTO> getSimpleResultsByYearAndTournament(int year, String tournament) {
        String sql = "SELECT r.match_date, r.home_team, r.away_team, r.home_score, r.away_score, r.tournament, "
        		+ "r.city, r.country, c1.Display_Name AS home_team_name, c2.Display_Name AS away_team_name, c3.Display_Name AS country_name "
        		+ "FROM results r "
        		+ "JOIN countries c1 ON r.home_team = c1.ISO_Code "
        		+ "JOIN countries c2 ON r.away_team = c2.ISO_Code "
        		+ "JOIN countries c3 ON r.country = c3.ISO_Code "
        		+ "WHERE YEAR(r.match_date) = ? AND r.tournament = ?";
        
        return jdbcTemplate.query(sql, new Object[]{year, tournament}, (rs, rowNum) -> {
            SimpleMatchDTO dto = new SimpleMatchDTO();
            dto.setDate(rs.getString("match_date"));
            dto.setHomeTeam(rs.getString("home_team_name"));
            dto.setAwayTeam(rs.getString("away_team_name"));
            dto.setHomeScore(rs.getInt("home_score"));
            dto.setAwayScore(rs.getInt("away_score"));
            dto.setTournament(rs.getString("tournament"));
            dto.setCity(rs.getString("city"));
            dto.setCountryName(rs.getString("country_name"));
            return dto;
        });
    }


    public List<String> getTournamentsByYear(int year) {
        String sql = "SELECT DISTINCT tournament FROM results WHERE YEAR(match_date) = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{year}, String.class);
    }

}
