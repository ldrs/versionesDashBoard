package rd.huma.dashboard.model.maven;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class PomModel {
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}