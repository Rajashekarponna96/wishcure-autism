
//location Controller

angular.module("app").controller("LocationMasterController", function($scope, $http, $filter, $uibModal, $rootScope,$log) {

    init();
//getAllLocationMaster
    function init() {
        _locations = [];
        $scope.locations = _locations;


        $.ajax({
            type: "get",
            async: false,
            url: "http://localhost:8080/healthapp/getLocationMaster",
            contentType: "application/json;charset:utf-8",
            dataType: "json",
           
            success: function(response) {
                $scope.locationObject=response;
                for (i in $scope.locationObject) {
                    $scope.locations.push({
                    	lid: $scope.locationObject[i].locationid,
                        lcid: $scope.locationObject[i].cityid,
                        lfname:$scope.locationObject[i].fullname,
                        lzcode:$scope.locationObject[i].zipcode,
                        lcuserid:$scope.locationObject[i].createdByUserId,
                        lmuserid:$scope.locationObject[i].modifiedByUserId,
						lcdate:$scope.locationObject[i].createdDate,
						lmdate:$scope.locationObject[i].modifiedDate,
						
						sid: $scope.locationObject[i].statusmaster
                    });
				}
           
            },
            error: function(jqXHR, exception) {
                if (jqXHR.location === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.location == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.location == 500) {
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

       /* , $scope.searchKeywords = "", $scope.filteredStores = [], $scope.row = "", $scope.select = function(page) {
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
            templateUrl: 'LModalContent.html',
            controller: 'LModalInstanceCtrl',
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
        _lid = _locations[_row].lid;
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'LModalContentdelete.html',
            controller: 'LdeleteModalInstanceCtrl',
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

    $scope.locationEdit = function(id) {
              $rootScope.tag = "Update location";
        
        _n = 2;
        _row = id;
        _lid = $scope.locations[_row].lid;
        _lfname = $scope.locations[_row].lfname;
       _lzcode = $scope.locations[_row].lzcode;
        
        
    
    };
    $scope.locationMasterSubmit = function() {
        // alert($rootScope.id)

        $("#lbl_e_location_Name,#lbl_e_location_Category_Name").text("");
        if ($.trim($("#txt_location_Name").val()) == "") {
            $("#lbl_e_location_Name").text("Please enter location");
        }
        if ($.trim($("#txt_location_Category_Name").val()) == "") {
            $("#lbl_e_location_Category_Name").text("Please enter category");
        }
       
        // success
    };
    $scope.LocationMasterAdd = function(item) {

        $rootScope.tag = "Add location";
        _n = 1;
        _lid = 0;

    };
}).controller('LModalInstanceCtrl', function($scope, $uibModalInstance, $rootScope) {
	if (_n == 2) {
        $scope.lid = _lid;
        $scope.lfname = _lfname;
        $scope.lzcode = _lzcode;
    } else if(_n == 1){
        $scope.lid = $scope.lfname = '';
    }
    $scope.ok = function() {
    	if(_n == 1){
        var x = 1;
    	}else if(_n==2){
    		var x=2;
    	}
        var lfname = document.getElementById("txt_location_fullname").value;
        $("#lbl_e_location_fullname").text("");

        if ($.trim($("#txt_location_fullname").val()) == "") {
            x = 0;$("#lbl_e_location_fullname").text("Please Enter location ");
        } else if ($.trim($("#txt_location_fullname").val().length) < 2) {
            x = 0;$("#lbl_e_location_fullname").text("location  should be more than 2 Chars");
        } else if ($.trim($("#txt_location_fullname").val().length) > 20) {
            x = 0;$("#lbl_e_location_fullname").text("location  should not exceed 20 Chars");
        }
        
        var lzcode = document.getElementById("txt_location_zipcode").value;
        $("#lbl_e_location_zipcode").text("");

        if ($.trim($("#txt_location_zipcode").val()) == "") {
            x = 0;$("#lbl_e_location_zipcode").text("Please Enter ZIP code ");
        } else if ($.trim($("#txt_location_zipcode").val().length) < 2) {
            x = 0;$("#lbl_e_location_zipcode").text("ZIP code  should be more than 2 numbers");
        } else if ($.trim($("#txt_location_zipcode").val().length) > 20) {
            x = 0;$("#lbl_e_location_zipcode").text("ZIP code  should not exceed 20 numbers");
        }

         $scope.saveData={
        	
        	"fullname":lfname,
        	"zipcode":lzcode,
        	"createdByUserId":$rootScope.loggedUserId,
        	"modifiedByUserId":$rootScope.loggedUserId
        	
        }
        if (x == 1) {

            $.ajax({
                type: "post",
                async: false,
                url: "http://localhost:8080/healthapp/saveLocationMaster",
                contentType: "application/json;charset:utf-8",
              //  dataType: "json",
                data: JSON.stringify($scope.saveData),
                success: function(response) {
                    if (_n == 1) {
						$scope.locationmasterid=response;
					
                        _locations.push({ lid: $scope.locationid,lfname:lfname ,lzcode:lzcode});
                    }


                },
                error: function(jqXHR, exception) {
                    if (jqXHR.location === 0) {
                        alert('Not connect.\n Verify Network.');
                    } else if (jqXHR.location == 404) {
                        alert('Requested page not found. [404]');
                    } else if (jqXHR.location == 500) {
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
      
        		"locationid": $scope.lid,
        	   "fullname":lfname,
        	   "zipcode":lzcode,
        	   "modifiedByUserId":$rootScope.loggedUserId
        }
        
        // update location
        
        if (x == 2) {
            $.ajax({
                type: "put",
                async: false,
                url: "http://localhost:8080/healthapp/updateLocationMaster",
                contentType: "application/json",
                data:JSON.stringify($scope.updateddata),
                success: function(response) {
                    
                    if(_n== 2){
                        _locations[_row].lid =  $scope.lid;
                        _locations[_row].lfname =lfname;
                        _locations[_row].lzcode =lzcode;
                       
                    }


                },
                error: function(jqXHR, exception) {
                    if (jqXHR.location === 0) {
                        alert('Not connect.\n Verify Network.');
                    } else if (jqXHR.location == 404) {
                        alert('Requested page not found. [404]');
                    } else if (jqXHR.location == 500) {
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
}).controller('LdeleteModalInstanceCtrl', function($scope, $uibModalInstance, items, $rootScope) {


	
	$scope.deleteObj={
			"locationid":_lid,
			"modifiedByUserId":$rootScope.loggedUserId
	}
    $scope.ok = function() {
        $.ajax({
            type: "put",
            async: false,
            url: "http://localhost:8080/healthapp/deleteLocationMaster",
            contentType: "application/json",
            dataType: "text",
            data: JSON.stringify($scope.deleteObj), 
            success: function(response) {
                _locations.splice(_row, 1);

            },
            error: function(jqXHR, exception) {
                if (jqXHR.location === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.location == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.location == 500) {
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
