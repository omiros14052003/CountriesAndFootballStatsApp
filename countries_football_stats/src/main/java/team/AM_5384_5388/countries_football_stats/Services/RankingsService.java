package team.AM_5384_5388.countries_football_stats.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import team.AM_5384_5388.countries_football_stats.Datamodel.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class RankingsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CountryRankingDTO> getCountryRankings(String sortBy) {
        String orderBy = switch (sortBy) {
            case "wins" -> "wins DESC";
            case "draws" -> "draws DESC";
            case "losses" -> "losses DESC";
            case "totalMatches" -> "totalMatches DESC";
            case "goalsFor" -> "goalsFor DESC";
            case "goalsAgainst" -> "goalsAgainst DESC";
            case "winrate" -> "CASE WHEN COUNT(*) = 0 THEN 0 ELSE 1.0 * SUM(CASE WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR (r.away_team = c.ISO_Code AND r.away_score > r.home_score) THEN 1 ELSE 0 END) / COUNT(*) END DESC";
            case "winsPerYear" -> "wins / (YEAR(CURDATE()) - firstYear + 1) DESC";
            case "pointsPerYear" -> "points / (YEAR(CURDATE()) - firstYear + 1) DESC";
            default -> "points DESC";
        };

        String sql = """
        	    SELECT c.Display_Name AS countryName,
        	           SUM(
        	               CASE
        	                   WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
        	                        (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
        	                   THEN 3
        	                   WHEN r.home_score = r.away_score THEN 1
        	                   ELSE 0
        	               END
        	           ) AS points,
        	           SUM(
        	               CASE
        	                   WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
        	                        (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
        	                   THEN 1
        	                   ELSE 0
        	               END
        	           ) AS wins,
        	           SUM(CASE WHEN r.home_score = r.away_score THEN 1 ELSE 0 END) AS draws,
        	           SUM(
        	               CASE
        	                   WHEN (r.home_team = c.ISO_Code AND r.home_score < r.away_score) OR
        	                        (r.away_team = c.ISO_Code AND r.away_score < r.home_score)
        	                   THEN 1
        	                   ELSE 0
        	               END
        	           ) AS losses,
        	           COUNT(*) AS totalMatches,
        	           SUM(
        	               CASE
        	                   WHEN r.home_team = c.ISO_Code THEN r.home_score
        	                   WHEN r.away_team = c.ISO_Code THEN r.away_score
        	                   ELSE 0
        	               END
        	           ) AS goalsFor,
        	           SUM(
        	               CASE
        	                   WHEN r.home_team = c.ISO_Code THEN r.away_score
        	                   WHEN r.away_team = c.ISO_Code THEN r.home_score
        	                   ELSE 0
        	               END
        	           ) AS goalsAgainst,
        	           MIN(EXTRACT(YEAR FROM r.match_date)) AS firstYear
        	    FROM results r
        	    JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
        	    GROUP BY c.ISO_Code, c.Display_Name
        	    """ + " ORDER BY " + orderBy;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new CountryRankingDTO(
                rs.getString("countryName"),
                rs.getInt("points"),
                rs.getInt("wins"),
                rs.getInt("draws"),
                rs.getInt("losses"),
                rs.getInt("totalMatches"),
                rs.getInt("goalsFor"),
                rs.getInt("goalsAgainst"),
                rs.getInt("firstYear")
            )
        );
    }
    
    public List<CountryRankingDTO> getTop10ByPoints() {
        String sql = """
            SELECT c.Display_Name AS countryName,
                   SUM(
                       CASE
                           WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
                                (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                           THEN 3
                           WHEN r.home_score = r.away_score THEN 1
                           ELSE 0
                       END
                   ) AS points
            FROM results r
            JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
            GROUP BY c.ISO_Code, c.Display_Name
            ORDER BY points DESC LIMIT 10
        """;

        return jdbcTemplate.query(sql, new RowMapper<CountryRankingDTO>() {
            @Override
            public CountryRankingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                String countryName = rs.getString("countryName");
                int points = rs.getInt("points");

                CountryRankingDTO countryRankingDTO = new CountryRankingDTO(countryName);
                countryRankingDTO.setPoints(points); // Ρυθμίζουμε τα points με τον setter
                return countryRankingDTO;
            }
        });
    }
    
    public List<CountryRankingDTO> getTop10ByWins() {
        String sql = """
            SELECT c.Display_Name AS countryName,
                   SUM(
                       CASE
                           WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
                                (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                           THEN 1
                           ELSE 0
                       END
                   ) AS wins
            FROM results r
            JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
            GROUP BY c.ISO_Code, c.Display_Name
            ORDER BY wins DESC LIMIT 10
        """;

        return jdbcTemplate.query(sql, new RowMapper<CountryRankingDTO>() {
            @Override
            public CountryRankingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                String countryName = rs.getString("countryName");
                int wins = rs.getInt("wins");

                CountryRankingDTO countryRankingDTO = new CountryRankingDTO(countryName);
                countryRankingDTO.setWins(wins);
                return countryRankingDTO;
            }
        });
    }
    
    public List<CountryRankingDTO> getTop10WinsPerYearByCountry() {
        String sql = """
            SELECT c.Display_Name AS countryName,
                   SUM(
                       CASE
                           WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
                                (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                           THEN 1
                           ELSE 0
                       END
                   ) AS wins,
                   MIN(EXTRACT(YEAR FROM r.match_date)) AS firstYear,
                   (SUM(
                       CASE
                           WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
                                (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                           THEN 1
                           ELSE 0
                       END
                   ) * 1.0) / NULLIF((2025 - MIN(EXTRACT(YEAR FROM r.match_date))), 0) AS winsPerYear
            FROM results r
            JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
            GROUP BY c.ISO_Code, c.Display_Name
            ORDER BY winsPerYear DESC
            LIMIT 10
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String countryName = rs.getString("countryName");
            int wins = rs.getInt("wins");
            int firstYear = rs.getInt("firstYear");
            double winsPerYear = rs.getDouble("winsPerYear");

            CountryRankingDTO dto = new CountryRankingDTO(countryName);
            dto.setWins(wins);
            dto.setFirstYear(firstYear);
            dto.setWinsPerYear(winsPerYear);
            return dto;
        });
    }
    
    public List<CountryRankingDTO> getTop10PointsPerYearByCountry() {
        String sql = """
            SELECT c.Display_Name AS countryName,
                   SUM(
                       CASE
                           WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
                                (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                           THEN 3
                           WHEN r.home_score = r.away_score THEN 1
                           ELSE 0
                       END
                   ) AS points,
                   MIN(EXTRACT(YEAR FROM r.match_date)) AS firstYear,
                   (SUM(
                       CASE
                           WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
                                (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
                           THEN 3
                           WHEN r.home_score = r.away_score THEN 1
                           ELSE 0
                       END
                   ) * 1.0) / NULLIF((2025 - MIN(EXTRACT(YEAR FROM r.match_date))), 0) AS pointsPerYear
            FROM results r
            JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
            GROUP BY c.ISO_Code, c.Display_Name
            ORDER BY pointsPerYear DESC
            LIMIT 10
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String countryName = rs.getString("countryName");
            int points = rs.getInt("points");
            int firstYear = rs.getInt("firstYear");
            double pointsPerYear = rs.getDouble("pointsPerYear");

            CountryRankingDTO dto = new CountryRankingDTO(countryName);
            dto.setPoints(points);
            dto.setFirstYear(firstYear);
            dto.setPointsPerYear(pointsPerYear);
            return dto;
        });
    }
    
    public List<CountryPoPointsDTO> getCountryPoPoints() {
    	String sql = """
    		    SELECT c.Display_Name AS countryName,
    		           c.Population AS population,
    		           c.Continent AS continent,
    		           SUM(
    		               CASE
    		                   WHEN (r.home_team = c.ISO_Code AND r.home_score > r.away_score) OR
    		                        (r.away_team = c.ISO_Code AND r.away_score > r.home_score)
    		                   THEN 3
    		                   WHEN r.home_score = r.away_score THEN 1
    		                   ELSE 0
    		               END
    		           ) AS totalPoints
    		    FROM results r
    		    JOIN countries c ON c.ISO_Code = r.home_team OR c.ISO_Code = r.away_team
    		    GROUP BY c.ISO_Code, c.Display_Name, c.Population, c.Continent
    		    ORDER BY totalPoints DESC
    		""";

        return jdbcTemplate.query(sql, (rs, rowNum) -> 
            new CountryPoPointsDTO(
                rs.getString("countryName"),
                rs.getLong("population"),
                rs.getInt("totalPoints"),
                rs.getString("continent")
            )
        );
    }
    
    public List<String> getContinents() {
    	String sql = """
    		    SELECT DISTINCT Continent FROM countries
    		""";

        return jdbcTemplate.query(sql, (rs, rowNum) -> 
            rs.getString("Continent")
        );
    }

}
