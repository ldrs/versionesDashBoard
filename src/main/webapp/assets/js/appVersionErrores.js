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

versionesApp.factory("HistoricaFilas", function($resource) {
	return $resource("/dashboard/api/filaHistorica/versiones3DiasError/:aplicacion",null,{
		'filas':{ 'method':'GET','isArray':true},
    });
});


versionesApp.controller('appController', function($scope,Aplicaciones,HistoricaFilas) {
	var app = this;

	app.aplicacion = queryString.aplicacion;
	app.modificado = {aplicaciones : false};

	app.actualizaFila=function(){

		HistoricaFilas.filas({aplicacion:app.aplicacion}, function(data) {
			app.versionesFila = data;
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

		app.actualizaFila();
	});


	$scope.seleccionarAplicacion = function(o){
		seleccionAplicacion(o);
	}
})
;