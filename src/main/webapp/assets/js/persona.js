var personasApp = angular.module('personasApp',['ngResource']);

personasApp.factory("PersonasApi", function($resource) {
	return $resource("/dashboard/api/grupos/todos",null,{
		'todos':{ 'method':'GET','isArray':true},
		'todasPersonas':{ 'method':'GET','url':'/dashboard/api/personaConsulta','isArray':true},
        'agregaGrupo': { 'method':'GET','url':'/dashboard/api/grupos/agregaGrupo/:nombre'},
        'eliminaGrupo': { 'method':'GET','url':'/dashboard/api/grupos/eliminaGrupo/:idGrupo'},
        'agregaPersona': { 'method':'GET','url':'/dashboard/api/grupos/agregaPersona/:idGrupo/:idPersona'},
        'eliminaPersona': { 'method':'GET','url':'/dashboard/api/grupos/eliminaPersona/:idGrupo/:idPersona'}
    });
});

personasApp.controller('personasController', function($scope,PersonasApi) {

	var pControl = this;

	pControl.actualizar = function(){

		PersonasApi.todos().$promise.then(function(data){
			pControl.grupos = data;
		});

		PersonasApi.todasPersonas().$promise.then(function(data){
			pControl.personas = data;
		});
	};

	$scope.eliminaPersona = function(idGrupo,idPersona){
		PersonasApi.eliminaGrupo(idGrupo,idPersona).$promise.then(function(data){
			pControl.actualizar();
		});
	};

	$scope.agregarPersona = function(idGrupo,idPersona){
		PersonasApi.agregaPersona(idGrupo,idPersona).$promise.then(function(data){
			pControl.actualizar();
		});
	};

	$scope.agregarGrupo = function(nombre){
		PersonasApi.agregaGrupo(nombre).$promise.then(function(data){
			pControl.actualizar();
		});
	};

	$scope.eliminaGrupo = function(idgrupo){
		PersonasApi.eliminaGrupo(idgrupo).$promise.then(function(data){
			pControl.actualizar();
		});
	};

	pControl.actualizar();
});