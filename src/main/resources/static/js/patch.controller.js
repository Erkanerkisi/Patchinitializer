var app = angular.module("MyApp");

app.controller('PatchController', function($scope, $http,$mdDialog) {

	$scope.errorCode = "";
	$scope.errorMessage = "";
	
	$scope.patch = {
		winNum : $scope.winNum,
		winName : $scope.winName,
		destination : $scope.destination,
		svnPath : $scope.svnPath,
		checked : $scope.checked,
		user : {
			userName : $scope.userName,
			password : $scope.password
				}
	};
	
	
	 $scope.showConfirm = function(ev) {
		    // Appending dialog to document.body to cover sidenav in docs app
		    var confirm = $mdDialog.confirm()
		          .title('Are you sure ?')
		          .textContent('The folder structure will be created')
		          .ariaLabel('Lucky day')
		          .targetEvent(ev)
		          .ok('Yes')
		          .cancel('No');

		    $mdDialog.show(confirm).then(function() {
		      $scope.Create();
		    }, function() {
		      $scope.status = 'You decided to create';
		    });
		  };
		  

	$scope.Create = function() {
		$scope.obj = [];
		console.log("$scope.svnPath" + $scope.patch.svnPath);
		console.log("$scope.userName" + $scope.patch.user.userName);
		$http({
			method : "POST",
			url : "http://localhost:8080/mk",
			data : $scope.patch
		}).then(function mySuccess(response) {

			console.log("Success!! ");
			$scope.obj = response.data;
			$scope.errorMessage = response.data.errorMessage;
			$scope.errorCode = response.data.errorCode;

		}, function myError(response) {
			
			//$scope.obj = response.statusText;
			$scope.errorMessage = response.data.errorMessage;
			$scope.errorCode = response.data.errorCode;

		});

	}
	
});
