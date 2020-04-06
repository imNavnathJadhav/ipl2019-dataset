package ipl;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Problem3 {
	private static Map<Integer, List<Integer>> seasons;
	private static Predicate<Deliveries> notWideOrNoBall = (delivery) -> delivery.getNoballRuns()==0 && delivery.getWideRuns()==0;
	
	private static BiPredicate<Entry<Integer, List<Integer>>, Integer> isInRange = (map,
			matchId) -> matchId >= map.getValue().get(0) && matchId <= map.getValue().get(1);
	private static BiFunction<Optional<Integer>, Optional<Integer>, List<Integer>> collectMatchIdMinMax = (e1,
			e2) -> Arrays.asList(e1.get(), e2.get());

	public static void solve(List<Deliveries> deliveries, List<Match> matches) {
		Map<Integer, Map<String, List<Long>>> collect = deliveries.stream()
				.collect(groupingBy(Deliveries::getMatchId,
						groupingBy(Deliveries::getBowlerName,
								Collectors.teeing(
										Collectors.teeing(
												summingLong(Deliveries::getByeRuns),
												summingLong(Deliveries::getLegByeRuns),
												List::of),
										Collectors.teeing(
												Collectors.filtering(notWideOrNoBall, counting()), 
												summingLong(Deliveries::getTotalRuns),
												List::of),
										Problem3::listExtract))));
		
		//System.out.println(collect);
		seasons = getMatchIdSeasonYearMapper(matches);
		List<Bowler> bowlersStatistics = bowlerMapper(collect);
		
		Map<Integer, Map<String, List<Double>>> collect2 = bowlersStatistics.stream()
		.collect(
				Collectors.groupingBy(Bowler::getYear, TreeMap::new,
						groupingBy(Bowler::getBowlerName,
								Collectors.teeing(
										Collectors.summingDouble(Bowler::getNoBalls),
										Collectors.summingDouble(Bowler::getNoOfRunsConceded),
										Problem3::doubleToList
										)
								)
						)
				);
		System.out.println(collect2);
		
		Map<Integer, Map<String, Double>> finalResult = collect2.entrySet().stream()
				.map(Problem3::filterByAtleastTenOvers)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		print(finalResult);
	}
	private static void print(Map<Integer, Map<String, Double>> input) {
		System.out.println("Year-PlayaerName-Economy");
		input.entrySet().stream().forEach(entry1->{
			entry1.getValue().entrySet().stream().forEachOrdered(entry2->{
				System.out.println(entry1.getKey()+" "+entry2.getKey()+" "+entry2.getValue());
			}
			);
			System.out.println();
			});
	}
	private static Map.Entry<Integer, Map<String,Double>> filterByAtleastTenOvers(Map.Entry<Integer,Map<String,List<Double>>> collection){
		Predicate<? super Entry<String, List<Double>>> predicate = e -> e.getValue().get(0)>=60;
		Map<String, Double> collect = collection.getValue()
		.entrySet()
		.stream()
		.filter(predicate)
		.map(Problem3::calculateEconomy)
		.sorted(Map.Entry.comparingByValue())
		.limit(10)
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)->e1, LinkedHashMap::new));
		return Map.entry(collection.getKey(), collect);
	}
	private static Map.Entry<String, Double> calculateEconomy(Entry<String, List<Double>> input){
		Double overs= input.getValue().get(0)/6;
		Double res = input.getValue().get(1)/overs;
		return Map.entry(input.getKey(), Double.parseDouble(new DecimalFormat("##.##").format(res)));
	}
	private static List<Bowler> bowlerMapper(Map<Integer, Map<String, List<Long>>> collect) {
		List<Bowler>  list = new ArrayList<Bowler>();
		collect.entrySet().stream().forEachOrdered(e1->{
			e1.getValue().entrySet().stream().forEachOrdered(e2->{
				Integer year = seasonMapper(e1.getKey());
				Double noOfRunsConceded = (Double)e2.getValue().get(1).doubleValue();
				Double noOfBalls = (Double)e2.getValue().get(0).doubleValue();
				list.add(new Bowler(e1.getKey(), e2.getKey(), noOfBalls, noOfRunsConceded, year));
			});
		});
		return list;
	}

	private static List<Double> doubleToList(Double noOfBalls, Double noOfRunsConceded) {
		return Arrays.asList(noOfBalls, noOfRunsConceded);
	}
	private static List<Long> listExtract(List<Long> e1, List<Long> e2) {
		Long sum = e1.stream().reduce(0L, Long::sum); //total legbye and bye
		Long runsConceded = e2.get(1)-sum; //substract from total runs
		return Arrays.asList(e2.get(0), runsConceded); // add no of balls and runs conceded
	}


	private static Map<Integer, List<Integer>> getMatchIdSeasonYearMapper(List<Match> matches) {
		return matches.stream().collect(Collectors.groupingBy(Match::getSeason, TreeMap::new,
				Collectors.teeing(Collectors.mapping(Match::getMatchId, Collectors.minBy(Comparator.naturalOrder())),
						Collectors.mapping(Match::getMatchId, Collectors.maxBy(Comparator.naturalOrder())),
						collectMatchIdMinMax)));
	}

	private static Integer seasonMapper(Integer matchId) {
		return seasons.entrySet().stream().filter((map) -> isInRange.test(map, matchId)).mapToInt(x -> x.getKey())
				.findFirst().getAsInt();
	}
}
class Bowler{
	private Integer matchId;
	private String BowlerName;
	private Double noBalls;
	private Double noOfRunsConceded;
	private Integer year;
	public Bowler(Integer matchId, String bowlerName, Double noBalls, Double noOfRunsConceded, Integer year) {
		super();
		this.matchId = matchId;
		BowlerName = bowlerName;
		this.noBalls = noBalls;
		this.noOfRunsConceded = noOfRunsConceded;
		this.year = year;
	}
	public Bowler() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public String getBowlerName() {
		return BowlerName;
	}
	public void setBowlerName(String bowlerName) {
		BowlerName = bowlerName;
	}
	public Double getNoBalls() {
		return noBalls;
	}
	public void setNoBalls(Double noBalls) {
		this.noBalls = noBalls;
	}
	public Double getNoOfRunsConceded() {
		return noOfRunsConceded;
	}
	public void setNoOfRunsConceded(Double noOfRunsConceded) {
		this.noOfRunsConceded = noOfRunsConceded;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Bowler [matchId=" + matchId + ", BowlerName=" + BowlerName + ", noBalls=" + noBalls
				+ ", noOfRunsConceded=" + noOfRunsConceded + ", year=" + year + "]";
	}
	
}