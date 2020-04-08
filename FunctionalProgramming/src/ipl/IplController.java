package ipl;

import java.util.List;

public class IplController {
	public static void main(String[] args) {
		
		MatchDataReaderService.readData();
		List<Match> matches = MatchDataReaderService.matchesData;
		//Prob1.solve(matches);
		
		DeliveriesDataReaderService.readData();
		List<Deliveries> deliveries = DeliveriesDataReaderService.deliveriesData;
		//Problem2.solve(deliveries, matches);
		
		//Problem3.solve(deliveries, matches);
		Problem4.solve(deliveries, matches);
	}

}