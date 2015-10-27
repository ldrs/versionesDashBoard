package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.dato.AreaPorTicket;

@Stateless
@Servicio
public class ServicioArea {


	@Inject
	private EntityManager entityManager;

	public List<AreaPorTicket> ticketsPorArea(){
		return entityManager.createQuery(getQueryTicketsPorArea(),AreaPorTicket.class).getResultList();
	}

	public static ServicioArea getInstanciaTransaccional(){
		Servicio servicio = ServicioArea.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioArea.class, servicio).get();
	}


	private static final String getQueryTicketsPorArea(){
		return "SELECT new rd.huma.dashboard.model.transaccional.dato.AreaPorTicket(A.nombre,A.orden,T.numero) from EntTicketSysAid T, EntTicketEstadoArea TS join TS.area A join TS.estado E where E.codigo = T.estado";
	}

}