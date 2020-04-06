package ipl;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Problem2 {
	private static Map<Integer, List<Integer>> seasons;
	private static Predicate<Deliveries> isFour = (delivery) -> delivery.getBatsmanRuns().equals(4);
	private static Predicate<Deliveries> isSix = (delivery) -> delivery.getBatsmanRuns().equals(6);
	private static BiPredicate<Entry<Integer, List<Integer>>, Integer> isInRange = (map, matchId) -> matchId >= map.getValue().get(0) && matchId <= map.getValue().get(1);	
	private static BiFunction<Optional<Integer>, Optional<Integer>, List<Integer>> collectMatchIdMinMax = (e1, e2) -> Arrays.asList(e1.get(), e2.get());
	
	public static void solve(List<Deliveries> deliveries, List<Match> matches) {		
		Map<Integer, Map<String, List<Long>>> collect = deliveries.stream()
		.collect(groupingBy(Deliveries::getMatchId,
						groupingBy(Deliveries::getBattingTeam,
								Collectors.teeing(
										fourSix(isSix, isFour),
										summingLong(Deliveries::getTotalRuns),
										Problem2::listExtract
									)
								)
						)
					);
		
		seasons = getMatchIdSeasonYearMapper(matches);
		List<OverviewByTeam> list = overviewByTeamMapper(collect);
		
		Map<Integer, Map<String, List<Long>>> finalResult = list.stream()
				.collect(groupingBy(OverviewByTeam::getYear, TreeMap::new,
									groupingBy(OverviewByTeam::getTeamName,
												Collectors.teeing(
													Collectors.teeing(
															summingLong(OverviewByTeam::getNoOfFours),
															summingLong(OverviewByTeam::getNoOfSixes),
															List::of),
													Collectors.summingLong(OverviewByTeam::getNoOfRuns),
													Problem2::listExtract
													)
											)
									)
						);
		finalResult(finalResult);
		
	}
	private static void finalResult(Map<Integer, Map<String, List<Long>>> finalResult) {
		System.out.println();
		System.out.println("--------Print 2nd problem--------------------");
		finalResult.entrySet().stream().forEach(p->{
			p.getValue().entrySet().forEach(p2->{
				System.out.println(p.getKey()+" "+p2.getKey()+" "+p2.getValue());
			});
		});
	}
	private static List<OverviewByTeam> overviewByTeamMapper(Map<Integer, Map<String, List<Long>>> collect) {
		List<OverviewByTeam>  list = new ArrayList<OverviewByTeam>();
		collect.entrySet().stream().forEachOrdered(entry1->{
			entry1.getValue().entrySet().stream().forEachOrdered(entry2->{
				Integer year = seasonMapper(entry1.getKey());
				list.add(new OverviewByTeam(entry1.getKey(), entry2.getKey(), entry2.getValue().get(0), entry2.getValue().get(1), entry2.getValue().get(2), year));
			});
		});
		return list;
	}

	private static Map<Integer, List<Integer>> getMatchIdSeasonYearMapper(List<Match> matches) {
		return matches.stream().collect(
				Collectors.groupingBy(Match::getSeason,
				TreeMap::new,
				Collectors.teeing(
						Collectors.mapping(Match::getMatchId, Collectors.minBy(Comparator.naturalOrder())),
						Collectors.mapping(Match::getMatchId, Collectors.maxBy(Comparator.naturalOrder())),
						collectMatchIdMinMax
						)
				));
	}
	
	private static Integer seasonMapper(Integer matchId) {
		return seasons.entrySet().stream()
				.filter((map)-> isInRange.test(map, matchId))
				.mapToInt(x->x.getKey()) //getSeason year
				.findFirst()
				.getAsInt();
	}
	
	private static List<Long> listExtract(List<Long> e1, Long e2) {
			List<Long> list = new ArrayList<Long>();
			list.addAll(e1); //four and sixes list
			list.add(e2); //total runs
			return list;
	}
	
	private static Collector<Deliveries, ?, List<Long>> fourSix(Predicate<Deliveries> isSix, Predicate<Deliveries> isFour) {
		return Collectors.teeing(
			filtering(isFour, counting()), //count fours
			filtering(isSix, counting()), //count sixes
			List::of //return list
		);
	}
}
