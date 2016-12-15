package eldrelore.intersticed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import eldrelore.intersticed.dto.IntersticableString;
import eldrelore.intersticed.dto.StringPair;

public class Interstition {

	/**
	 * get the longest string of characters that interstice (no duplicates next
	 * to each other)
	 * 
	 * @param input
	 * @return String
	 */
	public Set<String> getLongestIntersticedStrings(String input) {
		Map<String, IntersticableString> parsedString = parseInputString(input);
		/*
		 * for each set of potential interstitions; find out if they are, and if
		 * so set the length to the size of both
		 */
		Integer longestInterstitionLength = 0;
		Set<String> longestValues = new TreeSet<String>();
		for (String key : parsedString.keySet()) {
			IntersticableString firstString = parsedString.get(key);
			for (String pairedKey : parsedString.keySet()) {
				if (!key.equals(pairedKey)) {
					IntersticableString secondString = parsedString.get(pairedKey);
					StringPair stringPair = new StringPair(firstString, secondString);
					/*
					 * check each string pair, only evaluate if it can be the
					 * longest.
					 */
					if ((firstString.getLength() + secondString.getLength() >= longestInterstitionLength)
							&& stringPair.areIndicesIntersticed()) {
						/*
						 * if new longest value found, clear out and update
						 * longest value
						 */
						if (longestInterstitionLength < firstString.getIndexes().size()
								+ secondString.getIndexes().size()) {
							longestInterstitionLength = firstString.getIndexes().size()
									+ secondString.getIndexes().size();
							longestValues.clear();
						}
						longestValues.add(stringPair.intersticeStrings());
					}
				}
			}
		}
		return longestValues;
	}

	/**
	 * parse the input string into a map that contains the character, and the
	 * indices of that character.
	 * 
	 * @param input
	 * @return Map<String, IntersticableString>
	 */
	Map<String, IntersticableString> parseInputString(String input) {
		Map<String, IntersticableString> intersticedStrings = new HashMap<String, IntersticableString>();
		int index = 0;
		for (Character c : input.toCharArray()) {
			String character = String.valueOf(c);
			if (intersticedStrings.containsKey(character)) {
				IntersticableString currentString = intersticedStrings.get(character);
				currentString.getIndexes().add(index);
			} else {
				IntersticableString currentString = new IntersticableString();
				currentString.setContent(character);
				List<Integer> indices = new ArrayList<Integer>();
				indices.add(index);
				currentString.setIndexes(indices);
				intersticedStrings.put(character, currentString);
			}
			index++;
		}
		return intersticedStrings;
	}

}
