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

versionesApp.factory("HistoricaFilas", function($resource) {
	return $resource("/dashboard/api/filaHistorica/versiones3Dias/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
    });
});

versionesApp.factory("Ambientes", function($resource) {
	return $resource("/dashboard/api/ambientes/:idAplicacion");
});



versionesApp.controller('appController', function($scope,Aplicaciones,Ambientes,HistoricaFilas,persistanceService) {
	var tituloAplicacion = "Configuracion Aplicaciones",tituloAmbiente = "Configuracion Aplicaciones";
	var app = this;

	app.aplicacion = queryString.aplicacion;
	app.ambiente = queryString.ambiente;
	app.modificado = {aplicaciones : false};
	app.configuraciones = {"tituloAplicaciones":tituloAplicacion,"tituloAmbientes":tituloAmbiente};
	app.cssControlesPrioridad = "none";
	app.cssControlesUndeploy = "none";


	app.containsElement = function(arr,value){
		for (var i=0; i < arr.length; i++){
			 if (arr[i] == value){
				 return true;
			 }
		}
		return false;
	}


	app.actualizarAparienciaPorPermisos=function(){
		tienePermisoControlesPrioridad = function(){
			return app.logeado &&  app.containsElement(app.usuario.prioridadAmbientes,app.ambienteId);
		};
		app.cssControlesPrioridad = tienePermisoControlesPrioridad()?"block":"none";

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

	app.actualizaFila=function(){
		if (!app.ambienteId){
			return;
		}
		HistoricaFilas.filas({idAmbiente:app.ambienteId}, function(data) {
			app.versionesFila = data;
		});

	}

	app.actualizarAmbiente=function(){
		if (!$scope.aplicacion.id){
			return;
		}

		Ambientes.query({idAplicacion:$scope.aplicacion.id}, function(data){
			app.ambientes = data;

			app.ambientes.forEach(function(o){
				o.href="restaurarVersion.html?aplicacion="+queryString.aplicacion+"&ambiente="+o.nombre;
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


	$scope.seleccionarAplicacion = function(o){
		seleccionAplicacion(o);
	}
})
;