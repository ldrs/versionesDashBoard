package rd.huma.dashboard.model.transaccional.dominio;

public enum EEstadoJobDespliegue {
	ESPERANDO_DEPLOY,
	EN_PROCESO_DEPLOY_JENKINS,
	DEPLOY_EXITOSO{
		@Override
		public EEstadoVersion getEstadoVersionRelativo() {
			return EEstadoVersion.DESPLIEGE_EXITOSO;
		}
	},
	FALLIDO_DEPLOY_JENKINS{
		@Override
		public EEstadoVersion getEstadoVersionRelativo() {
			return EEstadoVersion.CANCELADA_POR_ERROR_DESPLIEGUE_JENKINS;
		}

	},
	DEPLOY_JENKINS_EXITOSO{
		@Override
		public EEstadoVersion getEstadoVersionRelativo() {
			return EEstadoVersion.EJECUTO_EXITOSO_JENKINS;
		}
	}
	;

	public EEstadoVersion getEstadoVersionRelativo(){
		return null;
	}

}