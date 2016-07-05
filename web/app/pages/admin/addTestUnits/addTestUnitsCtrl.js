/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.admin.addTestUnits').controller('AddTestUnitsCtrl', AddTestUnitsCtrl);


    /** @ngInject */
    function AddTestUnitsCtrl($scope, $filter, $http, $q, myUtilService, UtilFactory, toastr, editableOptions, editableThemes) {
        $scope.products = [];
        $http.get("web/products").success(function (data) {
            $scope.products = data;
        });

        $scope.getTestUnits = function () {
            $scope.testUnits = [];
            $http.get("web/testunits/product/" + $scope.selectedProduct).success(function (data) {
                $scope.testUnits = data;
            });
        };

        $scope.smartTablePageSize = 10;

        $scope.addTestUnit = function () {
            $scope.inserted = {
                "testUnitName": "",
                "topoid": "",
                "jobreqAgentCommand": "",
                "description": "",
                "productName": {"productName": $scope.selectedProduct}
            };
            $scope.testUnits.push($scope.inserted);
        };

        $scope.persistTestUnit = function (index, rowform) {
            $scope.tempTestUnit = $scope.testUnits[index];

            if ($scope.tempTestUnit.id >= 0) {
                $http.put("web/testunits/" + $scope.testUnits[index].id, $scope.testUnits[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {
                    rowform.$setError(rowform.saveButton, "Error: Problem while editing record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return $q.reject('Server error!');
                });
            } else {
                $http.post("web/testunits", $scope.testUnits[index]).success(function (data, status, headers, config)
                {
                    //console.log("madhu:" + "data:" + data + " Status:" + status + " headers:" + headers + " config:" + config);
                    if (status === 200) {
                        rowform.$setError(rowform.saveButton, "Success: Record added successfully");
                        myUtilService.showSuccessMsg("Success: Record added successfully");
                    } else {
                        rowform.$setError(rowform.saveButton, "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error occurred while saving record: staus : " + status);
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    rowform.$setError(rowform.saveButton, "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error: Problem while adding record");
                    return $q.reject('Server error!');
                });
            }

            // return d.promice;
        };

        $scope.removeTestUnit = function (index) {
            $http.delete("web/testunits/" + $scope.testUnits[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                    });
            $scope.testUnits.splice(index, 1);
        };

        // editableOptions.theme = 'bs3';
        // editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        // editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
    }
})();