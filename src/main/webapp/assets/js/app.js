var tmpQueryString = location.search.slice(1).split('&');

var queryString = {};
tmpQueryString.forEach(function(pair) {
    pair = pair.split('=');
    queryString[pair[0]] = decodeURIComponent(pair[1] || '');
});

function toQueryString(page){
	return page+"?"+ $.param(queryString);
}


angular.module('versionesApp', [])
  .controller('appController', function() {
    var app = this;
    app.aplicacion = queryString.aplicacion;
    app.ambiente = queryString.ambiente;

    app.aplicaciones = [
                      {nombre:'sigef', activo:false, css : ""},
                      {nombre:'esigef', activo:false, css : ""}];



    app.ambientes = [
                      {text:'desarrollo', activo:false, css : ""},
                      {text:'testing', activo:false, css : ""},
                      {text:'preproduccion', activo:false, css : ""},
                      {text:'helpdesk', activo:false, css : ""},
                      {text:'produccion', activo:false, css : ""},
                      ];

    app.versionesFila = [
                              {"prioridad":1, "numero":"10.19278.7","autor":"ronny_jimenez","dueno":"alejandra_perez", "branchOrigen":"19270.00", "fechaVersion":"17/06/2015 11:52", "controles":"block", "tickets" : ["1050"], "jiras":["SGF-1550"]},
                              {"prioridad":3, "numero":"10.13278.3","autor":"galo_escobar","dueno":"yunior_echavarriga", "branchOrigen":"17270.00", "fechaVersion":"12/05/2015 11:52", "controles":"block", "tickets" : ["2540","3050"], "jiras":["SGF-150","SGF-1551","SGF-750"]},
                              {"prioridad":2, "numero":"10.132df8.3","autor":"eudris","dueno":"yunior_echavarriga", "branchOrigen":"17270.00", "fechaVersion":"12/05/2015 11:52", "controles":"block", "tickets" : ["2540","3050"], "jiras":["SGF-150","SGF-1551","SGF-750"]},
                              ];


    app.servidores = [
                          {nombre:'172.16.7.30:7101', basedatos:"MHDDEV01", version : "10.11278.7", "estado" : "no_disponible","tickets" : ["2540","3050"], "jiras":["SGF-150","SGF-1551","SGF-750"], "deploy":"block"},
                          {nombre:'172.16.7.30:8888', basedatos:"MHDDEV02", version : "10.1128.7", "estado" : "no_disponible","tickets" : ["30"], "jiras":["SGF-151"], "display":"block"},
                          {nombre:'172.16.7.21:7777', basedatos:"MHDDEV03", version : "NONE", "estado" : "disponible", "deploy":"none"},
                          ];



    app.aplicaciones.forEach(function(o){
    	if (! app.aplicacion){
    		app.aplicacion = o.nombre;
    	}

    	if (o.nombre===app.aplicacion){
    		o.css="current-page-item";
    		o.activo=true;
    	}
    })

     app.ambientes.forEach(function(o){
    	 o.href="app.html?aplicacion="+queryString.aplicacion+"&ambiente="+o.text;
    	if (! app.ambiente){
    		app.ambiente = o.text;
    	}
    	if (o.text===app.ambiente){
    		o.css="current-page-item";
    		o.activo=true;
    	}
    })

    app.addTodo = function() {
      app.aplicaciones.push({text:app.todoText, done:false});
      app.todoText = '';
    };

    app.remaining = function() {
      var count = 0;
      angular.forEach(app.todos, function(todo) {
        count += todo.done ? 0 : 1;
      });
      return count;
    };

    app.archive = function() {
      var oldTodos = app.aplicaciones;
      app.aplicaciones = [];
      angular.forEach(oldTodos, function(todo) {
        if (!todo.done) app.aplicaciones.push(todo);
      });
    };
  })

  ;

