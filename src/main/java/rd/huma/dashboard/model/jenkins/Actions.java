package rd.huma.dashboard.model.jenkins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("causes")
public class Actions {
	private Parameters[] parameters;

	public Parameters[] getParameters() {
		return parameters;
	}

	public void setParameters(Parameters[] parameters) {
		this.parameters = parameters;
	}


}