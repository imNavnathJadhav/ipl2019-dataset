package ipl;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.teeing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Problem4 {
	private static List<Match> matches;
	
	private static BiPredicate<Entry<Integer, List<Integer>>, Integer> isInRange = (map,
			matchId) -> matchId >= map.getValue().get(0) && matchId <= map.getValue().get(1);
	private static Predicate<Deliveries> notWideOrNoBall = (delivery) -> delivery.getNoballRuns()==0 && delivery.getWideRuns()==0;
	private static BiFunction<Optional<Integer>, Optional<Integer>, List<Integer>> collectMatchIdMinMax = (e1,
			e2) -> Arrays.asList(e1.get(), e2.get());
	public static void solve(List<Deliveries> deliveries, List<Match> matchesList) {
		matches=matchesList;
		Map<Integer, Map<String, List<Long>>> collect = deliveries.stream()
		.collect(filtering(x->getMatchResult(x.getMatchId()),
				groupingBy(Deliveries::getMatchId,
						groupingBy(Deliveries::getBattingTeam, 
								teeing(filtering(notWideOrNoBall, counting()),
										summingLong(Deliveries::getTotalRuns),
										List::of
										)
								)
					)
				));
		//System.out.println(collect);
		List<TeamStatistics> statistics = mapToTeamStatistics(collect);
		System.out.println(statistics);
		
	}
	private static List<TeamStatistics> mapToTeamStatistics(Map<Integer, Map<String, List<Long>>> collect) {
		List<TeamStatistics> list = new ArrayList<TeamStatistics>();
		collect.entrySet().stream().forEachOrdered(eachMatchEntry->{
			Integer mId = eachMatchEntry.getKey();
			if(getMatchResult(mId)) {
				List<TeamStatistics> list2=exchangeTeamData(eachMatchEntry.getValue(), mId);
				list.addAll(list2);
			}
		});
		return list;
	}
	private static List<TeamStatistics> exchangeTeamData(Map<String, List<Long>> entrySet, Integer matchId) {
		//System.out.println(matchId);
		Iterator<Entry<String, List<Long>>> iterator = entrySet.entrySet().stream().iterator();
		Entry<String, List<Long>> team1=iterator.next();
		//Entry<String, List<Long>> team2=iterator.next();
		Entry<String, List<Long>> team2=iterator.hasNext()?iterator.next():Map.entry("", Collections.emptyList());
		/*
		 * Object[] keySets = entrySet.keySet().toArray();
		 * System.out.println(entrySet.keySet().toArray()); List<Long> list =
		 * entrySet.get(keySets[0]); List<Long> list2 =
		 * entrySet.getOrDefault(keySets[1], Collections.emptyList());
		 */
		if(team1.getValue().isEmpty() || team2.getValue().isEmpty()) {
			return List.of();
		}else {
			TeamStatistics statistics = new TeamStatistics(matchId, team1.getKey(), team1.getValue().get(1), team1.getValue().get(0), team2.getValue().get(1), team2.getValue().get(0));
			TeamStatistics statistics2 = new TeamStatistics(matchId, team2.getKey(), team2.getValue().get(1), team2.getValue().get(0), team1.getValue().get(1), team1.getValue().get(0));
			return List.of(statistics, statistics2);
		}
	}
	private static Predicate<? super Match> isMatchResultNormal(int matchId) {
		return x->x.getMatchId()==matchId && x.getResult().equalsIgnoreCase("normal");
	}
	private static Boolean getMatchResult(int matchId) {
		Optional<Match> findFirst = matches.stream().filter(isMatchResultNormal(matchId)).findFirst();
		return findFirst.isPresent()?Boolean.TRUE:Boolean.FALSE;
	}
}
