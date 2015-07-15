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
}
