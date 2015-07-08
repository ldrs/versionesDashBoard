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
  .controller('appController', ['$scope', function($scope) {
	var tituloAplicacion = "Configuracion Aplicaciones",tituloAmbiente = "Configuracion Aplicaciones";
    var app = this;


    app.aplicacion = queryString.aplicacion;
    app.ambiente = queryString.ambiente;
    app.modificado = {aplicaciones : false};
    app.configuraciones = {"tituloAplicaciones":tituloAplicacion,"tituloAmbientes":tituloAmbiente};

    app.aplicaciones = [
                      {nombre:'sigef', css : "", "jira":"SGF", "svn":"sigef","id":"xcsdfsfsdfsd"},
                      {nombre:'esigef', css : "", "jira":"ESG", "svn":"esigef","id":"xcsdfsfsdsdsd"}
                      ];



    app.ambientes = [
                      {nombre:'desarrollo', activo:false, css : "","orden" : 1},
                      {nombre:'testing', activo:false, css : "","orden" : 2},
                      {nombre:'preproduccion', activo:false, css : "","orden" : 3},
                      {nombre:'helpdesk', activo:false, css : "","orden" : 4},
                      {nombre:'produccion', activo:false, css : "","orden" : 5},
                      ];

    app.versionesFila = [
                              {"prioridad":1, "numero":"10.19278.7","autor":"ronny_jimenez","duenos":["alejandra_perez","ronny_jimenez"], "branchOrigen":"19270.00", "fechaVersion":"17/06/2015 11:52", "controles":"block", "tickets" : ["1050"], "jiras":["SGF-1550"]},
                              {"prioridad":3, "numero":"10.13278.3","autor":"galo_escobar","duenos":["yunior_echavarriga","galo_escobar"], "branchOrigen":"17270.00", "fechaVersion":"12/05/2015 11:52", "controles":"block", "tickets" : ["2540","3050"], "jiras":["SGF-150","SGF-1551","SGF-750"]},
                              {"prioridad":2, "numero":"10.132df8.3","autor":"eudris","duenos":["yunior_echavarriga","eudris"], "branchOrigen":"17270.00", "fechaVersion":"12/05/2015 11:52", "controles":"block", "tickets" : ["2540","3050"], "jiras":["SGF-150","SGF-1551","SGF-750"]},
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
    		app.aplicacionSeleccionado=o;
    	}
    });

     app.ambientes.forEach(function(o){
    	 o.href="app.html?aplicacion="+queryString.aplicacion+"&ambiente="+o.nombre;
    	if (! app.ambiente){
    		app.ambiente = o.nombre;
    	}
    	if (o.nombre===app.ambiente){
    		o.css="current-page-item";
    		o.activo=true;
    		o.ambienteSeleccionado=o;
    	}
    })

    $scope.aplicacion = app.aplicacionSeleccionado;

     var seleccionAplicacion = function(s){
    	 app.aplicacionSeleccionado = s;
       	 app.aplicaciones.forEach(function(o){
     		o.css="";
      		o.activo=false;
     	 });
      $scope.aplicacion = s;
      app.aplicacion=s.nombre;
      s.css="current-page-item";
    };

    var seleccionAmbiente = function(s){
   	 app.ambienteSeleccionado = s;
      	 app.ambientes.forEach(function(o){
    		o.css="";
     		o.activo=false;
    	 });
     $scope.ambiente = s;
     app.ambiente=s.nombre;
     s.css="current-page-item";
   };

    $scope.adicionarAplicacion = function(){
    	 var o = {nombre:'?', css : "", "jira":"?", "svn":"?","id":"?"};

    	 seleccionAplicacion(o);
    	 app.aplicaciones.push(o);
    	 app.modificado.aplicaciones = true;
    	 app.configuraciones.tituloAplicaciones="*"+tituloAplicacion;
    	 $("input[name='aplicacion']" ).focus();
    	 $("#guardarAplicaciones").css("display","block");
     }

    $scope.adicionarAmbiente = function(){

   	 var o = {nombre:'?', css : "", "orden":"?","id":"?"};

     seleccionAmbiente(o);
   	 app.ambientes.push(o);
   	 app.modificado.ambientes = true;
   	 app.configuraciones.tituloAplicaciones="*"+tituloAplicacion;
   	 $("input[name='nombreAmbiente']" ).focus();
   	 $("#guardarAmbientes").css("display","block");
    }

    $scope.seleccionarAplicacion = function(o){
    	seleccionAplicacion(o);
    }

    $scope.guardarAplicaciones = function(){
    	 app.configuraciones.tituloAplicaciones=tituloAplicacion;
    	 app.modificado.aplicaciones = false;
    	 $("#guardarAplicaciones").css("display","none");
    }

  }])
  ;