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
	return $resource("/dashboard/api/versionConsulta/consulta/:id",null,{
		'get':{ 'method':'GET'},
		'getServidores':{ 'method':'GET','isArray':true,'url':'/dashboard/api/versionConsulta/servidores/:id'},
		'getCambiosModelos':{ 'method':'GET','isArray':true,'url':'/dashboard/api/versionConsulta/cambiosModelos/:id'},
		'getJobs':{ 'method':'GET','isArray':true,'url':'/dashboard/api/versionConsulta/jobs/:id'},
		'getUndeploys':{ 'method':'GET','isArray':true,'url':'/dashboard/api/versionConsulta/undeploys/:id'}

    });
});


versionesApp.factory("SeguridadApi", function($resource) { //TODO hacer esto...
	return $resource("/dashboard/api/filaDeploymentVersion/:idAmbiente",null,{
		'filas':{ 'method':'GET','isArray':true},
    });
});



versionesApp.controller('appController', function($scope,Version,SeguridadApi,persistanceService) {
	var app = this;
	app.versionId = queryString.versionId;
	app.mensaje = "Version no esta deploy";
	app.link = "app.html";
	app.cambiosModelo = [];
	app.jobs = [];
	app.undeploys = [];

	 Version.get({id :app.versionId},function(data){
		 app.version = data;
	 } );

	 Version.getServidores({id :app.versionId},function(data){
	 	if (data.length>0){
	 		 servidor =  data[0];
	 		app.mensaje = "Version esta desplegada en el servidor " + servidor.nombre + " que tiene la base de datos " + servidor.basedatos;
	 		app.link = servidor.url;
	 	}
	 });

	 Version.getCambiosModelos({id :app.versionId},function(data){
		 app.cambiosModelo = data;
	 });

	 Version.getJobs({id :app.versionId},function(data){
		 app.jobs = data;
	 });

	 Version.getUndeploys({id :app.versionId},function(data){
		 app.undeploys = data;
	 });
})