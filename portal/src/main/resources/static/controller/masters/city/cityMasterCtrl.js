
	//City Controller

angular.module("app").controller("CityMasterController", function($scope, $http, $filter, $uibModal, $rootScope,$log) {
	
    init();
//getAllStatusMasters
    function init() {
        _cities = [];
        $scope.cities = _cities;


        $.ajax({
            type: "get",
            async: false,
            url:  "http://localhost:8080/cognore/getAllCityMasters",
            contentType: "application/json;charset:utf-8",
            dataType: "json",
           
            success: function(response) {
                $scope.cityObject=response;
                for (i in $scope.cityObject) {
                    $scope.cities.push({
                        cid: $scope.cityObject[i].cityId,
                        csname: $scope.cityObject[i].shortName,
                        cfname: $scope.cityObject[i].fullName,
                        csid: $scope.cityObject[i].stateId,
                        cstname: $scope.cityObject[i].stateName,
                        cscode: $scope.cityObject[i].stdCode,
                        cllength: $scope.cityObject[i].landLineLength,
                        
                        ccuserid: $scope.cityObject[i].createdByUserId,
                        cmuserid: $scope.cityObject[i].modifiedByUserId,
						ccdate: $scope.cityObject[i].createdDate,
						cmdate: $scope.cityObject[i].modifiedDate,
						
						sid: $scope.cityObject[i].statusmaster
						

                    });
				}
             
            },
            error: function(jqXHR, exception) {
                if (jqXHR.city === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.ciyt == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.city == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            }
        })

    }


    $scope.items = ['item1', 'item2', 'item3'];

    $scope.animationsEnabled = true;
    // add update
    $scope.open = function(size) {

        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'CMModalContent.html',
            controller: 'CMModalInstanceCtrl',
            size: size,
            resolve: {
                items: function() {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function(selectedItem) {
            $scope.selected = selectedItem;
        }, function() {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
    // delete
    $scope.deleteopen = function(id) {
        _row = id;
        _n = 3;
        _cid = _cities[_row].cid;
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'CMModalContentdelete.html',
            controller: 'CMMdeleteModalInstanceCtrl',
            size: id,
            resolve: {
                items: function() {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function(selectedItem) {
            $scope.selected = selectedItem;
        }, function() {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
    $scope.toggleAnimation = function() {
        $scope.animationsEnabled = !$scope.animationsEnabled;
    };

    $scope.cityMasterEdit = function(id) {
        
        $rootScope.tag = "Update City";
        
        _n = 2;
        _row = id;
        _cid =$scope.cities[_row].cid;
        _csname =$scope.cities[_row].csname;
        _cfname =$scope.cities[_row].cfname;
        _cstname=$scope.cities[_row].cstname;
        _cscode=$scope.cities[_row].cscode;
        _cllength=$scope.cities[_row].cllength;
        
      
    };
    $scope.cityMasterSubmit = function() {

        $("#lbl_e_City_Name,#lbl_e_City_Category_Name").text("");
        if ($.trim($("#txt_City_Name").val()) == "") {
            $("#lbl_e_City_Name").text("Please enter City");
        }
        if ($.trim($("#txt_City_Category_Name").val()) == "") {
            $("#lbl_e_City_Category_Name").text("Please enter category");
        }
        // success
    };
    $scope.cityMasterAdd = function(item) {

        $rootScope.tag = "Add City";
        _n = 1;
        _cid = 0;

    };
}).controller('CMModalInstanceCtrl', function($scope, $uibModalInstance, $rootScope) {
   
	if (_n == 2) {
        $scope.cid = _cid;
        $scope.csname = _csname;
        $scope.cfname = _cfname;
        $scope.cstname = _cstname;
        $scope.cscode = _cscode;
        $scope.cllength = _cllength;
    } else if(_n == 1){
        $scope.cid = $scope.csname = '';
    }
    $scope.ok = function() {
    	if(_n == 1){
        var x = 1;
    	}else if(_n==2){
    		var x=2;
    	}
        var csname = document.getElementById("txt_City_shortName").value;
       
        $("#lbl_e_City_shortName").text("");

        if ($.trim($("#txt_City_shortName").val()) == "") {
            x = 0;$("#lbl_e_City_shortName").text("Please Enter City shortname");
        } else if ($.trim($("#txt_City_shortName").val().length) < 2) {
            x = 0;$("#lbl_e_City_shortName").text("City shortname should be more than 2 Chars");
        } else if ($.trim($("#txt_City_shortName").val().length) > 20) {
            x = 0;$("#lbl_e_City_shortName").text("City shortname should not exceed 20 Chars");
        }

        
        
        var cfname = document.getElementById("txt_City_fullName").value;
        
        $("#lbl_e_city_fullName").text("");

        if ($.trim($("#txt_City_fullName").val()) == "") {
            x = 0;$("#lbl_e_city_fullName").text("Please Enter fullname");
        } else if ($.trim($("#txt_City_fullName").val().length) < 2) {
            x = 0;$("#lbl_e_city_fullName").text("City fullname should be more than 2 Chars");
        } else if ($.trim($("#txt_City_fullName").val().length) > 20) {
            x = 0;$("#lbl_e_city_fullName").text("City fullname should not exceed 20 Chars");
        }
        /////////////////////////
       var cstname = document.getElementById("txt_City_statename").value;
        
        $("#lbl_e_city_statename").text("");

        if ($.trim($("#txt_City_statename").val()) == "") {
            x = 0;$("#lbl_e_city_statename").text("Please Enter state name");
        } else if ($.trim($("#txt_City_statename").val().length) < 2) {
            x = 0;$("#lbl_e_city_statename").text("state name should be more than 2 Chars");
        } else if ($.trim($("#txt_City_statename").val().length) > 20) {
            x = 0;$("#lbl_e_city_statename").text("state name should not exceed 20 Chars");
        }
        //////////////////////////
        var cscode = document.getElementById("txt_City_stdcode").value;
        
        $("#lbl_e_City_stdcode").text("");

        if ($.trim($("#txt_City_stdcode").val()) == "") {
            x = 0;$("#lbl_e_City_stdcode").text("Please Enter stdcode");
        } else if ($.trim($("#txt_City_stdcode").val().length) < 2) {
            x = 0;$("#lbl_e_City_stdcode").text("stdcode should be more than 2 Chars");
        } else if ($.trim($("#txt_City_stdcode").val().length) > 20) {
            x = 0;$("#lbl_e_City_stdcode").text("std code should not exceed 20 Chars");
        }
        
 var cllength = document.getElementById("txt_City_llength").value;
        
        $("#lbl_e_City_llenght").text("");

        if ($.trim($("#txt_City_llength").val()) == "") {
            x = 0;$("#lbl_e_City_llength").text("Please Enter landline length");
        } else if ($.trim($("#txt_City_llength").val().length) < 2) {
            x = 0;$("#lbl_e_City_llength").text("landline length should be more than 2 Chars");
        } else if ($.trim($("#txt_City_llength").val().length) > 20) {
            x = 0;$("#lbl_e_City_llength").text("lamdline length should not exceed 20 Chars");
        }
        
        
        
         $scope.saveData={
        	"shortName" :csname,
        	"fullName"  :cfname,
        	"stateName" :cstname,
        	"stdCode"   :cscode,
        	"landLineLength":cllength,
        	"stateId"   :2,
        	"statusmaster":{
        		"statusmasterid":$rootScope.uniqueStatusMasterId
        		
        	}
        	
        }
         
        if (x == 1) {

            $.ajax({
                type: "post",
                async: false,
                url: "http://localhost:8080/cognore/saveCityMaster",
                contentType: "application/json;charset:utf-8",
                dataType: "json",
                data: JSON.stringify($scope.saveData),
                success: function(response) {
                    if (_n == 1) {
						$scope.cityId=response;
					
                        _cities.push({ cid: $scope.cityId, csname:csname });
                        _cities.push({ cid: $scope.cityId, cfname:cfname });
                        _cities.push({ cid: $scope.cityId, cstname:cstname });
                        _cities.push({ cid: $scope.cityId, cscode: cscode });
                        _cities.push({ cid: $scope.cityId, cllength:cllength });
                    }
                    


                },
                error: function(jqXHR, exception) {
                    if (jqXHR.status === 0) {
                        alert('Not connect.\n Verify Network.');
                    } else if (jqXHR.status == 404) {
                        alert('Requested page not found. [404]');
                    } else if (jqXHR.status == 500) {
                        alert('Internal Server Error [500].');
                    } else if (exception === 'parsererror') {
                        alert('Requested JSON parse failed.');
                    } else if (exception === 'timeout') {
                        alert('Time out error.');
                    } else if (exception === 'abort') {
                        alert('Ajax request aborted.');
                    } else {
                        alert('Uncaught Error.\n' + jqXHR.responseText);
                    }
                }
            });
            
            

            $uibModalInstance.dismiss('cancel');
        };
        $scope.updateddata={
        		 "cityId": $scope.cid,
        		"shortName": csname,
            	"fullName" :cfname,
            	"stateName" :cstname,
            	"stdCode" :cscode,
            	"landLineLength" :cllength
        }
        
        // update statusMaster
        
        if (x == 2) {
            $.ajax({
                type: "put",
                async: false,
                url: "http://localhost:8080/cognore/updateCityMaster",
                contentType: "application/json",
                data:JSON.stringify($scope.updateddata),
                success: function(response) {
                    /*
					 * if (_n == 1) { $scope.statusid=response;
					 * 
					 * _statuses.push({ sid: $scope.statusid, sname: sname }); }
					 * else
					 */
                    if(_n== 2){
                        _cities[_row].cid =  $scope.cid;
                        _cities[_row].csname =csname;
                        _cities[_row].cfname =cfname;
                        _cities[_row].cstname=cstname;
                        _cities[_row].cscode =cscode;
                        _cities[_row].cllength =cllength;
                       
                    }


                },
                error: function(jqXHR, exception) {
                    if (jqXHR.status === 0) {
                        alert('Not connect.\n Verify Network.');
                    } else if (jqXHR.status == 404) {
                        alert('Requested page not found. [404]');
                    } else if (jqXHR.status == 500) {
                        alert('Internal Server Error [500].');
                    } else if (exception === 'parsererror') {
                        alert('Requested JSON parse failed.');
                    } else if (exception === 'timeout') {
                        alert('Time out error.');
                    } else if (exception === 'abort') {
                        alert('Ajax request aborted.');
                    } else {
                        alert('Uncaught Error.\n' + jqXHR.responseText);
                    }
                }
            });
            
            

            $uibModalInstance.dismiss('cancel');
        };
        
        
        
        
    }
    $scope.cancel = function() {
        $uibModalInstance.dismiss('cancel');
    };
}).controller('CMMdeleteModalInstanceCtrl', function($scope, $uibModalInstance, items, $rootScope) {


	
	$scope.deleteObj={
			"cityId":_cid
	}
    $scope.ok = function() {
        $.ajax({
            type: "delete",
            async: false,
            url: "http://localhost:8080/cognore/deleteCityMaster",
            contentType: "application/json",
            dataType: "text",
            data: JSON.stringify($scope.deleteObj), 
            success: function(response) {
            	_cities.splice(_row, 1);

            },
            error: function(jqXHR, exception) {
                if (jqXHR.status === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.status == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            }
        });

        $uibModalInstance.dismiss('cancel');
    };

    $scope.cancel = function() {
        $uibModalInstance.dismiss('cancel');
    };
});


























