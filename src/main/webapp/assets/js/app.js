var tmpQueryString = location.search.slice(1).split('&');

var queryString = {};
tmpQueryString.forEach(function(pair) {
	pair = pair.split('=');
	queryString[pair[0]] = decodeURIComponent(pair[1] || '');
});

function toQueryString(page){
	return page+"?"+ $.param(queryString);
}

var versionesApp = angular.module('versionesApp',['ngResource','appDbServices','angucomplete-alt']);


versionesApp.factory("Aplicaciones", function($resource) {
	return $resource("/dashboard/api/aplicaciones");
});

versionesApp.factory("VersionesFilas", function($resource) {
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
        'sube': { 'method':'GET','url':'/dashboard/api/filasPrioridad/sube/:idFila'},
        'baja': { 'method':'GET','url':'/dashboard/api/filasPrioridad/baja/:idFila'},
        'elimina': { 'method':'GET','url':'/dashboard/api/filasPrioridad/elimina/:idFila'},
        'versionesInfo' : { 'method':'GET','url':'/dashboard/api/versionesInfo'}
    });
});

versionesApp.factory("Servidores", function($resource) {
	return $resource("/dashboard/api/servidores/ambiente/:idAmbiente",null,{
		'delAmbiente':{ 'method':'GET','isArray':true},
		'undeploy':{'method':'GET','url':'/dashboard/api/servidores/undeploy/:idServidor/:idUsuario'}
	});
});

versionesApp.factory("Ambientes", function($resource) {
	return $resource("/dashboard/api/ambientes/:idAplicacion");
});



versionesApp.factory("SeguridadApi", function($resource) { //TODO hacer esto...
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
        'sube': { 'method':'GET','url':'/dashboard/api/filasPrioridad/sube/:idFila'},
        'baja': { 'method':'GET','url':'/dashboard/api/filasPrioridad/baja/:idFila'},
        'elimina': { 'method':'GET','url':'/dashboard/api/filasPrioridad/elimina/:idFila'}
    });
});



versionesApp.controller('appController', function($scope,Aplicaciones,Ambientes,VersionesFilas,Servidores,SeguridadApi,persistanceService) {
	var tituloAplicacion = "Configuracion Aplicaciones",tituloAmbiente = "Configuracion Aplicaciones";
	var app = this;

	app.aplicacion = queryString.aplicacion;
	app.ambiente = queryString.ambiente;
	app.modificado = {aplicaciones : false};
	app.configuraciones = {"tituloAplicaciones":tituloAplicacion,"tituloAmbientes":tituloAmbiente};
	app.cssControlesPrioridad = "none";
	app.cssControlesUndeploy = "none";
	app.cssActualizando = "vacio";
	app.versionesProcesando = 0;
	app.versionesFallidas = 0;



	app.containsElement = function(arr,value){
		for (var i=0; i < arr.length; i++){
			 if (arr[i] == value){
				 return true;
			 }
		}
		return false;
	}

	app.cambiaCSSServidores =function(servidores){
		for (var j=0; j<servidores.length; j++){
			var s=servidores[j], dueno = false, responsable = false;
			for (var i=0; i<s.ambienteDuenos.length; i++){
				if (app.usuario && s.ambienteDuenos[i]==app.usuario.id){
					dueno = true;
					break;
				}
			}
			for (var i=0; i<s.ambienteResponsables.length; i++){
				if (app.usuario && s.ambienteResponsables[i]==app.usuario.id){
					responsable = true;
					break;
				}
			}

			s.css = app.logeado && s.estado.indexOf("OCUPADO")!=-1 &&  (dueno || responsable ||  app.containsElement(s.version.duenos,app.usuario.id)) ? "block":"none" ;
		}
	}


	app.actualizarAparienciaPorPermisos=function(){
		tienePermisoControlesPrioridad = function(){
			return app.logeado &&  app.containsElement(app.usuario.prioridadAmbientes,app.ambienteId);
		};
		app.cssControlesPrioridad = tienePermisoControlesPrioridad()?"block":"none";
		if (app.servidores){
			app.cambiaCSSServidores(app.servidores);
		}
	}

	persistanceService.init().then(function(){

		persistanceService.getUsuario().then(function(usuario){
			if (usuario){
				app.logeado = true;
				app.usuario = usuario;
				$(".inicioSesion").find("a").text(usuario.nombre).attr('href','#').click(function(){
					persistanceService.logout().then(function(){
						app.logeado = false;
						$(".inicioSesion").find("a").text("Iniciar Sesion").attr('href','inicioSesion.html').click(function(){});
					});
				});
			}else{
				app.logeado = false;
			}
			app.actualizarAparienciaPorPermisos();
		});
	});

	app.actualizaInfoVersiones=function(){
		if (!app.ambienteId){
			return;
		}

		VersionesFilas.versionesInfo({},function(data){
			app.versionesProcesando = data.procesandoDato;
			app.versionesFallidas = data.conError;
		});
	}

	app.actualizaFila=function(){
		if (!app.ambienteId){
			return;
		}
		VersionesFilas.filas({idAmbiente:app.ambienteId}, function(data) {
			app.versionesFila = data;
			app.cssActualizando = "vacio";
			app.actualizaInfoVersiones();
		});

	}

	app.actualizaServidores=function(){
		if (!app.ambienteId){
			return;
		}
		Servidores.delAmbiente({idAmbiente:app.ambienteId}, function(data) {
			app.servidores = data;
			app.cambiaCSSServidores(app.servidores);
		});

	}

	app.actualizarAmbiente=function(){
		if (!$scope.aplicacion.id){
			return;
		}

		Ambientes.query({idAplicacion:$scope.aplicacion.id}, function(data){
			app.ambientes = data;

			app.ambientes.forEach(function(o){
				o.href="app.html?aplicacion="+app.aplicacion+"&ambiente="+o.nombre;
				if (! app.ambiente){
					app.ambiente = o.nombre;
				}
				if (o.nombre===app.ambiente){
					o.css="current-page-item";
					o.activo=true;
					o.ambienteSeleccionado=o;
					app.ambienteId=o.id;
				}
			});

			var seleccionAmbiente = function(s){
				app.ambienteSeleccionado = s;
				app.ambientes.forEach(function(o){
					o.css="";
					o.activo=false;
				});
				$scope.ambiente = s;
				app.ambiente=s.nombre;
				app.ambienteId=s.id;
				s.css="current-page-item";
			};
			app.actualizaFila();
			app.actualizaServidores();
		});
	}

	Aplicaciones.query(function(data){
		app.aplicaciones = data;

		app.aplicaciones.forEach(function(o) {
			if (!app.aplicacion) {
				app.aplicacion = o.nombre;
			}

			if (o.nombre === app.aplicacion) {
				o.css = "current-page-item";
				o.activo = true;
				app.aplicacionSeleccionado = o;
			}
		});


		$scope.aplicacion = app.aplicacionSeleccionado;

		var seleccionAplicacion = function(s){
			app.aplicacionSeleccionado = s;
			app.aplicaciones.forEach(function(o){
				o.css="";
				o.activo=false;
			});
			$scope.aplicacion = s;
			app.aplicacion=s.nombre;
			s.css="current-page-item";
		};

		app.actualizarAmbiente();

	});


	$scope.upVersion = function(v){
		VersionesFilas.sube({"idFila":v.id}).$promise.then(function(data){
			app.actualizaFila();
		});
	}

	$scope.downVersion = function(v){
		VersionesFilas.baja({"idFila":v.id}).$promise.then(function(data){
			app.actualizaFila();
		});
	}

	$scope.cancelarVersionDialog = function(v){
		$("#dig"+v.id).css("display","block");
	}

	$scope.cerrarVersionDialog = function(v){
		$("#dig"+v.id).css("display","none");
	}

	$scope.eliminaVersion = function(v){
		VersionesFilas.elimina({"idFila":v.id}).$promise.then(function(data){
			app.actualizaFila();
		});
		$scope.cerrarVersionDialog(v);
	}

	$scope.undeploy=function(s){
		Servidores.undeploy({'idServidor':s.id,'idUsuario':app.usuario.id}).$promise.then(function(data){
			app.actualizaServidores();
		});
	}

	$scope.seleccionVersionAut=function(s){
		 window.location.assign("http://dashboard.version.sigef.gov.do/dashboard/version.html?versionId="+s.originalObject.id);
	}

	app.actualizar = function(){
		app.actualizaFila();
		app.actualizaServidores();
	}


	window.setTimeout(function(){
		app.cssActualizando = "waitIcon";
		app.actualizar();
	}, 30000);



	$scope.adicionarAplicacion = function(){
		var o = {nombre:'?', css : "", "jira":"?", "svn":"?","id":"?"};

		seleccionAplicacion(o);
		app.aplicaciones.push(o);
		app.modificado.aplicaciones = true;
		app.configuraciones.tituloAplicaciones="*"+tituloAplicacion;
		$("input[name='aplicacion']" ).focus();
		$("#guardarAplicaciones").css("display","block");
	}

	$scope.adicionarAmbiente = function(){

		var o = {nombre:'?', css : "", "orden":"?","id":"?"};

		seleccionAmbiente(o);
		app.ambientes.push(o);
		app.modificado.ambientes = true;
		app.configuraciones.tituloAplicaciones="*"+tituloAplicacion;
		$("input[name='nombreAmbiente']" ).focus();
		$("#guardarAmbientes").css("display","block");
	}

	$scope.seleccionarAplicacion = function(o){
		seleccionAplicacion(o);
	}

	$scope.guardarAplicaciones = function(){
		app.configuraciones.tituloAplicaciones=tituloAplicacion;
		app.modificado.aplicaciones = false;
		$("#guardarAplicaciones").css("display","none");
	}

})
;

