package ipl;

public class Deliveries {

    private int matchId;
    private int innings;
    private String  battingTeam;
    private String bowlingTeam;
    private int over;
    private int ball;
    private String batsmanName;
    private String nonStriker;
    private String bowlerName;
    private int isSuperOver;
    private int wideRuns;
    private int byeRuns;
    private int legByeRuns;
    private int noballRuns;
    private int penaltyRuns;
    private Integer batsmanRuns;
    private int extraRuns;
    private int totalRuns;
    
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public void setInnings(int innings) {
        this.innings = innings;
    }
    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }
    
    public String getNonStriker() {
		return nonStriker;
	}
	public void setNonStriker(String nonStriker) {
		this.nonStriker = nonStriker;
	}
	public int getIsSuperOver() {
		return isSuperOver;
	}
	public void setIsSuperOver(int isSuperOver) {
		this.isSuperOver = isSuperOver;
	}
	public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }
    public void setOver(int over) {
        this.over = over;
    }
    public void setBall(int ball) {
        this.ball = ball;
    }
    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }
    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }
    public void setWideRuns(int wideRuns) {
        this.wideRuns = wideRuns;
    }
    public void setByeRuns(int byeRuns) {
        this.byeRuns = byeRuns;
    }
    public void setLegByeRuns(int legByeRuns) {
        this.legByeRuns = legByeRuns;
    }
    public void setNoballRuns(int noballRuns) {
        this.noballRuns = noballRuns;
    }
    public void setPenaltyRuns(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }
    public void setBatsmanRuns(Integer batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }
    public void setExtraRuns(int extraRuns) {
        this.extraRuns = extraRuns;
    }
    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }
    public int getMatchId() {
        return matchId;
    }
    public int getInnings() {
        return innings;
    }
    public String getBattingTeam() {
        return battingTeam;
    }
    public String getBowlingTeam() {
        return bowlingTeam;
    }
    public int getOver() {
        return over;
    }
    public int getBall() {
        return ball;
    }
    public String getBatsmanName() {
        return batsmanName;
    }
    public String getBowlerName() {
        return bowlerName;
    }
    public int getWideRuns() {
        return wideRuns;
    }
    public int getByeRuns() {
        return byeRuns;
    }
    public int getLegByeRuns() {
        return legByeRuns;
    }
    public int getNoballRuns() {
        return noballRuns;
    }
    public int getPenaltyRuns() {
        return penaltyRuns;
    }
    public Integer getBatsmanRuns() {
        return batsmanRuns;
    }
    public int getExtraRuns() {
        return extraRuns;
    }
    public int getTotalRuns() {
        return totalRuns;
    }
	@Override
	public String toString() {
		return "Deliveries [matchId=" + matchId + ", innings=" + innings + ", battingTeam=" + battingTeam
				+ ", bowlingTeam=" + bowlingTeam + ", over=" + over + ", ball=" + ball + ", batsmanName=" + batsmanName
				+ ", nonStriker=" + nonStriker + ", bowlerName=" + bowlerName + ", isSuperOver=" + isSuperOver
				+ ", wideRuns=" + wideRuns + ", byeRuns=" + byeRuns + ", legByeRuns=" + legByeRuns + ", noballRuns="
				+ noballRuns + ", penaltyRuns=" + penaltyRuns + ", batsmanRuns=" + batsmanRuns + ", extraRuns="
				+ extraRuns + ", totalRuns=" + totalRuns + "]";
	}
    
    
}