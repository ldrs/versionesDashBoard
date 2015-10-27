var configApp = angular.module('ticketsApp',['ngResource']);

configApp.factory("TicketsApi", function($resource) {
	return $resource("/dashboard/api/area/tickets/",null,{
		'todos':{ 'method':'GET','isArray':true}
    });
});

configApp.controller('ticketsController', function($scope,TicketsApi) {

	var pControl = this;

	cargaApp = function(){
		TicketsApi.todos().$promise.then(function(data){
			pControl.areas = data;
		});
	}

	cargaApp();
});