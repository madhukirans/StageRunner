/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.shiphomes.announcement').controller('ShiphomeAnnouncementCtrl', ShiphomeAnnouncementCtrl);

    function ShiphomeAnnouncementCtrl($scope, $http, $filter, myUtilService, UtilFactory, editableOptions, editableThemes, $q, $timeout) {
        $scope.smartTablePageSize = 10;


        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.stages = [];
        $scope.getStages = function () {
            $http.get("web/stage/release/" + $scope.selectedRelease).success(function (data) {
                $scope.stages = data;
            });
        };
        
        $http.get("web/product").success(function (data) {
                $scope.products = data;
            });

        $http.get("web/platform").success(function (data) {
            $scope.platforms = data;
        });
            
        $scope.shiphomes = [];
        $scope.products = [];
        $scope.platforms = [];
        $scope.stage = [];
        $scope.loadTable = function (data) {

            $http.get("web/shiphomes/stage/" + $scope.selectedStage).success(function (data) {
                $scope.shiphomes = data;
            });
            $scope.editableTableData = $scope.shiphomes.slice(0, 36);
        };

        $scope.selectProduct = function (product) {
            var selected = [];
            if (product.id) {
                selected = $filter('filter')($scope.products, {name: product.name});
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

        $scope.removeShiphome = function (index) {
            $http.delete("web/shiphomes/" + $scope.shiphomes[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        console.log("record deleted");
                    }).error(function (data, status, headers, config) {
                        myUtilService.showErrorMsg("Deleted record failed.");
                        console.log("record deleted");
                    });
            $scope.shiphomes.splice(index, 1);

        };

        $scope.addRow = function () {
            $scope.inserted = {
                //"id":"0",
                "platform": "",
                "product": "",
                "shiphomeloc": "",
                "shiphomenames": "",
                "stage": {"id":$scope.selectedStage},
                "comment": ""
            };
            $scope.shiphomes.push($scope.inserted);
            console.log($scope.inserted.platform);
        };

        //$scope.tempshiphomedisplay = [];
        $scope.persistShiphome = function (index, rowform) {
            if (! $scope.shiphomes[index].shiphomeloc) {
                rowform.$setError('shiphomeloc', 'Missing shiphome location.');
                return "error";
            }
                
            
            //$scope.tempshiphomedisplay = $scope.shiphomes[index];
            
            if ($scope.shiphomes[index].id >= 0) {
                $http.put("web/shiphomes/" + $scope.shiphomes[index].id, $scope.shiphomes[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {                    
                    rowform.$setError('shiphomeloc', "Error: Problem while editing record");
                    myUtilService.showErrorMsg("Error: Record edit failed"); 
                    return "error";// $q.reject('Server error!');
                });
            } else {
                $http.post("web/shiphomes", $scope.shiphomes[index]).success(function (data, status, headers, config) {   
                    console.log("Status"+ status);
                    if (status === 204) {
                        myUtilService.showSuccessMsg("Success: Record added successfully");
                    } else {
                        rowform.$setError('shiphomeloc', "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error while saving Record");
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {                    
                    rowform.$setError('shiphomeloc', "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return "error";//$q.reject('Server error!');
                });
            }           
            
        };
        
        

        editableOptions.theme = 'bs3';
        editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';

    };


})();

