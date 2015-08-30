var personasApp = angular.module('personasApp',['ngResource']);

personasApp.factory("ConfigApi", function($resource) {
	return $resource("/dashboard/api/configuracionNotificacion/grupos/{idAmb}/{tipo}",null,{
		'tiposConfig':{ 'method':'GET','isArray':true},
		'todasApp':{ 'method':'GET','url':'/dashboard/api/aplicaciones','isArray':true},
		'ambientesApp':{ 'method':'GET','url':'/dashboard/api/ambientes/{idApp}','isArray':true},
		'todosTiposNotificaciones':{ 'method':'GET','url':'/dashboard/api/configuracionNotificacion/todosTipos','isArray':true},
        'agregaGrupo': { 'method':'GET','url':'/dashboard/api/grupos/agregaGrupo/:nombre'},
        'eliminaGrupo': { 'method':'GET','url':'/dashboard/api/grupos/eliminaGrupo/:idGrupo'},
        'agregaPersona': { 'method':'GET','url':'/dashboard/api/grupos/agregaPersona/:idGrupo/:idPersona'},
        'eliminaPersona': { 'method':'GET','url':'/dashboard/api/grupos/eliminaPersona/:idPersonaDetalle'}
    });
});

personasApp.controller('personasController', function($scope,ConfigApi) {

	var pControl = this;
	
	cargaGrupoTipoConfigucaciones = function(amb,tipo){
		ConfigApi.tiposConfig({idAmb:amb.id,"tipo":tipo}).$promise.then(function(data){
			tipo.grupos = data;
		});
	}
	
	cargaTiposConfiguraciones = function(amb){
		ConfigApi.todosTiposNotificaciones().$promise.then(function(data){
			amb.tipoNotificaciones = data;
			app.tipoNotificaciones.forEach=function(tipo){
				cargaGrupoTipoConfigucaciones(amb,tipo);
			}
		});
	}
	
	
	cargaAmbiente = function(app){
		ConfigApi.ambientesApp({idApp:app.id}).$promise.then(function(data){
			app.ambientes=data;
			app.ambientes.forEach=function(amb){
				cargaTiposConfiguraciones(amb);	
			}
		});
	}
	
	pControl.cargaApp = function(){
		ConfigApi.todasApp().$promise.then(function(data){
			pControl.applicaciones = data;
			pControl.applicaciones.forEach=function(a){
				cargaAmbiente(a);
			}
		});
	}
	
	
	pControl.cargaApp();
});