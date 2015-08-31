var configApp = angular.module('configApp',['ngResource']);

personasApp.factory("ConfigApi", function($resource) {
	return $resource("/dashboard/api/configuracionNotificacion/grupos/:idAmb/:tipo",null,{
		'tiposConfig':{ 'method':'GET','isArray':true},
		'todasApp':{ 'method':'GET','url':'/dashboard/api/aplicaciones','isArray':true},
		'ambientesApp':{ 'method':'GET','url':'/dashboard/api/ambientes/:idApp','isArray':true},
		'todosTiposNotificaciones':{ 'method':'GET','url':'/dashboard/api/configuracionNotificacion/todosTipos','isArray':true}
    });
});

configApp.controller('configController', function($scope,ConfigApi) {

	var pControl = this;

	cargaGrupoTipoConfigucaciones = function(amb,tipo){
		ConfigApi.tiposConfig({idAmb:amb.idAmbiente,"tipo":tipo}).$promise.then(function(data){
			tipo.grupos = data;
		});
	}

	cargaTiposConfiguraciones = function(amb){
		ConfigApi.todosTiposNotificaciones().$promise.then(function(data){
			amb.tipoNotificaciones = data;
			for (var i=0;i<amb.tipoNotificaciones.length;i++){
				cargaGrupoTipoConfigucaciones(amb,amb.tipoNotificaciones[i]);
			}
		});
	}


	cargaAmbiente = function(app){
		ConfigApi.ambientesApp({idApp:app.id}).$promise.then(function(data){
			app.ambientes=data;
			for (var i=0;i<app.ambientes.length;i++){
				cargaTiposConfiguraciones(app.ambientes[i]);
			}
		});
	}

	pControl.cargaApp = function(){
		ConfigApi.todasApp().$promise.then(function(data){
			pControl.applicaciones = data;
			for (var i=0;i<pControl.applicaciones.length;i++){
				cargaAmbiente(pControl.applicaciones[i]);
			}
		});
	}


	pControl.cargaApp();
});