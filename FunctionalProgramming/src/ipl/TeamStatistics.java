package ipl;

public record TeamStatistics(Integer matchId, String teamName, Long totalRunsScored,
		Long totalBallsFaced, Long totalRunsConceded, Long totalBallsBowled) {}
