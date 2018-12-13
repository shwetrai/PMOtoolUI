var app = angular.module('app', ['app1']);
var app1 = angular.module('app1', []);
var createApp = angular.module('createApp', []); // Used for Create page
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// This controller is being used for dashboard page
app1.controller('getalluserinfocontroller', function($scope, $http, $location, $window) {
	$scope.getusersinfo = function(){
		var url = $location.absUrl() + "/getallusersInfo";
		$http.get(url).then(function (response) {
			$scope.response = response.data
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
	
		$scope.edit = function(item){
			$window.localStorage['cid']=item;
			//$location.path('/onboard');
		
		}
	
});

// This controller is being used for resource data display and edit
app.controller('cidgetcontroller', function($scope, $http, $location, $window) {
	$scope.getuserinfo = function(){
		var url = $location.absUrl() + "/getuserInfo/"+ $scope.cid;
		$scope.editing= false;
		$scope.visible=false;
		$http.get(url).then(function (response) {
			$scope.response = response.data;
			$scope.visible=true;
			$scope.postResultMessage ='';
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
	
	// Udate function to update the user information
	
	$scope.updateuserinfo = function(){
		//alert($location.absUrl());
		var url = $location.absUrl() + "/postcustomer";
		alert(url);
		alert($scope.response.comments);
		var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        }
		var resource = {
            cid: $scope.response.cid,
            name: $scope.response.name,
            role: $scope.response.role,
            band: $scope.response.band,
            blocation: $scope.response.blocation,
            clocation: $scope.response.clocation,
            vdi: $scope.response.vdi,
            readyAPI: $scope.response.readyAPI,
            adc: $scope.response.adc,
            ard: $scope.response.ard,
            webex: $scope.response.webex,
            rtc: $scope.response.rtc,
            jenkins: $scope.response.jenkins,
            alm: $scope.response.alm,
            iib: $scope.response.iib,
            db2: $scope.response.db2,
            db2explorer: $scope.response.db2explorer,
            mq: $scope.response.mq,
            mqexplorer: $scope.response.mqexplorer,
            putty: $scope.response.putty,
            winscp: $scope.response.winscp,
            clientInd: $scope.response.clientInd,
            sisInd: $scope.response.sisInd,
            arch: $scope.response.arch,
            comPatterns: $scope.response.comPatterns,
            devTech: $scope.response.devTech,
            testFram: $scope.response.testFram,
            cdWalkthrough: $scope.response.cdWalkthrough,
            agile: $scope.response.cdWalkthrough,
            codecov: $scope.response.codecov,
            coderev: $scope.response.coderev,
            logfram: $scope.response.logfram,
            esqlGen: $scope.response.esqlGen,
            internalId: $scope.response.internalId,
            comments: $scope.response.comments
            
        };
		
		$http.put(url, resource, config).then(function (response) {
			$scope.postResultMessage = response.data;
		}, function error(response) {
			alert(url);
			$scope.postResultMessage = "Error with status:123 " +  response.statusText;
		});
		
		$scope.firstname = "";
		$scope.lastname = "";
	}
	
	// Reload User
	$scope.reloaduserinfo= function(){
		
		var cid= $window.localStorage['cid'];
		if(cid){
			var url = $location.absUrl() + "/getuserInfo/"+ cid;
			$scope.editing= false;
			$http.get(url).then(function (response) {
				$scope.response = response.data;
				$scope.visible=true;
				$window.localStorage['cid']='';
			}, function error(response) {
				$scope.postResultMessage = "Oops! Something went wrong. we got error : " +  response.statusText;
				$scope.visible=true;
				$window.localStorage['cid']='';
			});
		}else{
			$scope.visible=false;
			cid= $window.localStorage['cid']='';
		}
		
	}
});

//This controller is being used to create the record
createApp.controller('createcontroller', function($scope, $http, $location, $window) {
	

	$scope.createuserinfo = function(){
		//alert($location.absUrl());
		var url = $location.absUrl() + "/createuser";
		//alert(url);
		var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        }
		var resource = {
				cid: $scope.response.cid,
	            name: $scope.response.name,
	            role: $scope.response.role,
	            band: $scope.response.band,
	            blocation: $scope.response.blocation,
	            clocation: $scope.response.clocation,
	            vdi: $scope.response.vdi,
	            readyAPI: $scope.response.readyAPI,
	            adc: $scope.response.adc,
	            ard: $scope.response.ard,
	            webex: $scope.response.webex,
	            rtc: $scope.response.rtc,
	            jenkins: $scope.response.jenkins,
	            alm: $scope.response.alm,
	            iib: $scope.response.iib,
	            db2: $scope.response.db2,
	            db2explorer: $scope.response.db2explorer,
	            mq: $scope.response.mq,
	            mqexplorer: $scope.response.mqexplorer,
	            putty: $scope.response.putty,
	            winscp: $scope.response.winscp,
	            clientInd: $scope.response.clientInd,
	            sisInd: $scope.response.sisInd,
	            arch: $scope.response.arch,
	            comPatterns: $scope.response.comPatterns,
	            devTech: $scope.response.devTech,
	            testFram: $scope.response.testFram,
	            cdWalkthrough: $scope.response.cdWalkthrough,
	            agile: $scope.response.cdWalkthrough,
	            codecov: $scope.response.codecov,
	            coderev: $scope.response.coderev,
	            logfram: $scope.response.logfram,
	            esqlGen: $scope.response.esqlGen,
	            comments: $scope.response.comments
        };
		
		$http.post(url, resource, config).then(function (response) {
			$scope.postResultMessage = response.data;
		}, function error(response) {
			alert(url);
			$scope.postResultMessage = "Error with status:123 " +  response.statusText;
		});
		
		$scope.firstname = "";
		$scope.lastname = "";
	}
});