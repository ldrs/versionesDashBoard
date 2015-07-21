var db;

cargaDb=function(){
	var openRequest = window.indexedDB.open("app",1);

	openRequest.onerror = function(e) {
		console.log("Error opening db");
		console.dir(e);
	};

	openRequest.onupgradeneeded = function(e) {

		var thisDb = e.target.result;
		var objectStore;


		if(!thisDb.objectStoreNames.contains("usuario")) {
			objectStore = thisDb.createObjectStore("usuario", { keyPath: "logeado", autoIncrement:false });
			objectStore.createIndex("id", "id", { unique: false });
			objectStore.createIndex("nombre", "nombre", { unique: false });
			objectStore.createIndex("correo", "correo", { unique: false });
			objectStore.createIndex("usuarioSVN", "usuarioSVN", { unique: false });
		}

	};

	openRequest.onsuccess = function(e) {
		db = e.target.result;

		db.onerror = function(event) {
			// Generic error handler for all errors targeted at this database's
			// requests!
			deferred.reject("Database error: " + event.target.errorCode);
		};
	};
}

msgValidacion =function(titulo,body){
	var modal = $("#openModal");
	modal.find("h2").text(titulo);
	modal.find("p").text(body);
	document.querySelector("#linkModal").click();
}

$(document).ready(function(){
	cargaDb();
	$("#olvidoContrasena").click(function(e){
		e.preventDefault();
		msgValidacion("Olvidó de contraseña","Esta es la contraseña del dominio interno, favor informar a infraestructura por una nueva.");
	});

	$("#login").click(function(e){
		e.preventDefault();
		var tmpPass=$("#pass").val(), pass = window.btoa(tmpPass) , tmUser = $("#user").val(),   user = window.btoa(tmUser);
		if (tmpPass.trim().length===0 || user.trim().length===0){
			msgValidacion("Datos Requeridos","Debe llenar el usuario y la contraseña");
			return;
		}


		$.getJSON("/dashboard/api/seguridad/iniciasesion/"+user+"/"+pass,function(data){
			if (data.inicioSesion==="true"){
				var transaction = db.transaction(["usuario"],"readwrite"),store = transaction.objectStore("usuario");
				store.add(data.usuario,1);
			}else{
				msgValidacion("Validación Credencial","Favor revisar su usuario/contraseña");
			}
		});
	});
});