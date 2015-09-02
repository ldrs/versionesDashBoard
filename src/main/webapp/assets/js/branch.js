var tmpQueryString = location.search.slice(1).split('&');

var queryString = {};
tmpQueryString.forEach(function(pair) {
	pair = pair.split('=');
	queryString[pair[0]] = decodeURIComponent(pair[1] || '');
});

function toQueryString(page){
	return page+"?"+ $.param(queryString);
}

var branchApp = angular.module('branchApp',['ngResource','appDbServices']);



branchApp.factory("Branch", function($resource) {
	return $resource("/dashboard/api/branchConsulta/consulta/:branch",null,{
		'get':{ 'method':'GET','isArray':true}
    });
});


branchApp.factory("SeguridadApi", function($resource) { //TODO hacer esto...
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
    });
});



branchApp.controller('appController', function($scope,Branch,SeguridadApi,persistanceService) {
	var app = this;
	app.branch = queryString.branch;
	app.mensaje = "Version no esta deploy";
	app.link = "app.html";

	app.versiones = [];

	Branch.get({branch :app.branch},function(data){
		 app.versiones = data;
	 } );


})