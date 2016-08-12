/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.admin.addShiphomeNames').controller('AddShiphomeNamesCtrl', AddShiphomeNamesCtrl);


    /** @ngInject */
    function AddShiphomeNamesCtrl($scope, $filter, $http, $q, myUtilService, UtilFactory, editableOptions, editableThemes) {

        var loadProductsAndReleases = function () {
            $http.get("web/product").success(function (data) {
                $scope.products = data;
            });

            $http.get("web/platform").success(function (data) {
                $scope.platforms = data;
            });
        }
        
        loadProductsAndReleases();
        

        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.shiphomeNames = [];
        $scope.loadTable = function () {
            
         //   loadProductsAndReleases();
            
//            if ($scope.selectedRelease)
//                $http.get("web/shiphomenames/release/" + $scope.selectedRelease ).success(function (data) {
//                    $scope.shiphomeNames = data;
//                });
//            else
//                $scope.shiphomeNames = [];
            
            if ($scope.selectedRelease && $scope.selectedProduct ) {
                $http.get("web/shiphomenames/release/" + $scope.selectedRelease + "/product/" + $scope.selectedProduct).success(function (data) {
                    $scope.shiphomeNames = data;
                });
                
                $http.get("web/component/product/" + $scope.selectedProduct).success(function (data) {
                    $scope.components = data;
                });
            }
            else{
                $scope.shiphomeNames = [];
                $scope.components = [];
            }
            
            
            
           
        };

        $scope.smartTablePageSize = 10;

        $scope.addRow = function () {
            $scope.inserted = {
                "shiphome": "",
                "platform": "",
                "product": {"id": $scope.selectedProduct},
                "component": "",
                "release": {"id": $scope.selectedRelease}
            };
            $scope.shiphomeNames.push($scope.inserted);
        };
       
        
        $scope.selectComponent = function (component) {
            var selected = [];
            if (component.id) {
                selected = $filter('filter')($scope.components, {name: component.name});
            }
            return selected.length ? selected[0].name : 'Not set';
        };

        $scope.selectPlatform = function (platform) {
            var selected = [];
            if (platform.id) {
                selected = $filter('filter')($scope.platforms, {name: platform.name});
            }
            return selected.length ? selected[0].name : 'Not set';
        };

        $scope.persist = function (index, rowform) {
            $scope.tempStages = $scope.shiphomeNames[index];

            //$scope.shiphomeNames[index].releaseEntity = $scope.selectedRelease;
            //var d = $q.defer();
            if ($scope.tempStages.id >= 0) {
                $http.put("web/shiphomenames/" + $scope.shiphomeNames[index].id, $scope.shiphomeNames[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    myUtilService.showSuccessMsg("Success: Record edited successfully");
                    return $q.reject('Server error!');
                });
            } else {
                $http.post("web/shiphomenames", $scope.shiphomeNames[index]).success(function (data, status, headers, config)
                {
                    //console.log("madhu:" + "data:" + data + " Status:" + status + " headers:" + headers + " config:" + config);
                    if (status === 204) {
                        myUtilService.showSuccessMsg("Success: Record added successfully");
                    } else {
                        rowform.$setError(rowform.saveButton, "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error while saving Record");
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    rowform.$setError(rowform.saveButton, "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return $q.reject('Server error!');
                });
            }
            // return d.promice;
        };

        $scope.removeStage = function (index) {
            $http.delete("web/shiphomenames/" + $scope.shiphomeNames[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        console.log("record deleted");
                    }).error(function (data, status, headers, config) {
                myUtilService.showErrorMsg("Deleted record failed.");
                console.log("record deleted");
            });
            $scope.shiphomeNames.splice(index, 1);
        };
        // editableOptions.theme = 'bs3';
        // editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        // editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
    }
})();