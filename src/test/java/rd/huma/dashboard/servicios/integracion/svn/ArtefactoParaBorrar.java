package rd.huma.dashboard.servicios.integracion.svn;

public class ArtefactoParaBorrar{
	private String grupo;
	private String artefacto;

	public ArtefactoParaBorrar(String grupo, String artefacto) {
		this.grupo = grupo;
		this.artefacto = artefacto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artefacto == null) ? 0 : artefacto.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ArtefactoParaBorrar)) {
			return false;
		}
		ArtefactoParaBorrar other = (ArtefactoParaBorrar) obj;
		if (artefacto == null) {
			if (other.artefacto != null) {
				return false;
			}
		} else if (!artefacto.equals(other.artefacto)) {
			return false;
		}
		if (grupo == null) {
			if (other.grupo != null) {
				return false;
			}
		} else if (!grupo.equals(other.grupo)) {
			return false;
		}
		return true;
	}

	public String getGrupo() {
		return grupo;
	}
	public String getArtefacto() {
		return artefacto;
	}

}