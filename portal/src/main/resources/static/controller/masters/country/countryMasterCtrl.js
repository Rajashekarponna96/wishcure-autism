
	//Country Controller

angular.module("app").controller("CountryMasterController", function($scope, $http, $filter, $uibModal, $rootScope,$log) {
	
    init();
//getAllStatusMasters
    function init() {
        _countryuses = [];
        $scope.countryuses = _countryuses;


        $.ajax({
            type: "get",
            async: false,
            url: "http://localhost:8080/cognore/getCountryMaster",
            contentType: "application/json;charset:utf-8",
            dataType: "json",
           
            success: function(response) {
                $scope.countryObject=response;
                for (i in $scope.countryObject) {
                    $scope.countryuses.push({
                        cid: $scope.countryObject[i].countryid,
                        csname: $scope.countryObject[i].shortname,
                        cfname: $scope.countryObject[i].fullname,
                        cicode: $scope.countryObject[i].isdcode,
                        cmobile: $scope.countryObject[i]. mobilelength,
                        
                        ccuserid: $scope.countryObject[i].createdByUserId,
                        cmuserid: $scope.countryObject[i].modifiedByUserId,
						ccdate: $scope.countryObject[i].createdDate,
						cmdate: $scope.countryObject[i].modifiedDate,
						
						sid: $scope.countryObject[i].statusMasterId
						

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
            templateUrl: 'CModalContent.html',
            controller: 'CModalInstanceCtrl',
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
        _cid = _countryuses[_row].cid;
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'CModalContentdelete.html',
            controller: 'CdeleteModalInstanceCtrl',
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

    $scope.countryMasterEdit = function(id) {
        $rootScope.tag = "Update Country";
        _n = 2;
        _row = id;
        _cid = $scope.countryuses[_row].cid;
        _csname = $scope.countryuses[_row].csname;
        _cfname = $scope.countryuses[_row].cfname;
        _cicode=$scope.countryuses[_row].cicode;
        _cmobile=$scope.countryuses[_row].cmobile;
        
    };
    $scope.countryMasterSubmit = function() {
        // alert($rootScope.id)

        $("#lbl_e_Country_Name,#lbl_e_Country_Category_Name").text("");
        if ($.trim($("#txt_Country_Name").val()) == "") {
            $("#lbl_e_Country_Name").text("Please enter Country");
        }
        if ($.trim($("#txt_Country_Category_Name").val()) == "") {
            $("#lbl_e_Country_Category_Name").text("Please enter category");
        }
        // success
    };
    $scope.countryMasterAdd = function(item) {

        $rootScope.tag = "Add Country";
        _n = 1;
        _cid = 0;

    };
}).controller('CModalInstanceCtrl', function($scope, $uibModalInstance, $rootScope) {
   
	if (_n == 2) {
        $scope.cid = _cid;
        $scope.csname = _csname;
        $scope.cfname = _cfname;
        $scope.cicode = _cicode;
        $scope.cmobile = _cmobile;
    } else if(_n == 1){
        $scope.cid = $scope.csname = $scope.cfname=$scope.cicode=$scope.cmobile ='';
    }
    $scope.ok = function() {
    	if(_n == 1){
        var x = 1;
    	}else if(_n==2){
    		var x=2;
    	}
        var csname = document.getElementById("txt_Country_shortName").value;
       
        $("#lbl_e_Country_shortName").text("");

        if ($.trim($("#txt_Country_shortName").val()) == "") {
            x = 0;$("#lbl_e_Country_shortName").text("Please Enter Country ShortName");
        } else if ($.trim($("#txt_Country_shortName").val().length) < 2) {
            x = 0;$("#lbl_e_Country_shortName").text("Country  ShortName should be more than 2 chars ");
        } else if ($.trim($("#txt_Country_shortName").val().length) > 20) {
            x = 0;$("#lbl_e_Country_shortName").text("Country  ShortName should not exceed 20 chars");
        }

        
        
        var cfname = document.getElementById("txt_Country_fullName").value;
        
        $("#lbl_e_country_fullName").text("");

        if ($.trim($("#txt_Country_fullName").val()) == "") {
            x = 0;$("#lbl_e_country_fullName").text("Please Enter Country FullName");
        } else if ($.trim($("#txt_Country_fullName").val().length) < 2) {
            x = 0;$("#lbl_e_country_fullName").text("Country FullName should be more than 2 Chars");
        } else if ($.trim($("#txt_Country_fullName").val().length) > 20) {
            x = 0;$("#lbl_e_country_fullName").text("Country FullName should not exceed 20 Chars");
        }
        /////////////////////////
       var cicode = document.getElementById("txt_Country_isdCode").value;
        
        $("#lbl_e_country_isdCode").text("");

        if ($.trim($("#txt_Country_isdCode").val()) == "") {
            x = 0;$("#lbl_e_country_isdCode").text("Please Enter Country ISD code");
        } else if ($.trim($("#txt_Country_isdCode").val().length) < 2) {
            x = 0;$("#lbl_e_country_isdCode").text("Country ISD code should be more than 2 numbers");
        } else if ($.trim($("#txt_Country_isdCode").val().length) > 20) {
            x = 0;$("#lbl_e_country_isdCode").text("Country ISD code should not exceed 20 numbers");
        }
        //////////////////////////
        var cmobile = document.getElementById("txt_Country_mobile").value;
        
        $("#lbl_e_Country_mobile").text("");

        if ($.trim($("#txt_Country_mobile").val()) == "") {
            x = 0;$("#lbl_e_Country_mobile").text("Please Enter Mobile Length");
        } else if ($.trim($("#txt_Country_mobile").val().length) < 2) {
            x = 0;$("#lbl_e_Country_mobile").text("Mobile Length should be more than 2 numbers");
        } else if ($.trim($("#txt_Country_mobile").val().length) > 20) {
            x = 0;$("#lbl_e_Country_mobile").text("Mobile Length should not exceed 20 numbers");
        }
        
         $scope.saveData={
        	"shortname": csname,
        	"fullname" :cfname,
        	"isdcode" : cicode,
        	"mobilelength":cmobile,
        	"statusMasterId":{"statusmasterid":$rootScope.uniqueStatusMasterId
        		
        	}
        	
        }
         
        if (x == 1) {

            $.ajax({
                type: "post",
                async: false,
                url: "http://localhost:8080/cognore/saveCountryMaster",
                contentType: "application/json;charset:utf-8",
                dataType: "json",
                data: JSON.stringify($scope.saveData),
                success: function(response) {
                    if (_n == 1) {
						$scope.countryid=response;
					
                        _countryuses.push({ cid: $scope.countryid, csname: csname ,cfname: cfname, cicode: cicode,cmobile: cmobile});
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
        		"countryid": $scope.cid,
        	   "shortname": csname,
        	   "fullname" :cfname,
        	   "isdcode":cicode,
        	   "mobilelength":cmobile
        }
        // update statusMaster
        
        if (x == 2) {
            $.ajax({
                type: "post",
                async: false,
                url: "http://localhost:8080/cognore/updateCountryMaster",
                contentType: "application/json",
                data:JSON.stringify($scope.updateddata),
                success: function(response) {
                    if(_n== 2){
                        _countryuses[_row].cid =  $scope.cid;
                        _countryuses[_row].csname =csname;
                        _countryuses[_row].cfname =cfname;
                        _countryuses[_row].cicode =cicode;
                        _countryuses[_row].cmobile =cmobile;
                       
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
}).controller('CdeleteModalInstanceCtrl', function($scope, $uibModalInstance, items, $rootScope) {


	
	$scope.deleteObj={
			"countryid":_cid
	}
    $scope.ok = function() {
        $.ajax({
            type: "delete",
            async: false,
            url: "http://localhost:8080/cognore/deleteCountryMaster/"+_cid,
            contentType: "application/json",
            dataType: "text",
            data: $scope.deleteObj, 
            success: function(response) {
            	_countryuses.splice(_row, 1);
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


























