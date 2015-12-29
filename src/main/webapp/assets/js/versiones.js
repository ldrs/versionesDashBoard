var configApp = angular.module('versionesApp',['ngResource']);

configApp.factory("VersionesApi", function($resource) {
	return $resource("/dashboard/api/version/recientes/",null,{
		'todas':{ 'method':'GET','isArray':true}
    });
});

configApp.controller('versionesController', function($scope,VersionesApi) {

	var pControl = this;

	cargaApp = function(){
		VersionesApi.todas().$promise.then(function(data){
			pControl.versiones = data;
		});
	}

	cargaApp();
});