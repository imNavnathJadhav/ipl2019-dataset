package ipl;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Prob1 {
	
	public static void solve(List<Match> input) {
		Map<Integer, Map<String, Long>> map=
				input
				.stream()
				.filter(filterMatches())
				.collect(groupBySeason());

		Map<Integer, Map<String, Long>> result = map.entrySet()
				.stream()
				.collect(toTopSortedTeam());
		printFirstProblem(result);
	}

	
	private static Collector<Entry<Integer, Map<String, Long>>, ?, Map<Integer, Map<String, Long>>> toTopSortedTeam() {
		return Collectors.toMap(e -> e.getKey(), e -> sortMapValueDescending(e.getValue()));
	}

	private static Collector<Match, ?, Map<Integer, Map<String, Long>>> groupBySeason() {
		return Collectors.groupingBy(Match::getSeason, countAndGroupByTossWinner());
	}

	private static Collector<Match, ?, Map<String, Long>> countAndGroupByTossWinner() {
	
		return Collectors.groupingBy(Match::getTossWinner, Collectors.counting());
	}
	
	private static Predicate<? super Match> filterMatches() {
		return x->x.getTossDecision().equals("field") && x.getSeason()==2016 || x.getSeason()==2017;
	}
	
	private static Map<String, Long> sortMapValueDescending(Map<String, Long> team) {
		return team.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(4) //top 4 teams
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
	
	private static void printFirstProblem(Map<Integer, Map<String, Long>> result) {
		result.entrySet().stream().forEach(p->{
			p.getValue().entrySet().forEach(p2->{
				System.out.println(p.getKey()+" "+p2.getKey()+" "+p2.getValue());
			});
		});
	}
}
