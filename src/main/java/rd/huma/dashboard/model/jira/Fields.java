package rd.huma.dashboard.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "timespent", "project", "customfield_11000",
		"fixVersions", "aggregatetimespent", "aggregateprogress", "resolution",
		"customfield_10500", "customfield_10501", "customfield_10700",
		"customfield_10701", "customfield_10900", "resolutiondate",
		"customfield_10903", "customfield_10904", "customfield_10905",
		"customfield_10906", "customfield_10907", "workratio", "lastViewed",
		"watches", "priority", "labels", "timeestimate",
		"aggregatetimeoriginalestimate", "versions", "issuelinks", "updated",
		"components", "timeoriginalestimate", "customfield_10401",
		"customfield_10600", "customfield_10800", "customfield_10801",
		"aggregatetimeestimate", "customfield_10802", "customfield_10806",
		"customfield_10807", "customfield_10400", "environment", "progress",
		"votes","parent" })
public class Fields {
	private String summary;

	private Issuetype issuetype;

	private Status status;

	private Priority priority;

	private String customfield_10902;

	private String customfield_10810;

	private String created;

	private Assignee assignee;

	private String description;

	private Creator creator;

	private Subtasks[] subtasks;

	private Reporter reporter;

	private String duedate;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Issuetype getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(Issuetype issuetype) {
		this.issuetype = issuetype;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getCustomfield_10902() {
		return customfield_10902;
	}

	public void setCustomfield_10902(String customfield_10902) {
		this.customfield_10902 = customfield_10902;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}


	@JsonIgnore
	public String getSysAidTicket(){
		return customfield_10902;
	}

	public String getCustomfield_10810() {
		return customfield_10810;
	}

	public void setCustomfield_10810(String customfield_10810) {
		this.customfield_10810 = customfield_10810;
	}
	@JsonIgnore
	public String getBranch(){
		return customfield_10810;
	}

	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public Subtasks[] getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(Subtasks[] subtasks) {
		this.subtasks = subtasks;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}


}