var personasApp = angular.module('personasApp',['ngResource']);

personasApp.factory("PersonasApi", function($resource) {//TODO hacer el api web y su parte
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
        'sube': { 'method':'GET','url':'/dashboard/api/filasPrioridad/sube/:idFila'},
        'baja': { 'method':'GET','url':'/dashboard/api/filasPrioridad/baja/:idFila'},
        'elimina': { 'method':'GET','url':'/dashboard/api/filasPrioridad/elimina/:idFila'}
    });
});

personasApp.controller('personasController', function($scope,PersonasApi) {

	var pControl = this;

	pControl.grupos = [
	                   	{ "nombre" :'Area Desarrollo', "id" : 'sdsds', "personas" : [{"nombre":"Leidi Pena","id":"sdsd"},{"nombre":"Mitchell Guzman","id":"sdsd"}]  },
	                   	{ "nombre" :'Funcionales', "id" : 'sdsdsdfd', "personas" : [{"nombre":"Vicente Nina","id":"sdsd"}, {"nombre":"Rafael Gonzalez","id":"sdsd"}]  }
	                  ] ;
//,'Funcionales','Desarrolladores','Analistas Testing']


});