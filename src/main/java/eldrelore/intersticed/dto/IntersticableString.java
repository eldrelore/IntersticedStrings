package eldrelore.intersticed.dto;

import java.util.List;

public class IntersticableString {
	private String content;
	private List<Integer> indexes;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Integer> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<Integer> indexes) {
		this.indexes = indexes;
	}

	public int getLength() {
		return content.length();
	}
}
