var tmpQueryString = location.search.slice(1).split('&');

var queryString = {};
tmpQueryString.forEach(function(pair) {
	pair = pair.split('=');
	queryString[pair[0]] = decodeURIComponent(pair[1] || '');
});

function toQueryString(page){
	return page+"?"+ $.param(queryString);
}


var versionesApp = angular.module('versionesApp',['ngResource','appDbServices']);


versionesApp.factory("Aplicaciones", function($resource) {
	return $resource("/dashboard/api/aplicaciones");
});

versionesApp.factory("VersionesFilas", function($resource) {
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
        'sube': { 'method':'GET','url':'/dashboard/api/filasPrioridad/sube/:idFila'},
        'baja': { 'method':'GET','url':'/dashboard/api/filasPrioridad/baja/:idFila'},
        'elimina': { 'method':'GET','url':'/dashboard/api/filasPrioridad/elimina/:idFila'}
    });
});

versionesApp.factory("Servidores", function($resource) {
	return $resource("/dashboard/api/servidores/:idAmbiente",null,{
		'delAmbiente':{ 'method':'GET','isArray':true},
		'undeploy':{'method':'GET','URL':'/dashboard/api/servidores/undeploy/:idServidor'}
	});
});

versionesApp.factory("Ambientes", function($resource) {
	return $resource("/dashboard/api/ambientes/:idAplicacion");
});



seguridadApp.factory("SeguridadApi", function($resource) { //TODO hacer esto...
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

	persistanceService.getUsuario().then(function(usuario){
		if (usuario){
			app.logeado = true;
			app.usuario = usuario;
		}else{
			app.logeado = false;
			
		}
	});
	
	app.actualizaFila=function(){
		if (!app.ambienteId){
			return;
		}
		VersionesFilas.filas({idAmbiente:app.ambienteId}, function(data) {
			app.versionesFila = data;
		});

	}

	app.actualizaServidores=function(){
		if (!app.ambienteId){
			return;
		}
		Servidores.delAmbiente({idAmbiente:app.ambienteId}, function(data) {
			app.servidores = data;
		});

	}

	app.actualizarAmbiente=function(){
		if (!$scope.aplicacion.id){
			return;
		}

		Ambientes.query({idAplicacion:$scope.aplicacion.id}, function(data){
			app.ambientes = data;

			app.ambientes.forEach(function(o){
				o.href="app.html?aplicacion="+queryString.aplicacion+"&ambiente="+o.nombre;
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
		Servidores.undeploy({'idServidor':s.id}).$promise.then(function(data){
			app.actualizaServidores();
		});
	}

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

