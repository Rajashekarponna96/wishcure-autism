function mentalMotorCtrl($scope, $state, MentalScaleService, motorScaleService,
		mchartLookupService, mchartService, isaaBehaviourLookUpService,
		$rootScope, $stateParams, PatientNichqTeacherCategoryservice,
		nichqteachercategorylookupservice, NichqCategoryLookupService,
		nichqservice, PatientNichqParentCategoryservice,
		isaaBehaviourLookUpService, patientIsaaBehaviourCategoryService) {

	if ($stateParams.Patient != undefined) {
		$scope.patient = $stateParams.Patient;
	}
	$scope.nichqparentcategoryLookuplist = [];

	$scope.getAllMentalScale = function() {
		MentalScaleService.getAllMentalScale().then(function(response) {
			$scope.mentalScaleList = response.data;
		}, function(error) {
		});
	};

	$scope.getMotorScales = function() {
		motorScaleService.getMotorScales().then(function(response) {
			$scope.motorScaleList = response.data;
		}, function(error) {
		});
	};

	$scope.assignMentalScalestoPatient = function() {
		$scope.mentalScaleTemplateDto = {
			"mentalScales" : $scope.mentalScaleList,
			"patient" : $scope.patient
		}
		MentalScaleService.assignMentalScalestoPatient(
				$scope.mentalScaleTemplateDto).then(function(response) {
			$state.go('main.patientviewtabs.patient_evaluation_sheet', {
				patientObject : $scope.patient
			});
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', 'Mental Scales Add To Patient Successfully');

		}, function(error) {
		});
	};

	$scope.assignMotorScalestoPatient = function() {
		$scope.motorScaleTemplateDto = {
			"motorScale" : $scope.motorScaleList,
			"patient" : $scope.patient
		}
		MentalScaleService.assignMotorScalesToPatient(
				$scope.motorScaleTemplateDto).then(function(response) {
			$state.go('main.patientviewtabs.patient_evaluation_sheet', {
				patientObject : $scope.patient
			});
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', 'Motor Scales Add To Patient Successfully');

		}, function(error) {
		});
	};

	$scope.getAllMcharts = function() {
		mchartLookupService.getAllMcharts().then(function(response) {
			$scope.mchartLookupList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllMchartByPatientIdResult = function() {
		mchartService.getMchartCountResult($scope.patient.id).then(
				function(response) {
					$scope.mchartResult = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllMchartByPatientId = function() {
		mchartService.getAllMchartsByPatientId($scope.patient.id).then(
				function(response) {
					$scope.mchartList = response.data;
					$scope.getAllMchartByPatientIdResult();
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.mchartObject = {};

	$scope.addMchartForPatient = function() {
		$scope.mchartObject = {
			"id" : $scope.patient.id,
			"mchartLookup" : $scope.mchartLookupList,
			"mchart" : $scope.mchartList
		}
		mchartService.addMchartPatient($scope.mchartObject).then(
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
					$scope.getAllMcharts();
					$state.go('main.patientviewtabs.patient_evaluation_sheet',
							{
								patientObject : $scope.patient
							});
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getNichqteachercategorylookup = function() {
		nichqteachercategorylookupservice.getNichqteachercategorylookup().then(
				function(response) {
					$scope.nichqteachercategorylookupList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.addnichqteachertemplatetotheparent = function() {
		$scope.PatientNichqTeacherCategoryDto = {
			"nichqTeacherCategoryLookups" : $scope.nichqteachercategorylookupList,
			"patient" : $scope.patient,
			"patientNichqTeacherCategories":null
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
			toastr.success('successMessageHandler.CATEGORY_ADD_DATA_SUCCESS');
			$scope.getNichqteachercategorylookup();
			$state.go('main.patientviewtabs.patient_evaluation_sheet', {
				patientObject : $scope.patient
			});
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	// nichqParent CategoryLookup
	$scope.getAllNichqCategoryQuestions = function() {
		NichqCategoryLookupService.getAllNichqCategoryLookupQuestions().then(
				function(response) {
					$scope.nichqParentCategoryQuestionList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.addNichqParenttemplate = function() {
		$scope.nichqParentCategoryDto = {
			"nichqParentCategoryLookupsList" : $scope.nichqparentcategoryLookuplist,
			"patient" : $scope.patient
		}

		PatientNichqParentCategoryservice.addNichqParenttemplate(
				$scope.nichqParentCategoryDto).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('CATEGORY_ADD_DATA_SUCCESS');
			$scope.getAllNichqCategoryQuestions();
			$state.go('main.patientviewtabs.patient_evaluation_sheet', {
				patientObject : $scope.patient
			});
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getNichqparentCategoryLookups = function() {
		nichqservice.getNichqparent().then(function(response) {
			$scope.nichqparentcategoryLookuplist = response.data;
		}, function(error) {
		})
	};

	// isaaBehaviourCategoryLookup
	$scope.getAllCategorylookup = function() {
		isaaBehaviourLookUpService.getAllCategorylookup().then(
				function(response) {
					$scope.isaaBehaviourCategoriesList = response.data;
				}, function(error) {

				})
	};

	$scope.addIsaaBehaviourtemplate = function() {
		$scope.ISAABehaviorObjectDto = {
			"isaaBehaviorCategoryLookupsList" : $scope.isaaBehaviourCategoriesList,
			"iSAABehaviorCategorys" : null,
			"patient" : $scope.patient
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
			toastr.success('CATEGORY_ADD_DATA_SUCCESS');
			$scope.getAllCategorylookup();
			$state.go('main.patientviewtabs.patient_evaluation_sheet', {
				patientObject : $scope.patient
			});
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	// isaa Behaviour category lookup
	/*
	 * $scope.getAllCategorylookup = function() {
	 * isaaBehaviourLookUpService.getAllCategorylookup().then(
	 * function(response) { $scope.CategoriesList = response.data; },
	 * function(error) { }) }; //isaa Behaviour Question lookup
	 * $scope.getAllISAABQuestionlookups = function() {
	 * isaaBehaviourQuestionLookUpService.getAllISAABQuestionlookups().then(
	 * function(response) { $scope.isaabQuestionLookupList = response.data; },
	 * function(error) { }) };
	 * 
	 * $scope.getAllisaaBehaviourCategoryByPatientId = function() {
	 * isaaBehaviourLookUpService.getAllisaaBehaviourCategoryByPatientId($scope.patient.id).then(function(response) {
	 * $scope.categoryList = response.data; }, function(error) { $scope.message =
	 * JSON.stringify(error.data.message.trim()); }); };
	 * 
	 * $scope.ISAABehaviorObjectDto={};
	 * 
	 * $scope.addCategory = function() { $scope.ISAABehaviorObjectDto={
	 * "iSAABehaviorCategoryLookupDto":$scope.CategoriesList,
	 * "iSAABehaviorCategory":$scope.categoryList,
	 * "id":$scope.ISAABehaviorCategoryLookup.id, "patient":$scope.patient }
	 * isaaBehaviourQuestionLookUpService.addCategoryForPatient($scope.ISAABehaviorObjectDto).then(function(response) {
	 * toastr.options = { closeButton: true, progressBar: true, showMethod:
	 * 'slideDown', positionClass : "toast-top-full-width", timeOut: 2000 };
	 * toastr.success('',"isaa behaviour added successfully");
	 * $scope.getAllMcharts();
	 * $state.go('main.patientviewtabs.patient_evaluation_sheet',{ patientObject
	 * :$scope.patient }); }, function(error) { $scope.message =
	 * JSON.stringify(error.data.message.trim()); }); };
	 */

};
angular.module("HealthApplication").controller("mentalMotorCtrl",
		mentalMotorCtrl);
