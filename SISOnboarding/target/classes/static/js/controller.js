var app = angular.module('app', [ 'app1' ]);
var app1 = angular.module('app1', []);
var createApp = angular.module('createApp', []); // Used for Create page
var loginApp = angular.module('loginApp', []);
//var host = "localhost";
//var port = "8080";
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// This controller is being used for dashboard page
app1.controller('getalluserinfocontroller', function($scope, $http, $location,
		$window) {

	$scope.getusersinfo = function() {
		var user = $window.localStorage['role'];
		$scope.user = user;
		if (user != 'admin') {
			$window.location.href = "http://" + $location.host() + ":"+ $location.port() + "/";
		}
		$scope.postResultMessageE = '';
		$scope.postResultMessageS = '';
		var url = $location.absUrl() + "/getallusersInfo";
		$http.get(url).then(
				function(response) {
					$scope.response = response.data
				},
				function error(response) {
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}

	$scope.edit = function(item) {
		$window.localStorage['cid'] = item;
		// $location.path('/onboard');

	}

});

// This controller is being used for resource data display and edit
app
		.controller(
				'cidgetcontroller',
				function($scope, $http, $location, $window) {
					$scope.getuserinfo = function() {
						var user = $window.localStorage['role'];
						var uname = $window.localStorage['uname'];
						$scope.user = user;
						if (user != 'user' && user != 'admin') {
							$window.location.href = "http://" + $location.host() + ":"+ $location.port() + "/";
						}
						var url = $location.absUrl() + "/getuserInfo/"
								+ $scope.cid;
						$scope.postResultMessageE = '';
						$scope.postResultMessageS = '';
						$scope.editing = false;
						$scope.visible = false;
						// alert("url "+url);
						//alert("uname "+uname);
						//alert("$scope.cid "+$scope.cid );
						
						if (uname == $scope.cid ||user=='admin') {
							$http
									.get(url)
									.then(
											function(response) {
												$scope.response = response.data;
												if (response.data == null) {
													$scope.visible = false;
													$scope.postResultMessageE = 'Record Not Found';
												} else {
													$scope.visible = true;
													$scope.postResultMessage = '';
												}

											},
											function error(response) {
												$scope.postResultMessageE = "Oops! Something went wrong. we got error : "
														+ response.statusText;
											});
						} else {
							$scope.postResultMessageE = "You can not edit other's data!";
						}
					}

					// Udate function to update the user information

					$scope.updateuserinfo = function() {
						var user = $window.localStorage['role'];
						$scope.user = user;
						if (user != 'user' && user != 'admin') {
							$window.location.href = "http://" + $location.host() + ":"+ $location.port() + "/";
						}
						var url = $location.absUrl() + "/postcustomer";
						$scope.postResultMessageE = '';
						$scope.postResultMessageS = '';
						var config = {
							headers : {
								'Accept' : 'text/plain'
							}
						}
						var resource = {
							cid : $scope.response.cid,
							name : $scope.response.name,
							role : $scope.response.role,
							band : $scope.response.band,
							nbsid : $scope.response.nbsid,
							rate : $scope.response.rate,
							ibmid : $scope.response.ibmid,
							blocation : $scope.response.blocation,
							clocation : $scope.response.clocation,
							vdi : $scope.response.vdi,
							readyAPI : $scope.response.readyAPI,
							adc : $scope.response.adc,
							ard : $scope.response.ard,
							webex : $scope.response.webex,
							rtc : $scope.response.rtc,
							jenkins : $scope.response.jenkins,
							alm : $scope.response.alm,
							iib : $scope.response.iib,
							db2 : $scope.response.db2,
							db2explorer : $scope.response.db2explorer,
							mq : $scope.response.mq,
							mqexplorer : $scope.response.mqexplorer,
							putty : $scope.response.putty,
							winscp : $scope.response.winscp,
							clientInd : $scope.response.clientInd,
							sisInd : $scope.response.sisInd,
							arch : $scope.response.arch,
							comPatterns : $scope.response.comPatterns,
							devTech : $scope.response.devTech,
							testFram : $scope.response.testFram,
							cdWalkthrough : $scope.response.cdWalkthrough,
							agile : $scope.response.agile,
							codecov : $scope.response.codecov,
							ddt : $scope.response.ddt, // Changed field
							logfram : $scope.response.logfram,
							esqlGen : $scope.response.esqlGen,
							// New Fields
							internalId : $scope.response.internalId,
							comments : $scope.response.comments,
							buildProcess : $scope.response.buildProcess,
							cicd : $scope.response.cicd,
							codingStd : $scope.response.codingStd,
							governanceTool : $scope.response.governanceTool

						};

						$http.put(url, resource, config).then(
								function(response) {
									$scope.editing = false;
									$scope.postResultMessageS = response.data;
								}, function error(response) {
									// alert(url);
									$scope.postResultMessageE = response.data;
								});

						$scope.firstname = "";
						$scope.lastname = "";
					}

					// Reload User
					$scope.reloaduserinfo = function() {
						var user = $window.localStorage['role'];
						$scope.postResultMessageE = '';
						$scope.postResultMessageS = '';
						$scope.user = user;
						if (user != 'user' && user != 'admin') {
							$window.location.href = "http://" + $location.host() + ":"+ $location.port() + "/";
						}
						var cid = $window.localStorage['cid'];
						if (cid) {
							var url = $location.absUrl() + "/getuserInfo/"
									+ cid;
							$scope.editing = false;
							$http
									.get(url)
									.then(
											function(response) {
												$scope.response = response.data;
												$scope.visible = true;
												$window.localStorage['cid'] = '';
											},
											function error(response) {
												$scope.postResultMessageE = "Oops! Something went wrong. we got error : "
														+ response.statusText;
												$scope.visible = true;
												$window.localStorage['cid'] = '';
											});
						} else {
							$scope.visible = false;
							cid = $window.localStorage['cid'] = '';
						}

					}
				});

// This controller is being used to create the record
createApp
		.controller(
				'createcontroller',
				function($scope, $http, $location, $window) {
					// INIT FOR CREATE PAGE	
					$scope.getloggedInUser = function() {
						
						//alert($location.host());
						//alert($location.port());
						var user = $window.localStorage['role'];
						$scope.user = user;
						$scope.cid=$window.localStorage['uname'];
						
						if (user != 'user' && user != 'admin') {
							$window.location.href = "http://" + $location.host() + ":"+ $location.port() + "/";
						}
					}

					$scope.createuserinfo = function() {
						// alert($location.absUrl());
						$scope.postResultMessageE = '';
						$scope.postResultMessageS = '';
						var uname = $window.localStorage['uname'];
						var cid=$scope.response.cid;
						var url = $location.absUrl() + "/createuser";
						// alert(url);
						var config = {
							headers : {
								'Accept' : 'text/plain'
							}
						}
						var resource = {
							cid : $scope.response.cid,
							name : $scope.response.name,
							role : $scope.response.role,
							band : $scope.response.band,
							nbsid : $scope.response.nbsid,
							rate : $scope.response.rate,
							ibmid : $scope.response.ibmid,
							blocation : $scope.response.blocation,
							clocation : $scope.response.clocation,
							vdi : $scope.response.vdi,
							readyAPI : $scope.response.readyAPI,
							adc : $scope.response.adc,
							ard : $scope.response.ard,
							webex : $scope.response.webex,
							rtc : $scope.response.rtc,
							jenkins : $scope.response.jenkins,
							alm : $scope.response.alm,
							iib : $scope.response.iib,
							db2 : $scope.response.db2,
							db2explorer : $scope.response.db2explorer,
							mq : $scope.response.mq,
							mqexplorer : $scope.response.mqexplorer,
							putty : $scope.response.putty,
							winscp : $scope.response.winscp,
							clientInd : $scope.response.clientInd,
							sisInd : $scope.response.sisInd,
							arch : $scope.response.arch,
							comPatterns : $scope.response.comPatterns,
							devTech : $scope.response.devTech,
							testFram : $scope.response.testFram,
							cdWalkthrough : $scope.response.cdWalkthrough,
							agile : $scope.response.cdWalkthrough,
							codecov : $scope.response.codecov,
							ddt : $scope.response.ddt,
							logfram : $scope.response.logfram,
							esqlGen : $scope.response.esqlGen,
							comments : $scope.response.comments,
							buildProcess : $scope.response.buildProcess,
							cicd : $scope.response.cicd,
							codingStd : $scope.response.codingStd,
							governanceTool : $scope.response.governanceTool
						};
						var user = $window.localStorage['role'];
						if (cid==uname || user=='admin') {
							// alert("calling create "+url);
							$http
									.post(url, resource, config)
									.then(
											function(response) {
												$scope.postResultMessageS = response.data;
											},
											function error(response) {
												$scope.postResultMessageE = response.data;
											});
						} else {
							$scope.postResultMessageE = "You can not create record on other's behalf!"
						}

						$scope.firstname = "";
						$scope.lastname = "";
					}
				});

// ============================================================LOGIN================================================================================================

loginApp.controller('logincontroller', function($scope, $http, $location,
		$window) {
	$scope.login = function() {
		var loginStatus = '';
		var loginType = $scope.loginType;
		$scope.postResultMessage = '';
		$scope.postResultMessageE = '';
		var uname = $scope.uname;
		var password = $scope.password;
		var config = {
			headers : {
				'Accept' : 'text/plain'
			}
		}
		var data = {
			cid : uname,
			password : password
		}

		if (loginType == 'L') {
			var url = $location.absUrl() + "/login";
			$http.post(url, data, config).then(
					function(response) {
						loginStatus = response.data;
						//alert(response.data);
						if (loginStatus == "success") {
							if (uname == 'admin') {
								$scope.hide = true;
								$window.localStorage['role'] = 'admin';
								$window.location.href = "http://" + $location.host() + ":"+ $location.port()+ "/onboarding/info";
							} else {
								$scope.hide = true;
								$window.localStorage['uname'] = uname;
								$window.localStorage['role'] = 'user';
								$window.location.href = "http://" +$location.host() + ":"+ $location.port()+ "/onboarding";
							}
						} else {
							alert("login Failure")
							$window.localStorage['role'] = 'guest';
						}
					}, function error(response) {
						//alert("Error Login123 " + response.data);
						$scope.postResultMessageE = response.data;
					});
		} else {
			var url = $location.absUrl() + "/login/create";
			$http.post(url, data, config).then(
					function(response) {
						//alert(response.data);
						loginStatus = response.data;
						$scope.postResultMessage = 'Sign-Up is Successful!';
						if (loginStatus == "success") {
							if (uname == 'admin') {
								$scope.hide = true;
								$window.localStorage['role'] = 'admin';
								$window.location.href = "http://" + $location.host() + ":"+ $location.port()+"/onboarding/info";
							} else {
								$scope.hide = true;
								$window.localStorage['uname'] = uname;
								$window.localStorage['role'] = 'user';
								$window.location.href = "http://" + $location.host() + ":"+ $location.port()+ "/createrecord";
							}
						} else {
							alert("login Failure S")
							$window.localStorage['role'] = 'guest';
						}
					}, function error(response) {
						//alert("Error create " + response.data);
						$scope.postResultMessageE = response.data;
					});

		}

	}

	$scope.refresh = function() {

		$scope.loginType = 'L';
		//	alert($scope.loginType);
		$window.localStorage['uname'] = "";
		$window.localStorage['role'] = "";
	}

});

// =================================================================================================================================================================
