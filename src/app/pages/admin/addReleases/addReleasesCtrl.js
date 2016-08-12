/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';
    angular.module('BlurAdmin.pages.admin.addreleases').controller('AddReleasesCtrl', AddReleasesCtrl);


    /** @ngInject */
    function AddReleasesCtrl($scope, $filter, $http, myUtilService, UtilFactory, editableOptions, editableThemes, $q, $timeout) {

        $scope.loadTable = function () {
            $scope.releases = [];
            $http.get("web/releases").success(function (data) {
                $scope.releases = data;                
            });
            return $timeout(function() {}, 3000);
        };

        $scope.loadTable();


        $scope.smartTablePageSize = 10;
        // $scope.editableTableData = $scope.releases.sslice(0, 9);

        $scope.addRelease = function () {
            $scope.inserted = {
               // "id":"",
                "name": ""                
            };
            $scope.releases.push($scope.inserted);
        };

        $scope.persistRelease = function (index, rowform) {            
            var d = $q.defer();
            console.log("Ma:" + $scope.releases[index].id);
            if ($scope.releases[index].id >= 0) {
                $http.put("web/releases/" + $scope.releases[index].id, $scope.releases[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {                    
                    rowform.$setError('releaseName', "Error: Problem while editing record");
                    myUtilService.showErrorMsg("Error: Record edit failed"); 
                    return "error";// $q.reject('Server error!');
                });
            } else {
                $http.post("web/releases", $scope.releases[index]).success(function (data, status, headers, config) {   
                    console.log("Status"+ status);
                    if (status === 204) {
                        myUtilService.showSuccessMsg("Success: Record added successfully");
                    } else {
                        rowform.$setError('releasenames', "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error while saving Record");
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {                    
                    rowform.$setError('releasenames', "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return "error";//$q.reject('Server error!');
                });
            }

            return d.promice;
        };
        
              
        $scope.removeRelease = function (index, releasenames) {
            $http.delete("web/releases/" + $scope.releases[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        $scope.releases.splice(index, 1);
                    }).error(function (data, status, header, config) {
                //$scope.rowform.$setError('releasenames', "Error: Problem while deleting record");
                myUtilService.showErrorMsg("Error: Record delete failed");
                return "error";// $q.reject('Server error!')
            });
        };
    }
})();