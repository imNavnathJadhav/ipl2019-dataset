package collectors.josepaumard;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.summarizingLong;
import static java.util.stream.Collectors.summingLong;

import java.util.Comparator;
import java.util.LongSummaryStatistics;
import java.util.Set;

import com.navnath.switchcasemodule.SwitchCaseTest;

public class CountMinMax {

	public static void main(String[] args) {
		//module from other project
		System.out.println(new SwitchCaseTest().getCountryShortCode("INDIA"));
		
		Set<Book> setOfBooks = BookService.getBooks();
		
		//get total count of Bookss (1)
		long count = setOfBooks.stream().count();
		System.err.println("Count-> "+count);
		
		//get total count of Bookss (2)
		System.out.println("Count-> "+setOfBooks.size());
		
		//get total count of Bookss (3)
		System.out.println("Count-> "+setOfBooks.stream().collect(counting()));
		
		//--------------------------------------------------
		//get oldest year (1)
		System.out.println("Oldest Year article ->"+getMinYear(setOfBooks));
		
		//get oldest year (2) 
		System.out.println("Oldest Year article ->"+getMinYear2(setOfBooks));
		
		//get oldest year (3) 
		System.out.println("Oldest Year article ->"+getMinYear3(setOfBooks));
		
		//get oldest year (4) 
		System.out.println("Oldest Year article ->"+getMinYear4(setOfBooks));
		
		//--------------------------------------------------------------------
		//get newest year (1)
		System.out.println("Newest Year article ->"+getMaxYear(setOfBooks));
		
		//get newest year (2)
		System.out.println("Newest Year article ->"+getMaxYear2(setOfBooks));
		
		//get newest year (3)
		System.out.println("Newest Year article ->"+getMaxYear3(setOfBooks));
		
		//get newest year (4)
		System.out.println("Newest Year article ->"+getMaxYear4(setOfBooks));
		
		//sum of price
		System.out.println("sum of price " + getSumOfPrice(setOfBooks));
		System.out.println("sum of price " + getSumOfPrice2(setOfBooks));
		System.out.println("sum of price " + getSumOfPrice3(setOfBooks));
		
		//average of price
		System.out.println("Average of price " + getAverageOfPrice(setOfBooks));
		System.out.println("Average of price " + getAverageOfPrice2(setOfBooks));
		
		//statistics of price
		System.out.println("statistics of price "+ getStatisticsOfPrice(setOfBooks));
		System.out.println("statistics of price "+ getStatisticsOfPrice2(setOfBooks));
	}
	
	private static Integer getMinYear(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.mapToInt(Book::getYear)
				.min()
				.getAsInt();
	}
	
	private static Integer getMinYear2(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.map(Book::getYear)
				.min(Comparator.naturalOrder())
				.get(); 
	}
	
	private static Integer getMinYear3(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.map(Book::getYear)
				.collect(minBy(Comparator.naturalOrder()))
				.get();
	}
	
	private static Integer getMinYear4(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.map(Book::getYear)
				//.reduce(Integer::min).get();
				.reduce(Integer.MAX_VALUE,Integer::min);
	}
	
	private static Integer getMaxYear(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.mapToInt(Book::getYear)
				.max()
				.getAsInt();
	}
	
	private static Integer getMaxYear2(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.map(Book::getYear)
				.max(Comparator.naturalOrder())
				.get();
	}
	
	private static Integer getMaxYear3(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.map(Book::getYear)
				.collect(maxBy(Comparator.naturalOrder()))
				.get();
	}
	
	private static Integer getMaxYear4(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.map(Book::getYear)
				.reduce(Integer::max).get();
				//.reduce(0, Math::max);
	}
	
	//average of year
	private static Double getAverageOfPrice(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.collect(averagingLong(Book::getPrice));
	}
	private static Double getAverageOfPrice2(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.mapToLong(Book::getPrice)
				.average().getAsDouble();
	}
	
	//sum of all year
	private static Long getSumOfPrice(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.collect(summingLong(Book::getPrice));
	}
	private static Long getSumOfPrice2(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.mapToLong(Book::getPrice)
				.sum();
	}
	private static Long getSumOfPrice3(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.mapToLong(Book::getPrice)
				.reduce(0, Long::sum);
	}
	
	//statistics of year
	private static LongSummaryStatistics  getStatisticsOfPrice(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.collect(summarizingLong(Book::getPrice));
	}
	private static LongSummaryStatistics  getStatisticsOfPrice2(Set<Book> setOfBookss) {
		return setOfBookss.stream()
				.mapToLong(Book::getPrice)
				.summaryStatistics();
	}
}
