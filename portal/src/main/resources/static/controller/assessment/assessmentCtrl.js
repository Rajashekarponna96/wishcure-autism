function assessmentCtrl($scope, $state, $rootScope, $stateParams,
		successMessageHandler, mchartService, assessmentService,
		MentalScaleService,motorScaleService, mchartLookupService, mchartService,
		NichqQuestionLookupService, nichqTeacherQuestionLookupService,
		csbsLookupservice, csbsTemplateService,csbsQuestionLookupService, NichqCategoryLookupService,
		isaaBehaviourLookUpService, isaaBehaviourQuestionLookUpService,
		nichqteachercategorylookupservice, nichqTemplateService) {
	$scope.mchartLookupList = [];
	$scope.mchartResult = {};
	$scope.mentalScaleList = [];
	$scope.mentalScalelist1 = [];
	$scope.motorScaleList=[];
	$scope.ismentalstatus = false;
	$scope.nichqParentCategoryLookupList = [];
	$scope.categoryId = 1;
	$scope.nichqParentQuestionLookupListByCategoryId = [];
	$scope.nichqParentCategoriesByPatient = [];
	$scope.nichqteachercategorylookupList = [];
	$scope.nichqParentResult = {};
	$scope.nichqParentResultByPatient = {};
	$scope.nichqTeacherresult = {};
	$scope.results1to9 = [];
	$scope.results10to18 = [];
	$scope.results19to26 = [];
	$scope.results27to40 = [];
	$scope.results41to47 = [];
	$scope.results48to55 = [];

	$scope.results1to35 = [];
	$scope.results36to38 = [];
	$scope.results39to43 = [];

	$scope.isaaBehavioralCategoryLookupList = [];
	$scope.isaaBehavioralQuestionLookupList = [];
	$scope.isaaBehavioralCategoryLookup = {};
	$scope.isaaBehaviourreport = {};
	$scope.nichqParentQuestions = [];
	$scope.categorylookup = {};
	$scope.nichqTeachercategorylookup = {};
	$scope.nichqTeacherReport = [];

	$scope.results1to9 = {};
	$scope.results10to14 = {};
	$scope.results15to23 = {};
	$scope.results24to30 = {};
	$scope.results31to36 = {};
	$scope.results37to40 = {};
	$scope.patientMchatQuestions = [];
	$scope.csbsQuestionLookups = [];
	$scope.csbsAnswerLookups = [];
	$scope.csbsAnswerLookup = {};
	$scope.isDisabled = false;
	$scope.isMchatExists = false;
	
	$scope.type = 'bar';
	 $scope.colors = ['#97BBCD', '#DCDCDC', '#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'];
//	 $scope.labels = ["2017-05-31 21:51:47", "2017-05-31 21:51:45", "2017-05-31 21:51:44"];
//     $scope.data = [3,5,8];
	 $scope.labels = [];
     $scope.data = [];
     $scope.labels10to18 = [];
     $scope.data10to18 = [];
     
     $scope.labels1to35teacher = [];
     $scope.data1to35teacher = [];
     
     $scope.labels36to38teacher = [];
     $scope.data36to38teacher = [];
     
     $scope.labels39to43teacher = [];
     $scope.data39to43teacher = [];
     

	if ($stateParams.patient != undefined) {
		$scope.patient = $stateParams.patient;

	}
	if ($stateParams.category != undefined) {
		$scope.category1 = $stateParams.category;

	}
	if ($stateParams.category != undefined) {
		$scope.category2 = $stateParams.category;

	}
	if ($stateParams.category1 != undefined) {
		$scope.mentalscale = $stateParams.category1;

	}
	// mchat template implementations

	// getAll Mchat lookup questions
	$scope.getAllMchartLookups = function() {
		mchartLookupService.getAllMcharts().then(function(response) {
			$scope.mchartLookupList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getMchartResultByPatient = function() {
		mchartService.getMchartCountResult($scope.patient.id).then(
				function(response) {
					$scope.mchartResult = response.data;
				}, function(error) {
					alert(JSON.stringify(error));
				})
	};

	$scope.getAllMchartQuestionsByPatient = function() {
		mchartService.getAllMchartsByPatientId($scope.patient.id).then(
				function(response) {
					$scope.patientMchatQuestions = response.data;
					if($scope.patientMchatQuestions == ""){
						$scope.isMchatExists = false; 
					}else{
						$scope.isMchatExists = true;
					}
				}, function(error) {
					alert(JSON.stringify(error));
				})
	};

	// assign Mchat lookups data to patient
	$scope.addMchartForPatient = function() {
		$scope.mchartObject = {
			"id" : $scope.patient.id,
			"mchartLookup" : $scope.mchartLookupList,
			"mchart" : $scope.patientMchatQuestions
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
							successMessageHandler.MCHAT_ADD_DATA_SUCCESS);
					// $scope.getAllMcharts();
					$scope.getMchartResultByPatient();
					/*
					 * $state.go('main.patientviewtabs.patient_evaluation_sheet', {
					 * patientObject : $scope.patient });
					 */
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	// assign mental scales to patient
	$scope.getAllMentalScale = function() {
		MentalScaleService.getAllMentalScale().then(function(response) {
			$scope.mentalScaleList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.error($scope.message, 'Error');
		});
	};

	$scope.assignMentalScalestoPatient = function() {
		$scope.mentalScaleTemplateDto = {
			"mentalScales" : $scope.mentalScaleList,
			"patient" : $scope.patient
		}
		MentalScaleService.assignMentalScalestoPatient(
				$scope.mentalScaleTemplateDto).then(function(response) {
			/*
			 * $state.go('main.patientviewtabs.patient_evaluation_sheet', {
			 * patientObject : $scope.patient });
			 */

			$scope.ismentalstatus = true;
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			var count = 1;
			 angular.forEach($scope.mentalScaleList, function (value, key)
		             {
		    	
		    	 //console.log(key);
		                    //console.log(value);
		                    if (value.mentalScaleStatus == "P") {
		                    	count++;
		                    
		                    }
		    	 
		             });
		     alert("Count "  +count);
		     
			toastr.success('', 'Mental Scales Add To Patient Successfully');
			
		}, function(error) {
		});
		
	};
	//1369
	$scope.ppMentalChange = function(status) {
	     console.log(status);
	     var keepGoing = true;
	     
	     angular.forEach($scope.mentalScaleList, function (value, key)
	             {
	    	 if(keepGoing){ 
	    	 //console.log(key);
	                    //console.log(value);
	                    if (value.mentalScaleStatus == null) {
	                    	value.mentalScaleStatus="P";
	                    }else if(value.mentalScaleStatus == "PP"){
	                    	keepGoing = false;
	                    }
	    	 } 
	             });
	    
	             
	}
	$scope.rfMentalChange = function(status) {
	     console.log(status);
	     angular.forEach($scope.mentalScaleList, function (value, key)
	             {
	    	
	    	 //console.log(key);
	                    //console.log(value);
	                    if (value.mentalScaleStatus == "RF") {
	                    	value.mentalScaleStatus="RF";
	                    }
	                    else if (value.mentalScaleStatus == null)
	                    {
	                    	value.mentalScaleStatus="F";
	                    	
	                    }	    	 
	             });
	    
	             
	}
	
	
	// 718 assign motor scales to patient
	
	$scope.getMotorScales = function() {
		motorScaleService.getMotorScales().then(function(response) {
			$scope.motorScaleList = response.data;
		}, function(error) {

		});
	};
	
	$scope.assignMotorScaleTemplateToPatient = function() {
		//$scope.getMotorScales();
		
		$scope.motorScaleTemplateDto = {
			"motorScales" : $scope.motorScaleList,
			"patient" : $scope.patient
		}
		motorScaleService.assignMotorScaleTemplateToPatient(
				$scope.motorScaleTemplateDto).then(function(response) {
						$scope.ismotorstatus = true;
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', 'Motor Scales Add To Patient Successfully');
			$scope.isDisabled = true;

		}, function(error) {
		});
	};
	$scope.ppMotorChange = function(status) {
	     console.log(status);
	     var keepGoing = true;
	     
	     angular.forEach($scope.motorScaleList, function (value, key)
	             {
	    	 if(keepGoing){ 
	    	 //console.log(key);
	                    //console.log(value);
	                    if (value.motorScaleStatus == null) {
	                    	value.motorScaleStatus="P";
	                    }else if(value.motorScaleStatus == "PP"){
	                    	keepGoing = false;
	                    }
	    	 } 
	             });
	    
	             
	}
	$scope.rfMotorChange = function(status) {
	     console.log(status);
	     angular.forEach($scope.motorScaleList, function (value, key)
	             {
	    	
	    	 //console.log(key);
	                    //console.log(value);
	                    if (value.motorScaleStatus == "RF") {
	                    	value.motorScaleStatus="RF";
	                    }
	                    else if (value.motorScaleStatus == null)
	                    {
	                    	value.motorScaleStatus="F";
	                    	
	                    }	    	 
	             });
	    
	             
	}


	$scope.gotoReport = function() {
		$state.go('main.mentalReportGraph', {
			patientObject : $scope.patient
		});
	};

	$scope.getAllTeacherQuestionLookup = function() {
		nichqTeacherQuestionLookupService.getAllTeacherQuestionLookup().then(
				function(response) {
					$scope.nichqteacherQuestionlookupList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.error($scope.message, 'Error');
				});
	};

	// csbs template

	$scope.getAllcsbs = function() {
		csbsLookupservice.getAllcsbs().then(function(response) {
			$scope.csbsCategoryLookupList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllcsbsQuestions = function() {
		csbsQuestionLookupService.getAllcsbsQuestions().then(function(response) {
			$scope.csbsQuestionsList = response.data;
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

	// screening test get functions from ParentQuestionCategory table
	$scope.getAllParentQuestionsByCategoryAndPatientId = function(category) {
		csbsTemplateService.getAllParentQuestionCategoryscsbs(category.id
				).then(function(response) {
			$scope.flagSocial = false;
			$scope.csbsQuestionLookups = response.data;
			if ($scope.csbsQuestionLookups.length < 1) {
				/*$scope.getAllQuestionCategoriesSocial(tabdate);*/
			}
			/*if (tabdate.date == $scope.todaytabdate) {
				$scope.addFlag = true;
			} else {
				$scope.addFlag = false;
			}*/
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}

	$scope.Category = {};
	$scope.gotoCategory = function(category) {
		$scope.Category = category;
		csbsTemplateService.getAllcsbsQuestionLookupByCategoryId(category.id
		).then(function(response) {
	$scope.flagSocial = false;
	$scope.csbsQuestionLookups = response.data;
	if ($scope.csbsQuestionLookups.length < 1) {
		/*$scope.getAllQuestionCategoriesSocial(tabdate);*/
	}
	/*if (tabdate.date == $scope.todaytabdate) {
		$scope.addFlag = true;
	} else {
		$scope.addFlag = false;
	}*/
}, function(error) {
	$scope.message = JSON.stringify(error.data.message.trim());
});
	}

	// nichq Parent Template

	$scope.getAllNichqparentCategoryLookups = function() {
		NichqCategoryLookupService.getAllNichqparentCategoryLookups().then(
				function(response) {
					$scope.nichqParentCategoryLookupList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllNichqParentQuestionsLookup = function() {
		NichqQuestionLookupService.getAllNichqParentQuestionsLookup().then(
				function(response) {
					$scope.screeninhTestQuestionsList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.error($scope.message, 'Error');
				});
	};

	$scope.getAllNichqparentQuestionLookupsByCategory = function(categoryId) {
		$scope.categoryId = categoryId;
		NichqQuestionLookupService
				.getAllNichqparentQuestionLookupsByCategory(categoryId)
				.then(
						function(response) {
							$scope.nichqParentQuestionLookupListByCategoryId = response.data;
							console
									.log(" nichqParentQuestionLookupListByCategoryId "
											+ JSON
													.stringify($scope.nichqParentQuestionLookupListByCategoryId));
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						});
	};

	$scope.getAllNichqParentQuestionsByPatientAndCategory = function(category) {
		if (category == undefined) {
			$scope.categorylookup = $scope.nichqParentCategoryLookupList[0];
			$scope.categoryId = $scope.categorylookup.id;
		} else {
			$scope.categorylookup = category;
			$scope.categoryId = category.id;
		}
		console.log(JSON.stringify(category));

		NichqCategoryLookupService
				.getAllNichqParentQuestionsByPatientAndCategory(
						$scope.patient.id, $scope.categoryId)
				.then(
						function(response) {
							$scope.nichqParentQuestions = response.data;
							console
									.log(" nichqParentQuestions "
											+ JSON
													.stringify($scope.nichqParentQuestions));
							if ($scope.nichqParentQuestions.length < 1) {
								$scope
										.getAllNichqparentQuestionLookupsByCategory($scope.categoryId);
							}

						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						})
	};

	$scope.getAllNichqParentCategoriesByPatient = function() {
		NichqCategoryLookupService
				.getAllNichqParentCategoriesByPatient($scope.patient.id)
				.then(
						function(response) {
							$scope.nichqParentCategoriesByPatient = response.data;
							console
									.log(" data isss  "
											+ JSON
													.stringify($scope.nichqParentCategoriesByPatient));
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						})
	};

	$scope.assignNichqParentTemplateToPatient = function() {

		$scope.nichqParentTemplateObj = {
			"patient" : $scope.patient,
			"nichqParentCategoryLookupsList" : $scope.nichqParentCategoryLookupList,
			"nichqParentCategoryLookup" : $scope.categorylookup,
			"nichqParentCategoryList" : $scope.nichqParentCategoriesByPatient,
			"nichqParentCategory" : $scope.categorylookup,
			"nichqParentQuestionLookupList" : $scope.nichqParentQuestionLookupListByCategoryId,
			"nichqParentQuestionList" : $scope.nichqParentQuestions

		};
		console.log(JSON.stringify($scope.nichqParentTemplateObj));
		NichqQuestionLookupService
				.assignNichqParentTemplateToPatient(
						$scope.nichqParentTemplateObj)
				.then(
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
											'Nichq Parent Template is assigned to Patient Successfully');
							$scope
									.getAllNichqParentQuestionsByPatientAndCategory($scope.categorylookup);

						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr.error($scope.message, 'Error');
						});
	};

	$scope.nichqParentTemplateResult = function() {
		NichqQuestionLookupService.nichqParentTemplateResultByPatient(
				$scope.patient.id).then(function(response) {
			$scope.nichqParentResultByPatient = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.error($scope.message, 'Error');

		})
	}

	// nichq Teacher Template

	$scope.getAllNichqteachercategorylookups = function() {
		nichqteachercategorylookupservice.getNichqteachercategorylookup().then(
				function(response) {
					$scope.nichqteachercategorylookupList = response.data;
				}, function(error) {
				})
	};

	$scope.getAllNichqTeacherQuestionLookupsByCategory = function(categoryId) {
		$scope.categoryId = categoryId;
		nichqteachercategorylookupservice
				.getAllNichqTeacherQuestionLookupsByCategory(categoryId)
				.then(
						function(response) {
							$scope.nichqTeacherQuestionLookupListByCategoryId = response.data;
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						});
	};

	$scope.getAllNichqTeacherQuestionsByPatientAndCategory = function(category) {
		$scope.nichqTeachercategorylookup = category;
		$scope.categoryId = category.id;
		nichqteachercategorylookupservice
				.getAllNichqTeacherQuestionsByPatientAndCategory(
						$scope.patient.id, $scope.categoryId)
				.then(
						function(response) {
							$scope.nichqTeacherQuestions = response.data;
							console
									.log(" nichqTeacherQuestions  "
											+ JSON
													.stringify($scope.nichqTeacherQuestions));
							if ($scope.nichqTeacherQuestions.length < 1) {
								$scope
										.getAllNichqTeacherQuestionLookupsByCategory($scope.categoryId);
							}
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						})
	};

	$scope.assignNichqTeacherTemplateToPatient = function() {

		$scope.nichqTeacherTemplateObj = {
			"patient" : $scope.patient,
			"nichqTeacherCategoryLookup" : $scope.nichqTeachercategorylookup,
			"patientNichqTeacherCategory" : $scope.nichqTeachercategorylookup,
			"patientNichqTeacherQuestionsList" : $scope.nichqTeacherQuestions,
			"nichqTeacherQuestionLookupsList" : $scope.nichqTeacherQuestionLookupListByCategoryId

		};
		console.log(JSON.stringify($scope.nichqTeacherTemplateObj));
		nichqteachercategorylookupservice
				.assignNichqTeacherTemplateToPatient(
						$scope.nichqTeacherTemplateObj)
				.then(
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
											'Nichq teacher Template is assigned to Patient Successfully');

						},
						function(error) {
							$scope.message = JSON
									.stringify(error.question.message.trim());
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr.error($scope.message, 'Error');
						});
	};

	$scope.gotoNichqTeacherTemplateResult = function() {
		$state
				.go(
						"main.patientviewtabs.patient_evaluation_sheet.nichqTeacherTemplateResult",
						{
							patient : $scope.patient
						});
	};

	$scope.getNichqTeacherResultByPatientId = function() {
		nichqteachercategorylookupservice.getNichqTeacherResultByPatientId(
				$scope.patient.id).then(function(response) {
			$scope.nichqTeacherresult = response.data;
		}, function(error) {

		})
	};

	$scope.chartOptions1to35 = function() {
		$scope.chartOptions1to35teacher = {
			dataSource : $scope.results1to35,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism Nichq Teacher Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}
	$scope.chartOptions36to38 = function() {
		$scope.chartOptions36to38teacher = {
			dataSource : $scope.results36to38,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism Nichq Teacher Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}
	$scope.chartOptions39to43 = function() {
		$scope.chartOptions39to43teacher = {
			dataSource : $scope.results39to43,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism Nichq Teacher Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}

	$scope.nichqTeacherReportBypatient = function() {
		nichqteachercategorylookupservice.nichqTeacherReportBypatient(
				$scope.patient.id).then(function(response) {
			$scope.nichqTeacherReport = response.data;
			$scope.results1to35 = $scope.nichqTeacherReport[0];
			
			angular.forEach($scope.results1to35, function(value, key) {
				  this.push( value.question);
				}, $scope.labels1to35teacher);
			angular.forEach($scope.results1to35, function(value, key) {
				  this.push( value.answer);
				}, $scope.data1to35teacher);
			
			$scope.results36to38 = $scope.nichqTeacherReport[1];
			angular.forEach($scope.results36to38, function(value, key) {
				  this.push( value.question);
				}, $scope.labels36to38teacher);
			angular.forEach($scope.results36to38, function(value, key) {
				  this.push( value.answer);
				}, $scope.data36to38teacher);
			
			$scope.results39to43 = $scope.nichqTeacherReport[2];
			angular.forEach($scope.results39to43, function(value, key) {
				  this.push( value.question);
				}, $scope.labels39to43teacher);
			angular.forEach($scope.results39to43, function(value, key) {
				  this.push( value.answer);
				}, $scope.data39to43teacher);
			
			$scope.chartOptions1to35();
			$scope.chartOptions36to38();
			$scope.chartOptions39to43();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		})

	};

	// nichq parent template graphs

	$scope.gotoNichqParentTemplateResult = function() {
		$state
				.go(
						"main.patientviewtabs.patient_evaluation_sheet.nichqparentTemplateResult",
						{
							patient : $scope.patient
						});
	}

	$scope.chartOptions1to91 = function() {
		$scope.chartOptions1to9 = {
			dataSource : $scope.results1to9,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism Nichq Parent Result ",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}

	$scope.chartOptions10to181 = function() {
		$scope.chartOptions10to18 = {
			dataSource : $scope.results10to18,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism Nichq Parent Result",
				
				type : "bar",
				color : '#ffaa66'
			}
		};
	};

	$scope.nichqParentResultBypatient = function() {
		nichqTemplateService.nichqParentResultBypatient($scope.patient.id)
				.then(function(response) {
					$scope.nichqParentResult = response.data;
					$scope.results1to9 = $scope.nichqParentResult[0];
					
					angular.forEach($scope.results1to9, function(value, key) {
						  this.push( value.question);
						}, $scope.labels);
					angular.forEach($scope.results1to9, function(value, key) {
						  this.push( value.answer);
						}, $scope.data);
					
					
					$scope.results10to18 = $scope.nichqParentResult[1];
					angular.forEach($scope.results10to18, function(value, key) {
						  this.push( value.question);
						}, $scope.labels10to18);
					angular.forEach($scope.results1to9, function(value, key) {
						  this.push( value.answer);
						}, $scope.data10to18);
					
					
					
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

	// issa behavioral template ....

	$scope.getAllIsaaBehavioralCategorylookups = function() {
		isaaBehaviourLookUpService.getAllIsaaBehavioralCategorylookups().then(
				function(response) {
					$scope.isaaBehavioralCategoryLookupList = response.data;
				}, function(error) {

				})
	};

	$scope.getAllIsaaBehavioralQuestionLookupsByCAtegoryLookup = function(
			categoryId) {
		isaaBehaviourQuestionLookUpService
				.getAllIsaaBehavioralQuestionLookupsByCAtegoryLookup(categoryId)
				.then(function(response) {
					$scope.isaaBehavioralQuestionLookupList = response.data;
				}, function(error) {
				})
	};

	$scope.getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup = function(
			category) {
		$scope.isaaBehavioralCategoryLookup = category;
		$scope.isaaBcategoryId = category.id;
		isaaBehaviourQuestionLookUpService
				.getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup(
						$scope.patient.id, $scope.isaaBcategoryId)
				.then(
						function(response) {
							$scope.isaaBehavioralQuestionsList = response.data;
							if ($scope.isaaBehavioralQuestionsList.length < 1) {
								$scope
										.getAllIsaaBehavioralQuestionLookupsByCAtegoryLookup($scope.isaaBcategoryId);
							}
						}, function(error) {
						})
	};

	$scope.assignIsaaBehavioralTemplateToPatient = function() {

		$scope.isaaBehavioralObj = {
			"patient" : $scope.patient,
			"isaaBehaviorCategoryLookup" : $scope.isaaBehavioralCategoryLookup,
			"isaaBehaviorCategory" : $scope.isaaBehavioralCategoryLookup,
			"isaaBehaviorQuestionLookups" : $scope.isaaBehavioralQuestionLookupList,
			"isaaBehaviorQuestions" : $scope.isaaBehavioralQuestionsList
		};
		isaaBehaviourQuestionLookUpService
				.assignIsaaBehavioralTemplateToPatient($scope.isaaBehavioralObj)
				.then(
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
											'ISAA Behaviour Template is assigned to Patient Successfully');
							$scope
									.getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup($scope.isaaBehavioralCategoryLookup);

						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr.error($scope.message, 'Error');
						});
	};

	$scope.getISAABehaviorReportByPatientId = function() {
		isaaBehaviourQuestionLookUpService.getISAABehaviorReportByPatientId(
				$scope.patient.id).then(function(response) {
			$scope.isaaBehaviourreport = response.data;
		}, function(error) {

		})
	};

	$scope.gotoIsaaBehaviourTemplateResult = function() {
		$state
				.go(
						'main.patientviewtabs.patient_evaluation_sheet.isaaBehaviouralTemplateResult',
						{
							patient : $scope.patient

						})
	};

	$scope.chartOptionsresults1to9 = function() {
		$scope.chartOptions1to9 = {
			dataSource : $scope.results1to9,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism ISAA Behaviourt Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	};

	$scope.chartOptionsresults10to14 = function() {
		$scope.chartOptions10to14 = {
			dataSource : $scope.results10to14,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism ISAA Behaviourt Result ",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}

	$scope.chartOptionsresults15to23 = function() {
		$scope.chartOptions15to23 = {
			dataSource : $scope.results15to23,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism ISAA Behaviourt Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	};

	$scope.chartOptionsresults24to30 = function() {
		$scope.chartOptions24to30 = {
			dataSource : $scope.results24to30,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism ISAA Behaviourt Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}

	$scope.chartOptionsresults31to36 = function() {
		$scope.chartOptions31to36 = {
			dataSource : $scope.results31to36,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism ISAA Behaviourt Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}

	$scope.chartOptionsresults37to40 = function() {
		$scope.chartOptions37to40 = {
			dataSource : $scope.results37to40,
			series : {
				argumentField : "question",
				valueField : "answer",
				name : "Autism ISAA Behaviourt Result",
				type : "bar",
				color : '#ffaa66'
			}
		};
	}

	$scope.isaaBehavioralResultBypatient = function() {
		isaaBehaviourQuestionLookUpService.isaaBehavioralResultBypatient(
				$scope.patient.id).then(function(response) {
			$scope.isaaBehavioralResult = response.data;
			$scope.results1to9 = $scope.isaaBehavioralResult[0];
			$scope.results10to14 = $scope.isaaBehavioralResult[1];
			$scope.results15to23 = $scope.isaaBehavioralResult[2];
			$scope.results24to30 = $scope.isaaBehavioralResult[3];
			$scope.results31to36 = $scope.isaaBehavioralResult[4];
			$scope.results37to40 = $scope.isaaBehavioralResult[5];
			$scope.chartOptionsresults1to9();
			$scope.chartOptionsresults10to14();
			$scope.chartOptionsresults15to23();
			$scope.chartOptionsresults24to30();
			$scope.chartOptionsresults31to36();
			$scope.chartOptionsresults37to40();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		})

	};
	$scope.gotoissabehaviourtemplate = function(category) {
		$state
				.go(
						'main.patientviewtabs.patient_evaluation_sheet.issaBehaviourview.isaaTemplate',
						{
							category : category

						});
	};

	$scope.gotomentalscale = function() {
		$state
				.go('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation.mentalQuestions')
	};
	

	$scope.gotomotorscale = function() {
		$state
				.go('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation.motorQuestions')
	};


	
	

//718 multi page pdf

(function () {  
    var  
     form = $('.form'),  
     cache_width = form.width(),  
     a4 = [595.28, 841.89]; // for a4 size paper width and height  

    $('#create_pdf').on('click', function () {  
    	alert("from assesment ctrl");
    	window.scrollTo(0, 0); 
        createPDF();  
    });  
   
    //create pdf  
    function createPDF() { 
    	alert("create pdf from assesment");
    	var quotes = document.getElementById('canvas');
    	
   html2canvas(quotes)
  .then((canvas) => {
        //! MAKE YOUR PDF
        var pdf = new jsPDF('p', 'pt', 'letter');

        for (var i = 0; i <= quotes.clientHeight/980; i++) {
            //! This is all just html2canvas stuff
            var srcImg  = canvas;
            var sX      = 0;
            var sY      = 980*i; // start 980 pixels down for every new page
            var sWidth  = 900;
            var sHeight = 980;
            var dX      = 0;
            var dY      = 0;
            var dWidth  = 900;
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
    
    $('#download2').on('click', function () {  
    	alert("from assesment ctrl");
    	//window.scrollTo(0, 0); 
       // createPDF();  
    	alert("download2 from assesment ctrl");
        html2canvas($("#canvas"), {
            onrendered: function (chart) {
                var imgData = chart.toDataURL(
                        'image/png');

                var doc = new jsPDF('p', 'pt', 'a4');

                doc.text("Dynamic -- Angularjs Smart Table + Chart JS + JSON:", 70, 40);
                doc.addImage(imgData, 'PNG', 0, 60);

                var tbl = $('#demo').clone();
                tbl.find('tfoot').remove();

               // var res = doc.autoTableHtmlToJson(tbl.get(0));

                /*doc.autoTable(res.columns, res.data, {
                    startY: 400,
                    styles: {
                        overflow: 'linebreak'
                    }
//	showHeader: 'firstPage'
                })*/

//                doc.output('dataurlnewwindow');
            doc.save('sample-file.pdf');
            }
        });
    }); 

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

};
// 718 multi page pdf end
$(document).ready(function () {

    /*var d_canvas = document.getElementById('chart');
    var context = d_canvas.getContext('2d');*/
    var chart = document.getElementById('chart').getContext('2d');

    $('#download2').click(function () {
    	alert("from assesment ctrl");
        html2canvas($("#canvas"), {
            onrendered: function (chart) {
                var imgData = chart.toDataURL(
                        'image/png');

                var doc = new jsPDF('p', 'pt', 'a4');

                doc.text("Dynamic -- Angularjs Smart Table + Chart JS + JSON:", 70, 40);
                doc.addImage(imgData, 'PNG', 0, 60);

                var tbl = $('#demo').clone();
                tbl.find('tfoot').remove();

                var res = doc.autoTableHtmlToJson(tbl.get(0));

                doc.autoTable(res.columns, res.data, {
                    startY: 400,
                    styles: {
                        overflow: 'linebreak'
                    }
//	showHeader: 'firstPage'
                })

//                doc.output('dataurlnewwindow');
            doc.save('sample-file.pdf');
            }
        });
    });
});

//pdf end
angular.module("HealthApplication")
		.controller("assessmentCtrl", assessmentCtrl);
