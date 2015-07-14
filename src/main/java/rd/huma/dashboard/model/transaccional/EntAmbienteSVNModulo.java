package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AMBIENTE_SVN_MODULO")
public class EntAmbienteSVNModulo extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -9048823529391233498L;

	@JoinColumn
	@ManyToOne
	private EntAmbienteSVN ambienteSVN;

	private String grupo;
	private String artefacto;
	private String paquete;
	private String clasificador;

	public EntAmbienteSVN getAmbienteSVN() {
		return ambienteSVN;
	}

	public void setAmbienteSVN(EntAmbienteSVN ambienteSVN) {
		this.ambienteSVN = ambienteSVN;
	}

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
	public String toString() {
		return new StringBuilder(350).append(grupo).append(':').append(artefacto).append(clasificador == null ? "": ':'+clasificador).append(paquete == null ? "" : ':'+paquete).toString();
	}
}