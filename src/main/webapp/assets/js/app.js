var tmpQueryString = location.search.slice(1).split('&');

var queryString = {};
tmpQueryString.forEach(function(pair) {
	pair = pair.split('=');
	queryString[pair[0]] = decodeURIComponent(pair[1] || '');
});

function toQueryString(page){
	return page+"?"+ $.param(queryString);
}


var versionesApp = angular.module('versionesApp',['ngResource']);


versionesApp.factory("Aplicaciones", function($resource) {
	return $resource("/dashboard/api/aplicaciones");
});

versionesApp.factory("VersionesFilas", function($resource) {
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente");
});

versionesApp.factory("Ambientes", function($resource) {
	return $resource("/dashboard/api/ambientes/:idAplicacion");
});

versionesApp.controller('appController', function($scope,Aplicaciones,Ambientes,VersionesFilas) {
	var tituloAplicacion = "Configuracion Aplicaciones",tituloAmbiente = "Configuracion Aplicaciones";
	var app = this;


	app.aplicacion = queryString.aplicacion;
	app.ambiente = queryString.ambiente;
	app.modificado = {aplicaciones : false};
	app.configuraciones = {"tituloAplicaciones":tituloAplicacion,"tituloAmbientes":tituloAmbiente};


	
	app.actualizaFila=function(){
		VersionesFilas.query({idAmbiente:app.ambienteId}, function(data) {
			app.versionesFila = data;
		});

	}

	app.actualizarAmbiente=function(){
		Ambientes.query({idAplicacion:$scope.aplicacion.id}, function(data){
			app.ambientes = data;
		
			app.ambientes.forEach(function(o){
				o.href="app.html?aplicacion="+queryString.aplicacion+"&ambiente="+o.nombre;
				if (! app.ambiente){
					app.ambiente = o.nombre;
					app.ambienteId=o.id;
				}
				if (o.nombre===app.ambiente){
					o.css="current-page-item";
					o.activo=true;
					o.ambienteSeleccionado=o;
				}
			})
			
			
			
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


	

	app.servidores = [
	                  {nombre:'172.16.7.30:7101', basedatos:"MHDDEV01", version : "10.11278.7", "estado" : "no_disponible","tickets" : ["2540","3050"], "jiras":["SGF-150","SGF-1551","SGF-750"], "deploy":"block"},
	                  {nombre:'172.16.7.30:8888', basedatos:"MHDDEV02", version : "10.1128.7", "estado" : "no_disponible","tickets" : ["30"], "jiras":["SGF-151"], "display":"block"},
	                  {nombre:'172.16.7.21:7777', basedatos:"MHDDEV03", version : "NONE", "estado" : "disponible", "deploy":"none"},
	                  ];






	muevePrioridad=function(v,d,limit){
		if (v.prioridad==limit){
			return;
		}

		var cacheVersiones={};
		for (i = 0; i < app.versionesFila.length; i++) {
			var tmp = app.versionesFila[i];
			cacheVersiones[tmp.prioridad]=tmp;
		}
		v2 =cacheVersiones[v.prioridad+d];
		if (v2){
			v2.prioridad=v.prioridad;
			v.prioridad+=d;
		}

	}


	$scope.upVersion = function(v){
		muevePrioridad(v,1,1);
	}

	$scope.downVersion = function(v){
		muevePrioridad(v,-1,app.versionesFila.length);
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