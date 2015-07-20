/* global angular,window */
var appDbServices = angular.module('appDbServices', []);

appDbServices.factory('persistanceService', ['$q', function($q) {

	var setUp=false,db , primeraLlamada = false;

	function init() {
		primeraLlamada = true;
		var deferred = $q.defer();

		if(setUp) {
			deferred.resolve(true);
			return deferred.promise;
		}

		var openRequest = window.indexedDB.open("app",1);

		openRequest.onerror = function(e) {
			console.log("Error opening db");
			console.dir(e);
			deferred.reject(e.toString());
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
				objectStore.createIndex("prioridadAmbientes","prioridadAmbientes", {unique:false,multiEntry:true});
				objectStore.createIndex("undeployAmbientes", "undeployAmbientes", { unique: false,multiEntry:true });
			}

		};

		openRequest.onsuccess = function(e) {
			db = e.target.result;

			db.onerror = function(event) {
				// Generic error handler for all errors targeted at this database's
				// requests!
				deferred.reject("Database error: " + event.target.errorCode);
			};

			setUp=true;
			deferred.resolve(true);

		};

		return deferred.promise;
	}

	function isSupported() {
		return ("indexedDB" in window);
	}

	function logout() {
		var deferred = $q.defer(), t = db.transaction(["usuario"], "readwrite");
		t.objectStore("usuario").delete(1);
		t.oncomplete = function(event) {
			deferred.resolve();
		};
		return deferred.promise;
	}

	function getUsuario() {
		if (!db && !primeraLlamada){
			return init().then(getUsuario());
		}

		var deferred = $q.defer();

		var transaction = db.transaction(["usuario"]);
		var objectStore = transaction.objectStore("usuario");
		var request = objectStore.get(1);

		request.onsuccess = function(event) {
			deferred.resolve(request.result);
		};

		return deferred.promise;
	}

	function ready() {
		return setUp;
	}

	function login(usuario) {
		//Should this call init() too? maybe
		var deferred = $q.defer();


		var t = db.transaction(["usuario"], "readwrite");


            t.objectStore("usuario").add(usuario,1);

		t.oncomplete = function(event) {
			deferred.resolve();
		};

		return deferred.promise;
	}

	function supportsIDB() {
		return "indexedDB" in window;
	}

	return {
		isSupported:isSupported,
		init:init,
		logout:logout,
		getUsuario:getUsuario,
		ready:ready,
		login:login,
		supportsIDB:supportsIDB
	};

}]);

