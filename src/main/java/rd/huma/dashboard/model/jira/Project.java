package rd.huma.dashboard.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"avatarUrls"})
public class Project {
	private String id;

	private String name;

	private String self;
	private String key;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
