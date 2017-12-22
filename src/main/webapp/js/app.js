var app = angular.module('MyApp',['ui.router','ngMaterial']);


app.config(function($stateProvider, $urlRouterProvider) {

	
	$urlRouterProvider.otherwise('/');

	

//	.state('newPatch', {
//		url : '/',
//		templateUrl : 'newPatch.html'
//	});

	$stateProvider		
	  .state('index',{	
		url : '/',
	    views: {		
	      'newPatch': {		
	        templateUrl: 'newPatch.html',		
	        controller: 'PatchController'		
	      },
	      'svntab': {		
		        templateUrl: 'svntab.html',		
		        controller: 'svnController'		
		      }
	    }		
	  })	
	
	
});
