package eldrelore.intersticed;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import eldrelore.intersticed.dto.IntersticableString;
import eldrelore.intersticed.dto.StringPair;
import junit.framework.TestCase;

public class TestInterstition extends TestCase {

	private static final String TEST_STRING = "agbcbgcab";

	@Test
	public void testGetLongestInterstition() {
		Interstition interstition = new Interstition();
		Set<String> longestInterstitions = interstition.getLongestIntersticedStrings(TEST_STRING);
		assertTrue(1 == longestInterstitions.size());
		boolean foundKnown = false;
		for (String longestInterstition : longestInterstitions) {
			if ("bcbcb".equals(longestInterstition))
				foundKnown = true;
		}
		assertTrue(foundKnown);
	}

	@Test
	public void testIndices() {
		Interstition interstition = new Interstition();
		Map<String, IntersticableString> strings = interstition.parseInputString(TEST_STRING);
		IntersticableString a = strings.get("a");
		assertTrue(2 == a.getIndexes().size());
		assertTrue(0 == a.getIndexes().get(0));
		assertTrue(7 == a.getIndexes().get(1));
	}

	private IntersticableString getIntersticableString(Integer[] index) {
		IntersticableString string = new IntersticableString();
		List<Integer> list = Arrays.asList(index);
		string.setIndexes(list);
		return string;
	}

	private void checkInterstition(Integer[] first, Integer[] second, boolean result) {
		IntersticableString firstString = getIntersticableString(first);
		IntersticableString secondString = getIntersticableString(second);
		StringPair stringPair = new StringPair(firstString, secondString);
		assertTrue(result == stringPair.areIndicesIntersticed());
	}

	@Test
	public void testIntersticed() {
		Integer[] firstIntegers = { new Integer(0), new Integer(2), new Integer(4) };
		Integer[] secondIntegers = { new Integer(1), new Integer(3), new Integer(5) };
		checkInterstition(firstIntegers, secondIntegers, true);
	}

	@Test
	public void testNotIntersticedLength() {
		Integer[] firstIntegers = { new Integer(0), new Integer(2), new Integer(4) };
		Integer[] secondIntegers = { new Integer(1) };
		checkInterstition(firstIntegers, secondIntegers, false);
	}

	@Test
	public void testNotIntersticedMid() {
		Integer[] firstIntegers = { new Integer(0), new Integer(3), new Integer(4) };
		Integer[] secondIntegers = { new Integer(1), new Integer(2), new Integer(5) };

		checkInterstition(firstIntegers, secondIntegers, false);
	}

	@Test
	public void testNotIntersticedEnd() {
		Integer[] firstIntegers = { new Integer(0), new Integer(2), new Integer(5) };
		Integer[] secondIntegers = { new Integer(1), new Integer(3), new Integer(4) };

		checkInterstition(firstIntegers, secondIntegers, false);
	}

	@Test
	public void testNotIntersticedGreaterLonger() {
		Integer[] firstIntegers = { new Integer(0), new Integer(2) };
		Integer[] secondIntegers = { new Integer(1), new Integer(3), new Integer(5) };

		checkInterstition(firstIntegers, secondIntegers, false);
	}

	@Test
	public void testNotIntersticed() {
		Integer[] firstIntegers = { new Integer(0), new Integer(2), new Integer(3) };
		Integer[] secondIntegers = { new Integer(1), new Integer(4) };
		checkInterstition(firstIntegers, secondIntegers, false);
	}

	@Test
	public void testNotIntersticedEmpty() {
		Integer[] firstIntegers = {};
		Integer[] secondIntegers = {};
		boolean exceptionFound = false;
		try {
			checkInterstition(firstIntegers, secondIntegers, false);
		} catch (Exception e) {
			exceptionFound = true;
		}
		assertTrue(exceptionFound);
	}

	@Test
	public void testIntersticeStrings() {
		Integer[] firstIntegers = { new Integer(0), new Integer(2), new Integer(4) };
		List<Integer> firstIndex = Arrays.asList(firstIntegers);
		Integer[] secondIntegers = { new Integer(1), new Integer(3), new Integer(5) };
		List<Integer> secondIndex = Arrays.asList(secondIntegers);

		IntersticableString firstString = new IntersticableString();
		firstString.setIndexes(firstIndex);
		firstString.setContent("a");

		IntersticableString secondString = new IntersticableString();
		secondString.setIndexes(secondIndex);
		secondString.setContent("b");

		StringPair stringPair = new StringPair(firstString, secondString);

		String intersticedStrings = stringPair.intersticeStrings();
		assertTrue("ababab".equals(intersticedStrings));
	}
}
