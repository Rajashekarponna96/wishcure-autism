
	//Country Controller

angular.module("app").controller("StateMasterController", function($scope, $http, $filter, $uibModal, $rootScope,$log) {

	
    init();
//getAllStatusMasters
    function init() {
        _stateuses = [];
        $scope.stateuses = _stateuses;


        $.ajax({
            type: "get",
            async: false,
            url: "http://localhost:8080/cognore/getAllStateMasters",
            contentType: "application/json;charset:utf-8",
            dataType: "json",
           
            success: function(response) {
                $scope.stateObject=response;
                for (i in $scope.stateObject) {
                    $scope.stateuses.push({
                        cid: $scope.stateObject[i].stateId,
                        csname: $scope.stateObject[i].shortName,
                        cfname: $scope.stateObject[i].fullName,
                        cmobile: $scope.stateObject[i].countryId,
                        cicode: $scope.stateObject[i].countryName,
                        ccuserid: $scope.stateObject[i].createdByUserId,
                        cmuserid: $scope.stateObject[i].modifiedByUserId,
						ccdate: $scope.stateObject[i].createdDate,
						cmdate: $scope.stateObject[i].modifiedDate,
						
						sid: $scope.stateObject[i].statusmaster
						

                    });
				}
            
            },
            error: function(jqXHR, exception) {
                if (jqXHR.country === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.country == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.country == 500) {
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

      /*  , $scope.searchKeywords = "", $scope.filteredStores = [], $scope.row = "", $scope.select = function(page) {
            var end, start;
            return start = (page - 1) * $scope.numPerPage, end = start + $scope.numPerPage, $scope.currentPageStores = $scope.filteredStores.slice(start, end)
        }, $scope.onFilterChange = function() {
            // alert("2");
            return $scope.select(1), $scope.currentPage = 1, $scope.row = ""
        }, $scope.onNumPerPageChange = function() {
            // alert("1");
            return $scope.select(1), $scope.currentPage = 1
        }, $scope.onOrderChange = function() {
            // alert("3");
            return $scope.select(1), $scope.currentPage = 1
        }, $scope.search = function() {
            // alert("4");
            return $scope.filteredStores = $filter("filter")($scope.stores, $scope.searchKeywords), $scope.onFilterChange()
        }, $scope.order = function(rowName) {
            // alert("5");
            return $scope.row !== rowName ? ($scope.row = rowName, $scope.filteredStores = $filter("orderBy")($scope.stores, rowName), $scope.onOrderChange()) : void 0
        }, $scope.numPerPageOpt = [3, 5, 10, 20], $scope.numPerPage = $scope.numPerPageOpt[2], $scope.currentPage = 1, $scope.currentPageStores = [], (init = function() {
            // alert("6");
            return $scope.search(), $scope.select($scope.currentPage)
        });*/
    }


    $scope.items = ['item1', 'item2', 'item3'];

    $scope.animationsEnabled = true;
    // add update
    $scope.open = function(size) {

        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'STModalContent.html',
            controller: 'STModalInstanceCtrl',
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
        _cid = _stateuses[_row].cid;
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'STModalContentdelete.html',
            controller: 'STdeleteModalInstanceCtrl',
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

    $scope.stateMasterEdit = function(id) {
       
        $rootScope.tag = "Update State";
       
        _n = 2;
        _row = id;
        _cid = $scope.stateuses[_row].cid;
        _csname = $scope.stateuses[_row].csname;
        _cfname = $scope.stateuses[_row].cfname;
        _cicode=$scope.stateuses[_row].cicode;
  
    };
    $scope.stateMasterSubmit = function() {
        $("#lbl_e_Country_Name,#lbl_e_State_Category_Name").text("");
        if ($.trim($("#txt_State_Name").val()) == "") {
            $("#lbl_e_State_Name").text("Please enter State");
        }
        if ($.trim($("#txt_State_Category_Name").val()) == "") {
            $("#lbl_e_State_Category_Name").text("Please enter category");
        }
        // success
    };
    $scope.stateMasterAdd = function(item) {

        $rootScope.tag = "Add State";
        _n = 1;
        _cid = 0;

    };
}).controller('STModalInstanceCtrl', function($scope, $uibModalInstance, $rootScope) {
   
	if (_n == 2) {
        $scope.cid = _cid;
        $scope.csname = _csname;
        $scope.cfname = _cfname;
        $scope.cicode = _cicode;
    } else if(_n == 1){
        $scope.cid = $scope.cname = '';
    }
    $scope.ok = function() {
    	if(_n == 1){
        var x = 1;
    	}else if(_n==2){
    		var x=2;
    	}
        var csname = document.getElementById("txt_State_shortName").value;
       
        $("#lbl_e_State_shortName").text("");

        if ($.trim($("#txt_State_shortName").val()) == "") {
            x = 0;$("#lbl_e_State_shortName").text("Please Enter State ShortName");
        } else if ($.trim($("#txt_State_shortName").val().length) < 2) {
            x = 0;$("#lbl_e_State_shortName").text("State ShortName should be more than 2 Chars");
        } else if ($.trim($("#txt_State_shortName").val().length) > 20) {
            x = 0;$("#lbl_e_State_shortName").text("State ShortName should not exceed 20 Chars");
        }

        
        
        var cfname = document.getElementById("txt_State_fullName").value;
        
        $("#lbl_e_State_fullName").text("");

        if ($.trim($("#txt_State_fullName").val()) == "") {
            x = 0;$("#lbl_e_State_fullName").text("Please Enter State FullName");
        } else if ($.trim($("#txt_State_fullName").val().length) < 2) {
            x = 0;$("#lbl_e_State_fullName").text("State FullName should be more than 2 Chars");
        } else if ($.trim($("#txt_State_fullName").val().length) > 20) {
            x = 0;$("#lbl_e_State_fullName").text("State FullName should not exceed 20 Chars");
        }
    var cicode = document.getElementById("txt_State_cntname").value;
        
        $("#lbl_e_State_cntname").text("");

        if ($.trim($("#txt_State_cntname").val()) == "") {
            x = 0;$("#lbl_e_State_cntname").text("Please Enter Country Nmae");
        } else if ($.trim($("#txt_State_cntname").val().length) < 2) {
            x = 0;$("#lbl_e_State_cntname").text("Country Nmae should be more than 2 Chars");
        } else if ($.trim($("#txt_State_cntname").val().length) > 20) {
            x = 0;$("#lbl_e_State_cntname").text("Country Name should not exceed 20 Chars");
        }
       
         $scope.saveData={
        	"shortName": csname,
        	"fullName" :cfname,
        	"countryName" : cicode,
        	"countryId":18,
        	"statusmaster":{"statusmasterid":$rootScope.uniqueStatusMasterId
        		
        	}
        	
        }
         
        if (x == 1) {

            $.ajax({
                type: "post",
                async: false,
                url: "http://localhost:8080/cognore/saveStateMaster",
                contentType: "application/json;charset:utf-8",
                dataType: "json",
                data: JSON.stringify($scope.saveData),
                success: function(response) {
                    if (_n == 1) {
						$scope.stateId=response;
					
                        _stateuses.push({ cid: $scope.stateId, csname: csname });
                        _stateuses.push({ cid: $scope.stateId, cfname: cfname });
                        _stateuses.push({ cid: $scope.stateId, cicode: cicode });
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
        	  "stateId": $scope.cid,
        	   "shortName": csname,
        	   "fullName" :cfname,
        	   "countryName":cicode
        }
        // update statusMaster
        
        if (x == 2) {
            $.ajax({
                type: "put",
                async: false,
                url: "http://localhost:8080/cognore/updateStateMaster",
                contentType: "application/json",
                data:JSON.stringify($scope.updateddata),
                success: function(response) {
                    if(_n== 2){
                        _stateuses[_row].cid =  $scope.cid;
                        _stateuses[_row].csname =csname;
                        _stateuses[_row].cfname =cfname;
                        _stateuses[_row].cicode =cicode;
                       
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
}).controller('STdeleteModalInstanceCtrl', function($scope, $uibModalInstance, items, $rootScope) {


	
	$scope.deleteObj={
			"stateId":_cid
	}
    $scope.ok = function() {
        $.ajax({
            type: "delete",
            async: false,
            url: "http://localhost:8080/cognore/deleteStateMaster/"+_cid,
            contentType: "application/json",
            dataType: "text",
            data: $scope.deleteObj, 
            success: function(response) {
            	_stateuses.splice(_row, 1);

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












