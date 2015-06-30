package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

@Path("/configuracionGeneral")
public class WSConfiguracionGeneral {

	@Inject @Servicio
	private ServicioConfiguracionGeneral servicioConfiguracionGeneral;


	@GET
	public String configurar(
							@QueryParam("rutaSvn") String rutaSvn,
							@QueryParam("usrSvn") String usrSvn,
							@QueryParam("pwdSvn")  String pwdSvn,
							@QueryParam("rutaJira") String rutaJira,
							@QueryParam("usrJira") String usrJira,
							@QueryParam("pwdJira") String pwdJira,
							@QueryParam("rutaJenkins") String rutaJenkins,
							@QueryParam("usrJenkins") String usrJenkins,
							@QueryParam("pwdJenkins") String pwdJenkins,
							@QueryParam("rutaSysAid") String rutaSysAid,
							@QueryParam("usrSysaid") String usrSysaid,
							@QueryParam("pwdSysAid") String pwdSysAid
							) {

		return servicioConfiguracionGeneral.configurar(rutaSvn, usrSvn, pwdSvn, rutaJira, usrJira, pwdJira, rutaJenkins, usrJenkins, pwdJenkins, rutaSysAid, usrSysaid, pwdSysAid).getId();

	}
}