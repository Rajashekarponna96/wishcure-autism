function vsmsQuestionCtrl($scope,$state,$rootScope,$stateParams,vsmsQuestionService,vsmsClusterService,successMessageHandler,patientService){

	$scope.vsmsQuestion = {};
	$scope.vsmsQuestionsList = [];
	$scope.patientVSMSQuestionList = [];
	$scope.patientVSMSReportList = [];
	$scope.mcluster = {};
	$scope.patient = {};
	$scope.vsmsQuestionDto = {};
	$scope.isVsmsQuestionsExists = false;
	$scope.vsmsQuestionsCount={};
	$scope.vsmsReportGraphDTO={};
	$scope.vsmsClusterLabels=[];
	$scope.vsmsClusterData=[];
	$scope.type = 'bar';
    $scope.colors = ['#97BBCD', '#DCDCDC', '#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'];
	
	
	if ($stateParams.patient != undefined) {
		$scope.patient = $stateParams.patient;

	}
	if ($stateParams.patientObject != undefined) {
		$scope.patientObject = $stateParams.patientObject;
		
	}

	/*if ($stateParams.motocluster != undefined) {
		$scope.mcluster = $stateParams.motocluster;
	}*/

	// for save
	$scope.addVSMSQuestion = function() {
		vsmsQuestionService.addVSMSQuestion($scope.vsmsQuestion).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.VSMS_CLUSTER_ADDED );
					$state.go('main.vsmsQuestionList');
					
				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
				})
	}

	// for getAll vsms Questions with out pagination
	$scope.getVsmsQuestions = function() {
		vsmsQuestionService.getVsmsQuestions().then(function(response) {
			$scope.vsmsQuestionsList = response.data;
		}, function(error) {

		})
	}

	$scope.gotoupdate = function(vsmsQuestion) {
		$state.go('main.updateQuestionCluster', {
			vsmsQuestion : vsmsQuestion
		});
	};
	
	// for update vsmsQuestions
	$scope.updateVSMSQuestion = function(vsmsQuestion) {
		vsmsQuestionService.updateVSMSQuestion(vsmsQuestion).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.VSMS_QUESTION_UPDATED );
					$state.go('main.vsmsQuestionList');

				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');

				})
	}

	// for detele VsmsQuestion
	$scope.deleteVSMSQuestion = function(vsmsQuestionId) {
		vsmsQuestionService.deleteVSMSQuestion(vsmsQuestionId).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.VSMS_QUESTION_DELETED);
					$scope.getVsmsQuestions();
				}, function(error) {

				})
	}

	$scope.gotoList = function() {
		$state.go('main.vsmsQuestionList');
	};

	$scope.gotoback = function() {
		$state.go('main.vsmsQuestionList');
	};

	$scope.gotoAdd = function() {
		$state.go('main.VSMSQuestion');
	};
	// for getAll vsms clusters with out pagination
	$scope.getVsmsClusters = function() {
		vsmsClusterService.getVSMSClusters($scope.patient.id).then(function(response) {
			$scope.vsmsClustersList = response.data;
		}, function(error) {

		})
	}
	$scope.ppVSMSChange = function(status) {
	     console.log(status);
	     var keepGoing = true;
	     
	     angular.forEach($scope.vsmsQuestionsList, function (value, key)
	             {
	    	 if(keepGoing){ 
	    	 //console.log(key);
	                    //console.log(value);
	                    if (value.vsmsQuestionStatus == null) {
	                    	value.vsmsQuestionStatus="P";
	                    }else if(value.vsmsQuestionStatus == "PP"){
	                    	keepGoing = false;
	                    }
	    	 } 
	             });
	    
	             
	}
	
	$scope.rfVSMSChange = function(status) {
	     console.log(status);
	     angular.forEach($scope.vsmsQuestionsList, function (value, key)
	             {
	    	
	    	 //console.log(key);
	                    //console.log(value);
	                    if (value.vsmsQuestionStatus == "RF") {
	                    	value.vsmsQuestionStatus="RF";
	                    }
	                    else if (value.vsmsQuestionStatus == null)
	                    {
	                    	value.vsmsQuestionStatus="F";
	                    	
	                    }	    	 
	             });
	    
	             
	}
	
	$scope.assignvsmsQuestionstoPatient = function() {
	console.log("vsmsQuestionsList "+ $scope.vsmsQuestionsList);

	//convert vsmsQuestionsList to patientVSMSQuestionList
	$scope.vsmsQuestionDto = {
			"vsmsQuestionsList" : $scope.vsmsQuestionsList,
			"patient" : $scope.patient
		}
	vsmsQuestionService.assignvsmsQuestionstoPatient($scope.vsmsQuestionDto).then(
			function(response) {
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-right",
	                    timeOut: 2000
	                };
				$scope.vsmsQuestionsCount = 1;
				 angular.forEach($scope.vsmsQuestionsList, function (value, key)
			             {
			    	
			    	 //console.log(key);
			                    //console.log(value);
			                    if (value.vsmsQuestionStatus == "P") {
			                    	$scope.vsmsQuestionsCount++;
			                    
			                    }
			    	 
			             });
			     alert("Count "  +$scope.vsmsQuestionsCount);
	                toastr.success('',successMessageHandler.VSMS_CLUSTER_ADDED );
				//$state.go('main.vsmsQuestionList');
				
			}, function(error) {
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-right",
	                    timeOut: 2000
	                };
				$scope.message = JSON.stringify(error.data.message.trim());
				toastr.error($scope.message, 'Error');
			})

	}
	$scope.getAllPatientVsmsQuestionsByPatientId = function() {
		vsmsQuestionService.getAllPatientVsmsQuestionsByPatientId(
				$scope.patientObject.id).then(function(response) {
			$scope.patientVsmsQuestionslist = response.data;
			if($scope.patientVsmsQuestionslist == ""){
				$scope.isVsmsQuestionsExists = false; 
			}else{
				$scope.isVsmsQuestionsExists = true;
			}
			
		}, function(error) {

		})
	};
	$scope.gotoReport = function() {
		$state.go('main.vsmsReportGraph', {
			patientObject : $scope.patient
		});
	};
	
	$scope.getVSMSReportData = function(){
		console.log("$scope.patient "+$scope.patient);
		console.log("$scope.patient "+$scope.patient.id);
		console.log("$scope.patient "+$scope.patientObject.id);
		vsmsQuestionService.getVSMSReportData($scope.patientObject.id).then(function(response) {
			$scope.vsmsReportGraphDTO=response.data;
			$scope.patientVSMSReportList = $scope.vsmsReportGraphDTO.vsmsChildPerformanceDTOList;
		}, function(error) {

		})
		console.log("getVSMSReportData");
		
	}
	$scope.getDoctorByPatientId = function() {
		patientService.findDoctorByPatientId($scope.patientObject.id).then(
				function(response) {
					$scope.doctorObj = response.data;
					$scope.doctorName=$scope.doctorObj.firstName+" "+$scope.doctorObj.lastName;
				}, function(error) {

				})
	};
	
	$scope.getPatientVSMSCluterAndClusterCountForGraph = function() {
		vsmsQuestionService.getVSMSReportData($scope.patientObject.id).then(function(response) {
			$scope.vsmsReportGraphDTO=response.data;
			$scope.patientVSMSReportList = $scope.vsmsReportGraphDTO.vsmsChildPerformanceDTOList;
			
			angular.forEach($scope.patientVSMSReportList, function(value, key) {
				  this.push( value.nameOfCluster);
				}, $scope.vsmsClusterLabels);
			angular.forEach($scope.patientVSMSReportList, function(value, key) {
				  this.push( value.totalNoOfItemsPassed);
				}, $scope.vsmsClusterData);
			
			
		}, function(error) {

		})
			
	};
	
	

}
angular.module('HealthApplication').controller("vsmsQuestionCtrl", vsmsQuestionCtrl);