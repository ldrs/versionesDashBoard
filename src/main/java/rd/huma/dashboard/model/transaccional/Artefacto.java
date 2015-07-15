package rd.huma.dashboard.model.transaccional;

import javax.persistence.Embeddable;

@Embeddable
public class Artefacto implements Comparable<Artefacto> {

	private String grupo;
	private String artefacto;
	private String paquete;
	private String clasificador;
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getArtefacto() {
		return artefacto;
	}
	public void setArtefacto(String artefacto) {
		this.artefacto = artefacto;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getClasificador() {
		return clasificador;
	}
	public void setClasificador(String clasificador) {
		this.clasificador = clasificador;
	}
	
	@Override
	public int compareTo(Artefacto o) {
		return 	grupo.compareTo(o.grupo) + 
				artefacto.compareTo(o.artefacto) + 
				paquete.compareTo(o.paquete) +
				comparaClasificador(o.clasificador)
				;
	}
	
	private int comparaClasificador(String other){
		
		if (clasificador == null && other == null){
			return 0;
		}
		if (clasificador==null){
			return 1;
		}
		if (other == null){
			return -1;
		}
		return clasificador.compareTo(other);
	}
	
	@Override
	public String toString() {
		return new StringBuilder(350).append(grupo).append(':').append(artefacto).append(clasificador == null ? "": ':'+clasificador).append(paquete == null ? "" : ':'+paquete).toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artefacto == null) ? 0 : artefacto.hashCode());
		result = prime * result
				+ ((clasificador == null) ? 0 : clasificador.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((paquete == null) ? 0 : paquete.hashCode());
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
		if (!(obj instanceof Artefacto)) {
			return false;
		}
		Artefacto other = (Artefacto) obj;
		if (artefacto == null) {
			if (other.artefacto != null) {
				return false;
			}
		} else if (!artefacto.equals(other.artefacto)) {
			return false;
		}
		if (clasificador == null) {
			if (other.clasificador != null) {
				return false;
			}
		} else if (!clasificador.equals(other.clasificador)) {
			return false;
		}
		if (grupo == null) {
			if (other.grupo != null) {
				return false;
			}
		} else if (!grupo.equals(other.grupo)) {
			return false;
		}
		if (paquete == null) {
			if (other.paquete != null) {
				return false;
			}
		} else if (!paquete.equals(other.paquete)) {
			return false;
		}
		return true;
	}
	
	
}
