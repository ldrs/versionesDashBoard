var datos;

var ejecutar = function(){


	var myTemplateConfig = {
    colors: [ "#979797", "#008fb5", "#f1c109" ],
	  branch: {
		  lineWidth: 10,
          spacingX: 50
	  },
	  commit: {
		     spacingY: -80,
             dot: {
               size: 14
             },
	    message: {
	      displayAuthor: true,
	      displayHash: false,
	      font: "normal 12pt Arial"
	    }
	  }
	};
	var myTemplate = new GitGraph.Template( myTemplateConfig );

	var config = {
	  template: myTemplate       // could be: "blackarrow" or "metro" or myTemplate (custom Template object)
	//  , mode: "compact"     // special compact mode : hide messages & compact graph
	 // , orientation : "horizontal"
	 , author: ""
	};
	var gitGraph = new GitGraph( config );

	var master = gitGraph.branch( "trunk" ),branches={},branchBusqueda="";
	master.commit("origen");

	carga = function cargaBranch(datos,origen){

		for (var indexBranch in datos.branches){
			var branch = datos.branches[indexBranch], color = branchBusqueda === branch.nombre ? "#4E78A0" : "#008fb5"   , rama = origen.branch({"name":branch.nombre,"color":color});
			branches[branch.nombre]={"rama":rama,"branch":branch};
			rama.commit({"message": "Creación del Branch " + branch.nombre,"color":color} );
			if (branch.versiones){
				for (var indexVersion in branch.versiones){
					var version = branch.versiones[indexVersion], tickets="";
					for (var ticketIndex in version.tickets){
						tickets+=version.tickets[ticketIndex] + " , ";
					}
					if (version.tickets.length>0){
						tickets = " con los tickets ("+ tickets.substring(0,tickets.length-3)+")";
					}

					rama.commit({"message":"Versión " + version.numero +tickets  ,"author":version.autor,"color":"#000099"})
				}
			}

			if (branch.branches){
				cargaBranch(branch,rama);
			}
		}

		for (var indexBranch in datos.branches){

			var jsonRama = branches[datos.branches[indexBranch].nombre];

			if (jsonRama && jsonRama.branch.destinos){
				for (var indexDestino in jsonRama.branch.destinos){
					var destino = branches[jsonRama.branch.destinos[indexDestino]];
					if (destino){
						destino.rama.merge(jsonRama.rama);
					}
				}
			}
		}

	}
	branchBusqueda = datos.branchActual.branch;
	carga(datos.branches,master);
}

fetch('/dashboard/api/branchConsulta/origenBranch/'+queryString.branch).then(
    function(response) {
	response.json().then(function(data) {
		datos=data;
		ejecutar();
	});

 });


