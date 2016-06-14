var _dashBoardConfig = angular.module('configDashBoardApp', ['ngRoute','ngResource']);

		_dashBoardConfig.config(['$routeProvider',
		                  function($routeProvider){
							$routeProvider.
						    when('/servidor/agregar', {
						      templateUrl: 'paginas/agregar_servidor.html',
						      controller: 'AgregaServidorController'
						    })
						    .when('/servidor/listar', {
							      templateUrl: 'servidores.html',
							      controller: 'ListaServidorController'
							    })
							.when('/servidor/editar/:idServidor', {
								      templateUrl: 'paginas/editar_servidor.html',
								      controller: 'EditaServidorController'
								    })
						     .when('/lista_ambientes', {
								      templateUrl: 'ambientes.html',
								      controller: 'ListaAmbientesController'
								    })
							 .otherwise({
							        redirectTo: '/servidor/listar'
							      });
						}]);

		_dashBoardConfig.factory("FactoryServidor", function($resource){

			return $resource("/dashboard/api/servidores/:idServidor", {},
								{
							        'actualizar': { method:'PUT', params: {idServidor :'@idServidor'} },
							        'grabar': { method:'POST' }
							    }
							  );
		});

		_dashBoardConfig.factory("FactoryAmbiente", function($resource){
			return $resource("/dashboard/api/ambientes/");
		});

		_dashBoardConfig.factory("FactoryRepositorioDatos", function($resource){
			return $resource("/dashboard/api/repositorioDatos/");
		});

		_dashBoardConfig.controller('dashBoardAooController', function($scope) {
			$scope.message = 'This is Add new order screen';
		});

		_dashBoardConfig.controller('AgregaServidorController', function($scope, $resource, FactoryServidor, FactoryAmbiente, FactoryRepositorioDatos) {
			FactoryAmbiente.query(function(data){
		        $scope.ambientes = data;
		    });

			FactoryRepositorioDatos.query(function(data){
		        $scope.repositorioDatos = data;
		    });

			$scope.grabarServidor = function(){
				$scope.servidor.ambiente = $scope.ambienteSeleccionado.id;
				$scope.servidor.baseDatos = $scope.repositorioDatoSeleccionado.id;

				 FactoryServidor.grabar($scope.servidor,function(){
			    		$scope.mensaje ='Grabado de Forma Satisfactoria..';
					    $scope.muestraMensaje = true;
			    	});
			 };
		});

		_dashBoardConfig.controller('ListaServidorController', function($scope) {
			 angular.element(document).ready(function() {
					$('#_servidores').DataTable( {
						"processing": true,
						"serverSide": false,
						"ajax": { url:"/dashboard/api/servidores/",
								  dataSrc:''
								},
						"columns": [
									    { data: 'id',
									      "orderable": false,
									   	  "render": function ( data, type, full, meta ) {
												 return '<a href="#servidor/editar/'+ data +'"><i class="fa fa-fw fa-edit"></i></a>';
										}
									   },
									   { data: 'nombre' },
									   { data: 'ruta',
									   	  "render": function ( data, type, full, meta ) {
												 return '<a href="'+ data +'">'+ data +'</a>';
										}
									   },
									   { data: 'version.numero' },
									   { data: 'repositorioDatos.host'}
								   ],
						"language":{
							    "decimal":        "",
							    "emptyTable":     "No hay Datos en la Tabla",
							    "info":           "Mostrando _START_ al _END_ de _TOTAL_",
							    "infoEmpty":      "Mostrando 0 al 0 de 0 ",
							    "infoFiltered":   "(Filtrada desde _MAX_ totales )",
							    "infoPostFix":    "",
							    "thousands":      ",",
							    "lengthMenu":     "Mostrar _MENU_ Registros",
							    "loadingRecords": "Cargando...",
							    "processing":     "Procesando...",
							    "search":         "Buscar:",
							    "zeroRecords":    "Registro no Encontrado",
							    "paginate": {
							        "first":      "Primero",
							        "last":       "Ultimo",
							        "next":       "Proximo",
							        "previous":   "Anterior"
							    },
							    "aria": {
							        "sortAscending":  ": Organizar Ascendente",
							        "sortDescending": ": Organizar Descendente"
							    }
							},
							dom: 'Bfrtip',
					        buttons: [
					            {
					                text: 'Nuevo',
					                action: function ( e, dt, node, config ) {
					                    window.location="#servidor/agregar";
					                }
					            }
					        ]
					});
				});
		});

		_dashBoardConfig.controller('EditaServidorController', function($scope, $routeParams, $resource, FactoryServidor, FactoryAmbiente, FactoryRepositorioDatos) {

			$scope.idServidor = $routeParams.idServidor;

			FactoryAmbiente.query(function(data){
		        $scope.ambientes = data;
		    });

			FactoryRepositorioDatos.query(function(data){
		        $scope.repositorioDatos = data;
		    });

		    FactoryServidor.get({ idServidor: $routeParams.idServidor }, function(data) {
		    	$scope.servidor = {};
		    	$scope.servidor.nombre = data.nombre;
		        $scope.servidor.rutaEntrada = data.rutaEntrada;
		        $scope.servidor.nombreServidorJenkins = data.nombreServidorJenkins;
		        $scope.estadoServidor = data.estadoServidor;

		        $scope.ambienteSeleccionado = data.ambiente;
		        $scope.repositorioDatoSeleccionado = data.baseDatos;
		    });

		    $scope.actualizaServidor = function(){
		    	$scope.servidor.ambiente = $scope.ambienteSeleccionado.id;
				$scope.servidor.baseDatos = $scope.repositorioDatoSeleccionado.id;

		    	FactoryServidor.actualizar({idServidor: $routeParams.idServidor } , $scope.servidor,function(){
		    		$scope.mensaje ='Actualizado de Forma Satisfactoria..';
				    $scope.muestraMensaje = true;
		    	});
		    };
		});


		_dashBoardConfig.controller('ListaAmbientesController', function($scope) {
			 angular.element(document).ready(function() {
					$('#_ambientes').DataTable( {
						"processing": true,
						"serverSide": false,
						"ajax": { url:"/dashboard/api/servidores/",
								  dataSrc:''
								},
						"columns": [
									    { data: 'id',
									      "orderable": false,
									   	  "render": function ( data, type, full, meta ) {
												 return '<a href="#edita_servidor/'+ data +'"><i class="fa fa-fw fa-edit"></i></a>';
										}
									   },
									   { data: 'nombre' },
									   { data: 'ruta',
									   	  "render": function ( data, type, full, meta ) {
												 return '<a href="'+ data +'">'+ data +'</a>';
										}
									   },
									   { data: 'version.numero' },
									   { data: 'repositorioDatos.host'}
								   ],
						"language":{
							    "decimal":        "",
							    "emptyTable":     "No hay Datos en la Tabla",
							    "info":           "Mostrando _START_ al _END_ de _TOTAL_",
							    "infoEmpty":      "Mostrando 0 al 0 de 0 ",
							    "infoFiltered":   "(Filtrada desde _MAX_ totales )",
							    "infoPostFix":    "",
							    "thousands":      ",",
							    "lengthMenu":     "Mostrar _MENU_ Registros",
							    "loadingRecords": "Cargando...",
							    "processing":     "Procesando...",
							    "search":         "Buscar:",
							    "zeroRecords":    "Registro no Encontrado",
							    "paginate": {
							        "first":      "Primero",
							        "last":       "Ultimo",
							        "next":       "Proximo",
							        "previous":   "Anterior"
							    },
							    "aria": {
							        "sortAscending":  ": Organizar Ascendente",
							        "sortDescending": ": Organizar Descendente"
							    }
							},
							dom: 'Bfrtip',
					        buttons: [
					            {
					                text: 'Nuevo',
					                action: function ( e, dt, node, config ) {
					                    window.location="#agrega_servidor";
					                }
					            }
					        ]
					});
				});
		});

