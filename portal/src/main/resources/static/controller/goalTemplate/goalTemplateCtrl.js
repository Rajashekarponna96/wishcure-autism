function goalTemplateCtrl($scope, $state, goalTemplateService, $rootScope,
		$stateParams, patientService, companyService, loginService, successMessageHandler) {

	$scope.goal = {};
	$scope.patientList = [];
	$scope.goalUpdate = $stateParams.goalObj;
	$scope.goalsList1 = [];
	$scope.goalsList2 = [];
	$scope.goalsList3 = [];
	$scope.goalsList4 = [];
	$scope.goalsList5 = [];
	$scope.goalsList6 = [];
	$scope.assigned = false;
	$scope.selected = false;
	$scope.addGoalCategory=false;
	$scope.listOfStatusGoals = [];
	$scope.patientGoals = [];
	$scope.patient = {
		patientGoals : []
	};
	$scope.listOfGoals = [];
	$scope.goalCategoryLookup ={};
	
	$scope.goalTemplateNamesList = [ "Receptive	Language",
			"Expressive Langage", "Pragmatic/Social", "Fluency",
			"Articulation/Phonology", "Oral Motor", "Voice",
			"Augmentative Communication" ];
	
	if ($stateParams.Patient != undefined) {
		$scope.patient = $stateParams.Patient;
		$scope.assigned = true;
	}
	;

	$scope.getAllgoals = function() {
		goalTemplateService.getAllgoals().then(function(response) {
			$scope.goalList = response.data;
		}, function(error) {

		});
	};
// Add Goals Categories Lookups
	$scope.addGoalCategoryLookup = function() {
		goalTemplateService.addGoalCategoryLookup($scope.goalCategoryLookup).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', 'GoalLookup Added Successfully!');
				//$state.go('main.careTakerMilestones');
				$scope.getGoalCategoryLookups();
				$scope.addGoalCategory=false;
		}, function(error) {
		});
	};
	
// end Goals Categories Lookups	
	$scope.getGoalCategoryLookups= function() {
		$scope.addGoalCategory=false;
		goalTemplateService.getGoalCategoryLookups().then(function(response) {
			$scope.goalCategorylookupLists = response.data;
			
			
			//$scope.getAllGoalCategories($scope.goalCategorylookupLists[0]);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllGoalCategories=function(goalCategory){
		
		if(goalCategory == "Speech Goals"){
			$scope.goalTemplateNamesList = [ "Receptive	Language",
				"Expressive Langage", "Pragmatic/Social", "Fluency",
				"Articulation/Phonology", "Oral Motor", "Voice",
				"Augmentative Communication" ];
		}else if(goalCategory == "Occupational Goals"){
			$scope.goalTemplateNamesList = [ "A","B","C" ];
			$scope.getAllTemplateGoalsByName("A");
		}else if(goalCategory == "Physiotherapy Goals"){
			$scope.goalTemplateNamesList = [ "Fine Motor","Gross Motor" ];
			$scope.getAllTemplateGoalsByName("Fine Motor");
		}
		else if(goalCategory == "Personal Goals"){
			$scope.goalTemplateNamesList = [ "EATING","TOILET TRAINING","BRUSHING","BATHING","DRESSING","GROOMING","HYGEINE","SELF AWARENESS","SELF HELP"];
			$scope.getAllTemplateGoalsByName("EATING");
		}
		else if(goalCategory == "Academic Goals"){
			$scope.goalTemplateNamesList = [ "Identification","Reading" ,"Writing"];
			$scope.getAllTemplateGoalsByName("Identification");
		}
		else if(goalCategory == "Visual Stimulation Goals"){
			$scope.goalTemplateNamesList = ["VisualStimulation" ];
			$scope.getAllTemplateGoalsByName("VisualStimulation");
		}
		else if(goalCategory == "Cognitive Goals"){
			$scope.goalTemplateNamesList = [ "Learning","Thinking",	"Problem Solving","Imitation"];
			$scope.getAllTemplateGoalsByName("Learning");
		}
		else if(goalCategory == "Behavioral Goals"){
			$scope.goalTemplateNamesList = [ "Interaction","Paying Attention"];
			$scope.getAllTemplateGoalsByName("Interaction");
		}
		else if(goalCategory == "Vocational Training"){
			$scope.goalTemplateNamesList = [ "Skills" ];
			$scope.getAllTemplateGoalsByName("Skills");
		}
		
		/*$scope.currentCategory=category;
		$scope.adminUserName = $rootScope.loggedUsername;
		goalTemplateService.getAllGoalCategories(category.id,$scope.adminUserName).then(function(response) {
			//$scope.categoryGeneralList = response.data;
		if($scope.categoryGeneralList.length<1){
			$scope.getQuestionsBasedOnCategoryStatus(category.id);
		}
		},
		function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		}
		);*/
	};
	
	$scope.addNewGoalCategory=function(){
		$scope.addGoalCategory=true;
	};
	
	$scope.addGoal = function(template1) {
		if (template1 == "Receptive	Language") {
			$scope.goal.status ="1";
		}
		if (template1 == "Expressive Langage") {
			$scope.goal.status ="2";
		}
		if (template1 == "Pragmatic/Social") {
			$scope.goal.status ="3";
		}
		 if (template1 == "Fluency") {
			$scope.goal.status ="4";
		}
	if (template1 == "Articulation/Phonology") {
			$scope.goal.status ="5";
		}
		if (template1 == "Oral Motor") {
			$scope.goal.status ="6";
		}
		 if (template1 == "Voice") {
			$scope.goal.status ="7";
		}
		 if (template1 == "Augmentative Communication") {
			$scope.goal.status ="8";
		}
		 if (template1 == "A") {
				$scope.goal.status ="9";
			}
		 if (template1 == "B") {
				$scope.goal.status ="10";
			}
		 if (template1 == "C") {
				$scope.goal.status ="11";
		 }
		 
		 if (template1 == "Skills") {
				$scope.goal.status ="12";
			}
		 if (template1 == "EATING") {
				$scope.goal.status ="13";
			}
		 if (template1 == "TOILET TRAINING") {
				$scope.goal.status ="14";
			}
		 if (template1 == "BRUSHING") {
				$scope.goal.status ="15";
			}
			 if (template1 == "BATHING") {
					$scope.goal.status ="16";
				}
		 if (template1 == "DRESSING") {
				$scope.goal.status ="17";
			}
		 if (template1 == "GROOMING") {
				$scope.goal.status ="18";
			}
		 if (template1 == "HYGEINE") {
				$scope.goal.status ="19";
			}
		 if (template1 == "SELF AWARENESS") {
				$scope.goal.status ="20";
			}
		 if (template1 == "SELF HELP") {
				$scope.goal.status ="21";
			}
		 if (template1 == "Fine Motor") {
				$scope.goal.status ="22";
			}
		 if (template1 == "Gross Motor") {
				$scope.goal.status ="23";
		 }
		 if (template1 == "Identification") {
				$scope.goal.status ="24";
		 }
		 if (template1 == "Reading") {
				$scope.goal.status ="25";
		 }
		 if (template1 == "Writing") {
				$scope.goal.status ="26";
		 }
		 if (template1 == "VisualStimulation") {
				$scope.goal.status ="27";
		 }
		 if (template1 == "Learning") {
				$scope.goal.status ="28";
		 }
		 if (template1 == "Thinking") {
				$scope.goal.status ="29";
		 }
		 if (template1 == "Problem Solving") {
				$scope.goal.status ="30";
		 }
		 if (template1 == "Imitation") {
				$scope.goal.status ="31";
		 }
		
		 if (template1 == "Interaction") {
				$scope.goal.status ="32";
		 }
		 if (template1 == "Paying Attention") {
				$scope.goal.status ="33";
		 }
		 
		$scope.goal.selected = $scope.selected;
		goalTemplateService.addGoal($scope.goal).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-right",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.GOALTEMPLATE_SAVE_SUCCESS);
			$scope.getAllGoalsByStatus1($scope.goal.status);
			$scope.goal.status = undefined;
			$scope.goal.goalTemplateLookupName = undefined;

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
	};
	

	
	
	$scope.updateGoal = function(goal) {

		goalTemplateService.updateGoal(goal).then(function(response) {
			$state.go('main.goalsTemplate');
		}, function(error) {
		});
	};
	$scope.deleteGoal = function(id) {
		goalTemplateService.deleteGoal(id).then(function(response) {
			$scope.getAllgoals();
		}, function(error) {
		});
	};
	
	$scope.updateGoalCategory = function(goalCategory) {
		alert('UPDATE'+goalCategory);

		goalTemplateService.updateGoalCategory(goalCategory).then(function(response) {
			$state.go('main.goalCategory');
		}, function(error) {
		});
	};
	$scope.deleteGoalCategory = function(id) {
		goalTemplateService.deleteGoalCategory(id).then(function(response) {
			$scope.getGoalCategoryLookups();
		}, function(error) {
		});
	};
	$scope.gotoList = function() {
		$state.go('main.goalsTemplate');
	};

	$scope.gotoback = function() {
		$state.go('main.goalsTemplate');
	};
	$scope.gotoupdate = function(goal) {
		$state.go('main.updateGoalTemplate', {
			goalObj : goal
		});
	};
	var counter = 0;
	$scope.elements = [ {
		id : counter,
		goalLookupName : ''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.elements.push({
			id : counter,
			goalLookupName : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.elements.pop({
			id : counter,
			goalLookupName : ''
		});

	}
	$scope.show = function(elements) {
		// alert(JSON.stringify(elements));

	};

	$scope.getAllPatientsByRole = function() {
		patientService.getAllPatientsByRole($rootScope.loggedUsername).then(
				function(response) {
					$scope.patientList = response.data;
				}, function(error) {

				})
	};

	$scope.showMethod = function() {
		$scope.buttonstatus = true;
	};

	$scope.hideMethod = function() {
		$scope.buttonstatus = false;
	};

	$scope.patient.patientGoals=[];
	$scope.assignGoalsToPatient = function() {
		if ($scope.patient.patientGoals != undefined) {
			patientService
					.assignGoalstoPatient($scope.patient.patientGoals,
							$scope.patient.id)
					.then(
							function(repsonse) {
								toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-right",
									timeOut : 2000
								};
								toastr
										.success('',
												successMessageHandler.GOALTEMPLATE_ASSIGN_SUCCESS);
								$state
										.go(
												'main.patientviewtabs.patient_goals_sheet',
												{
													patientObject : $scope.patient
												});
							}, function(error) {

							})
		}
	};

	$scope.getAllGoalsByStatus1 = function(status) {
		goalTemplateService.getAllGoalsByStatus(status).then(
				function(response) {
					$scope.goalsList = response.data;
				}, function(error) {

				})
	};
	
	//1369
	
	$scope.fileChanged = function(files) {
		$rootScope.imageUrlForGoal=undefined;
		if (files != null) {
			
			var imagefile = document.querySelector('#file');
            if (imagefile.files && imagefile.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#temp_image')
                        .attr('src', e.target.result);
                };
                reader.readAsDataURL(imagefile.files[0]);
                this.imagefile = imagefile.files[0];
                $scope.flagValue="2";
            }else{
                console.log("Image not selected");
            }
			
			
			
			var file = files[0];
			$scope.filepath = files[0];
			
			alert("File path "+files[0]);
			
			companyService.uploadImage($scope.filepath).then(
					function(response) {
						$scope.goal.logoUrl = response.data.imagePath;
						$rootScope.imageUrlForGoal = $scope.goal.logoUrl
						$scope.getImageS3();
						$scope.viewImagePathData($rootScope.imageUrlForGoal);
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						toastr.success(' ', successMessageHandler.COMPANYMANAGEMENT_IMAGE_UPLOAD_SUCCESS);

					},
					function(error) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						$scope.message1 = JSON.stringify(error.data.message
								.trim());
						$scope.message = $scope.message1.substring(0,
								$scope.message1.lenth);
						toastr.error($scope.message, 'Error');
					});
		}
	};
	$scope.adminUserName = $rootScope.loggedUsername;
	loginService.getImageFromS3($scope.adminUserName).then(
			function(response) {
				$scope.getImage = response.data;
				$scope.getImageUrl = $scope.getImage.location;
			}, function(error) {
			})

$scope.loadGoalImage=function(){
	if($rootScope.imageUrlForGoal != undefined){
		$scope.viewImagePathData($rootScope.imageUrlForGoal);
	}else{
		$scope.getImageS3();
	}
}
	
	$scope.getImageS3 = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		loginService.getImageFromS3($scope.adminUserName).then(
				function(response) {
					$scope.getImage = response.data;
					$scope.getImageUrl = $scope.getImage.location;
				}, function(error) {
				})
	};

$scope.viewImagePathData = function(imageUrlForGoal) {
	$scope.flagValue="3";
	return PATIENT_MODULE_CONFIG.VIEW_USER_IMAGEPATHDATA(imageUrlForGoal);
};
	

	$scope.getAllTemplateGoalsByName = function(template) {
		$rootScope.templateName=template;
		if (template == "Receptive	Language") {
			$scope.getAllGoalsByStatus1(1);
		}
		else if (template == "Expressive Langage") {
			$scope.getAllGoalsByStatus1(2);
		}
		else if (template == "Pragmatic/Social") {
			$scope.getAllGoalsByStatus1(3);
		}
		else if (template == "Fluency") {
			$scope.getAllGoalsByStatus1(4);
		}
		else if (template == "Articulation/Phonology") {
			$scope.getAllGoalsByStatus1(5);
		}
		else if (template == "Oral Motor") {
			$scope.getAllGoalsByStatus1(6);
		}
		else if (template == "Voice") {
			$scope.getAllGoalsByStatus1(7);
		}
		else if (template == "Augmentative Communication") {
			$scope.getAllGoalsByStatus1(8);
		}
		else if (template == "A") {
			$scope.getAllGoalsByStatus1(9);
		}
		else if (template == "B") {
			$scope.getAllGoalsByStatus1(10);
		}
		else if (template == "C") {
			$scope.getAllGoalsByStatus1(11);
		}
		else if (template == "Skills") {
				$scope.getAllGoalsByStatus1(12);
			}
		else if (template == "EATING") {
				$scope.getAllGoalsByStatus1(13);
			}
		else if (template == "TOILET TRAINING") {
				$scope.getAllGoalsByStatus1(14);
			}
		else if (template == "BRUSHING") {
				$scope.getAllGoalsByStatus1(15);
			}
		else if (template == "BATHING") {
					$scope.getAllGoalsByStatus1(16);
				}
		else if (template == "DRESSING") {
				$scope.getAllGoalsByStatus1(17);
			}
		else if (template == "GROOMING") {
				$scope.getAllGoalsByStatus1(18);
			}
		else if (template == "HYGEINE") {
				$scope.getAllGoalsByStatus1(19);
			}
		else if (template == "SELF AWARENESS") {
				$scope.getAllGoalsByStatus1(20);
			}
		else if (template == "SELF HELP") {
				$scope.getAllGoalsByStatus1(21);
			}
		else if (template == "Fine Motor") {
				$scope.getAllGoalsByStatus1(22);
			}
		else if (template == "Gross Motor") {
				$scope.getAllGoalsByStatus1(23);
		 }
		else if (template == "Identification") {
			$scope.getAllGoalsByStatus1(24);
	 }
		else if (template == "Reading") {
			$scope.getAllGoalsByStatus1(25);
	 }
		else if (template == "Writing") {
			$scope.getAllGoalsByStatus1(26);
	 }
		else if (template == "VisualStimulation") {
			$scope.getAllGoalsByStatus1(27);
	 }
		 if (template == "Learning") {
				$scope.getAllGoalsByStatus1(28);
		 }
		 if (template == "Thinking") {
				$scope.getAllGoalsByStatus1(29);
		 }
		 if (template == "Problem Solving") {
				$scope.getAllGoalsByStatus1(30);
		 }
		 if (template == "Imitation") {
				$scope.getAllGoalsByStatus1(31);
		 }
		 if (template == "Interaction") {
				$scope.getAllGoalsByStatus1(32);
		 }
		 if (template == "Paying Attention") {
				$scope.getAllGoalsByStatus1(33);
		 }
	};

	$scope.$watchGroup([ 'selected' ], function(oldSize) {

	});
	$scope.onclickEvent = function(goal) {
		$scope.listOfStatusGoals.push(goal);
	};

	// generate present Date
	$scope.today = new Date();

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
	$scope.localDate = $scope.month + " " + $scope.date + " "
			+ $scope.today.getFullYear();

	console.log(" $scope.localDate" + $scope.localDate);

};

angular.module("HealthApplication").controller("goalTemplateCtrl",
		goalTemplateCtrl);