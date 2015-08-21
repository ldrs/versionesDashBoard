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



versionesApp.factory("Version", function($resource) {
	return $resource("/dashboard/api/versionConsulta/:id",null,{
		'get':{ 'method':'GET'}
    });
});


versionesApp.factory("SeguridadApi", function($resource) { //TODO hacer esto...
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
        'sube': { 'method':'GET','url':'/dashboard/api/filasPrioridad/sube/:idFila'},
        'baja': { 'method':'GET','url':'/dashboard/api/filasPrioridad/baja/:idFila'},
        'elimina': { 'method':'GET','url':'/dashboard/api/filasPrioridad/elimina/:idFila'}
    });
});



versionesApp.controller('appController', function($scope,Version,SeguridadApi,persistanceService) {
	var app = this;
	app.versionId = queryString.versionId;
	 Version.get({id :app.versionId},function(data){
		 app.version = data;
	 } );
})
;

