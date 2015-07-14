package rd.huma.dashboard.model.maven;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Dependencies {
	private Dependency[] dependency;

	public Dependency[] getDependency() {
		return dependency;
	}

	public void setDependency(Dependency[] dependency) {
		this.dependency = dependency;
	}
}
