/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.admin.addStages').controller('AddStagesCtrl', AddStagesCtrl);


    /** @ngInject */
    function AddStagesCtrl($scope, $filter, $http, $q, myUtilService, UtilFactory, editableOptions, editableThemes) {



        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.stages = [];
        $scope.loadTable = function () {
            $http.get("web/stage/release/" + $scope.selectedRelease).success(function (data) {
                $scope.stages = data;
            });
        };

        $scope.smartTablePageSize = 10;

        $scope.addStage = function () {
            $scope.inserted = {
                "stageName": "",
                "comments": "",
                "release": {"id": $scope.selectedRelease}
            };
            $scope.stages.push($scope.inserted);
        };
        
        

        $scope.persistStage = function (index, rowform) {
            //$scope.stages[index].releaseEntity = $scope.selectedRelease;
            //var d = $q.defer();
            if ($scope.stages[index].id >= 0) {
                $http.put("web/stage/" + $scope.stages[index].id, $scope.stages[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    myUtilService.showSuccessMsg("Success: Record edited successfully");
                    return $q.reject('Server error!');
                });
            } else {
                $http.post("web/stage", $scope.stages[index]).success(function (data, status, headers, config)
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
            $http.delete("web/stage/" + $scope.stages[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        console.log("record deleted");
                    }).error(function (data, status, headers, config) {
                        myUtilService.showErrorMsg("Deleted record failed.");
                        console.log("record deleted");
                    });
            $scope.stages.splice(index, 1);
        };
        // editableOptions.theme = 'bs3';
        // editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        // editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
    }
})();