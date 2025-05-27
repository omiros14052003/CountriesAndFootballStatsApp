package team.AM_5384_5388.countries_football_stats.Datamodel;


public class CountryRankingDTO {
    private String countryName;
    private int points;
    private int wins;
    private int draws;
    private int losses;
    private int totalMatches;
    private int goalsFor;
    private int goalsAgainst;
    private double winRate;
    private int firstYear;
    private int yearsActive;
    private double winsPerYear;
    private double pointsPerYear;

    public CountryRankingDTO(String countryName, int points, int wins, int draws, int losses, int totalMatches,
                             int goalsFor, int goalsAgainst, int firstYear) {
        this.countryName = countryName;
        this.points = points;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.totalMatches = totalMatches;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.firstYear = firstYear;
        this.yearsActive = 2025 - firstYear;
        this.winRate = totalMatches == 0 ? 0 : (double) wins / totalMatches;
        this.winsPerYear = yearsActive == 0 ? 0 : (double) wins / yearsActive;
        this.pointsPerYear = yearsActive == 0 ? 0 : (double) points / yearsActive;
    }
    
    public CountryRankingDTO(String countryName) {
        this.countryName = countryName;
        this.points = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.totalMatches = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.firstYear = 0;
        this.yearsActive = 0;
        this.winRate = 0;
        this.winsPerYear = 0;
        this.pointsPerYear = 0;
    }
    
    public double calculateWinsPerYear() {
    	yearsActive = 2025 - firstYear;
    	winsPerYear = yearsActive == 0 ? 0 : (double) wins / yearsActive;
    	return winsPerYear;
    }
    
    public double calculatePointsPerYear() {
    	yearsActive = 2025 - firstYear;
    	pointsPerYear = yearsActive == 0 ? 0 : (double) points / yearsActive;
    	return winsPerYear;
    }
    
    // Getters and Setters
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

	public int getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(int firstYear) {
		this.firstYear = firstYear;
	}

	public int getYearsActive() {
		return yearsActive;
	}

	public void setYearsActive(int yearsActive) {
		this.yearsActive = yearsActive;
	}

	public double getWinsPerYear() {
		return winsPerYear;
	}

	public void setWinsPerYear(double winsPerYear) {
		this.winsPerYear = winsPerYear;
	}

	public double getPointsPerYear() {
		return pointsPerYear;
	}

	public void setPointsPerYear(double pointsPerYear) {
		this.pointsPerYear = pointsPerYear;
	}
}
