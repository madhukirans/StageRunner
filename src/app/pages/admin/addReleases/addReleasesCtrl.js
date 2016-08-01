/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';
    angular.module('BlurAdmin.pages.admin.addreleases').controller('AddReleasesCtrl', AddReleasesCtrl);


    /** @ngInject */  
    function AddReleasesCtrl($scope, $filter, $http, myUtilService, UtilFactory, editableOptions, editableThemes, $q, $timeout) {


        
        
        $scope.loadTable = function() {
            $scope.releases = [];
            $http.get("web/releases").success(function (data) {
            //$scope.releases = data;
            for (var key in data)
                $scope.releases.push(data[key]);
            });
            //return $timeout(function() {}, 3000);
        };
        
        $scope.loadTable();
        
        
        $scope.smartTablePageSize = 10;
        // $scope.editableTableData = $scope.releases.sslice(0, 9);

        $scope.addRelease = function () {
            $scope.inserted = {
                "releaseName": "",
                "desc1": ""
            };
            $scope.releases.push($scope.inserted);
        };

        $scope.persistRelease = function (index, rowform) {
            $scope.tempReleases = $scope.releases[index];
            var d = $q.defer();

            if ($scope.tempReleases.releaseName !== "") {
                $http.put("web/releases/" + $scope.releases[index].releaseName, $scope.releases[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    $scope.rowform.$setError('releasenames', "Error: Problem while editing record");
                    //myUtilService.showErrorMsg("Error: Record edit failed"); 
                    
                    return "error";// $q.reject('Server error!');
                });
            } else {

                $http.post("web/releases", $scope.releases[index]).success(function (data, status, headers, config)
                {
                    //console.log("madhu:" + "data:" + data + " Status:" + status + " headers:" + headers + " config:" + config);
                    if (status === 200) {
                        $scope.showSuccessMsg("Success: Record added successfully");
                    } else {
                        $scope.rowform.$setError('releasenames', "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error while saving Record");
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    $scope.rowform.$setError('releasenames', "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return "error";//$q.reject('Server error!');
                });
            }

            return d.promice;
        };

        $scope.removeRelease = function (index, releasenames) {
            $http.delete("web/releases/" + $scope.releases[index].releaseName)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");                        
                        $scope.releases.splice(index, 1);
                    }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                   $scope.rowform.$setError('releasenames', "Error: Problem while deleting record");
                    myUtilService.showErrorMsg("Error: Record delete failed"); 
                    
                    return "error";// $q.reject('Server error!')
                    });           
        };

        


        // editableOptions.theme = 'bs3';
        // editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        // editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
    }
})();