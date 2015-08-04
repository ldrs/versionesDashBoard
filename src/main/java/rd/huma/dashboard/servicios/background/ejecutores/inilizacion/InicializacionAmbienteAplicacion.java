package rd.huma.dashboard.servicios.background.ejecutores.inilizacion;

import java.util.Optional;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.servicios.transaccional.ServicioAmbiente;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioGrupo;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;


class InicializacionAmbienteAplicacion {
	private ServicioAmbiente servicioAmbiente = ServicioAmbiente.getInstanciaTransaccional();
	private ServicioAplicacion servicioAplicacion = ServicioAplicacion.getInstanciaTransaccional();
	private ServicioServidor servicioServidor = ServicioServidor.getInstanciaTransaccional();
	private ServicioRepositorioDatos servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
	private ServicioFila servicioFila = ServicioFila.getInstanciaTransaccional();
	private ServicioGrupo servicioGrupo =  ServicioGrupo.getInstanciaTransaccional();

	public void inicializar() {
		servicioGrupo.nuevoGrupo("Desarrollo");

		EntAplicacion sigef = servicioAplicacion.configurarCrearAplicacion("sigef", "SGF", "sigef", 1, "versionPDS,ensambladores.version","DespliegueAutomaticoSigef","http://172.16.7.35:9880/svn/ambientes/sigef/");


		EntAmbiente desarrollo = servicioAmbiente.nuevoAmbiente("Desarrollo", 1);
		EntAmbiente testing = servicioAmbiente.nuevoAmbiente("Testing", 2);
		EntAmbiente preproduccion = servicioAmbiente.nuevoAmbiente("Preproduccion", 3);
		EntAmbiente helpdesk = servicioAmbiente.nuevoAmbiente("Helpdesk", 4);
		EntAmbiente produccion = servicioAmbiente.nuevoAmbiente("Produccion", 5);

		crearAmbienteAplicacion(sigef, desarrollo,testing,preproduccion,helpdesk,produccion);
	}

	private void crearAmbienteAplicacion(EntAplicacion aplicacion, EntAmbiente...ambientes){
		for (EntAmbiente ambiente : ambientes) {
			EntAmbienteAplicacion ambienteAplicacion = servicioAmbiente.nuevoAmbienteAplicacion(ambiente, aplicacion);
			if (ambiente.getOrden()==1 && aplicacion.getOrden()==1){ //Pruebas Desarrollo
				creaServidoresAplicacionSigefDesarrollo(ambienteAplicacion);
				EntFilaDeployement fila = servicioFila.nuevaFila(ambienteAplicacion, "Abierto,Creado,En curso,Nuevo","En Desarrollo,Abierto");
				fila.setPermiteSinTicketSysAid(true);
				servicioFila.actualizarEntidad(fila);
			}else{
				servicioFila.nuevaFila(ambienteAplicacion, "Verificado","En Testing");
			}
		}
	}

	private void creaServidoresAplicacionSigefDesarrollo(EntAmbienteAplicacion ambienteAplicacion) {
//		servicioServidor.nuevoServidor(ambienteAplicacion, servicioRepositorioDatos.nuevoRepositorio("MHTST01", "SIGEF_PRD", Optional.empty()), "172.16.7.30:7101", "http://172.16.7.30:7101/sigef/principal.jsp","730-7101");
//		servicioServidor.nuevoServidor(ambienteAplicacion, servicioRepositorioDatos.nuevoRepositorio("MHTST02", "SIGEF_PRD", Optional.empty()), "172.16.7.30:8888", "http://172.16.7.30:8888/sigef/principal.jsp","730-8888");
//		servicioServidor.nuevoServidor(ambienteAplicacion, servicioRepositorioDatos.nuevoRepositorio("MHTST02", "SIGEF_PRD6", Optional.empty()), "172.16.7.21:7777", "http://172.16.7.21:7777/sigef/principal.jsp","721-7777");
//		servicioServidor.nuevoServidor(ambienteAplicacion, servicioRepositorioDatos.nuevoRepositorio("MHTST02", "SIGEF_PRD4", Optional.empty()), "172.16.7.21:8888", "http://172.16.7.21:8888/sigef/principal.jsp","721-8888");
		servicioServidor.nuevoServidor(ambienteAplicacion, servicioRepositorioDatos.nuevoRepositorio("MHTST04", "SIGEF_PRD6", Optional.empty()), "172.16.7.53:8080", "http://172.16.7.53:8080/sigef/principal.jsp","753-8080");
	}
}