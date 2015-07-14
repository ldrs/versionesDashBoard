package rd.huma.dashboard.model.maven;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Modules {
	private String[] module;

	public String[] getModule() {
		return module;
	}

	public void setModule(String[] module) {
		this.module = module;
	}
}
