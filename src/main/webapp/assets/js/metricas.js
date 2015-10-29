var metricasApp = angular.module('metricasApp',['ngResource']);

metricasApp.factory("MetricasApi", function($resource) {
	return $resource("/dashboard/api/metrica/reportesScript",null,{
		'reportes':{ 'method':'GET','isArray':true},
    });
});

metricasApp.controller('metricasController', function($scope,MetricasApi) {

	var pControl = this;

	pControl.reportes = {};

	pControl.actualizar = function(){

		MetricasApi.reportes().$promise.then(function(data){
			pControl.reportes = data;
		});

	};

	$scope.dibuja = function(reporte){
		dibujaReporte("/dashboard/api/metrica/reporteScript/"+reporte.id,"containerReporte",reporte.nombre,"por Mes")
	}

	pControl.actualizar();
});