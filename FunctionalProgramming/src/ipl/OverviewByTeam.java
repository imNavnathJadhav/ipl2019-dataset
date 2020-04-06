package ipl;

public class OverviewByTeam {
	private Integer matchId;
	private String teamName;
	private Long noOfFours;
	private Long noOfSixes;
	private Long noOfRuns;
	private Integer year;
	
	public OverviewByTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OverviewByTeam(Integer matchId, String teamName, Long noOfFours, Long noOfSixes, Long noOfRuns,
			Integer year) {
		super();
		this.matchId = matchId;
		this.teamName = teamName;
		this.noOfFours = noOfFours;
		this.noOfSixes = noOfSixes;
		this.noOfRuns = noOfRuns;
		this.year = year;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getNoOfFours() {
		return noOfFours;
	}

	public void setNoOfFours(Long noOfFours) {
		this.noOfFours = noOfFours;
	}

	public Long getNoOfSixes() {
		return noOfSixes;
	}

	public void setNoOfSixes(Long noOfSixes) {
		this.noOfSixes = noOfSixes;
	}

	public Long getNoOfRuns() {
		return noOfRuns;
	}

	public void setNoOfRuns(Long noOfRuns) {
		this.noOfRuns = noOfRuns;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "OverviewByTeam [matchId=" + matchId + ", teamName=" + teamName + ", noOfFours=" + noOfFours
				+ ", noOfSixes=" + noOfSixes + ", noOfRuns=" + noOfRuns + ", year=" + year + "]";
	}


}
