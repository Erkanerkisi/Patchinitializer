var app = angular.module("MyApp");

app.controller('svnController', function($scope, $http) {


	$scope.user = {
		userName : $scope.userName,
		password : $scope.password,
		SvnPath : $scope.SvnPath,
		localPath : $scope.localPath
	};
	
		  

	$scope.getsvnfolders = function() {
		$scope.obj = [];
		console.log("USER " + $scope.user.userName);
		console.log("USER " + $scope.user.password);
		console.log("USER " + $scope.user.SvnPath);
		$http({
			method : "GET",
			url : "http://localhost:8080/svnfolders",
			params : $scope.user
		}).then(function mySuccess(response) {

			console.log("Success SVN FOLDER GET!! ");
			$scope.obj = response.data;

		}, function myError(response) {
			
			//$scope.obj = response.statusText;
			console.log("ERROR SVN FOLDER GET!! ");

		});

	}
	
	$scope.getlocalfolders = function() {
		$scope.locals = [];

		console.log("$scope.localPath=>  " + $scope.localPath);
		$http({
			method : "GET",
			url : "http://localhost:8080/localfolders",
			params : $scope.user			
		}).then(function mySuccess(response) {

			console.log("Success local FOLDER GET!! ");
			$scope.locals = response.data;

		}, function myError(response) {
			
			//$scope.obj = response.statusText;
			console.log("ERROR local FOLDER GET!! ");

		});

	}
	
	
	
	
	$scope.change = function(){
		if($scope.isChecked){
			
			console.log("check! ");
			$scope.getDifferences();
	    } else {
	    	console.log("uncheck! all ");
	    }
		
	}
	
	
	$scope.getDifferences = function() {
		$scope.dif= [];
		console.log("func basladi");
		for (i = 0; i < $scope.locals.length; i++) {
			console.log("ilk for degeri aldi => " + $scope.locals[i].folderName );
			
			var a = $scope.locals[i].folderName;
			var f = -1;
			for (j = 0; j < $scope.obj.length; j++) {
				
				var b = $scope.obj[j].folderName;
				console.log('b is', b);
				//console.log("b = " + JSON.stringify(b));

				console.log("ikinci for "+ a.substring(0, 8) +" degerini "+ b +" degerinde aratiyor");
				var n = b.search(a.substring(0, 7));
				if(n == 0) {
					console.log("buldu!!!");
					 f = 1;
					
				}
			}
			console.log("if oncesi");
			if(f != 1) {
				console.log("puslama islemi basladi");
				$scope.dif.push($scope.locals[i]);
			}
			//$scope.dif.push(a);
		}
		
		
		}
	
});
