package rd.huma.dashboard.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChangeLog {

	private Histories[] histories;

	public Histories[] getHistories() {
		return histories;
	}

	public void setHistories(Histories[] histories) {
		this.histories = histories;
	}
}
