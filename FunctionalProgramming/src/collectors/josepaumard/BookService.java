package collectors.josepaumard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookService {
	private static Set<Book> articles;
	
	public static Set<Book> getBooks() {
		List<String> authorGroup1 = Arrays.asList("Author A", "Author B", "Author C");
		List<String> authorGroup2 = Arrays.asList("Author A", "Author B");
		List<String> authorGroup3 = Arrays.asList("Author B", "Author C");
		List<String> authorGroup4 = Arrays.asList("Author A", "Author B", "Author C", "Author D");
		List<String> authorGroup5 = Arrays.asList("Author X", "Author Y");
		List<String> authorGroup6 = Arrays.asList("Author Y", "Author Z");
		List<String> authorGroup7 = Arrays.asList("Author P", "Author Q");
		
		articles=new HashSet<Book>(48);
		articles.add(new Book(1991, "Article A", authorGroup1, 5000L));
		articles.add(new Book(1992, "Article B", authorGroup2, 6000L));
		articles.add(new Book(1993, "Article C", authorGroup3, 7000L));
		articles.add(new Book(1994, "Article D", authorGroup4, 3000L));
		articles.add(new Book(1995, "Article E", authorGroup1, 1000L));
		articles.add(new Book(1996, "Article F", authorGroup1, 1500L));
		articles.add(new Book(1997, "Article I", authorGroup2, 800L));
		articles.add(new Book(1998, "Article J", authorGroup5, 5500L));
		articles.add(new Book(1999, "Article K", authorGroup6, 8956L));
		articles.add(new Book(2000, "Article L", authorGroup7, 9850L));
		articles.add(new Book(2001, "Article M", authorGroup2, 6000L));
		articles.add(new Book(2002, "Article N", authorGroup3, 1200L));
		articles.add(new Book(2003, "Article O", authorGroup4, 18999L));
		articles.add(new Book(2004, "Article P", authorGroup5, 999L));
		articles.add(new Book(2005, "Article Q", authorGroup6, 5999L));
		articles.add(new Book(2006, "Article R", authorGroup7, 6399L));
		articles.add(new Book(2007, "Article S", authorGroup1, 3200L));
		articles.add(new Book(2008, "Article T", authorGroup2, 2000L));
		articles.add(new Book(2009, "Article U", authorGroup3, 1000L));
		articles.add(new Book(2010, "Article V", authorGroup4, 4200L));
		articles.add(new Book(2011, "Article W", authorGroup5, 4599L));
		articles.add(new Book(2012, "Article X", authorGroup6, 6300L));
		articles.add(new Book(2013, "Article Y", authorGroup7, 3999L));
		articles.add(new Book(2014, "Article Z", authorGroup1, 1200L));
		articles.add(new Book(2015, "Article a", authorGroup2, 1500L));
		articles.add(new Book(2016, "Article b", authorGroup3, 1499L));
		articles.add(new Book(2017, "Article c", authorGroup4, 1399L));
		articles.add(new Book(2018, "Article d", authorGroup5, 1300L));
		articles.add(new Book(2019, "Article e", authorGroup6, 10000L));
		articles.add(new Book(2020, "Article f", authorGroup7, 12533L));
		articles.add(new Book(2021, "Article i", authorGroup1, 1999L));
		articles.add(new Book(2022, "Article j", authorGroup2, 999L));
		articles.add(new Book(2023, "Article k", authorGroup3, 899L));
		articles.add(new Book(2024, "Article l", authorGroup4, 7899L));
		articles.add(new Book(2025, "Article m", authorGroup5, 7599L));
		articles.add(new Book(2026, "Article n", authorGroup6, 6999L));
		articles.add(new Book(2027, "Article o", authorGroup7, 8888L));
		articles.add(new Book(2028, "Article p", authorGroup1, 8999L));
		articles.add(new Book(2029, "Article q", authorGroup2, 9999L));
		articles.add(new Book(2030, "Article r", authorGroup3, 11200L));
		articles.add(new Book(2031, "Article s", authorGroup4, 12566L));
		articles.add(new Book(2032, "Article t", authorGroup5, 12999L));
		articles.add(new Book(2033, "Article u", authorGroup6, 13000L));
		articles.add(new Book(2034, "Article v", authorGroup7, 999L));
		articles.add(new Book(2035, "Article w", authorGroup1, 1800L));
		articles.add(new Book(2036, "Article x", authorGroup2, 9800L));
		articles.add(new Book(2037, "Article y", authorGroup3, 8600L));
		articles.add(new Book(2038, "Article z", authorGroup4, 8599L));
		return articles;
	}
	
}
