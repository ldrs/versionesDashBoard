package rd.huma.dashboard.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Histories {

	private String id;

	private Autor author;

	private String created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Autor getAuthor() {
		return author;
	}

	public void setAuthor(Autor author) {
		this.author = author;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}


}
