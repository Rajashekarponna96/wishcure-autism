function PatientCtrl($scope, $state, $rootScope, goalTemplateService, subCategoryService, $http,therapistService,appointmentService,
		$log, evalutionSheetService, $sce, patientGoalService, $stateParams,paymethodService,currencyService,regionalCenterZoneService,
		patientService, evalutionCategorService,schoolService, countryService,departmentService,insuranceService, categoryTypeService,
		companyService, templateService, clientTypeService,mchartLookupService,PatientNichqTeacherCategoryservice,PatientNichqParentCategoryservice,nichqservice,
		PATIENT_MODULE_CONFIG,MODULE_CONFIG,$uibModal ,successMessageHandler, isaaBehaviourLookUpService,patientIsaaBehaviourCategoryService, nichqTemplateService) {
	$scope.page = 0;
	$scope.payment={};
	$scope.size = 5;
	$scope.patientdata = {};
	$scope.patient = {
			"departments":[]
	};
	$scope.subCategorys = [];
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; //January is 0!

	var yyyy = today.getFullYear();
	if (dd < 10) {
	  dd = '0' + dd;
	} 
	if (mm < 10) {
	  mm = '0' + mm;
	} 
	var today = dd + '-' + mm + '-' + yyyy;
	$scope.patient.regDate = today;
	
	$scope.patient.regNumber = "CHAUT"+Math.floor(Math.random()*10000) + 1;
	$scope.payment.paymentId = "CHAUT"+Math.floor(Math.random()*10000) + 1;
	
	$scope.progress = {};
	$scope.paymentsList = [];
	$scope.progressNote1 = {};
	$scope.exitNote = {};
	$scope.speechEvaluation = {};
	$scope.patientGoalDto = {};
	$scope.patientGoalDtoObject = {};
	$scope.flagstatus=false;
	$scope.flag=false;
	$scope.invoiceList=[];
	$scope.status1=false;
	$scope.status2=false;
	$scope.status3=false;
	$scope.status4=false;
	$scope.status5=false;
	$scope.inActivePatientsObject=false;
	$scope.todayPatientsObject=false
	$scope.regional1={};
	$scope.privateClient={};
	$scope.school={};
	$scope.patientPayments={};
	$scope.insurance={};
	$scope.obj123={};
	$scope.patientSpeechTemplates=[];
	$scope.patientSpeechTemplatesInEvalution=[];
	$scope.stateChanged=false;
	$scope.regionalZone={};
	$scope.address1={};
	$scope.reginoalCenterAddress={};
	$scope.region={};
	$scope.clientBillingList=[];
	$scope.s3BucketUrl=undefined;
	$scope.billingsFlag=false;
	$scope.invoiceFlag=false;
	$scope.patientMentalScalelist = {};
	$scope.isselected=false;
	$scope.isselectedgoalsheet=false;
	$scope.isselectedpatientdocument=false;
	$scope.isDisabled = false;
	$scope.editMode = false;
	$scope.selectAssessent=false;
	$scope.editProgress = false;
	$scope.editExit = false;
	$scope.isMentalScaleExists = false;
	$scope.isMotorScaleExists = false;
	$scope.isSpeechEvalutionExists = false;
	$scope.isBehaviouralManagementExists = false;
	$scope.evalutionDatesList = [];
	$scope.speechEvaluation = {};
	$scope.behaviourlManagement = {};
	
	$scope.type = 'bar';
    $scope.colors = ['#97BBCD', '#DCDCDC', '#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'];
	 $scope.mentalAgeClusterLabels = [];
     $scope.mentalAgeClusterData = [];
     $scope.motorAgeClusterLabels = [];
     $scope.motorAgeClusterData = [];
     
     
	
	$scope.sort = function(keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; // if true make it false and vice
											// versa
	}
	$scope.showFrame=function(){
		$scope.showIframe = !$scope.showIframe;
		$scope.flag==true;
	};
	$scope.showFrame1=function(){
		$scope.showIframe = !$scope.showIframe;
		$scope.flag==false;
	};
	if ($stateParams.patientId != undefined) {
		$scope.PatientId = $stateParams.patientId;
	}
	if($scope.patient !=undefined){
		if($scope.patient.therapyAddress !=undefined){
			$scope.ModelData=true;
		}else{
			$scope.ModelData=false;
		}
	}
	
	if($stateParams.documentTypelookup != undefined){
		$rootScope.lookupFolder=$stateParams.documentTypelookup;
		$scope.lookupFolder=$rootScope.lookupFolder;
	}
	if($stateParams.documentsList1 !=undefined){
		$rootScope.documents=	$stateParams.documentsList1;
		$scope.documents=$rootScope.documents;
	}
	
	if($stateParams.PatientDocuments !=undefined){
		$scope.pdocuments =	$stateParams.PatientDocuments;
	}
	
	if($stateParams.patientObject !=undefined){
		$scope.patientObject =	$stateParams.patientObject;
	}
	
	/*if($stateParams.progress1 !=undefined){
		$scope.progress =	$stateParams.progress1;
	}*/
	if($stateParams.progress !=undefined){
		$scope.progress =	$stateParams.progress;
	}
	if($stateParams.regionalZone !=undefined){
		$rootScope.regionalZone=	$stateParams.regionalZone;
		$scope.regionalZone=$rootScope.regionalZone;
	}
	if($stateParams.mchatPatient != undefined){
		$scope.patient=$stateParams.mchatPatient;
	}
	
	
	
	 $scope.names = [
	                 {id:"1",firstName:'Jani',gaurdian:'John',mobileNumber:"987766543",email:"jani@gmail.com",a:"1000",b:"2000",c:"Insurance",
	                	 d:"lic pvt ltd",e:"65654",f:"1000",g:"1000",h:"0",z:"Speech Therapy"}];
	 
	 $scope.items1 = [{name:"item1",id:"1",firstName:'$39.20',gaurdian:'$71.00',mobileNumber:"$27.98"},
	                  {name:"item2",id:"2",firstName:'$57.00',gaurdian:'$56.80',mobileNumber:"$112.80"},
	                  {name:"item3",id:"3",firstName:'$645.00',gaurdian:'$321.20',mobileNumber:"$1286.20"},
	                  {name:"item4",id:"4",firstName:'$486.00',gaurdian:'$524.20',mobileNumber:"$789.20"}];
	 $scope.add="1355 Market Street, Suite 900 San Francisco, CA 94103";
	 $scope.email="john@gmail.com";
	 $scope.name="john";
	 // $scope.invoiceNo=$scope.data;
	 // "0044777";
	 $scope.mobileNumber="987766543";
	 $scope.issued="March 19th, 2017";
	 $scope.due="April 21th, 2017";
	if($scope.patientObject !=undefined){
	if($scope.patientObject.therapyAddress != undefined){
		$scope.ModelData123=true;
	}else{
		$scope.ModelData123=false;
	}
	}
	// converting dd/mm/yyyy formate to mm/dd/yyyy
	$scope.changeDate = function(appointmentStartedDate) {
		var currentDate = appointmentStartedDate; // Extracting the date value
		// in dd/mm/yyyy format from
		// the mentioned text box
		var newDate = currentDate.split('-'); // Splitting the extracted date
		// value using the delimiter /,
		// which is the seperator used
		// in the date value
		$scope.currentDate = newDate[1] + "-" + newDate[2].substring(0, 2)
				+ "-" + newDate[0];// Constructing a new date value (string)
		// using the splitted values.
		return $scope.currentDate;
	}
	
	// converting dd/mm/yyyy formate to mm/dd/yyyy
	$scope.changeDateForMonth=function(appointmentStartedDate){
		var currentDate= appointmentStartedDate; // Extracting the date value
													// in dd/mm/yyyy format from
													// the mentioned text box
		var newDate = currentDate.split('-'); // Splitting the extracted date
												// value using the delimiter /,
												// which is the seperator used
												// in the date value
		currentDate = newDate[1] + "-" + newDate[0] + "-" + newDate[2];// Constructing
																		// a new
																		// date
																		// value
																		// (string)
																		// using
																		// the
																		// splitted
																		// values.
		return currentDate;
	}
	if ($stateParams.patientObject != undefined) {
		$scope.patientObject = $stateParams.patientObject;
		
	}
	
	/*
	 * $scope.getAllgoals = function() {
	 * goalTemplateService.getAllgoals().then(function(response) {
	 * $scope.flag=true; $scope.goalList = response.data; }, function(error)
	 * {}); };
	 */
	function validateForm() {
		var x = $scope.patientForm.firstName.$valid;
		if (x == flase) {
			return false;
		}
	}

	$scope.changeMobileNumber = function(mobileNumber) {
		if (mobileNumber == 0) {
			var number = "";
			return number;
		}
		var currentNumber = mobileNumber.toString();
		changedNumber = "(" + currentNumber.substring(0, 3) + ")"
				+ currentNumber.substring(3, 6) + "-"
				+ currentNumber.substring(6, 10);
		return changedNumber;
	}
	
	$scope.getAlltherapistsByAdminUsername = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		therapistService.getAlltherapistsByAdminUsername($scope.adminUserName).then(
				function(response) {
					$scope.doctorsList = response.data;
				}, function(error) {
				});
	};
	
	// multiplication table
	const d = [
	  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
	  [1, 2, 3, 4, 0, 6, 7, 8, 9, 5], 
	  [2, 3, 4, 0, 1, 7, 8, 9, 5, 6], 
	  [3, 4, 0, 1, 2, 8, 9, 5, 6, 7], 
	  [4, 0, 1, 2, 3, 9, 5, 6, 7, 8], 
	  [5, 9, 8, 7, 6, 0, 4, 3, 2, 1], 
	  [6, 5, 9, 8, 7, 1, 0, 4, 3, 2], 
	  [7, 6, 5, 9, 8, 2, 1, 0, 4, 3], 
	  [8, 7, 6, 5, 9, 3, 2, 1, 0, 4], 
	  [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
	]

	// permutation table
	const p = [
	  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9], 
	  [1, 5, 7, 6, 2, 8, 3, 0, 9, 4], 
	  [5, 8, 0, 3, 7, 9, 6, 1, 4, 2], 
	  [8, 9, 1, 6, 0, 4, 3, 5, 2, 7], 
	  [9, 4, 5, 3, 1, 2, 6, 8, 7, 0], 
	  [4, 2, 8, 6, 5, 7, 3, 9, 0, 1], 
	  [2, 7, 9, 3, 8, 0, 6, 4, 1, 5], 
	  [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
	]

	// validates Aadhar number received as string
	function validate(aadharNumber) {
	  let c = 0
	  let invertedArray = aadharNumber.split('').map(Number).reverse()

	  invertedArray.forEach((val, i) => {
		  c = d[c][p[(i % 8)][val]]
	  })

	  return (c === 0)
	}
	
	$scope.searchByPatient = function(regNo,aadhaarNo) {
		if(regNo != undefined){

			patientService.searchPatientByRegNo(regNo).then(function(response) {
				$scope.patientdata = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		}
		
		if(aadhaarNo  != undefined){

			if (validate(aadhaarNo) == true) {
				patientService.searchPatientBySSN(aadhaarNo).then(function(response) {
					$scope.patientdata = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
				}
		}
		
	};
	
	$scope.getSubAppointmentsByInvoice = function(appointment) {
		// $scope.adminUserName = $rootScope.loggedUsername;
		appointment.adminUsername=$rootScope.loggedUsername;
		if(appointment.startDate!=null&&appointment.startDate!=null){
		 var date1 = appointment.startDate; 
		  mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
		  var startedDate =[day,mnth,date1.getFullYear() ].join("-");
		 
		  var date = appointment.endDate; 
		  mnth = ("0" +(date.getMonth() + 1)).slice(-2), day = ("0" + date.getDate()).slice(-2);
		  var endedDate =[day,mnth,date.getFullYear() ].join("-");
		  
		  appointment.startDate=startedDate;
		  appointment.endDate=endedDate;
		}
		appointmentService.getSubAppointmentsByInvoice(appointment).then(
				function(response) {
					$scope.appointmentsList = response.data;
					appointment.startDate=undefined;
					appointment.endDate=undefined;
					appointment.doctor=undefined;
				
				}, function(error) {
					appointment.startDate=undefined;
					appointment.endDate=undefined;
				});
	};
	if ($stateParams.inActivePatientsObject != undefined) {
		$scope.inActivePatientsObject = $stateParams.inActivePatientsObject;
	}
	if ($stateParams.todayPatientsObject != undefined) {
		$scope.todayPatientsObject = $stateParams.todayPatientsObject;
	}
	// navigation through dashboard
	if($scope.inActivePatientsObject==true){
		$scope.findAllpatientsByPaginationByRole = function() {
			patientService.findAllpatientsByPaginationByRoleByInactive($rootScope.loggedUsername ,$scope.page, $scope.size).then(
				function(response) {
					$scope.patientListByAdmin = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				});
		};
	}
	else if($scope.todayPatientsObject==true){
		$scope.findAllpatientsByPaginationByRole = function() {
			patientService.findAllpatientsByPaginationByRoleAndTodayDate($rootScope.loggedUsername ,$scope.page, $scope.size).then(
				function(response) {
					$scope.patientListByAdmin = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				});
		};
	}
	else{
	$scope.findAllpatientsByPaginationByRole = function(search) {
		if(search == " " || search == undefined || search == ""){
			search=null;
		}
		patientService.findAllpatientsByPaginationByRole(
				$rootScope.loggedUsername,search, $scope.page, $scope.size).then(
				function(response) {
					$scope.patientListByAdmin = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				});
	};
	}

	$scope.findAllpatientsSearchByRole = function(search) {
		if(search == " " || search == undefined || search == ""){
			$scope.findAllpatientsByPaginationByRole();
		}
		patientService.findAllPatientSearchByRole($rootScope.loggedUsername,search,$scope.page, $scope.size).then(
				function(response) {
					$scope.patientListByAdmin = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				});
	};
	
	$scope.getAllPatients = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		patientService.getAllPatients($scope.adminUserName).then(
				function(response) {
					$scope.patientList = response.data;
				}, function(error) {
				});
	};

	$scope.ngInitMethod = function() {
		if ($rootScope.loggedUserRole = "Therapist") {
			$scope.getAppointedPatientsByTherapist();

		} else {
			$scope.findAllpatientsByPaginationByAdmin();
		}
	};

	$scope.getAllTemplates = function() {
		templateService.getAllTemplates().then(function(response) {
			$scope.templateList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllStatesByCountryId = function(id) {
		countryService.getAllStatesByCountryId(id).then(function(response) {
			$scope.statesList = response.data;
		}, function(error) {
		})
	};
	$scope.getAllStatesByCountryId123 = function(id) {
		countryService.getAllStatesByCountryId(id).then(function(response) {
			$scope.statesList123 = response.data;
		}, function(error) {
		})
	};
	
	
	$scope.getAllClients = function() {
		clientTypeService.getAllClients().then(function(response) {
			$scope.clientsList = response.data;
			angular.forEach($scope.clientsList,function(client){
    			if(client.clientTypeName!="Regional Center"){
    				$scope.clientBillingList.push(client);
    			}
			})
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllCitiesByStateId = function(id) {
		countryService.getAllCitiesByStateId(id).then(function(response) {
			$scope.citiesList = response.data;
		}, function(error) {
		})
	};
	$scope.getCountrysBasedOnIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
			if($scope.patientObject != undefined){
				if($scope.patientObject.address != undefined){
					$scope.getAllStatesByCountryId($scope.patientObject.address.country.id);
					$scope.getAllCitiesByStateId($scope.patientObject.address.state.id);
				}
			
			if($scope.patientObject.regionalCenter != undefined){
				if($scope.patientObject.regionalCenter.reginoalCenterAddress != undefined){
					$scope.getAllStatesByCountryId($scope.patientObject.regionalCenter.reginoalCenterAddress.country.id);
					$scope.getAllCitiesByStateId($scope.patientObject.regionalCenter.reginoalCenterAddress.state.id);
				}
			}
			if($scope.patientObject.insurance !=undefined){
				if($scope.patientObject.insurance.address != undefined){
					$scope.getAllStatesByCountryId($scope.patientObject.insurance.address.country.id);
					$scope.getAllCitiesByStateId($scope.patientObject.insurance.address.state.id);
				}
			}
			}
			if($scope.patientObject != undefined){
				if($scope.patientObject.therapyAddress !=undefined){
					$scope.getAllStatesByCountryId($scope.patientObject.therapyAddress.country.id);
					$scope.getAllCitiesByStateId($scope.patientObject.therapyAddress.state.id);
			}
			}
				/*if($scope.patientObject != undefined){
					if($scope.patientObject.school.schoolAddress !=undefined){
						$scope.getAllStatesByCountryId($scope.patientObject.school.schoolAddress.country.id);
						$scope.getAllCitiesByStateId($scope.patientObject.school.schoolAddress.state.id);
				}
				}*/
					if($scope.patientObject != undefined){
						if($scope.patientObject.privateClient!=undefined){
						if($scope.patientObject.privateClient.address!=undefined){
							$scope.getAllStatesByCountryId($scope.patientObject.privateClient.address.country.id);
							$scope.getAllCitiesByStateId($scope.patientObject.privateClient.address.state.id);
					}
						}
			}
					if($scope.patient != undefined){
					if($scope.patient.regionalCenter != undefined){
						if($scope.patient.regionalCenter.reginoalCenterAddress!=undefined){
							$scope.getAllStatesByCountryId($scope.patient.regionalCenter.reginoalCenterAddress.country.id);
							$scope.getAllCitiesByStateId($scope.patient.regionalCenter.reginoalCenterAddress.state.id);
					}
			}
					if($scope.patient.privateClient != undefined){
						if($scope.patient.privateClient.address!=undefined){
							$scope.getAllStatesByCountryId($scope.patient.privateClient.address.country.id);
							$scope.getAllCitiesByStateId($scope.patient.privateClient.address.state.id);
					}
			}
					if($scope.patient.insurance != undefined){
						if($scope.patient.insurance.insuranceAddress!=undefined){
							$scope.getAllStatesByCountryId($scope.patient.insurance.insuranceAddress.country.id);
							$scope.getAllCitiesByStateId($scope.patient.insurance.insuranceAddress.state.id);
					}
			}
					
					if($scope.patient.school != undefined){
						if($scope.patient.school.schoolAddress!=undefined){
							$scope.getAllStatesByCountryId($scope.patient.school.schoolAddress.country.id);
							$scope.getAllCitiesByStateId($scope.patient.school.schoolAddress.state.id);
					}
			}
					}
		}, function(error) {
		})
	};
	
	$scope.fileChanged = function(files) {
		if (files != null) {
			var file = files[0];
			$scope.filepath = files[0];
			patientService.uploadDocument($scope.filepath).then(
					function(response) {
						$scope.path = response.data.imagePath;
						$scope.signaturePath = $scope.path;
					}, function(eror) {
					});
		}
	};
	
	$scope.clearResponse=function(){
		$scope.documents = [];
	};
	$scope.documents = [];
	$scope.documentChanged = function(files) {
		$scope.documents=[];
		if (files != null) {
			var file = files[0];
			$scope.filepath = files[0];
			patientService.uploadDocument($scope.filepath).then(
					function(response) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						toastr
								.success('',
										successMessageHandler.DOCUMENT_UPLOADED_SUCCESSFULLY);
						$scope.path = response.data.imagePath;
						$scope.document=response.data;
						$scope.document.folder=$scope.folder;
						$scope.documents.push($scope.document);
						files=undefined;
					},
					function(error) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						$scope.message = JSON.stringify(error.data.message
								.trim());
						toastr.error($scope.message, 'Error');
					});
		}
	};
	$scope.folder={};
	
	$scope.addDocument= function() {
		$scope.patientObject.adminUser = $rootScope.loggedUsername;
		// $scope.patientObject.documents = $scope.documents;
		 $scope.patientObject.folders  =$scope.listOfFolders;
		 $scope.patientInfo={
				 "patient":$scope.patientObject,
				 "documents":$scope.documents
		 }
		 if($scope.folder == undefined || $scope.folder.name == null){
			 toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-right",
						timeOut : 2000
					};
					toastr.error("Please Select Folder!", 'Error');
		 }else if($scope.documents == undefined || $scope.documents=="" || $scope.documents.length <0){
			 toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-right",
						timeOut : 2000
					};
					toastr.error("Please Select File!", 'Error');
		 }else{
			 patientService.addDocument($scope.patientInfo).then(function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.DOCUMENT_ADDED_SUCCESSFULLY);
					$scope.documents=undefined;
					$scope.folder = undefined;
					// $state.go('main.departmentlist');
				}, function(error) {
					toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
					
				});
		 }
	};
	
	 $scope.updateFolderName=function(folder){
		 patientService.updateFolderName(folder).then(function(response) {
				toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				toastr.success('', successMessageHandler.FOLDER_UPDATED_SUCCESSFULLY);
				// $state.go('main.departmentlist');
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		 
	 };
		
	// $scope.lookupFolder={};
  /*
	 * $scope.patientInfoDto={ "patient":{} , "documentTypeLookup":{} ,
	 * "documents":[] }
	 */
	 $scope.getRegionalCenterZones=function(){
		
		 patientService.getRegionalCenterZones().then(function(response) {
				$scope.regionalZoneList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
	 
	 $scope.getRegionalCentersByRegionalZone=function(id){
			patientService.getRegionalCenters(id,$rootScope.loggedUsername).then(function(response) {
				$scope.regionalList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		$scope.getschoolClientTypes=function(){
			patientService.getschoolClientTypes().then(function(response) {
				$scope.schoolList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		$scope.getAllSchoolsByCompanyUserNamePage = function() {
			schoolService.getAllSchoolsByCompanyUserNamePage($rootScope.loggedUsername,$scope.page, $scope.size)
					.then(function(response) {
						$scope.schoolList = response.data.content;
						$scope.totalElements = response.data.totalElements;
						$scope.totalPages = response.data.totalPages-1;
						$scope.noOfPgaes(response.data.totalPages);
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllSchoolsByCompanyUsername = function() {
			schoolService.getAllSchoolsByCompanyUsername($rootScope.loggedUsername)
					.then(function(response) {
						$scope.schoolList = response.data;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		$scope.getPrivateClienTypes=function(){
			patientService.getPrivateClienTypes().then(function(response) {
				$scope.privateList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		$scope.getInsurenceClientTypes=function(){
			patientService.getInsurenceClientTypes().then(function(response) {
				$scope.insurenceList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		$scope.getObject=function(regional){
			// $scope.regional1=regional;
			$scope.patient.regionalCenter=regional;
			/* return $scope.regional; */
		};
		$scope.getObject1=function(regional){
			$scope.patient.regionalCenter=regional;
			/* return $scope.regional; */
		};
		$scope.getObjectUpdate=function(regional){
			$scope.patientObject.regionalCenter=regional;
		}
		$scope.getPrivteClientObj=function(privateClient){
			$scope.patient.privateClient=privateClient;
			return $scope.patient.privateClient;
		};
		$scope.getPrivteClientObj1=function(privateClient){
			$scope.patient.privateClient=privateClient;
			return $scope.patient.privateClient;
		};
		$scope.getSchoolObject=function(school){
			$scope.school=school;
			$scope.patient.school=school;
			return $scope.patient.school;
		};
		$scope.getSchoolObject1=function(school){
			$scope.patient.school=school;
			$scope.getAllStatesByCountryId($scope.patient.school.schoolAddress.country.id);
			return $scope.patient.school;
		};
		$scope.getInsurenceObj1=function(insurance){
			$scope.patient.insurance=insurance;
			// $scope.getAllStatesByCountryId($scope.insurance.insuranceAddress.country.id);
			return $scope.patient.insurance;
		};
		$scope.getInsurenceObj=function(insurance){
			$scope.patient.insurance=insurance;
			$scope.getAllStatesByCountryId($scope.insurance.insuranceAddress.country.id);
			return $scope.patient.insurance;
		};
	

	
	

	$scope.gotoPatientViewPage = function(patient) {
		$state.go('main.patientviewtabs.patient_documents', {
			patientObject : patient
		});
	};

	$scope.gotoPatientDetails = function(patient) {
		$rootScope.userForSession=patient.email;
		$rootScope.patintId=patient.id;
		$state.go('main.patientDetails', {
			patientObject : patient
		});
	};
	/*
	 * $scope.getAllQuestionCategories = function() {
	 * evalutionCategorService.getAllEvalutionCategories().then(
	 * function(response) { $scope.questionCategoryList = response.data; },
	 * function(error) { $scope.message =
	 * JSON.stringify(error.data.message.trim()); }); };
	 */

	$scope.getAllQuestionCategories = function() {
		evalutionCategorService.getAllCategorysByPatientId(
				$scope.patientObject.id).then(function(response) {
			$scope.questionCategoryList = response.data;
		}, function(error) {
		});
	};

	$scope.noOfPages = function(totalNoOfPages) {
		$scope.pageList = [];
		for (i = 0; i < totalNoOfPages; i++) {
			$scope.pageList.push(i);
		}
	};
	$scope.genEvalReport=true;
	$scope.getEvalutionSheetByStatusAndPatientId = function(patientId, status) {
		evalutionSheetService.findEvalutionSheetByStatusAndPatientId($scope.patientObject.id,
				1).then(function(response) {
			$scope.progress = response.data;
			if($scope.progress == ""){
				$scope.isDisabled = false;
				$scope.editMode = false;
			}else{
				$scope.isDisabled = true;
				$scope.editMode = true;
			}
			
			
			if($scope.progress !=""){
				$scope.genEvalReport=false;
			}
		}, function(error) {

		})

	};
	$scope.genProReport=false;
	$scope.getProgressSheetReportBy = function() {
		evalutionSheetService.getProgressSheetReportBy($scope.patientObject.id)
				.then(function(response) {
					$scope.progressNote1 = response.data;
					if($scope.progressNote1 == ""){
						$scope.editProgress=false;
						$scope.genProReport=false;
					}else{
						$scope.editProgress=true;
						$scope.genProReport=true;
						
					}
				}, function(error) {
				})
	};

	$scope.getExitSheetReportBy = function() {
		evalutionSheetService.getExitSheetReportBy($scope.patientObject.id)
				.then(function(response) {
					$scope.exitNote = response.data;
					if($scope.exitNote == ""){
						$scope.editExit=false;
					}else{
						$scope.editExit=true;
					}
				}, function(error) {
				})
	};
// actival save method for final data
	$scope.progress.date=new Date();
	$scope.progressNote1.date=new Date();
	$scope.exitNote.date=new Date();
	
	$scope.addprogressSheet = function() {
		
		if($scope.progress.date==undefined){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				toastr.error('','Please Select Date in Evaluation Sheet');
			}
		if($scope.progress == undefined || $scope.progress == ''){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				toastr.error( "Please enter data in atleast one of the field",'Info');
		}
		$scope.progress.patient = $scope.patientObject;
		if($scope.progress.evaluator==undefined){
			$scope.progress.evaluator=$scope.doctorName;
		}
		$scope.patientSpeechtheraphyTemplatelist=[];
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate1);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate2);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate3);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate4);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate5);
		$scope.progress.patientSpeechTherapyTemplates=$scope.patientSpeechtheraphyTemplatelist;
		/*
		 * if ($scope.progress.patientQuestionCategories != undefined) {
		 * $scope.progress.patientQuestionCategories =
		 * $scope.progress.patientQuestionCategories; } else {
		 * $scope.progress.questionCategoriesDtos = $scope.questionCategoryList; }
		 */
		var date1 = $scope.progress.date; 
		if(typeof date1 !== 'string') {
			 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
			  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
			$scope.progress.date =changepaiddate; 
			}
		
		$scope.progress.evalutionSheetStatus = "1";
		$scope.progress.status = "1";
		/*console.log(JSON.stringify($scope.progress));*/
		evalutionSheetService.addprogressSheet($scope.progress).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('',
							successMessageHandler.PATIENT_EVALUTION_SHEET_NOTE_ADD_SUCCESS);
					$scope.getEvalutionSheetByStatusAndPatientId(
							$scope.patientObject.id, "1");
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
// save method for advance save while going to template to select speech
// template
	$scope.addEvalutionSave = function(){
		
		if(typeof $scope.progress.date == 'string' || $scope.progress.date==undefined){
			$scope.progress.date= new Date();
		}
		$scope.progress.patient = $scope.patientObject;
		if($scope.progress.evaluator==undefined){
			$scope.progress.evaluator=$scope.doctorName;
		}
		$scope.patientSpeechtheraphyTemplatelist=[];
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate1);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate2);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate3);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate4);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate5);
		$scope.progress.patientSpeechTherapyTemplates=$scope.patientSpeechtheraphyTemplatelist;
		if($scope.progress.date!=undefined){
			var date1 = $scope.progress.date; 
			if(typeof date1 !== 'string') {
				 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
				  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
				$scope.progress.date =changepaiddate; 
				}
		}
		$scope.progress.evalutionSheetStatus = "1";
		$scope.progress.status = "1";
		evalutionSheetService.addprogressSheet($scope.progress).then(
				function(response) {
					toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-full-width",
							timeOut : 2000
						};
						toastr.success('', successMessageHandler.PATIENT_EVALUTION_ADD_SUCCESS);
						$scope.isDisabled = true;
					}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.getEvalutionSheetByStatusAndPatientIdProgress = function(patientId,
			status) {
		evalutionSheetService.findEvalutionSheetByStatusAndPatientId(patientId,
				status).then(function(response) {
			$scope.progressNote = response.data;
			if($scope.progressNote == ""){
				$scope.isDisabled = false;;
			}else{
				$scope.isDisabled = true;
			}
		}, function(error) {

		})

	};

	$scope.getEvalutionSheetByPatientIdStatus = function(patientId) {
		evalutionSheetService.getEvalutionSheetByPatientIdStatus(patientId)
				.then(function(response) {
					$scope.progressNote = response.data;
				}, function(error) {

				})

	};

	$scope.addEvalutionSheet = function() {
		$scope.sheetData.patient = $scope.patientObject;
		$scope.sheetData.questionCategoriesDtos = $scope.questionCategoryList;
		if ($scope.sheetData.patientQuestionCategories != undefined) {
			$scope.sheetData.patientQuestionCategories = $scope.sheetData.patientQuestionCategories;
		}
		$scope.sheetData.status = "1";
		evalutionSheetService.addSheet($scope.sheetData).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.PATIENT_EVALUTION_ADD_SUCCESS);
					/* $state.go('main.roleist'); */
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.addProgressNoteSheet = function(progressNote1) {
		
		
		/* if($scope.progressNote1.date==undefined){
			 toastr.options = {
		 closeButton : true,
		 progressBar : true, 
		 showMethod : 'slideDown',
		 positionClass : "toast-top-full-width", timeOut : 2000 }; 
			 toastr.error('','Please Select Date In Progress Sheet');
			 }*/
		 
		$scope.progressNote1=progressNote1;
		if($scope.progressNote1 == undefined || $scope.progressNote1 == ''){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				toastr.error( "Please enter data in atleast one of the field",'Info');
		}
		$scope.progressNote1.patient = $scope.patientObject;
		
		if($scope.progressNote1.evaluator==undefined){
			$scope.progressNote1.evaluator=$scope.doctorName;
		}
		var date1 = $scope.progressNote1.date; 
		if(typeof date1 !== 'string') {
			 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
			  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
			$scope.progressNote1.date =changepaiddate; 
			}
		 
		$scope.patientSpeechtheraphyTemplatelist=[];
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate1);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate2);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate3);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate4);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate5);
		$scope.progressNote1.patientSpeechTherapyTemplates=$scope.patientSpeechtheraphyTemplatelist;
		$scope.progressNote1.status = "2";
		$scope.progressNote1.evalutionSheetStatus = "2";
		evalutionSheetService.addProgressNote($scope.progressNote1).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr
							.success('',
									successMessageHandler.PATIENT_PROGESS_ADD_SUCCESS);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.stateChangeProgress=false;
	// select templates from progress for save
	$scope.addProgressNote = function(progressNote1){
		
		/*
		 * if(progressNote1.date == undefined){ $scope.stateChangeProgress=true;
		 * toastr.options = { closeButton : true, progressBar : true, showMethod :
		 * 'slideDown', positionClass : "toast-top-full-width", timeOut : 2000 };
		 * toastr .error('','Please Select Date In Progress Sheet'); }
		 */
		progressNote1.patient = $scope.patientObject;
		/*
		 * if ($scope.progressNote1.patientQuestionCategories != undefined) {
		 * $scope.progressNote1.patientQuestionCategories =
		 * $scope.progressNote1.patientQuestionCategories; } else {
		 * $scope.progressNote1.questionCategoriesDtos =
		 * $scope.questionCategoryList; }
		 */
		if(progressNote1.evaluator==undefined){
			progressNote1.evaluator=$scope.doctorName;
		}
		if($scope.progressNote1.date == undefined ||$scope.progressNote1.date == ""){
			$scope.progressNote1.date=new Date();
		}
		if($scope.progressNote1.date != undefined){
		var date1 = $scope.progressNote1.date; 
		if(typeof date1 !== 'string') {
			 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
			  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
			$scope.progressNote1.date =changepaiddate; 
			}
		}
		 
		$scope.patientSpeechtheraphyTemplatelist=[];
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate1);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate2);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate3);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate4);
		$scope.patientSpeechtheraphyTemplatelist.push($scope.speechTheraphyTemplate5);
		progressNote1.patientSpeechTherapyTemplates=$scope.patientSpeechtheraphyTemplatelist;
		progressNote1.status = "2";
		progressNote1.evalutionSheetStatus = "2";
		evalutionSheetService.addProgressNote(progressNote1).then(
				function(response) {
					
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	
	}
	
	$scope.addPatientGoals = function(obj123) {
		if($scope.patientGoalList[0].evaluator!=undefined){
			obj123.evaluator=$scope.patientGoalList[0].evaluator;
		}else{
			obj123.evaluator==$scope.doctorName;
		}
		if($scope.patientGoalList[0].serviceCoordinator!=undefined){
			obj123.serviceCoordinator=$scope.patientGoalList[0].serviceCoordinator;
		}
		$scope.patientGoalDtoObject = {
			"golaTemplatelookups" : $scope.goalList,
			"patientGoals" : $scope.patientGoalList,
			"evaluator":obj123.evaluator,
			"serviceCoordinator":obj123.serviceCoordinator
		}
		if ($scope.patientGoalDtoObject != undefined) {
			patientGoalService.addPatientGoal($scope.patientGoalDtoObject,
					$scope.patientObject).then(function(response) {
				toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				toastr.success('', successMessageHandler.PATIENT_GOAL_ADD_SUCCESS);

			}, function(error) {
				toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				$scope.message = JSON.stringify(error.data.message.trim());
				toastr.error($scope.message, 'Error');
			});
		}

	};
	$scope.updatePatientData = function() {
		
		/*$scope.patientObject.departments.push($scope.patientObject.department);
		
		if($scope.patientObject.clientType.clientTypeName=='Regional Center'){
			$scope.patientObject.privateClient=null;
			$scope.patientObject.school=null;
			$scope.patientObject.insurance=null;
		}
		if($scope.patientObject.clientType.clientTypeName=='Private'){
			$scope.patientObject.school=null;
			$scope.patientObject.insurance=null;
			$scope.patientObject.regionalCenter=null;
		}
		if($scope.patientObject.clientType.clientTypeName=='School'){
			$scope.patientObject.privateClient=null;
			$scope.patientObject.insurance=null;
			$scope.patientObject.regionalCenter=null;
		}
		if($scope.patientObject.clientType.clientTypeName=='Insurance'){
			$scope.patientObject.privateClient=null;
			$scope.patientObject.school=null;
			$scope.patientObject.regionalCenter=null;
		}*/
		
		$scope.patientObject.adminUser = $rootScope.loggedUsername;
		// $scope.patientObject.documents = $scope.documents;
		 $scope.patientObject.folders  =$scope.listOfFolders;
		 $scope.patientInfo={
				 "patient":$scope.patientObject
				 /*
					 * , "documents":$scope.pdocuments
					 */
				 
		 }
		patientService.updatePatient($scope.patientInfo).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.PATIENT_UPLOAD_SUCCESS);

					$state.go('main.patient_list');
				}, function(error) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, ' Error');
				});
	};

	// report
	$scope.generateProgressReport = function(tabdate) {
		$scope.progressSheetDateDto = {
				"patientId" : $scope.patientObject.id,
				"progressDate" : tabdate
			};
		patientService.generateProgressReport($scope.progressSheetDateDto).then(
				function(response) {
					responseType: 'arraybuffer'
					var file = new Blob([ response.data ], {
						type : 'application/pdf'
					});
					var fileURL = URL.createObjectURL(file);
					$scope.content = $sce.trustAsResourceUrl(fileURL);
					window.open(fileURL);
				}, function(error) {
				});
	};
	$scope.getDoctorByPatientId = function() {
		patientService.findDoctorByPatientId($scope.patientObject.id).then(
				function(response) {
					$scope.doctorObj = response.data;
					$scope.doctorName=$scope.doctorObj.firstName+" "+$scope.doctorObj.lastName;
					$scope.obj123.evaluator=$scope.doctorName;
				}, function(error) {

				})
	};
	//718
	$scope.generateEvaluationReport = function(doctorObj) {
		$scope.getDoctorByPatientId();
		patientService.generateEvaluationReport($scope.patientObject.id,$scope.doctorObj).then(
				function(response) {
					responseType: 'arraybuffer'
					var file = new Blob([ response.data ], {
						type : 'application/pdf'
					});
					var fileURL = URL.createObjectURL(file);
					$scope.content = $sce.trustAsResourceUrl(fileURL);
					window.open(fileURL);
				}, function(error) {
				});
	};
	
	$scope.generateExitNoteReport = function() {
		patientService.generateExitNoteReport($scope.patientObject.id).then(
				function(response) {
					responseType: 'arraybuffer'
					var file = new Blob([ response.data ], {
						type : 'application/pdf'
					});
					var fileURL = URL.createObjectURL(file);
					$scope.content = $sce.trustAsResourceUrl(fileURL);
					window.open(fileURL);
				}, function(error) {
				});
	};

	$scope.addExitNoteSheet = function() {
		
		if($scope.exitNote.date==undefined){
			/*toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				toastr.error('','Please Select Date In Exit Sheet');*/
			}
		
		$scope.exitNote.patient = $scope.patientObject;
		$scope.exitNote.status = "3";
		
		  $scope.exitNote.questionCategoriesDtos=$scope.questionCategoryList;
		  if($scope.exitNote.patientQuestionCategories != undefined){
		  $scope.exitNote.patientQuestionCategories=$scope.exitNote.patientQuestionCategories; }
		 
		if($scope.exitNote.evaluator==undefined){
			$scope.exitNote.evaluator=$scope.doctorName;
		}
		var date1 = $scope.exitNote.date;
		if(date1 !=null){
			if(typeof date1 !== 'string') {
				 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
				  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
				$scope.exitNote.date =changepaiddate; 
				}
		}
		evalutionSheetService.addExitNote($scope.exitNote).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.PATIENT_NOTE_EXIT_ADD_SUCCESS);
									}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	
$scope.addExitNote = function() {
		
		$scope.exitNote.patient = $scope.patientObject;
		$scope.exitNote.status = "3";
		/*
		 * $scope.exitNote.questionCategoriesDtos=$scope.questionCategoryList;
		 * if($scope.exitNote.patientQuestionCategories != undefined){
		 * $scope.exitNote.patientQuestionCategories=$scope.exitNote.patientQuestionCategories; }
		 */
		if($scope.exitNote.evaluator==undefined){
			$scope.exitNote.evaluator=$scope.doctorName;
		}
		if(typeof $scope.progress.date == 'string' || $scope.progress.date==undefined ){
			$scope.progress.date= new Date();
		}
		var date1 = $scope.exitNote.date;
		if(date1 !=null){
			if(typeof date1 !== 'string') {
				 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
				  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
				$scope.exitNote.date =changepaiddate; 
				}
		}
		evalutionSheetService.addExitNote($scope.exitNote).then(
				function(response) {}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};



	$scope.status = {
		isFirstOpen : true,
		isFirstDisabled : false
	};
	$scope.gotoList = function() {
		$state.go('main.patient_list');
	};

	$scope.gotoAdd = function() {
		$state.go('main.add_patient.add_patient_details');
	};
	$scope.gotoback = function() {
		$state.go('main.patient_list');
	};
	$scope.gotoupdate = function(patient) {
		$rootScope.pid=patient.id;
		// $rootScope.flag=false;
		$state.go('main.updatePatient.updatePatientDetails', {
			patientObject : patient,
			payment :$scope.payment,
			PatientDocuments:$scope.pdocuments

		});
	};
	$scope.getAllDocuments = function() {
		patientService.getAllDocuments($scope.patientObject.id).then(
				function(response) {
					$scope.documentsList = response.data;
				}, function(error) {
				})
	};

	$scope.getDocument = function(path) {
		patientService.getPatientDocument(path).then(function(response) {
			$scope.documentPath = response.data;
		}, function(error) {
		})
	};
	
	$scope.getPatientDocuments=function(status){
		patientService.getAllDocumentsByStatus($scope.patientObject.id,status).then(
				function(response) {
					$scope.patientDocumentsList = response.data;
				}, function(error) {
				})
	};
	$scope.getEvaluationDocuments=function(status){
		patientService.getAllDocumentsByStatus($scope.patientObject.id,status).then(
				function(response) {
					$scope.evaluationDocumentsList = response.data;
				}, function(error) {
				})
	};

	$scope.getTherapistDocuments = function(status) {
		patientService.getAllDocumentsByStatus($scope.patientObject.id,status).then(
				function(response) {
					$scope.therapistDocumentsList = response.data;
				}, function(error) {
				})
	};
	$scope.displayFlag=false;
	$scope.displayPDF = function(documentId) {
		$http.get(PATIENT_MODULE_CONFIG.DISPLAY_PDF(documentId), {
			responseType : 'arraybuffer'
		}).success(function(response) {
			var file = new Blob([ response ], {
				type : 'application/pdf'
			});
			var fileURL = URL.createObjectURL(file);
			$scope.content = $sce.trustAsResourceUrl(fileURL);
			$scope.displayFlag=true;
		
		});
	};
	$scope.displayDocument = function(documentId){
		patientService.displayDocument(documentId).then(
				function(response) {
	        		   var file = new Blob([response ], { 
	        			   type :'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
	        		   });
	           var fileURL = URL.createObjectURL(file); 
	           $scope.content1 =$sce.trustAsResourceUrl(response.data.location); 
	           $scope.displayFlag=true;
	        });
	}
	
	  /*
		 * $scope.displayDocument = function(documentId) {
		 * $http.get(PATIENT_MODULE_CONFIG.DISPLAY_PDF(documentId), {
		 * responseType :'arraybuffer' }).success(function(response) { var file =
		 * new Blob([response ], { type
		 * :'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
		 * }); var fileURL = URL.createObjectURL(file); $scope.content1
		 * =$sce.trustAsResourceUrl(fileURL); $scope.displayFlag=true; }); };
		 */
	 
	$scope.displayXls = function(documentId) {
		patientService.displayDocument(documentId).then(
				function(response) {
					var file = new Blob([ response ], {
						type : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
					});
	           var fileURL = URL.createObjectURL(file); 
	           $scope.content1 =$sce.trustAsResourceUrl(response.data.location); 
	           $scope.displayFlag=true;
	        });
	};
	$scope.displayCsv = function(documentId) {
		$http.get(PATIENT_MODULE_CONFIG.DISPLAY_PDF(documentId), {
			responseType : 'arraybuffer'
		}).success(function(response) {
			var file = new Blob([ response ], {
				type : 'text/csv'
			});
			var fileURL = URL.createObjectURL(file);
			$scope.content1 = $sce.trustAsResourceUrl(fileURL);
			$scope.displayFlag=true;
		
		});
	};

	$scope.findAllpatientsByPagination = function() {
		patientService.findAllPatient($scope.page, $scope.size).then(
				function(response) {
					$scope.patientList = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				});
	};

	/*
	 * $scope.findAllpatientsByPaginationByAdmin = function() {
	 * patientService.findAllPatientsByAdmin($rootScope.loggedUsername
	 * ,$scope.page, $scope.size).then( function(response) {
	 * $scope.patientListByAdmin = response.data.content; $scope.totalElements =
	 * response.data.totalElements; $scope.totalPages = response.data.totalPages -
	 * 1; $scope.noOfPgaes(response.data.totalPages); }); };
	 */

	// Pagination logic
	$scope.noOfPgaes = function(totalpages) {

		$scope.pageList = [];
		for (i = 0; i < totalpages; i++) {
			$scope.pageList.push(i);
		}

	};

	$scope.pageChanged = function(page) {
		$scope.page = page;
	};

	$scope.sizeChanged = function(size) {
		$scope.size = size;
	};
	$scope.firstPage = function() {
		$scope.page = 0;
	};

	$scope.lastPage = function() {
		$scope.page = $scope.totalPages;
	};

	$scope.previousPage = function() {
		if ($scope.page > 0) {
			$scope.page = $scope.page - 1;
		}
	};

	$scope.nextPage = function() {
		if ($scope.page < $scope.totalPages) {
			$scope.page = $scope.page + 1;
		}
	};

	$scope.$watchGroup([ 'size', 'page' ], function(oldSize, oldPage) {
		$scope.findAllpatientsByPaginationByRole($scope.search);
		if($scope.billingsFlag==true){
		$scope.findAllPatientsByRoleForBillingsPage($rootScope.patientBillingDto, $rootScope.searching);
		}
		if($scope.invoiceFlag==true){
			$scope.getSubAppointmentsByInvoicePage($rootScope.appoimtnent);
			}
			
			
	});

	// ///////////////////////////////////////////////////////////

	$scope.today = new Date();

	var tabs = [
			{
				title : 'Zero',
				content : 'Woah...that is a really long title!'
			},

			{
				title : 'Twenty',
				content : "If you're still reading this, you should just go check out the API docs for tabs!"
			} ], selected = null, previous = null;
	$scope.tabs = tabs;
	$scope.selectedIndex = 0;
	$scope.$watch('selectedIndex', function(current, old) {
		previous = selected;
		selected = tabs[current];
		if (old + 1 && (old != current))
			$log.debug('Goodbye !');
		if (current + 1)
			$log.debug('Hello !');
	});

	if ($scope.today.getDate() < 10) {
		$scope.date = "0" + $scope.today.getDate();
	} else {
		$scope.date = $scope.today.getDate();
	}
	if ($scope.today.getMonth() < 10) {
		$scope.monthnumbaer = $scope.today.getMonth() + 1;
		$scope.month = "0" + $scope.monthnumbaer;
	} else {
		$scope.month = $scope.today.getMonth() + 1;
	}
	$scope.todaytabdate = $scope.month + " " + $scope.date + " "
			+ $scope.today.getFullYear();

	// console.log(" $scope.todaytabdate" + $scope.todaytabdate);

	// getAllPatientGoals By PatientId

	$scope.getAllgoalsByPatientId = function() {
		goalTemplateService.getAllgoalsByPatient_Id($scope.patientObject.id)
				.then(function(response) {
					$scope.flag = true;
					$scope.goalList = response.data;
				}, function(error) {
				});
	};
	$scope.findAllPatientGoalsByPatientIdAndCreatedDate = function(tabdate) {
		$scope.patientGoalList=[];
		
		$scope.patientGoalDto = {
			"patientId" : $scope.patientObject.id,
			"date" : tabdate
		};
		patientGoalService.findAllPatientGoalsByPatientIdAndCreatedDate(
				$scope.patientGoalDto).then(function(response) {
			$scope.flag = false;
			$scope.patientGoalList = response.data;
			if ($scope.patientGoalList.length < 1) {
				// $scope.getAllgoals();
				// $scope.getAllgoalsByPatientId();
				// $scope.findAllPatientGoalsByPatientIdAndCreatedDate
			}
			if (tabdate == $scope.todaytabdate) {
				$scope.addFlag = true;
			} else {
				$scope.addFlag = false;
			}

		});

	};

	$scope.getAllCratedDatesOfPatientGoals = function() {
		patientGoalService
				.getAllCratedDatesOfPatientGoals($scope.patientObject.id)
				.then(
						function(response) {
							$scope.datesList = response.data;
							if ($scope.datesList != undefined) {
								if ($scope.datesList.length < 1) {
									$scope.datesList.push($scope.todaytabdate);
									$scope.addFlag = true;
								}
							}
							$scope
									.findAllPatientGoalsByPatientIdAndCreatedDate($scope.datesList[0]);
						})
	};

	$scope.addFlag = false;
	$scope.addTab = function() {
		if ($scope.datesList[$scope.datesList.length - 1] != $scope.todaytabdate) {
			$scope.datesList.push($scope.todaytabdate);
			$scope.addFlag = true;
			$scope.previousDate = $scope.datesList[$scope.datesList.length - 1];
			$scope
					.findAllPatientGoalsByPatientIdAndCreatedDate($scope.previousDate);
		} else {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-right",
				timeOut : 2000
			};
			toastr.error('', 'Not allowed to add new goals in same date');
		}

	};
	$scope.removeTab = function(tab) {
		var index = tabs.indexOf(tab);
		tabs.splice(index, 1);
	};

	// //////////////////////////////////////////////////////////////////

	$scope.gotoDocuments = function() {
		$state.go('main.patientviewtabs.patient_documents');
	};
	$scope.gotoDocuments = function() {
		$state.go('main.patientviewtabs.patient_note');
	};
	$scope.gotoEvalution = function() {
		$state.go('main.patientviewtabs.patient_evaluation_sheet');
	};
	$scope.gotoGoals = function() {
		$state.go('main.patientviewtabs.patient_goals_sheet');
	};
	$scope.gotoProgress = function() {
		$state.go('main.patientviewtabs.patient_progress_notes');
	};
	$scope.gotoExit = function() {
		$state.go('main.patientviewtabs.patient_exit_note');
	};

	$scope.getAge = function() {

		var birthDate = document.getElementById('birth_date').value;
		var d = new Date(birthDate);
		var mdate = birthDate.toString();
		var yearThen = parseInt(mdate.substring(0, 4), 10);
		var monthThen = parseInt(mdate.substring(5, 7), 10);
		var dayThen = parseInt(mdate.substring(8, 10), 10);

		var today = new Date();
		var birthday = new Date(yearThen, monthThen - 1, dayThen);
		var differenceInMilisecond = today.valueOf() - birthday.valueOf();

		var year_age = Math.floor(differenceInMilisecond / 31536000000);
		var day_age = Math
				.floor((differenceInMilisecond % 31536000000) / 86400000);
		var month_age = Math.floor(day_age / 30);
		day_age = day_age % 30;
		var tMnt = (month_age + (year_age * 12));
		var tDays = (tMnt * 30) + day_age;
		if (isNaN(year_age) || isNaN(month_age) || isNaN(day_age)) {
			document.getElementById("age").innerHTML = ("Invalid birthday - Please try again!");
		} else {
			var age = tMnt + " months "+day_age +" days ";
			document.getElementsByName('age')[0].value = age;
			$scope.patient.age = age;
			/*
			 * document.getElementById("age").innerHTML = year_age + " years " +
			 * month_age + " months " + day_age + " days" + "<br/> or <br/> " +
			 * tMnt + " months " + day_age + " days" + "<br/> or <br/>" + tDays + "
			 * days" + "<br/> or <br/>" + tDays*24 + " hours" + "<br/> or
			 * <br/>" + tDays*24*3600 + " seconds" + "<br/> or <br/>" +
			 * tDays*24*3600*1000 + " miliseconds" ;
			 */
		}

	}

	

	$scope.getNewPatientsCount = function() {
		patientService.getNewPatientsCount().then(function(response) {
			$scope.count = response.data;
		}, function(error) {
		})
	};
	$scope.totalPatientsRegistered = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		patientService.totalPatientsRegistered($scope.adminUserName).then(
				function(response) {
					$scope.count = response.data;
				}, function(error) {
				})
	};

	$scope.patientGoalsInformation = function() {
		patientService.patientGoalsInformation($scope.patientObject.id).then(
				function(response) {
					$scope.labels4 = response.data.labels;
					$scope.series4 = response.data.series;
					$scope.data4 = response.data.data;

				}, function(error) {
				})
	};

	// added new Data..

	$scope.result = ''
	// $scope.details = ''
	$scope.options = {};

	$scope.form = {
		type : 'geocode',
		bounds : {
			SWLat : 49,
			SWLng : -97,
			NELat : 50,
			NELng : -96
		},
		country : 'ca',
		typesEnabled : false,
		boundsEnabled : false,
		componentEnabled : false,
		watchEnter : true
	}

	// watch form for changes
	$scope.watchForm = function() {
		return $scope.form
	};
	$scope.$watch($scope.watchForm, function() {
		$scope.checkForm()
	}, true);

	// set options from form selections
	$scope.checkForm = function() {

		$scope.options = {};

		$scope.options.watchEnter = $scope.form.watchEnter

		if ($scope.form.typesEnabled) {
			$scope.options.types = $scope.form.type
		}
		if ($scope.form.boundsEnabled) {

			var SW = new google.maps.LatLng($scope.form.bounds.SWLat,
					$scope.form.bounds.SWLng)
			var NE = new google.maps.LatLng($scope.form.bounds.NELat,
					$scope.form.bounds.NELng)
			var bounds = new google.maps.LatLngBounds(SW, NE);
			$scope.options.bounds = bounds

		}
		if ($scope.form.componentEnabled) {
			$scope.options.country = $scope.form.country
		}
	};
	
	
	
	$scope.deleteDocumnetbyPatient = function(document) {
		patientService.deleteDocumnet(document.id).then(function(response) {
			$scope.patientDocumentsByFolder=response.data;
			
		}, function(error) {
			console.log("Document Can't delete");
		})
	};
	
	$scope.deleteDocumentalert=function(document){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Document!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteDocumnetbyPatient(document);
			
			    swal("Proof! Your Document has been deleted!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Document is safe!");
			  }
			});
	};

	$scope.gotoGoalTemplate = function() {
		$state.go('main.goalsTemplate', {
			Patient : $scope.patientObject
		})
	};
	//718
	
	$scope.deletePatientGoailById = function(goalId) {
		patientGoalService.deletePatientGoailById($scope.patientObject.id,goalId).then(function(repsonse) {
			/*
			 * toastr.options = { closeButton : true, progressBar : true,
			 * showMethod : 'slideDown', positionClass : "toast-top-right",
			 * timeOut : 2000 }; toastr.success('','Delete Goals to Specific
			 * patient Successfully');
			 */
			$scope.getAllPatientGoalByPatientId();
		}, function(error) {

		})
	};
	  
	  
	$scope.getAllPatientGoalByPatientId = function() {
		$scope.patientGoalList =[];
		patientGoalService
				.getAllPatientGoalByPatientId($scope.patientObject.id).then(
						function(repsonse) {
							$scope.patientGoalList = repsonse.data;
						}, function(error) {

						})
	};

	// payments integration starts
	/*
	 * $scope.gotoPayments=function(){ $state.go('main.payments'); }
	 */
	$scope.gotoPayments=function(){
		
		$state.go('main.payments');
	}
	$scope.deletealert=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Goal!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deletePatientGoailById(id);
			    swal("Delete Goals to Specific patient Successfully!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Goal is safe!");
			  }
			});
	};
	// ///
	
	/* payment integration starts */
	
	   
	   
	   $scope.chargeCard=function(token) {
			  const headers = new Headers({'token': token, 'amount': 100});
			  return $http.post('http://103.255.146.157:8087/payment/charge', {}, {headers: headers}).then(function(response){
				$scope.card= response.data;
			  })
			};
		
	   
		 $scope.chargeCreditCard=function() {
			 return $http.post("https://api.stripe.com/v1/tokens/"+ $scope.FormData).then(function(response){
				 var token = response.id;
			      $scope.chargeCard(token);
				 
			 },function(error){
				 console.log(error.message);
			 })
			     
		}; 
		$scope.searchPatient = function(patient) {
			if(patient.status!=undefined&&patient.status==""){
				patient.status=null;
			}
			
			if(patient.occurance!=undefined&&patient.occurance==""){
				patient.occurance=undefined;
			}
			
			if(patient.clientType!=undefined&&patient.clientType==""){
				patient.clientType=undefined;
			}
			
			if(patient.department!=undefined&&patient.department==""){
				patient.department=undefined;
			}
			
			if(patient.startedDate!=undefined&&patient.endDate!=undefined){
			var date1 = patient.startedDate;
			mnth = ("0" + (date1.getMonth() + 1)).slice(-2), day = ("0" + date1
					.getDate()).slice(-2);
			var changepaiddate = [day,mnth,date1.getFullYear() ].join("-");
			patient.startedDate=changepaiddate;
			
			var date = patient.endDate;
			mnth = ("0" + (date.getMonth() + 1)).slice(-2), day = ("0" + date
					.getDate()).slice(-2);
			var changedate = [day,mnth,date.getFullYear() ].join("-");
			patient.endDate=changedate;
			}
			
			$scope.userName = $rootScope.loggedUsername;
			patientService.searchPatient(patient).then(
					function(response) {
						$scope.seachPatientsList = response.data;
						if($scope.seachPatientsList==0){
							toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-right",
									timeOut : 3000
								};
							toastr.error('No records found!', 'Alert');
						}
						// patient.startedDate=undefined;
						// patient.endDate=undefined;
						patient={};
						// $scope.gotoSavePayment($scope.patientdetals);
					}, function(error) {
						// patient.startedDate=undefined;
						// patient.endDate=undefined;
						patient={};
						toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 3000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
					});
		};
		
		$scope.searchPatientBySSNRREGNO = function(patient) {
			
			if(patient.regNo!=undefined&&patient.regNo==""){
				patient.regNo=null;
			}
			
			if(patient.ssn!=undefined&&patient.ssn==""){
				patient.ssn=undefined;
			}
			
			patientService.searchPatientBySSNRREGNO($scope.patient)
			.then(
					function(response) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-full-width",
							timeOut : 3000
						};
						$scope.paymentsList=[];
						toastr
								.success('',
										successMessageHandler.PATIENT_REGISTER_SUCCESS);

						$state.go('main.patient_list');
					},
					function(error) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 3000
						};
						$scope.message = JSON.stringify(error.data.message
								.trim());
						toastr.error($scope.message, 'Error');
						$scope.paymentsList=[];
					});
		}
	   
		$scope.gotoSavePayment = function(patient) {
			$rootScope.patentName=patient.email;
			$rootScope.patientFistName=patient.firstName;
			$state.go('main.savePayment', {
				patientObject : patient

			});
		};
		
		$scope.cycles=function(){
			$scope.cycleObj=$rootScope.cycleValues;
			// /
			
			$scope.assignedAmount=$scope.cycleObj.assignedAmount;
			$scope.modeOfPaymentTypes=$scope.cycleObj.modeOfPaymentTypes;
			$scope.paymentTypes=$scope.cycleObj.paymentTypes;
			
			$scope.noOfCycles=$scope.cycleObj.noOfCycles;
			$scope.totalAmount=$scope.cycleObj.assignedAmount*$scope.noOfCycles;
			$scope.remainingAmount=$scope.cycleObj.remainingAmount;
			$scope.doctor=$scope.cycleObj.doctor;
			$scope.amount=$scope.totalAmount-$scope.cycleObj.remainingAmount;
			if($scope.cycleObj.remainingAmount!=null){
				$scope.amount=$scope.totalAmount-$scope.cycleObj.remainingAmount;
				}else{
					$scope.remainingAmount=$scope.totalAmount;
					$scope.amount=0;
				}
			// /
			
		}
		$scope.getCycles=function(){
			patientService.getCycles($rootScope.patintId).then(
					function(response) {
						$scope.cycleObj = response.data;
						$rootScope.cycleValues=$scope.cycleObj;
						$scope.cycles();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
			
		}
		$scope.getSessions = function() {
			patientService.getSessions($rootScope.patintId).then(function(response){
				$scope.session=response.data;
			})
		}
		
		$scope.calamount=function(noofsessions,persessioncost,id){
			$scope.totalamount=noofsessions*persessioncost;
			$scope.calAmtObj={
					"patientId":id,
					// "answer":$scope.ans,
					"assignedAmount":persessioncost,
					"noOfCycles":noofsessions,
					"paidAmount":$scope.totalamount
			}
			patientService.calamount($scope.calAmtObj).then(function(response){
				$scope.calamountObj=response.data;
				if($scope.calamountObj.remainingAmount<0){
					toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 3000
						};	
					toastr.error('Balance Amount should not be less than Zero! ', '');
				}
			}/*
				 * , function(error) { toastr.options = { closeButton : true,
				 * progressBar : true, showMethod : 'slideDown', positionClass :
				 * "toast-top-right", timeOut : 3000 }; $scope.message =
				 * JSON.stringify(error.data.message .trim());
				 * toastr.error($scope.message, 'Error'); }
				 */);
		}
		
		$scope.addPayment = function(payment,calamountObj,assignedAmount,paidAmount,patientObject,modeOfPaymentTypes,paymentTypes,session) {
			// payment.patient.email=$rootScope.patentName;
			payment.patient=patientObject;
			payment.noOfCycles=payment.noOfSessions;
			payment.remainingAmount=calamountObj.remainingAmount;
			payment.totalAmount=calamountObj.totalAmount;
			payment.assignedAmount=assignedAmount;
			payment.userName=$rootScope.userForSession;
			payment.paidAmount=paidAmount;
			payment.modeOfPaymentTypes=modeOfPaymentTypes;
			payment.paymentTypes=paymentTypes;
			
			payment.department=patientObject.department;
			payment.clientType=patientObject.clientType;
			payment.totalSessions=session.totalSessions
			payment.completeSessions=session.completeSessions
			payment.awaitingSessions=session.awaitingSessions
			payment.cancelledSessions=session.cancelledSessions
			payment.paidSessions=session.paidSessions
			payment.unPaidSessions=session.unPaidSessions
			payment.createdBy=$rootScope.loggedUsername;;
			
		// payment.remainingAmount=payment.totalAmount-payment.paidAmount;
			
			  var date1 = payment.paidDate; 
			  mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
			  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
			  
			 
			  payment.paidDate=changepaiddate;
			 
		
			/*
			 * var date = $scope.payment.therapyDoneDate; // alert("date"+date);
			 * var date1 = $scope.payment.paidDate; mnth = ("0" +
			 * (date.getMonth() + 1)).slice(-2), day = ("0" + date
			 * .getDate()).slice(-2); var changetherapyDonedate = [
			 * day,mnth,date.getFullYear() ].join("-"); mnth = ("0" +
			 * (date1.getMonth() + 1)).slice(-2), day = ("0" + date1
			 * .getDate()).slice(-2); var changepaiddate =
			 * [day,mnth,date1.getFullYear() ].join("-"); //
			 * alert("changetherapyDonedate"+changetherapyDonedate); //
			 * alert("changepaiddate"+changepaiddate);
			 * 
			 * $scope.payment.therapyDoneDate = changetherapyDonedate;
			 * $scope.payment.paidDate = changepaiddate;
			 */
			 /* console.log("payment::"+payment);
			  console.log("payment1::"+JSON.stringify(payment));*/
			patientService.addPayment(payment).then(
					function(response) {
						toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr.success('', successMessageHandler.PATIENT_SAVE_SUCCESS);

							$state.go('main.paymentList');
					},
					function(error) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 3000
						};
						$scope.message = JSON.stringify(error.data.message
								.trim());
						toastr.error($scope.message, 'Error');
					});
		};
		
		$scope.getPaymentsByRole=function(){
			$scope.userName= $rootScope.loggedUsername;
			patientService.getPaymentsByRole($scope.userName,$scope.page,$scope.size).then(function(response){
				$scope.paymentsListByRole=response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			},function(error){
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-right",
	                    timeOut: 2000
	                };
				$scope.message = JSON.stringify(error.data.message.trim());
				toastr.error($scope.message, 'Error');
			});
		};
		$scope.getAllDepartments = function() {
			departmentService.getAlldepartments().then(function(response) {
				$scope.departmentsList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		$scope.getAllDepartmentsByCmpany = function() {
			departmentService.getAllDepartmentsByCmpany($rootScope.loggedUsername).then(function(response) {
				$scope.departmentsListByCmpany = response.data;
				if($scope.departmentsListByCmpany.length<1){
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
					$scope.message = "Departments are  not configured! Please configure the Departments First!!"
					toastr.error($scope.message, '');
				}
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		$scope.getAllCategoriesByRegistartiontype = function(id) {
			categoryTypeService.getAllCategoriesByRegistartiontype(id).then(function(response) {
				$scope.categoriesList = response.data;
				console.log("$scope.categoriesList "+$scope.categoriesList);
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		$scope.getAllDepartmentsByRegistartiontype = function(id) {
			$scope.adminUserName = $rootScope.loggedUsername;
			departmentService.getAllDepartmentsByRegistartiontype(id,$rootScope.loggedUsername).then(function(response) {
				$scope.departmentsListByRegistrationType = response.data;
				$scope.patientObject.department = $scope.patientObject.departments[0];
				console.log("$scope.departmentsListByRegistrationType "+$scope.departmentsListByRegistrationType);
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		//Get All Sub Categories Based on Category Type Selection
		
		
		
		
		
		
		
		$scope.getAllSubCategoriesByCategorytype = function(id) {
				subCategoryService.getAllSubCategoriesByCategorytype(id).then(function(response) {
				$scope.subcategoriesList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		
		$scope.subcatdata = function(subcatdata)
		{
			alert("sdfger"+JSON.stringify(subcatdata));
			$scope.subCategorys.push(subcatdata);
			alert("sdfg"+JSON.stringify(subCategorys));
		}
		
		function setSelectedValue(selectObj, valueToSet) {
		    for (var i = 0; i < selectObj.options.length; i++) {
		        if (selectObj.options[i].text== valueToSet) {
		            selectObj.options[i].selected = true;
		            alert("val -->"+selectObj.options[i].selected);
		            return;
		            
		        }
		    }
		}
		
		
// Folder
		$scope.createFolder = function(folder) {
			patientService.createFolder(folder).then(function(response) {
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-full-width",
	                    timeOut: 2000
	                };
	                toastr.success('',successMessageHandler.PATIENT_ADD_SUCCESS);
	                folder=undefined;
	                folder={};
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
			});
		};
		// code for from wizard
		if ($stateParams.patient != undefined) {
			$scope.patient = $stateParams.patient;
		}
		if ($stateParams.payment != undefined) {
			$scope.payment = $stateParams.payment;
		}
		// $scope.activeColor1 = {};
		$scope.activeColor1 = {
				"background-color" : "DodgerBlue ",
				"color" : "white"
			};
		$scope.activeColor2 = {
				"background-color" : "Cornsilk",
				"color" : "DarkBlue"
			};
		$scope.addPatientData = function() {
			
			// null all the remaning type of client if they select one time then
			// the object is created..
			
			/*
			 * var address1 = document.getElementById('street_number').value;
			 * var address2 = document.getElementById('route').value; var city =
			 * document.getElementById('locality').value; var state =
			 * document.getElementById('administrative_area_level_1').value; var
			 * country = document.getElementById('country').value; var zipcode =
			 * document.getElementById('postal_code').value;
			 * 
			 * $scope.patient.address.address1=address1;
			 * $scope.patient.address.address2=address2;
			 * $scope.patient.address.city=city;
			 * $scope.patient.address.state=state;
			 * $scope.patient.address.country=country;
			 * $scope.patient.address.zipcode=zipcode;
			 */
			//$scope.patient.departments.push($scope.patient.department);
			
			/*if($scope.patient.clientType.clientTypeName=='Regional Center'){
				$scope.patient.privateClient=null;
				$scope.patient.school=null;
				$scope.patient.insurance=null;
			}
			if($scope.patient.clientType.clientTypeName=='Private'){
				$scope.patient.school=null;
				$scope.patient.insurance=null;
				$scope.patient.regionalCenter=null;
			}
			if($scope.patient.clientType.clientTypeName=='School'){
				$scope.patient.privateClient=null;
				$scope.patient.insurance=null;
				$scope.patient.regionalCenter=null;
			}
			if($scope.patient.clientType.clientTypeName=='Insurance'){
				$scope.patient.privateClient=null;
				$scope.patient.school=null;
				$scope.patient.regionalCenter=null;
			}*/
			
			if($scope.payment!=undefined){
			if($scope.payment.modeOfPaymentTypes!=undefined&&$scope.payment.modeOfPaymentTypes==""){
				$scope.payment.modeOfPaymentTypes=undefined;
			}
			if($scope.payment.paymentTypes!=undefined&&$scope.payment.paymentTypes==""){
				$scope.payment.paymentTypes=undefined;
			}
			}
			if($scope.patient == undefined || $scope.patient =='' || $scope.patient.address1 == undefined||$scope.payment == undefined|| $scope.payment ==''||
					$scope.payment.modeOfPaymentTypes==undefined||$scope.payment.modeOfPaymentTypes==""||$scope.payment.assignedAmount==undefined){ 
				
				toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-right",
						timeOut : 5000
					};
					toastr.error("Please Fill the Required Fields!", 'Error');
			}else{
			$scope.patient.adminUser = $rootScope.loggedUsername;
			// $scope.patient.documents = $scope.documents;
			$scope.paymentsList.push($scope.payment);
			$scope.patient.payments = $scope.paymentsList;
			if ($scope.email) {
				$scope.patient.communicationStatus = true;
			}
			if ($scope.sms) {
				$scope.patient.communicationStatus = false;
			}
			
			patientService
					.addPatient($scope.patient)
					.then(
							function(response) {
								toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-full-width",
									timeOut : 3000
								};
								$scope.paymentsList=[];
								
								toastr.success('',
												successMessageHandler.PATIENT_REGISTER_SUCCESS);
								 $scope.isDisabled = true;

								$state.go('main.patient_list');
							},
							
						       
						    
							function(error) {
								toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-right",
									timeOut : 3000
								};
								$scope.message = JSON.stringify(error.data.message
										.trim());
								toastr.error($scope.message, 'Error');
								$scope.paymentsList=[];
							});
			}
			
		};
		// traversal for next previous states in add patient page
		$scope.gotoRegistrationDetails = function() {
			$state.go('main.add_patient.add_patient_details', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		$scope.getAddressFromGoogle = function() {
			var address1 = document.getElementById('street_number').value;
			var address2 = document.getElementById('route').value;
			var city = document.getElementById('locality').value;
			var state = document.getElementById('administrative_area_level_1').value;
			var country = document.getElementById('country').value;
			var zipcode = document.getElementById('postal_code').value;
			
			$scope.address1.address1=address1;
			$scope.address1.address2=address2;
			$scope.address1.city=city;
			$scope.address1.state=state;
			$scope.address1.country=country;
			$scope.address1.zipcode=zipcode;
			
			$scope.patient.address1=$scope.address1;
		}
		$scope.gotoChildDetails = function(address) {
			$state.go('main.add_patient.add_child_details', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		
		$scope.gotoParentDetails = function() {
			$state.go('main.add_patient.add_parent_details', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		
		$scope.gotoCommuncationAddress = function() {
			$state.go('main.add_patient.add_patient_communicationAddress', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		$scope.gotoUploadDocuments = function() {
			$state.go('main.add_patient.add_patient_uploadDocuments', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		$scope.gotoClientType = function() {
			$state.go('main.add_patient.add_patient_clientType', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		 $scope.gotoPayments = function() {
				$state.go('main.add_patient.add_patient_payments', {
					patient : $scope.patient,
					payment :$scope.payment,
					documentTypelookup:$scope.lookupFolder,
					documentsList1:$scope.documents,
					regionalZone:$scope.regionalZone
					
				});
			};
		
		$scope.gotoSummary = function() {
			$state.go('main.add_patient.add_patient_summary', {
				patient : $scope.patient,
				payment :$scope.payment,
				documentTypelookup:$scope.lookupFolder,
				documentsList1:$scope.documents,
				regionalZone:$scope.regionalZone
			});
		};
		
		
		// traversal for next previous states in update patient page
		$scope.gotoupdatepatientDetails = function() {
			$state.go('main.updatePatient.updatePatientDetails', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		$scope.gotoupdateRegistrationDetails = function() {
			$state.go('main.updatePatient.updatePatientRegistrationDetails', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		$scope.gotoupdateChildDetails = function() {
			$state.go('main.updatePatient.updateChildDetails', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		
		$scope.gotoupdateParentDetails = function() {
			$state.go('main.updatePatient.updateParentDetails', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		$scope.gotoupdateCommuncationAddress = function() {
			$state.go('main.updatePatient.updateCommunicationAddress', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		$scope.gotoupdateUploadDocuments = function() {
			$state.go('main.updatePatient.uploadDocuments', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		$scope.gotoupdateClientType = function() {
			$state.go('main.updatePatient.updateClientType', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments			
			});
		};
		$scope.gotoupdatePayments = function() {
			$state.go('main.updatePatient.updatePayments', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
				
			});
		};
		$scope.gotoupdateSummary = function() {
			$state.go('main.updatePatient.updateSummary', {
				patientObject : $scope.patientObject,
				payment :$scope.payment,
				PatientDocuments:$scope.pdocuments
			});
		};
		
		$scope.generateInvoice =function() {
			patientService.generateInvoice().then(
					function(response) {
						var file = new Blob([ response.data ], {
							type : 'application/pdf'
						});
						var fileURL = URL.createObjectURL(file);
						$scope.content = $sce.trustAsResourceUrl(fileURL);
					}, function(error) {
					});
			
		};
		
// evaluation
		
		$scope.findAllPatientProgressSheetByPatientIdAndCreatedDate = function(tabdate) {
			$scope.progressSheetDateDto = {
				"patientId" : $scope.patientObject.id,
				"progressDate" : tabdate
			};
			evalutionSheetService.findAllPatientProgressSheetByPatientIdAndCreatedDate(
					$scope.progressSheetDateDto).then(function(response) {
			
				$scope.progressNote1 = response.data;
				if($scope.progressNote1!=""){
					$scope.genProReport=false;
				}
				if ($scope.progressNote1 ==undefined) {
					// $scope.getAllgoals();
					// $scope.getAllgoalsByPatientId();
					// $scope.findAllPatientGoalsByPatientIdAndCreatedDate
				}
				if (tabdate == $scope.todaytabdate) {
					$scope.flag = true;
				} else {
					$scope.flag = false;
					
				}
				$scope.getAllSpeechTheraphyTemplatesByPatientInProgress(tabdate);
			});
			
		};
		$scope.getAllCratedDatesOfProgressnote = function() {
			evalutionSheetService
					.getAllCratedDatesOfProgressnote($scope.patientObject.id)
					.then(
							function(response) {
								$scope.progressNoteDatesList = response.data;
								
								if ($scope.progressNoteDatesList != undefined) {
									if ($scope.progressNoteDatesList.length < 1) {
										$scope.progressNoteDatesList.push($scope.todaytabdate);
										$scope.addFlag = true;
									}
								}
								$scope.findAllPatientProgressSheetByPatientIdAndCreatedDate($scope.progressNoteDatesList[0]);
								$scope.getAllSpeechTheraphyTemplatesByPatientInProgress($scope.progressNoteDatesList[0]);
							})
														
		};
		
		//718
		
		
		$scope.findAllPatientEvalutionSheetByPatientIdAndCreatedDate = function(tabdate) {
			$scope.patientEvaluationList=[];
			$scope.patientEvaluationDto = {
				"patientId" : $scope.patientObject.id,
				"date" : tabdate
			};
			evalutionSheetService.findAllPatientEvalutionSheetByPatientIdAndCreatedDate(
					$scope.patientEvaluationDto).then(function(response) {
				$scope.addFlagEvaluaion = false;
				$scope.patientEvaluationList = response.data;
				if ($scope.patientEvaluationList.length < 1) {
					// $scope.getAllgoals();
					// $scope.getAllgoalsByPatientId();
					// $scope.findAllPatientGoalsByPatientIdAndCreatedDate
				}
				if (tabdate == $scope.todaytabdate) {
					$scope.addFlagEvaluaion = true;
				} else {
					$scope.addFlagEvaluaion = false;
				}

			});

		};
		
		// 718
		$scope.gotoEvalutedSheet = function(){
			$state.go('main.patientviewtabs.patient_evaluation_sheet.mentalQuestions', {
				//$state.go('main.patientviewtabs.patient_evaluation_sheet.mentalReportGraph', {
				
				patientObject : $scope.patientObject
			});
			
		}
		
		$scope.getAllCratedDatesOfEvalutionSheet = function(){
			
			evalutionSheetService
			.getAllCratedDatesOfEvalutionSheet($scope.patientObject.id)
			.then(
					function(response) {
						$scope.evalutionDatesList = response.data;
						//alert(response.data);
						//alert("$scope.evalutionDatesList "+$scope.evalutionDatesList[0]);
						if ($scope.evalutionDatesList != undefined) {
							if ($scope.evalutionDatesList.length < 1) {
								$scope.evalutionDatesList.push($scope.todaytabdate);
								$scope.showAssesment = true;
							}
						}
							$scope.findAllPatientEvalutionSheetByPatientIdAndCreatedDate($scope.evalutionDatesList[0]);
						$scope.getAllSpeechTheraphyTemplatesByPatientInEvalution($scope.evalutionDatesList[0]);
					})
					
		}
		// Evalution sheet tab
		$scope.addFlagEvaluaion = false;
		$scope.addTabEvalution = function() {
			if ($scope.evalutionDatesList[$scope.evalutionDatesList.length - 1].date != $scope.todaytabdate) {
				$scope.evalutionDatesList.push({"date":$scope.todaytabdate});
				$scope.addFlagEvaluaion = true;
				$scope.previousDate1 = $scope.evalutionDatesList[$scope.evalutionDatesList.length - 1];
				$scope.findAllPatientEvalutionSheetByPatientIdAndCreatedDate($scope.previousDate1);
				$scope.getAllSpeechTheraphyTemplatesByPatientInEvalution($scope.previousDate1);
				$scope.selectAssessent = true;
			} else {
				toastr.options = {
					closeButton : true,
					evalutionBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				toastr.error('', 'Not allowed to add Evalutionsheet in same date');
			}

		};
		
		 
		// progress sheet tab
		$scope.addFlag = false;
		$scope.addTabProgress = function() {
			if ($scope.progressNoteDatesList[$scope.progressNoteDatesList.length - 1] != $scope.todaytabdate) {
				$scope.progressNoteDatesList.push($scope.todaytabdate);
				$scope.addFlag = true;
				$scope.previousDate1 = $scope.progressNoteDatesList[$scope.progressNoteDatesList.length - 1];
				$scope.findAllPatientProgressSheetByPatientIdAndCreatedDate($scope.previousDate1);
				$scope.getAllSpeechTheraphyTemplatesByPatientInProgress($scope.previousDate1);
			} else {
				toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				toastr.error('', 'Not allowed to add progressheet in same date');
			}

		};
			    $scope.removeTab = function (tab) {
			      var index = tabs.indexOf(tab);
			      tabs.splice(index, 1);
			    };
			    
			    $scope.generateInvoice = function(invoice,pa) {
			    	patientService.generateInvoice().then(
							function(response) {
								
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
							});
					    	
			    };
			    // assigntemplate to patient //
			    $scope.evalutionFlag=false;
			    $scope.selectTemplate=function(){
			    	if($scope.progress != undefined){
			    		$scope.addEvalutionSave();
			    	}
			    	if($scope.stateChanged == false){
			    	$state.go('main.speechTheraphyTemplates',{
			    		patient:$scope.patientObject,
			    		evalutionflag:$scope.evalutionFlag,
			    		progressinEvalution:$scope.progress
			    	})
			    	}
			    };
			    
			    $scope.selectMchartTemplate=function(){
			    	$state.go('main.mchart',{
			    		mchartObj:$scope.patientObject
			    	})
			    };
			    
			    $scope.selectTemplateForProgress=function(progressNote1){
			    	if(progressNote1 != undefined){
			    		$scope.addProgressNote(progressNote1);
			    	}
			    if($scope.stateChangeProgress == false){
			    	$state.go('main.speechTheraphyTemplates',{
			    		patient:$scope.patientObject
			    	})
			    }
			    };
			    $scope.exitFlag123=true;
			    $scope.selectTemplateExit=function(){
			    	$scope.addExitNote();
			    	$state.go('main.speechTheraphyTemplates',{
			    		patient:$scope.patientObject,
			    		exitflag:$scope.exitFlag123
			    	})
			    	
			    };
			    // getAllSpeechTheraphyTemplatesByPatientInEvalution in
				// patient_evalution_sheet page
			    $scope.getAllSpeechTheraphyTemplatesByPatientInEvalution=function(){
			    	patientService.getAllSpeechTheraphyTemplatesByPatientInEvalution($scope.patientObject.id,"1").then(function(response){
			    		$scope.patientSpeechTemplates	=response.data;
			    		angular.forEach($scope.patientSpeechTemplates,function(patientSpeechTemplate){
			    			if(patientSpeechTemplate.status=="1"){
			    				$scope.speechTheraphyTemplate1 =patientSpeechTemplate;
			    				$scope.status1=true;
			    			}
			    			if(patientSpeechTemplate.status=="2"){
			    				$scope.speechTheraphyTemplate2 =patientSpeechTemplate;
			    				$scope.status2=true;
			    			}
			    			if(patientSpeechTemplate.status=="3"){
			    				$scope.speechTheraphyTemplate3 =patientSpeechTemplate;
			    				$scope.status3=true;
			    			}
			    			if(patientSpeechTemplate.status=="4"){
			    				$scope.speechTheraphyTemplate4 =patientSpeechTemplate;
			    				$scope.status4=true;
			    			}
			    			if(patientSpeechTemplate.status=="5"){
			    				$scope.speechTheraphyTemplate5 =patientSpeechTemplate;
			    				$scope.status5=true;
			    			}
			    			
			    		})
			    	})
			    }
			// getAllSpeechTheraphyTemplatesByPatientInEvalution in
			// patient_progress_sheet page
			    $scope.getAllSpeechTheraphyTemplatesByPatientInProgress=function(tabdate){
			    	patientService.getAllSpeechTheraphyTemplatesByPatientInProgress($scope.patientObject.id,tabdate).then(function(response){
			    		$scope.patientSpeechTemplatesInEvalution	=response.data;
			    		angular.forEach($scope.patientSpeechTemplatesInEvalution,function(patientSpeechTemplate){
			    			if(patientSpeechTemplate.status=="1"){
			    				$scope.speechTheraphyTemplate1 =patientSpeechTemplate;
			    				$scope.status1=true;
			    			}
			    			if(patientSpeechTemplate.status=="2"){
			    				$scope.speechTheraphyTemplate2 =patientSpeechTemplate;
			    				$scope.status2=true;
			    			}
			    			if(patientSpeechTemplate.status=="3"){
			    				$scope.speechTheraphyTemplate3 =patientSpeechTemplate;
			    				$scope.status3=true;
			    			}
			    			if(patientSpeechTemplate.status=="4"){
			    				$scope.speechTheraphyTemplate4 =patientSpeechTemplate;
			    				$scope.status4=true;
			    			}
			    			if(patientSpeechTemplate.status=="5"){
			    				$scope.speechTheraphyTemplate5 =patientSpeechTemplate;
			    				$scope.status5=true;
			    			}
			    			
			    		})
			    	})
			    }
			    
			    $scope.getAllSpeechTheraphyTemplatesByPatientInExit=function(){
			    	patientService.getAllSpeechTheraphyTemplatesByPatientInEvalution($scope.patientObject.id,"3").then(function(response){
			    		$scope.patientSpeechTemplatesInExit	=response.data;
			    		angular.forEach($scope.patientSpeechTemplatesInExit,function(patientSpeechTemplate){
			    			if(patientSpeechTemplate.status=="1"){
			    				$scope.speechTheraphyTemplate1 =patientSpeechTemplate;
			    				$scope.status1=true;
			    			}
			    			if(patientSpeechTemplate.status=="2"){
			    				$scope.speechTheraphyTemplate2 =patientSpeechTemplate;
			    				$scope.status2=true;
			    			}
			    			if(patientSpeechTemplate.status=="3"){
			    				$scope.speechTheraphyTemplate3 =patientSpeechTemplate;
			    				$scope.status3=true;
			    			}
			    			if(patientSpeechTemplate.status=="4"){
			    				$scope.speechTheraphyTemplate4 =patientSpeechTemplate;
			    				$scope.status4=true;
			    			}
			    			if(patientSpeechTemplate.status=="5"){
			    				$scope.speechTheraphyTemplate5 =patientSpeechTemplate;
			    				$scope.status5=true;
			    			}
			    			
			    		})
			    	})
			    }
		// folder structre for patient
			    
			   $scope.getAllFolders=function(){
				   patientService.getAllFoldersbyPatient($scope.patientObject.id).then(function(repsonse){
					   $scope.listOfFolders=repsonse.data;
					   $scope.patientObject.folders  =$scope.listOfFolders;
				   },function(error){
					   
				   })
			   }; 
			   $scope.listOfDocumentlookupFolders=[];
			   $scope.getAllDocumentLookupFolders=function(){
				   patientService.getAllDocumentLookupFolders().then(function(repsonse){
					   $scope.listOfDocumentlookupFolders=repsonse.data;
				   },function(error){
					   
				   })
			   }; 
			   $scope.folder={
						"patient": null ,
						"name":null,
						"description":null
				   };
			   $scope.createFolderForPatient=function(){
				   $scope.folder={
						"patient": $scope.patientObject ,
						"name":$scope.foldername,
						"description":$scope.folderdescription
				   }
				   patientService.createFolderForPatient($scope.folder).then(function(response){
					   $scope.getAllFolders();
					   toastr.options = {
			                    closeButton: true,
			                    progressBar: true,
			                    showMethod: 'slideDown',
			                    positionClass : "toast-top-full-width",
			                    timeOut: 2000
			                };
			                toastr.success('',successMessageHandler.PATIENT_FOLDER_ADD_SUCCESS);
				 
				   },function(error){
					   toastr.options = {
			                    closeButton: true,
			                    progressBar: true,
			                    showMethod: 'slideDown',
			                    positionClass : "toast-top-full-width",
			                    timeOut: 2000
			                };
			                $scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
						
				   })
			   }
		
			   
			   $scope.getAllDocumentsByPatientAndFolderId=function(patientId,foldetId){
				   patientService.getAllDocumentsByPatientAndFolderId(patientId,foldetId).then(function(response){
					 $scope.patientDocumentsByFolder=  response.data;
				   },function(error){
					   
				   })
			   };
			   $scope.gotoInvoicePage = function(pa) {
				   if(pa.invoice!=null){
					   $rootScope.invoiceId=pa.invoice;
				   }
					$state.go('main.invoiceTemplate', {
						patientObject : pa,
					});
				};
				
				$scope.dto=$stateParams.dtoObjct;
				$scope.gotoEditInvoice = function(client) {
					if(client.invoice!=null){
						$rootScope.invoiceId=client.invoice;
					}
						/*
						 * $state.go('main.invoiceTemplate', { patientObject :
						 * pa, });
						 */
					$state.go('main.editInvoice',{
						dtoObjct:	client
					});
				};
				
				// new record
				    
				    
				        $scope.addNew = function(){
				            $scope.invoiceList.push({ 
				                'itemName': "", 
				                'itemDescription': "",
				                'quantity': "",
				                'unitCost':"",
				                'price':""
				            });
				        };
				    
				        $scope.remove = function(){
				            var newDataList=[];
				            $scope.selectedAll = false;
				            angular.forEach($scope.invoiceList, function(selected){
				                if(!selected.selected){
				                    newDataList.push(selected);
				                }
				            }); 
				            $scope.invoiceList = newDataList;
				        };
				    
				    $scope.checkAll = function () {
				        if (!$scope.selectedAll) {
				            $scope.selectedAll = true;
				        } else {
				            $scope.selectedAll = false;
				        }
				        angular.forEach($scope.personalDetails, function(personalDetail) {
				            personalDetail.selected = $scope.selectedAll;
				        });
				    };    
				    // //////////
				    $scope.saveInvoice = function(details,invoice,amt,patent) {
				    	 var date1 = invoice.paidDate; 
				    	
				    	
				    	 
				    	 if(date1.toString().charAt(2)!='-'){
						  mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
						  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
						  
						  invoice.paidDate=changepaiddate;
				    	 }
						$scope.invoiceDto={
								"invoiceNumber":$rootScope.invoiceId,
								"items":details,
								"custNotes":invoice.custNotes,
								"termsConditions":invoice.termsConditions,
								"paidAmount":invoice.paidAmount,
								"paymethod":invoice.paymethod,
								"currency":invoice.currency,
								"paidDate":invoice.paidDate,
								"paidStatus":invoice.paidStatus,
								"totalAmount":amt,
								"patient":patent
						}
						patientService.saveInvoice($scope.invoiceDto).then(function(response) {
							toastr.options = {
				                    closeButton: true,
				                    progressBar: true,
				                    showMethod: 'slideDown',
				                    positionClass : "toast-top-full-width",
				                    timeOut: 2000
				                };
				                toastr.success('',successMessageHandler.PATIENT_ADD_SUCCESS);
				                $scope.invoiceList=[];
				                $scope.getInvoice(pid);
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
					};
					
					$scope.getInvoice= function(id) {
						$scope.invoiceDto={
								"patientId":id,
								"invoiceNumber":$rootScope.invoiceId
						}
						patientService.getInvoice($scope.invoiceDto).then(function(response) {
							$scope.invoiceSingle = response.data;
							if($scope.invoiceSingle==""||$scope.invoiceSingle.items.length<1){
								$scope.getSubAppointmentsByInvoiceNo(id);
							}
							angular.forEach($scope.invoiceSingle.items,function(item){
								$scope.invoiceList.push(item);
							})
							
							
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
					};
					
					
					
					
					$scope.downloadInvoice= function() {
						$scope.invoiceDto={
								"invoiceNumber":$rootScope.invoiceId
						}
						patientService.getInvoiceItems($scope.invoiceDto).then(function(response){
								$scope.invoice = response.data;
						
								if($scope.invoice==0){
									toastr.options = {
						                    closeButton: true,
						                    progressBar: true,
						                    showMethod: 'slideDown',
						                    positionClass : "toast-top-full-width",
						                    timeOut: 2000
						                };
						                toastr.error('Please add Items to Download Invoice');
									
								}});
									patientService.downloadInvoice($scope.invoiceDto).then(function(response) {
										responseType: 'arraybuffer'
										var file = new Blob([ response.data ], {
											type : 'application/pdf'
										});
										var fileURL = URL.createObjectURL(file);
										$scope.downloadInvoicecontent = $sce.trustAsResourceUrl(fileURL);
										window.open(fileURL);
									}, function(error) {
										$scope.message = JSON.stringify(error.data.message.trim());
									});
								
						
						
						};
						
					
					
					$scope.goToEdit = function() {
						$state.go('main.editInvoice');
					};
					
					$scope.calculation=function(personalDetail){
						personalDetail.price=personalDetail.quantity*personalDetail.unitCost
					};
					$scope.deleteItem = function(id) {
						patientService.deleteItem(id).then(function(response) {
							toastr.options = {
				                    closeButton: true,
				                    progressBar: true,
				                    showMethod: 'slideDown',
				                    positionClass : "toast-top-full-width",
				                    timeOut: 2000
				                };
				                toastr.success('',successMessageHandler.PATIENT_DELETE_SUCCESS);
				                $state.go('main.new');
							   // $scope.getInvoice();
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
					};
					$scope.getPaidAmount= function(){
						$scope.paidAmountDto={
								"invoiceNo":$rootScope.invoiceId
						}
						patientService.getPaidAmount($scope.paidAmountDto).then(function(response) {
							$scope.paidAmountObj = response.data;
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
					};
					$scope.getTotalAmount= function(list){
						var amt1=0;
						angular.forEach(list,function(single){
							// amt1=amt1+single.price;
							//alert("single:"+single.price)
							 amt1=amt1+parseFloat(single.price);
							//amt1=amt1+single.price;
							//alert("amt1:"+amt1)
						})
						$scope.amt=amt1;
						$rootScope.amt=$scope.amt;
					};
					$scope.getTotalAmount1= function() {
						
						$scope.invoiceDto={
								"invoiceNumber":$rootScope.invoiceId
						}
						patientService.getTotalAmount($scope.invoiceDto).then(function(response) {
							$scope.amt = response.data;
							if($scope.amt==0){
								$scope.amt=$rootScope.amt;
							}
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
					};
					$scope.editing=function(){
						 $state.go('main.updateInvoice');
					};
					
					$scope.gotoView = function(pa) {
						   $rootScope.invoiceId=pa.invoice;
							$state.go('main.viewInvoice', {
								patientObject : pa,
							});
						};
						// get regional centers from lookup
						$scope.getAllRCZ = function() {
							regionalCenterZoneService.getAllRCZByStatus().then(function(response) {
								$scope.rczLists = response.data;
							}, function(error) {
							});
						};
						
						$scope.addRegionalCenter = function(region) {
							
							var address1 = document.getElementById('street_number').value;
							var address2 = document.getElementById('route').value;
							var city = document.getElementById('locality').value;
							var state = document.getElementById('administrative_area_level_1').value;
							var country = document.getElementById('country').value;
							var zipcode = document.getElementById('postal_code').value;
							$scope.reginoalCenterAddress.address1=address1;
							$scope.reginoalCenterAddress.address2=address2;
							$scope.reginoalCenterAddress.city=city;
							$scope.reginoalCenterAddress.state=state;
							$scope.reginoalCenterAddress.country=country;
							$scope.reginoalCenterAddress.zipcode=zipcode;
							region.reginoalCenterAddress=$scope.reginoalCenterAddress;
							region.adminUserName=$rootScope.loggedUsername;
							
							patientService.addRegionalCenter(region).then(function(response) {
								toastr.options = {
					                    closeButton: true,
					                    progressBar: true,
					                    showMethod: 'slideDown',
					                    positionClass : "toast-top-full-width",
					                    timeOut: 2000
					                };
					                toastr.success('',successMessageHandler.PATIENT_ADD_SUCCESS);
								$state.go('main.regionalCenterList');
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
					                
								
							});
						};
						
						$scope.getAllRegionalCentersByAdminPage= function() {
							patientService.getAllRegionalCentersByAdminPage($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
								$scope.regionalCentersLists = response.data.content;
								$scope.totalElements = response.data.totalElements;
								$scope.totalPages = response.data.totalPages-1;
								$scope.noOfPgaes(response.data.totalPages);
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
							});
						};
						$scope.gotoRegionList = function() {
							$state.go('main.regionalCenterList');
						};
						$scope.gotoRegionAdd = function() {
							$state.go('main.addRegionalCenter');
						};
						$scope.gotobackRegionalCenter=function(){
							$state.go('main.regionalCenterList');
						}
						$scope.region=	$stateParams.regionObject;
						$scope.gotoRegionupdate = function(reg) {
							$state.go('main.updateRegionalCenter', {
								regionObject : reg

							});
						};
						$scope.updateRegionalCenter = function(region) {
							region.adminUserName=$rootScope.loggedUsername;
							patientService.updateRegionalCenter(region).then(function(response) {
								toastr.options = {
					                    closeButton: true,
					                    progressBar: true,
					                    showMethod: 'slideDown',
					                    positionClass : "toast-top-full-width",
					                    timeOut: 2000
					                };
					                toastr.success('',successMessageHandler.PATIENT_UPLOAD_SUCCESS);
								$state.go('main.regionalCenterList');
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
								toastr.options = {
					                    closeButton: true,
					                    progressBar: true,
					                    showMethod: 'slideDown',
					                    positionClass : "toast-top-full-width",
					                    timeOut: 2000
					                };
					                toastr.error('',$scope.message);
								
							});
						};
						$scope.deleteRegionalCenter = function(id) {
							patientService.deleteRegionalCenter(id).then(function(response) {
								toastr.options = {
					                    closeButton: true,
					                    progressBar: true,
					                    showMethod: 'slideDown',
					                    positionClass : "toast-top-full-width",
					                    timeOut: 2000
					                };
					                toastr.success('',successMessageHandler.PATIENT_DELETE_SUCCESS);
								$scope.getAllRegionalCentersByAdminPage();
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
					                
								
							});
						};
						
						$scope.deletealertRegionalCenter=function(id){
							swal({
								  title: "Are you sure?",
								  text: "Once deleted, you will not be able to recover this!",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  patientService.deleteRegionalCenter(id).then(function(response) {
											 swal("Poof! Your RC has been deleted!", {
											      icon: "success",
											    });
											 $scope.getAllRegionalCentersByAdminPage();
										}, function(error) {
											$scope.message = JSON.stringify(error.data.message.trim());
											 swal($scope.message , {
											      icon: "info",
											    });
										});
								    swal("Proof! Your RC has been deleted!", {
								      icon: "success",
								    });
								   
								  } else {
								    swal("Your RC is safe!");
								  }
								});
						};
						
						$scope.getAllItems= function() {
							patientService.getAllItems().then(function(response) {
								$scope.invoiceList1 = response.data;
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
							});
						};
						
						$scope.getAllPaymethodsList= function() {
							paymethodService.getAllPaymethodsList($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
								$scope.paymethodLists = response.data.content;
								$scope.totalElements = response.data.totalElements;
								$scope.totalPages = response.data.totalPages-1;
								$scope.noOfPgaes(response.data.totalPages);
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
							});
						};
						
						$scope.getAllPaymethods= function() {
							paymethodService.getAllPaymethods($rootScope.loggedUsername).then(function(response) {
								$scope.paymethodsList = response.data;
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
							});
						};
						$scope.getAllCurrencys= function() {
							currencyService.getAllCurrencys($rootScope.loggedUsername).then(function(response) {
								$scope.currencysList = response.data;
							}, function(error) {
								$scope.message = JSON.stringify(error.data.message.trim());
							});
						};
						$scope.gotoAppointment= function(){
							$state.go('main.appointment_list');
						}
				    
				    
						$scope.deletePatintSpeechTheraphyTemplateByPatintAndStausInEvalution=function(templateId,patientId,statustest){
							if(statustest=='1'){
								$scope.status1=false;
							}
                           if(statustest=='2'){
                        	   $scope.status2=false;
							}
                          if(statustest=='3'){
                        	  $scope.status3=false;
                           }
                          if(statustest=='4'){
                        	  $scope.status4 =false;
                           }
                          if(statustest=='5'){
                        	  $scope.status5=false;
                          }
							$scope.status="1";
							patientService.deletePatintSpeechTheraphyTemplateByPatintAndStausInEvalution(templateId,patientId,$scope.status).then(function(repsonse){
								 $scope.getAllSpeechTheraphyTemplatesByPatientInEvalution();
							},function(error){
								
							})
						};
						
						$scope.deletePatintSpeechTheraphyTemplateByPatintAndStausInProgress=function(templateId,patientId,statusinProgress){
							if(statusinProgress =='1'){
								$scope.status1=false;
							}
                           if(statusinProgress =='2'){
                        	   $scope.status2=false;
							}
                          if(statusinProgress =='3'){
                        	  $scope.status3=false;
                           }
                          if(statusinProgress =='4'){
                        	  $scope.status4 =false;
                           }
                          if(statusinProgress =='5'){
                        	  $scope.status5=false;
                          }
							$scope.status="2";
							patientService.deletePatintSpeechTheraphyTemplateByPatintAndStausInEvalution(templateId,patientId,$scope.status).then(function(repsonse){
								 $scope.getAllSpeechTheraphyTemplatesByPatientInProgress();
							},function(error){
								
							})
						};
						
						$scope.deletePatintSpeechTheraphyTemplateByPatintAndStausInExit=function(templateId,patientId,statustest){
							if(statustest=='1'){
								$scope.status1=false;
							}
                           if(statustest=='2'){
                        	   $scope.status2=false;
							}
                          if(statustest=='3'){
                        	  $scope.status3=false;
                           }
                          if(statustest=='4'){
                        	  $scope.status4 =false;
                           }
                          if(statustest=='5'){
                        	  $scope.status5=false;
                          }
							$scope.status="3";
							patientService.deletePatintSpeechTheraphyTemplateByPatintAndStausInEvalution(templateId,patientId,$scope.status).then(function(repsonse){
								 $scope.getAllSpeechTheraphyTemplatesByPatientInExit();
							},function(error){
								
							})
						};
					
						$scope.deleteTemplateInEvalutionAlert=function(templateId,patientId,status){
							swal({
								  title: "Are you sure?",
								  text: "Once deleted, you will not be able to recover this Template!",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  $scope.deletePatintSpeechTheraphyTemplateByPatintAndStausInEvalution(templateId,patientId,status);
								
								    swal("Proof! Your Template has been deleted!", {
								      icon: "success",
								    });
								  } else {
								    swal("Your Template is safe!");
								  }
								});
						};
						
						$scope.deleteTemplateInProgressAlert=function(templateId,patientId,status){
							swal({
								  title: "Are you sure?",
								  text: "Once deleted, you will not be able to recover this Template!",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  $scope.deletePatintSpeechTheraphyTemplateByPatintAndStausInProgress(templateId,patientId,status);
								
								    swal("Proof! Your Template has been deleted!", {
								      icon: "success",
								    });
								  } else {
								    swal("Your Template is safe!");
								  }
								});
						};
						
						$scope.deleteTemplateInExitAlert=function(templateId,patientId,status){
							swal({
								  title: "Are you sure?",
								  text: "Once deleted, you will not be able to recover this Template!",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  $scope.deletePatintSpeechTheraphyTemplateByPatintAndStausInExit(templateId,patientId,status);
								
								    swal("Proof! Your Template has been deleted!", {
								      icon: "success",
								    });
								  } else {
								    swal("Your Template is safe!");
								  }
								});
						};
						
						
	$scope.gotoTherapyAddressUndefined = function(){					
	if($scope.patient.modelData==false){
		$scope.patient.therapyAddress.address1 = undefined;
		$scope.patient.therapyAddress.address2 = undefined;
		$scope.patient.therapyAddress.country= undefined;
		$scope.patient.therapyAddress.state= undefined;
		$scope.patient.therapyAddress.cityName=undefined;
		$scope.patient.therapyAddress.zipcode=undefined;
	}					
	};
	$scope.resetFilter=function(filter){
		filter.doctor=undefined;
		filter.startDate=undefined;
		filter.endDate=undefined;
	}
	$scope.openFole=function(){
		if($scope.showIframe = !$scope.showIframe){
			$scope.content=undefined;
		}
	};
	$scope.getSinglepayment= function() {
		patientService.getSinglepayment($rootScope.pid).then(function(response) {
			$scope.payment = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updatePayment= function(payment) {
		// payment.patientId=$rootScope.pid;
		payment.patient.id=$rootScope.pid;
		
		patientService.updatePayment(payment).then(function(response) {
			/*
			 * toastr.options = { closeButton: true, progressBar: true,
			 * showMethod: 'slideDown', positionClass : "toast-top-full-width",
			 * timeOut: 2000 }; toastr.success('',' Data Update Successfully');
			 */
                $scope.getSinglepayment();  
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	
	
	$scope.gotoPatient= function(){
		$state.go('main.add_patient.add_patient_details');
	};
	
	$scope.getSubAppointmentsByInvoiceNo= function(id) {
		$scope.itemDto = {
				"patientId":id,
			"invoiceNumber":$rootScope.invoiceId
		}
		appointmentService.getSubAppointmentsByInvoiceNo($scope.itemDto).then(function(response) {
			$scope.invoiceList = response.data;
			$scope.getTotalAmount($scope.invoiceList);
			var d1=new Date($scope.invoiceList[$scope.invoiceList.length-1].appointmentStartedDate.slice(6, 10)+"-"+$scope.invoiceList[$scope.invoiceList.length-1].appointmentStartedDate.slice(3,5)+"-"+
					$scope.invoiceList[$scope.invoiceList.length-1].appointmentStartedDate.slice(0,2));
			var date1=new Date(d1.setDate(d1.getDate() + 7));
			
			 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
			$scope.dueDte =[day,mnth,date1.getFullYear() ].join("-");
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.emailValid = function(email) {
		 	 $scope.isEmailIdExists=false;
			    $http.get(MODULE_CONFIG.IS_EMAIL_EXISTS(email)).
		      then(function(response) {
		    	  $scope.isEmailIdExists=response.data;
		      })
	};
	$scope.gotoSubAppointmentsByAppointment=function(id) {
		$rootScope.appointmentId=id;
		$state.go('main.subAppointmentList')
	// $scope.getAllSubAppointmentsByAppointment();
	}
	
	$scope.getClientType=function(){
		if($scope.patient !=undefined && $scope.patient.regionalCenter !=undefined && $scope.patient.regionalCenter.reginoalCenterAddress !=undefined && $scope.patient.regionalCenter.reginoalCenterAddress.country !=undefined  && $scope.patient.regionalCenter.reginoalCenterAddress.country.id !=undefined){
			$scope.getAllStatesByCountryId($scope.patient.regionalCenter.reginoalCenterAddress.country.id);
		}
		if($scope.patient != undefined && $scope.patient.privateClient != undefined && $scope.patient.privateClient.address.country != undefined && $scope.patient.privateClient.address.country.id != undefined){
			$scope.getAllStatesByCountryId($scope.patient.privateClient.address.country.id);
		}
		if($scope.patient != undefined && $scope.patient.school != undefined && $scope.patient.school.schoolAddress.country != undefined && $scope.patient.school.schoolAddress.country.id != undefined){
			$scope.getAllStatesByCountryId($scope.patient.school.schoolAddress.country.id);
		}
		if($scope.patient != undefined && $scope.patient.insurance != undefined && $scope.patient.insurance.address.country != undefined && $scope.patient.insurance.address.country.id != undefined){
			$scope.getAllStatesByCountryId($scope.patient.insurance.address.country.id);
		}
	};
	
	$scope.getCommunicationAddress=function(){
		if($scope.patient !=undefined && $scope.patient.address != undefined && $scope.patient.address.country!=undefined && $scope.patient.address.country.id!=undefined){
			$scope.getAllStatesByCountryId($scope.patient.address.country.id);
		}
		if($scope.patient !=undefined && $scope.patient.therapyAddress != undefined && $scope.patient.therapyAddress.country!=undefined && $scope.patient.therapyAddress.country.id!=undefined){
			$scope.getAllStatesByCountryId123($scope.patient.therapyAddress.country.id);
		}
	};
	
	$scope.getImagePathDataByUser = function() {
		return PATIENT_MODULE_CONFIG.GET_USER_IMAGEPATHDATA($scope.doctorObj.userAccount.username);
	};
	
	// converting dd/mm/yyyy formate to mm/dd/yyyy
	$scope.changeDate=function(appointmentStartedDate){
		var currentDate= appointmentStartedDate; 
		var newDate = currentDate.split('-'); 
		currentDate = newDate[1] + "-" + newDate[0] + "-" + newDate[2];
		return currentDate;
	}

	// s3 bucket
/*
 * $scope.getFilePathinS3Bucket=function(documentId){
 * patientService.findFilePathinS3Bucket(documentId).then(function(response){
 * $scope.s3BucketUrl= response.data; $scope.currents3BucketUrl =
 * $sce.trustAsResourceUrl($scope.s3BucketUrl); },function(error){
 * console.log("error"); }) }
 */

	$scope.getFilePathinS3Bucket= function(documentId) {
		patientService.findFilePathinS3Bucket(documentId).then(function(response) {
			$scope.s3Bucket = response.data;
			$scope.content =$sce.trustAsResourceUrl($scope.s3Bucket.location);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.getSubAppointmentsByInvoicePage = function(appointment) {
		$scope.invoiceFlag=true;
		// $scope.adminUserName = $rootScope.loggedUsername;
	/*
	 * alert(JSON.stringify(appointment));
	 * alert(JSON.stringify(appointment.doctor));
	 * alert(JSON.stringify(appointment.startDate));
	 * alert(JSON.stringify(appointment.endDate));
	 */
		appointment.adminUsername=$rootScope.loggedUsername;
		if(appointment.startDate!=null&&appointment.startDate!=null){
			if(appointment.startDate.toString().charAt(2)!='-'){
		 var date1 = appointment.startDate; 
		  mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
		  var startedDate =[day,mnth,date1.getFullYear() ].join("-");
		 
		  var date = appointment.endDate; 
		  mnth = ("0" +(date.getMonth() + 1)).slice(-2), day = ("0" + date.getDate()).slice(-2);
		  var endedDate =[day,mnth,date.getFullYear() ].join("-");
		  
		  appointment.startDate=startedDate;
		  appointment.endDate=endedDate;
			}
		}
		$rootScope.appoimtnent=appointment;
		appointmentService.getSubAppointmentsByInvoicePage(appointment,$scope.page, $scope.size).then(
				function(response) {
					$scope.appointmentsListPage = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
					// appointment.startDate=undefined;
					// appointment.endDate=undefined;
					// appointment.doctor=undefined;
				
				}, function(error) {
					appointment.startDate=undefined;
					appointment.endDate=undefined;
				});
	};
	
	$scope.gotoBillingsList= function() {
		$state.go('main.patientslist');
	}
	
	$scope.gotoPayList= function(id) {
		$rootScope.patintId=id;
		$state.go('main.paymentList');
	}
	$scope.getPaymentsByPatient = function() {
		patientService.getPaymentsByPatient($rootScope.patintId).then(
				function(response) {
					$scope.paymentsList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	
		patientService.searchPatientById($rootScope.patintId).then(function(response) {
			$scope.patientdata = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	
	};
	$scope.gotoDetails= function(patient) {
		// $state.go('main.patientDetails');
		$scope.gotoPatientDetails(patient) ;
	}
	
	$scope.findAllPatientsByRoleForBillingsPage=function(patientBillingDto,search){
		$scope.billingsFlag=true;
		// alert("patientBillingDto:"+JSON.stringify(patientBillingDto));
		/*
		 * if(patientBillingDto.status!=undefined&&patient.status==""){
		 * patient.status=null; }
		 * 
		 * if(patient.occurance!=undefined&&patient.occurance==""){
		 * patient.occurance=undefined; }
		 * 
		 * if(patient.clientType!=undefined&&patient.clientType==""){
		 * patient.clientType=undefined; }
		 * 
		 * if(patient.department!=undefined&&patient.department==""){
		 * patient.department=undefined; }
		 */
		
		if(patientBillingDto.startDate!=undefined && patientBillingDto.endDate!=undefined){
			 if(patientBillingDto.startDate.toString().charAt(2)!='-'){
				 // alert("- ledu")
		var date1 = patientBillingDto.startDate;
		mnth = ("0" + (date1.getMonth() + 1)).slice(-2), day = ("0" + date1
				.getDate()).slice(-2);
		var changepaiddate = [day,mnth,date1.getFullYear() ].join("-");
		patientBillingDto.startDate=changepaiddate;
		
		var date = patientBillingDto.endDate;
		mnth = ("0" + (date.getMonth() + 1)).slice(-2), day = ("0" + date
				.getDate()).slice(-2);
		var changedate = [day,mnth,date.getFullYear() ].join("-");
		patientBillingDto.endDate=changedate;
		}
		 }
	// alert("search:"+JSON.stringify(search));
		if(search == " " || search == undefined || search == ""){
			search=null;
		}
		patientBillingDto.adminUsername=$rootScope.loggedUsername;
		patientBillingDto.search=search;
	/*
	 * patientBillingDto.page=$scope.page; alert($scope.page)
	 * patientBillingDto.size=$scope.size;
	 */
		// alert("Final patientBillingDto:"+JSON.stringify(patientBillingDto));
		$rootScope.patientBillingDto=patientBillingDto;
		$rootScope.searching=search;
		patientService.findAllPatientsByRoleForBillingsPage(patientBillingDto, $scope.page, $scope.size).then(function(response) {
			$scope.BillingsPatients = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages - 1;
			$scope.noOfPgaes(response.data.totalPages);
			// alert("length:"+$scope.BillingsPatients.length)
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	$scope.getAllInsurancesByCompany=function(){
		insuranceService.getAllInsurancesByCompany($rootScope.loggedUsername).then(function(response) {
			$scope.insuranceDataList=response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	
	
	
	$scope.getAllCategoryTypeByStatus = function() {
		//alert("called ctrl");
		categoryTypeService.getAllCategoryTypeByStatus().then(function(response) {
			$scope.categoryTypesList = response.data;
			//alert($scope.categoryTypesList)
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
		});
	};
	
	$scope.getAllSchoolsByCompany = function() {
		//alert("getAllSchoolsByCompany")
		schoolService.getAllSchoolsByCompany($rootScope.loggedUsername)
				.then(function(response) {
					$scope.schoolsLists = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.getAllInsurancesByCompany = function() {
		insuranceService.getAllInsurancesByCompany($rootScope.loggedUsername)
				.then(function(response) {
					$scope.insurancesLists = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.getAllPatientMentalScalesByPatientId = function() {
		patientService.getAllPatientMentalScalesByPatientId(
				$scope.patientObject.id).then(function(response) {
			$scope.patientMentalScalelist = response.data;
			if($scope.patientMentalScalelist == ""){
				$scope.isMentalScaleExists = false; 
			}else{
				$scope.isMentalScaleExists = true;
			}
			
		}, function(error) {

		})
	};
	$scope.updatePatientMentalScalesByPatientId = function() {
		patientService.updatePatientMentalScalesByPatientId(
				$scope.patientMentalScalelist, $scope.patientObject.id).then(
				function(response) {
					// $scope.getPatientMentalScalesByPatientId();
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-right",
						timeOut : 2000
					};
					toastr.success('', 'Patient Mental Scales Updated!!');
				}, function(error) {

				})
	};
	
	$scope.getPatientMentalScalesAgeAndClusterCount = function() {
		patientService.getPatientMentalScalesAgeAndClusterCount(
				$scope.patientObject.id).then(function(response) {
			$scope.mentalAgeAndClusterCount = response.data;
			console.log("$scope.mentalAgeAndClusterCount "+$scope.mentalAgeAndClusterCount);
		}, function(error) {
		})
	};
	
	$scope.mentalAgeAndClusterCountGraph={};
	$scope.getPatientMentalScalesAgeAndClusterCountForGraph = function() {
		patientService.getPatientMentalScalesAgeAndClusterCountForGraph(
				$scope.patientObject.id).then(function(response) {
			$scope.mentalAgeAndClusterCountGraph = response.data;
			//1369
			$scope.mentalAgeAndClusterCount = $scope.mentalAgeAndClusterCountGraph;
			angular.forEach($scope.mentalAgeAndClusterCount, function(value, key) {
				  this.push( value.clusterId);
				}, $scope.mentalAgeClusterLabels);
			angular.forEach($scope.mentalAgeAndClusterCount, function(value, key) {
				  this.push( value.graphCount);
				}, $scope.mentalAgeClusterData);
			
			$scope.chartOptionsMental = {
					dataSource : $scope.mentalAgeAndClusterCountGraph,
					series : {
						argumentField : "clusterId",
						valueField : "graphCount",
						name : "Mental Clusters",
						type : "bar",
						color : '#ffaa66'
					}
				};
		}, function(error) {
		})
	};
	
	$scope.getAllPatientMotorScalesByPatientId = function() {
		patientService.getAllPatientMotorScalesByPatientId(
				$scope.patientObject.id).then(function(response) {
			$scope.patientMotorScalelist = response.data;
			if($scope.patientMotorScalelist == ""){
				$scope.isMotorScaleExists = false; 
			}else{
				$scope.isMotorScaleExists = true;
			}

			
		}, function(error) {

		})
	};
	$scope.getPatientMotorScalesAgeAndClusterCount = function() {
		patientService.getAllMotorScalesCountBypatientId(
				$scope.patientObject.id).then(function(response) {
			$scope.motorAgeAndClusterCount = response.data;
		}, function(error) {
		})
	};
	
	$scope.motorAgeAndClusterCountGraph={};
	$scope.getPatientMotorScalesAgeAndClusterCountForGraph = function() {
		patientService.getAllMotorScalesCountBypatientIdForGraph(
				$scope.patientObject.id).then(function(response) {
			$scope.motorAgeAndClusterCountGraph = response.data;
			
			$scope.motorAgeAndClusterCount = $scope.motorAgeAndClusterCountGraph;
			angular.forEach($scope.motorAgeAndClusterCount, function(value, key) {
				  this.push( value.clusterId);
				}, $scope.motorAgeClusterLabels);
			angular.forEach($scope.motorAgeAndClusterCount, function(value, key) {
				  this.push( value.graphCount);
				}, $scope.motorAgeClusterData);
			
			$scope.chartOptionsMotor = {
					dataSource : $scope.motorAgeAndClusterCountGraph,
					series : {
						argumentField : "clusterId",
						valueField : "graphCount",
						name : "Motor Clusters",
						type : "bar",
						color : '#ffaa66'
					}
				};
		}, function(error) {
		})
	};
	
	$scope.gotoMentalMotorTemplate = function() {
		$state.go('main.mental_Motor_Template', {
			Patient : $scope.patientObject
		});
	};
	$scope.updatePatientMotorScalesByPatientId = function() {
		patientService.updatePatientMotorScalesByPatientId($scope.patientMotorScalelist, $scope.patientObject.id).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-right",
						timeOut : 2000
					};
					toastr.success('', 'Patient Motor Scales Updated!!');
				}, function(error) {

				})
	};
	$scope.gotoReport=function(){
		$state.go('main.mentalReportGraph', {
			patientObject : $scope.patientObject
		});
	};
	//718
	
	$scope.getAllPatientBehaviouralProgrammeByPatientId = function() {
		evalutionSheetService.getAllPatientBehaviouralProgrammeByPatientId($scope.patientObject.id).then(
				function(response) {
					$scope.behaviourlManagement = response.data[0];
					if($scope.behaviourlManagement == ""){
						$scope.isBehaviouralManagementExists = false; 
					}else{
						$scope.isBehaviouralManagementExists = true;
					}
					
				}, function(error) {
					alert(JSON.stringify(error));
				})
	};
	$scope.getAllPatientSpeechEvaluationByPatientId = function() {
		evalutionSheetService.getAllPatientSpeechEvaluationByPatientId($scope.patientObject.id).then(
				function(response) {
					$scope.speechEvaluation = response.data[0];
					if($scope.speechEvaluation == ""){
						$scope.isSpeechEvalutionExists = false; 
					}else{
						$scope.isSpeechEvalutionExists = true;
					}
					
				}, function(error) {
					alert(JSON.stringify(error));
				})
	};
	
	
	// update the mchatTemplate to patient in evalution sheet.....
	$scope.updateMchartTemplateToPatient = function() {
		/*$scope.mchartObject = {
			"id" : $scope.patientObject.id,
			"mchart" : $scope.mchartLists
		}*/
		patientService.updateMchartTemplateToPatient($scope.patientMchatQuestions,$scope.patientObject.id).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('',
							successMessageHandler.CATEGORY_ADD_DATA_SUCCESS);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	
	
	// to implement the M-chat functionality in patient view more i.e
	// patient_Evalution_sheet
	
	/*$scope.getAllMchartsByPatientId = function(patientId) {
		mchartService.getAllMchartsByPatientId(patientId).then(function(response) {
			$scope.mchartList = response.data;
			$scope.getAllMchartByPatientIdResult();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllMchartByPatientId = function() {
		mchartService.getAllMchartsByPatientId($scope.patientObject.id).then(function(response) {
			$scope.mchartLists = response.data;
			$scope.getAllMchartByPatientIdResult();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllMchartByPatientIdResult = function() {
		mchartService.getMchartCountResult($scope.patientObject.id).then(function(response) {
			$scope.mchartResult = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	*/
	$scope.getAllMcharts = function() {
		mchartLookupService.getAllMcharts().then(function(response) {
			$scope.mchartList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.getPatient=function(){
		$scope.patientOb={
				"firstName":$scope.patient.firstName,
				"email":$scope.patient.emailPatient
		}
		patientService.getPatientObj($scope.patientOb).then(function(response){
			$scope.mchatPatientobj=response.data;
			$scope.getAllMchartsByPatientId($scope.mchatPatientobj.id);
		},function(error){
			
		})
	}
	
	
	// assign mchat template to patient
	$scope.mchatAssessmentDto={};
	$scope.addmchatTemplateToPatient=function(){
		$scope.mchatAssessmentDto={
			 	"patient":$scope.patient,
            "mchartLookups":$scope.mchartList
		}
		patientService.addMchatTemplateToPatient($scope.mchatAssessmentDto).then(function(response){
			
		},function(error){
			
		})
	}
	
	
	$scope.gotoMchatTemplateRepot=function(patient){
		$state.go('main.M-ChatReportTemplate',{
			mchatPatient:patient
		})
	};
	
	$scope.gotoMchat=function(){
		$state.go('main.M-Chat')
	};
	
	$scope.getAllNichqTeacherByPatientId = function() {
		PatientNichqTeacherCategoryservice.getAllNichqTeacherByPatientId($scope.patientObject.id).then(function(response) {
			$scope.nichqTeacherCategoryList = response.data;
			
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllNichqParentByPatientId = function() {
		PatientNichqParentCategoryservice.getAllNichqParentByPatientId($scope.patientObject.id).then(function(response) {
			$scope.nichqParentCategoryList = response.data;
			$scope.getNichqParentResults($scope.patientObject.id);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getNichqparentCategoryLookups=function(){
		nichqservice.getNichqparent().then(
				function(response){
					$scope.nichqparentcategoryLookuplist = response.data;
				}, function(error){
				})
	};
	// navigate to mchat report
	
	$scope.gotoMchatReport1=function(){
				  $state.go('main.mchatReport', {
						patientObject : $scope.patientObject
					});
	};
	$scope.gotoMchatReport=function(){
		
		swal({
			  title: "Are you sure?",
			  text: "Once Updated, you will goto Report...!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willUpdated) => {
			  if (willUpdated) {
				  /*
					 * $state.go('main.mchatReport', { patientObject :
					 * $scope.patientObject });
					 */
			    swal("Answers Update Successfully...!", {
			      icon: "success",
			    });
			  } else {
			    swal("Please fill the Answers...!");
			  }
			});
	};
	$scope.gotoNichqParentReport=function(){
		swal({
			  title: "Are you sure?",
			  text: "Once Updated, you will goto Report...!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willUpdated) => {
			  if (willUpdated) {
				  $state.go('main.nichqParentReport', {
						patientObject : $scope.patientObject
					});
			
			    swal("Answers Update Successfully...!", {
			      icon: "success",
			    });
			  } else {
			    swal("Please fill the Answers...!");
			  }
			});
	};
	$scope.gotoIsaaBehaviourReport=function(){
		swal({
			  title: "Are you sure?",
			  text: "Once Updated, you will goto Report...!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willUpdated) => {
			  if (willUpdated) {
				  $state.go('main.isaaBeahaviourReport', {
						patientObject : $scope.patientObject
					});
			
			    swal("Answers Update Successfully...!", {
			      icon: "success",
			    });
			  } else {
			    swal("Please fill the Answers...!");
			  }
			});
	};
	$scope.gotoNichqTeacherReport=function(){

		swal({
			  title: "Are you sure?",
			  text: "Once Updated, you will goto Report...!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willUpdated) => {
			  if (willUpdated) {
				  $state.go('main.nichqTeacherReport', {
						patientObject : $scope.patientObject
					});
			
			    swal("Answers Update Successfully...!", {
			      icon: "success",
			    });
			  } else {
			    swal("Please fill the Answers...!");
			  }
			});
	
		
	};
	
	$scope.updateNichqParentTemplateToPatient = function() {
		$scope.nichqParentCategoryDto = {
			"patient" : $scope.patientObject,
			"nichqParentCategoryList" : $scope.nichqParentCategoryList,
			"nichqParentCategoryLookupsList" : $scope.nichqparentcategoryLookuplist
		}
		PatientNichqParentCategoryservice.addNichqParenttemplate($scope.nichqParentCategoryDto).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('Nichq Parent Template is added Successfully');
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.getNichqParentResults = function(patientId) {
		PatientNichqParentCategoryservice.getNichqParentResults(patientId).then(function(response) {
			$scope.nichqParentRersults = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	
	// getAll IsaaBehaviour Templates By Patient ID
	
	$scope.getAllIsaaBeaviourTemplatesByPatientId=function(){
		patientService.getAllIsaaBeaviourTemplatesByPatientId($scope.patientObject.id).then(function(response){
			$scope.allIsaaBeaviourTemplatesByPatientId	=response.data;
			$scope.getIsaaBehaviourResultByPatientId($scope.patientObject.id);
		},function(error){
			
		})
	};
	
	// update isaa behaviour template to patient
	$scope.updateIsaaBehaviourTemplateToPatient = function() {
		$scope.ISAABehaviorObjectDto = {
			"isaaBehaviorCategoryLookupsList" : $scope.isaaBehaviourCategoriesList,
			"iSAABehaviorCategorys" : $scope.allIsaaBeaviourTemplatesByPatientId,
			"patient" :$scope.patientObject
		}
		patientIsaaBehaviourCategoryService.addIsaaBehaviourtemplate(
				$scope.ISAABehaviorObjectDto).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('Isaa Behaviour Template is added Successfully');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	// isaaBehaviourCategoryLookup
	$scope.getAllCategorylookup = function() {
		isaaBehaviourLookUpService.getAllCategorylookup().then(
				function(response) {
					$scope.isaaBehaviourCategoriesList = response.data;
				}, function(error) {

				})
	};
	$scope.button_clicked = true;
	// update method for nichq teacher
	$scope.updatenichqteachertemplatetotheparent = function() {
		$scope.button_clicked = false;
		$scope.PatientNichqTeacherCategoryDto = {
			"nichqTeacherCategoryLookups" : $scope.nichqteachercategorylookupList,
			"patient" : $scope.patientObject,
			"patientNichqTeacherCategories":$scope.nichqTeacherCategoryList
		}
		PatientNichqTeacherCategoryservice.addnichqteachertemplatetotheparent(
				$scope.PatientNichqTeacherCategoryDto).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('Nichq Teacher Template is added Successfully');
			// $scope.getNichqTeacherResults($scope.patientObject.id);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getNichqTeacherResults = function(patientId) {
		PatientNichqTeacherCategoryservice.getNichqTeacherResults(patientId).then(function(response) {
			$scope.nichqTeacherResult = response.data;
			console.log(JSON.stringify($scope.nichqTeacherRersults));
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	
	// get nichq teacher categories by patient id
	$scope.getNichqTeacherCategoriesByPatientId=function(){
		
		PatientNichqTeacherCategoryservice.getAllNichqTeacherByPatientId($scope.patientObject.id).then(function(response){
			$scope.patientNichqTeacherCategorieslist=response.data;
			$scope.getNichqTeacherResults($scope.patientObject.id);
		},function(error){
			
		})
	};
	// get IsaaBehaviour Result By PatientId
	
$scope.getIsaaBehaviourResultByPatientId=function(patiendid){
		
	patientIsaaBehaviourCategoryService.getIsaaBehaviourResultByPatientId(patiendid).then(function(response){
			$scope.isaaBehaviourResult=response.data;
		},function(error){
			
		})
	};
	
	// delete parent result
	$scope.deleteParentResult=function(){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Document!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteParentResultbyPatientId($scope.patientObject.id);
			
			    swal("Proof! Your Document has been deleted!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Document is safe!");
			  }
			});
	};
	
	$scope.deleteParentResultbyPatientId=function(patientid){
		PatientNichqParentCategoryservice.deleteParentResultByPatientId(patientid).then(function(response){
		$scope.getAllNichqParentByPatientId();		
		},function(error){
					
				});
	};
	// delete nichq teacher result
	
	$scope.deleteTeacherResult=function(){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Document!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteTeacherResultByPatientId($scope.patientObject.id);
			
			    swal("Proof! Your Document has been deleted!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Document is safe!");
			  }
			});
	};
	
	$scope.deleteTeacherResultByPatientId=function(patientid){
		PatientNichqTeacherCategoryservice.deleteTeacherResultByPatientId(patientid).then(function(response){
			$scope.getAllNichqTeacherByPatientId();
				},function(error){
					
				});
	};
	// delete isaa behaviour result
	$scope.deleteisaabehaviourResult=function(){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Document!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteIsaaBehaviourResultByPatientId($scope.patientObject.id);
			
			    swal("Proof! Your Document has been deleted!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Document is safe!");
			  }
			});
	};
	
	
	$scope.deleteIsaaBehaviourResultByPatientId=function(patientid){
		isaaBehaviourLookUpService.deleteIsaaBehaviourResultByPatientId(patientid).then(function(response){
			$scope.getAllIsaaBeaviourTemplatesByPatientId();
				},function(error){
					
				});
	};
	// delete Mchart result
	$scope.deleteMchartResult=function(){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Document!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteMchartResultByPatientId($scope.patientObject.id);
			
			    swal("Proof! Your Document has been deleted!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Document is safe!");
			  }
			});
	};
	
	$scope.deleteMchartResultByPatientId=function(patientid){
		mchartLookupService.deleteIsaaBehaviourResultByPatientId(patientid).then(function(response){
			$scope.getAllMchartByPatientId();
				},function(error){
					
				});
	};
	$scope.gotoBackPatient=function(progress,patientObject){
		$state.go('main.patientviewtabs.patient_evaluation_sheet', {
			patientObject : patientObject,
			progress : progress
		});
	};
	
	// mental scales
	$scope.gotoMentalSacles=function(){
		$state.go('main.patientviewtabs.patient_evaluation_sheet.mentalQuestions');
	};
	
	// show tabs navigation in evalution sheet template
	$scope.getTabs=function(){
		$scope.tabsList=["Documents","Patient Note","Evalution Sheet","Goals Sheet","Progress Note","Exit Note"];
	};
	/*$scope.selectedTab=function(tabname){
		if(tabname == "Documents"){
			$scope.isselected=false;
			$state.go('main.patientviewtabs.patient_documents');
		}else if(tabname == "Patient Note"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_note');
		}else if(tabname == "Evalution Sheet"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet');
		}else if(tabname == "Goals Sheet"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_goals_sheet');
		}
		else if(tabname == "Progress Note"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet');
		}
		else{
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet');
		}
	};
	      */
	//718
	$scope.gotoEvaluationTemplateCategories = function(sheetType) {
		//alert("Evalution sheet Type  "+ sheetType);
		
		if (sheetType == "EvalutionReport") {
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation.mentalQuestions',{
					patient: $scope.patientObject
					});
		} else if (sheetType == "SpeechEvalution"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.speechevaluation');
		}
		else if (sheetType == "NichqParentQuestion"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.nichqparentquestionlookup',{
				patient: $scope.patientObject
			});
		}
		else if (sheetType == "PatientNichqTeacherQuestion"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.nichqteacherquestionlookuplist',{
				patient: $scope.patientObject
			});
		}
		else if (sheetType == "BehaviourMangementProgramme"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.behaviourmanagementprogramme');
			
		}else if (sheetType == "Mchart"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.mchatQuestions',{
				patient: $scope.patientObject
			});
			
		}else if (sheetType == "Isaa Behavioural"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.issaBehaviourview',{
				patient: $scope.patientObject
			});
		}else if (sheetType == "VSMS"){
			$scope.selectAssessent=false;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.vsmsTemplate',{
				patient: $scope.patientObject
			});
		}else{
			$scope.selectAssessent=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.evaluationEmptypage');
		}
		
		/*$state.go('main.patientviewtabs.patient_evaluation_sheet.evaluationreport', {
			Patient : $scope.patientObject
		})*/
	}
	//718
	
	$scope.getTabsEvaluationTemplate=function(){
		$scope.tabsList=["DQ Assessment","IQ Assessment","Autism","Speech Evalution","CSBS Assessment"];
	};
	$scope.selectedTab=function(tabname){
		if(tabname == "Documents"){
			$scope.isselected=false;
			$state.go('main.patientviewtabs.patient_documents');
		}
		else if(tabname == "Patient Note"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_note');
		}else if(tabname == "Evalution Sheet"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet');
		}else if(tabname == "Goals Sheet"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_goals_sheet');
		}
		else if(tabname == "Progress Note"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet');
		}
		else{
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_exit_note');
		}
	};
	
	
	
	$scope.selectedAssessment=function(assessment){
		if(assessment == "MotorScales"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.mentalQuestions',{
				patient: $scope.patientObject
			});
		}else if(assessment == "MotorScales"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.motorQuestions');
		}else if(assessment == "Mchat"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.mchatQuestions',{
				patient: $scope.patientObject
			});
		}
		else if(assessment == "CareTaker"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.caretakerMilestones');
		}
		else if(assessment == "ChildObservation"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.childObservation');
		}
		else if(assessment == "ScreeningTest"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.screeningTest');
		}
		else if(assessment == "CSBS"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.csbsTemplate');
		}else if(assessment == "evalutionSheet"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.evalutionTemplate');
		}
		else if(assessment == "NICHQParent"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.nichqparentquestionlookup',{
				patient: $scope.patientObject
			});
		}
		else if(assessment == "NICHQTeacher"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.nichqteacherquestionlookuplist',{
				patient: $scope.patientObject
			});
		}
		else if(assessment == "Behaviour"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.behaviourmanagementprogramme');
		}
		else if(assessment == "Physiotheraphy"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.physiotheraphyevaluation');
		}
		else if(assessment == "SpeechEvalutionTemplate"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.speechevaluation');
		}
		else if(assessment == "ResultTemplate"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.patient_progress_notes');
		}else if( assessment == "Isaa"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.issaBehaviourview',{
				patient: $scope.patientObject
			});
		}
		
		else if( assessment == "Mental"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation.mentalQuestions',{
				patient: $scope.patientObject
			});
			
		}
		else if( assessment == "VSMS"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.vsmsTemplate',{
				patient: $scope.patientObject
			});
			
		}

	/*	else if( assessment == "ISAABehavioural"){
			$scope.isselected=true;
			$state.go('main.patientviewtabs.patient_evaluation_sheet.issaBehaviourview.isaaTemplate',{
				patient: $scope.patientObject
			});
		}*/
	};
	
	//Get All Patient Reg Type List
	$scope.getAllPatientRegistrationTypesList = function() {
		departmentService.getAllPatientRegistrationTypesList().then(
				function(response) {
					$scope.patientRegistrationTypes = response.data;
					
					
				}, function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	};
	
	
	//718 export pdf
	/*$scope.export = function(){
		html2canvas(document.getElementById('patientEvalutionReport'), {
			onrendered: function (canvas) {
				var data = canvas.toDataURL();
				var docDefinition = {
					content: [{
						image: data,
						width: 500,
					}]
				};
				pdfMake.createPdf(docDefinition).download("test.pdf");
			}
		});
	}*/
	/*
	$scope.export = function(){
		
		
		
		html2canvas(document.getElementById('patientEvalutionReport'), {
			onrendered: function (canvas) {
				var data = canvas.toDataURL("image/png");
				
				doc.addImage(data,'JPEG',1,2)
				doc.addPage()
				
			}
		});
		
		
		html2canvas(document.getElementById('patientEvalutionReport'), {
			onrendered: function (canvas) {
				var data1 = canvas.toDataURL("image/png");
				var doc = new jsPDF();
				doc.addImage(data1,'PNG',0,0)
				doc.addPage()
				doc.save('patient.pdf');
			}
		});
		
		
		function export1() {
			  var pdf = new jsPDF('p', 'pt', 'letter');
			  pdf.canvas.height = 72 * 11;
			  pdf.canvas.width = 72 * 8.5;

			  pdf.fromHTML(document.body);

			  pdf.save('test.pdf');
			};
	}
	*/
	
// 718 multi page pdf
	
	(function () {  
        var  
         form = $('.form'),  
         cache_width = form.width(),  
         a4 = [595.28, 841.89]; // for a4 size paper width and height  
  
        $('#create_pdf').on('click', function () {  
        	window.scrollTo(0, 0); 
            createPDF();  
        });  
       
        //create pdf  
        function createPDF() { var quotes = document.getElementById('patientEvalutionReport');
       html2canvas(quotes)
      .then((canvas) => {
            //! MAKE YOUR PDF
            var pdf = new jsPDF('p', 'pt', 'letter');

            for (var i = 0; i <= quotes.clientHeight/980; i++) {
                //! This is all just html2canvas stuff
                var srcImg  = canvas;
                var sX      = 0;
                var sY      = 980*i; // start 980 pixels down for every new page
               // var sWidth  = 900;
                var sWidth  = 1000;
                var sHeight = 980;
                var dX      = 0;
                var dY      = 0;
               // var dWidth  = 900;
                var dWidth  = 1000;
                var dHeight = 980;

                window.onePageCanvas = document.createElement("canvas");
                onePageCanvas.setAttribute('width', 900);
                onePageCanvas.setAttribute('height', 980);
                var ctx = onePageCanvas.getContext('2d');
                // details on this usage of this function: 
                // https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Using_images#Slicing
                ctx.drawImage(srcImg,sX,sY,sWidth,sHeight,dX,dY,dWidth,dHeight);

                // document.body.appendChild(canvas);
                var canvasDataURL = onePageCanvas.toDataURL("image/png", 1.0);

                var width         = onePageCanvas.width;
                var height        = onePageCanvas.clientHeight;

                //! If we're on anything other than the first page,
                // add another page
                if (i > 0) {
                    pdf.addPage(612, 791); //8.5" x 11" in pts (in*72)
                }
                //! now we declare that we're working on that page
                pdf.setPage(i+1);
                //! now we add content to that page!
                pdf.addImage(canvasDataURL, 'PNG', 20, 40, (width*.62), (height*.62));

            }
            //! after the for loop is finished running, we save the pdf.
            pdf.save('Test.pdf');
        }
      );
        }  
  
        // create canvas object  
        function getCanvas() {  
            form.width((a4[0] * 1.33333) - 80).css('max-width', 'none');  
            return html2canvas(form, {  
                imageTimeout: 2000,  
                removeContainer: true  
            });  
        }  
  
    }());  
	
	// next 
	
	(function ($) {  
        $.fn.html2canvas = function (options) {  
            var date = new Date(),  
            $message = null,  
            timeoutTimer = false,  
            timer = date.getTime();  
            html2canvas.logging = options && options.logging;  
            html2canvas.Preload(this[0], $.extend({  
                complete: function (images) {  
                    var queue = html2canvas.Parse(this[0], images, options),  
                    $canvas = $(html2canvas.Renderer(queue, options)),  
                    finishTime = new Date();  
  
                    $canvas.css({ position: 'absolute', left: 0, top: 0 }).appendTo(document.body);  
                    $canvas.siblings().toggle();  
  
                    $(window).click(function () {  
                        if (!$canvas.is(':visible')) {  
                            $canvas.toggle().siblings().toggle();  
                            throwMessage("Canvas Render visible");  
                        } else {  
                            $canvas.siblings().toggle();  
                            $canvas.toggle();  
                            throwMessage("Canvas Render hidden");  
                        }  
                    });  
                    throwMessage('Screenshot created in ' + ((finishTime.getTime() - timer) / 1000) + " seconds<br />", 4000);  
                }  
            }, options));  
  
            function throwMessage(msg, duration) {  
                window.clearTimeout(timeoutTimer);  
                timeoutTimer = window.setTimeout(function () {  
                    $message.fadeOut(function () {  
                        $message.remove();  
                    });  
                }, duration || 2000);  
                if ($message)  
                    $message.remove();  
                $message = $('<div ></div>').html(msg).css({  
                    margin: 0,  
                    padding: 10,  
                    background: "#000",  
                    opacity: 0.7,  
                    position: "fixed",  
                    top: 10,  
                    right: 10,  
                    fontFamily: 'Tahoma',  
                    color: '#fff',  
                    fontSize: 12,  
                    borderRadius: 12,  
                    width: 'auto',  
                    height: 'auto',  
                    textAlign: 'center',  
                    textDecoration: 'none'  
                }).hide().fadeIn().appendTo('body');  
            }  
        };  
    })(jQuery);  
	
	
	// 718 multi page pdf end
	
	
	
	
	

	//1369
	$scope.savePatientSpeechEvalution=function(){
		//$scope.speechEvaluation.patient = $scope.patientObject;
		evalutionSheetService.savePatientSpeechEvalution( $scope.patientObject.id, $scope.speechEvaluation).then(
		function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.PATIENT_SPEECH_EVALUATION_ADD_SUCCESS);
							});
		
		   /* var restorepage = document.body.innerHTML;
		    var printcontent = document.getElementById("patientEvalutionReport").innerHTML;
		    document.body.innerHTML = printcontent;
		    window.print();
		    document.body.innerHTML = restorepage;*/
	}
	
	$scope.savePatientBehaviourManagement=function(){
		//$scope.speechEvaluation.patient = $scope.patientObject;
		evalutionSheetService.savePatientBehaviourManagement( $scope.patientObject.id, $scope.behaviourlManagement).then(
		function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.PATIENT_BEHAVIOURAL_PROGRAMME_ADD_SUCCESS);
							});
		
		   /* var restorepage = document.body.innerHTML;
		    var printcontent = document.getElementById("patientEvalutionReport").innerHTML;
		    document.body.innerHTML = printcontent;
		    window.print();
		    document.body.innerHTML = restorepage;*/
	}
	
	//Get All Payments List for patient
	/*$scope.getAllPaymentsByPatient = function() {
		departmentService.getAllPaymentsByPatient().then(
				function(response) {
					$scope.patientPayments = response.data;
					
				}, function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	};
	*/
	
	$scope.getCategoryByRegistartiontype = function(patient) {
		alert("get specific cat");
		patientService.getCategoryByRegistartiontype(patient).then(
				function(response) {
					$scope.categoriesList = response.data;
					
					alert("data -->"+response.data);
					
				}, function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	};
	
	$scope.nichqParentResultBypatient = function() {
		nichqTemplateService.nichqParentResultBypatient($scope.patient.id)
				.then(function(response) {
					$scope.nichqParentResult = response.data;
					$scope.results1to9 = $scope.nichqParentResult[0];
					$scope.results10to18 = $scope.nichqParentResult[1];
					$scope.results19to26 = $scope.nichqParentResult[2];
					$scope.results27to40 = $scope.nichqParentResult[3];
					$scope.results41to47 = $scope.nichqParentResult[4];
					$scope.results48to55 = $scope.nichqParentResult[5];
					$scope.chartOptions1to91();
					$scope.chartOptions10to181();

				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				})

	};
	
	$scope.nichqParentTemplateResult = function() {
		alert("from patient ctrl");
		
	}
	//multi select code
	
	        /*var vm = this,
	        getRandomInt = function(min, max) {
	            return Math.floor(Math.random() * (max - min + 1) + min);
	        };

	        vm.options1 = [];
	        for (var i = 0; i < 10; i++) {
	            vm.options1.push($scope.subCategorys);
	            
	        }

	        vm.options2 = [];
	        for (var i = 0; i < 100; i++) {
	            vm.options2.push({ key: i + 1, value: 'Prop' + (i + 1).toString() });
	        }

	        vm.option6 = 3;
	        vm.option7 = [4, 11, 23];
	        
	        vm.clear = function() {
	            vm.option1 = [];
	            vm.option2 = [];
	            vm.option3 = [];
	            vm.option4 = [];
	            vm.option5 = [];
	            vm.option6 = [];
	            vm.option7 = [];
	        };
	        
	        vm.randomSelect = function() {
	            vm.clear();
	            var arrSelected = $scope.subcategoriesList;
	            var arrOptions = [ vm.options1, vm.options2, vm.options2, vm.options1, vm.options1, vm.options1, vm.options2 ];
	            var arrIsSingle = [ false, false, false, true, false, false, false  ];
	            var arrIsSimple = [ true, true, false, false, true, true, true  ];
	            
	            for (var i = 0; i < arrSelected.length; i++) {
	                var selected = arrSelected[i];
	                var options = arrOptions[i];
	                var isSingle = arrIsSingle[i];
	                var isSimple = arrIsSimple[i];
	                var min = 0;
	                var max = options.length - 1;
	                if (isSingle) {
	                    var randIndex = getRandomInt(min, max);
	                    if (isSimple) {
	                        selected.push(options[randIndex].key); 
	                    } else {
	                        selected.push(options[randIndex]);
	                    }
	                }
	                else
	                {
	                    var toSelectIndexes = [];
	                    var numItems = getRandomInt(0, options.length) + 1;
	                    for (var j = 0; j < getRandomInt(1, numItems); j++)
	                    {
	                        var randIndex = getRandomInt(min, max);
	                        var arrIndex = toSelectIndexes.indexOf(randIndex);
	                        if (arrIndex == -1) {
	                            toSelectIndexes.push(randIndex);
	                            if (isSimple) {
	                                selected.push(options[randIndex].key); 
	                            } else {
	                                selected.push(options[randIndex]);   
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    
*/
	
	
	
	
};


angular.module("HealthApplication").controller("PatientCtrl", PatientCtrl);
