package rd.huma.dashboard.model.jenkins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"keepLog", "executor","mavenArtifacts","mavenVersionUsed","builtOn","artifacts","timestamp","id","fullDisplayName","culprits","changeSet","description","causes"})
public class JenkinsJobStatus {

	private String result;

	private String building;

	private String number;

	private String duration;

	private String estimatedDuration;

	private Actions[] actions;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(String estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public Actions[] getActions() {
		return actions;
	}

	public void setActions(Actions[] actions) {
		this.actions = actions;
	}




}