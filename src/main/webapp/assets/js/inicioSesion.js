var db;


navegarApp=function(){
	window.location.assign("app.html");
}
//Permitiendo el uso de usuario de svn, nombre, correo y usuario dominio.
toUser = function(user){
	var indexCorreo = user.indexOf("@");
	if (indexCorreo==-1){
		return user.substr(0,indexCorreo);
	}else{
		var indexSvn = user.indexOf("_");
		if (indexSvn == -1){
			var indexEspacio = user.indexOf(" ");
			if (indexEspacio==-1){
				return user;
			}else{
				return user.toLocaleLowerCase().replace(" ",".");
			}

			return user;
		}else{
			return user.replace("_",".");
		}
	}
}

configurarBotones = function(){
	$("#olvidoContrasena").click(function(e){
		e.preventDefault();
		msgValidacion("Olvidó de contraseña","Esta es la contraseña del dominio interno, favor informar a infraestructura por una nueva.");
	});

	$("#login").on( "click",function(evento){
		var yo = $(this);
		evento.preventDefault();
		var tmpPass=$("#pass").val(), pass = window.btoa(tmpPass) , tmUser = toUser($("#user").val()),   user = window.btoa(tmUser);
		if (tmpPass.trim().length===0 || user.trim().length===0){
			msgValidacion("Datos Requeridos","Debe llenar el usuario y la contraseña");
			return;
		}

		$.getJSON("/dashboard/api/seguridad/iniciasesion/"+user+"/"+pass,function(data){
			if (data.inicioSesion==="true"){
				var transaction = db.transaction(["usuario"],"readwrite"),store = transaction.objectStore("usuario");
				store.add(data.usuario);
				navegarApp();
			}else{
				msgValidacion("Validación Credencial","Favor revisar su usuario/contraseña");
			}

			yo.off( evento );
		});
	});
}


cargaDb=function(){
	var openRequest = window.indexedDB.open("dashboard",1);

	openRequest.onerror = function(e) {
		console.log("Error opening db");
		console.dir(e);
	};

	openRequest.onupgradeneeded = function(e) {

		var thisDb = e.target.result;
		var objectStore;


		if(!thisDb.objectStoreNames.contains("usuario")) {
			objectStore = thisDb.createObjectStore("usuario", { keyPath: "id", autoIncrement:true });
//			objectStore.createIndex("id", "id", { unique: false });
//			objectStore.createIndex("nombre", "nombre", { unique: false });
//			objectStore.createIndex("correo", "correo", { unique: false });
//			objectStore.createIndex("usuarioSVN", "usuarioSVN", { unique: false });
		}

	};

	openRequest.onsuccess = function(e) {
		db = e.target.result;

		db.transaction(["usuario"],"readonly").objectStore("usuario")
		.count().onsuccess = function(event) {
			if (event.target.result===0){
				configurarBotones();
			}else{
				navegarApp();
			}
		}

		db.onerror = function(event) {
		};
	};
};



msgValidacion =function(titulo,body){
	var modal = $("#openModal");
	modal.find("h2").text(titulo);
	modal.find("p").text(body);
	document.querySelector("#linkModal").click();
}

$(document).ready(function(){
	cargaDb();

});