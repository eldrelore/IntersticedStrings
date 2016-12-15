package eldrelore.intersticed.dto;

import java.util.List;

public class StringPair {

	private IntersticableString lesser = null;
	private IntersticableString greater = null;

	public StringPair(IntersticableString firstString, IntersticableString secondString) {
		if (firstString.getIndexes().isEmpty() || secondString.getIndexes().isEmpty()) {
			throw new RuntimeException("No indicies in StringPair");
		} else {
			if (firstString.getIndexes().get(0) < secondString.getIndexes().get(0)) {
				lesser = firstString;
				greater = secondString;
			} else {
				greater = firstString;
				lesser = secondString;
			}
		}
	}

	public IntersticableString getLesser() {
		return lesser;
	}

	public IntersticableString getGreater() {
		return greater;
	}

	/**
	 * Stitch the strings together
	 * 
	 * @param firstString
	 * @param secondString
	 * @return String
	 */
	public String intersticeStrings() {
		int counter = 0;
		StringBuilder builder = new StringBuilder();
		for (counter = 0; counter < greater.getIndexes().size(); counter++) {
			builder.append(lesser.getContent()).append(greater.getContent());
		}
		if (lesser.getIndexes().size() > greater.getIndexes().size()) {
			builder.append(lesser.getContent());
		}
		return builder.toString();
	}

	/**
	 * are these indices intersticeable
	 * 
	 * @param firstIndex
	 * @param secondIndex
	 * @return boolean
	 */
	public boolean areIndicesIntersticed() {
		boolean intersticed = true;

		/*
		 * quick checks: both non-empty, both within 1 of same length
		 * 
		 * If firstIndex[0] < secondIndex[0], all same indices of firstIndex
		 * must be < all same indices of secondIndex (same with >)
		 *
		 * 
		 * if firstIndex[n] < secondIndex[n], and there is no firstIndex[n+1]
		 * and there is a secondIndex[n+1], not still intersticed.
		 * 
		 * if firstIndex[n] > secondIndex[n], and there is no firstIndex[n+1]
		 * and there is a secondIndex[n+1], still intersticed.
		 */
		List<Integer> lesserIndices = lesser.getIndexes();
		List<Integer> greaterIndices = greater.getIndexes();
		if (lesserIndices.isEmpty() || greaterIndices.isEmpty()) {
			intersticed = false;
		} else if (Math.abs(lesser.getIndexes().size() - greater.getIndexes().size()) > 1) {
			intersticed = false;
		} else {

			/*
			 * lesser always starts lower, and is the only one that can have
			 * more.
			 */
			if (lesserIndices.size() < greaterIndices.size()) {
				intersticed = false;
			} else {
				int counter = 0;
				Integer lastGreaterIndex = 0;
				/* check all the regular values */
				for (counter = 0; counter < greaterIndices.size(); counter++) {
					if (counter < greaterIndices.size()) {
						Integer lesserIndex = lesserIndices.get(counter);
						Integer greaterIndex = greaterIndices.get(counter);
						lastGreaterIndex = greaterIndex;
						intersticed = (lesserIndex < greaterIndex);
					}
					if (!intersticed) {
						break;
					}
				}
				/* check trailing value on the set that starts lower. */
				if (counter < lesserIndices.size()) {
					if (lastGreaterIndex > lesserIndices.get(counter)) {
						intersticed = false;
					}
				}
			}
		}
		return intersticed;
	}
}
