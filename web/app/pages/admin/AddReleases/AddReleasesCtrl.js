/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.admin.addreleases').controller('AddReleasesCtrl', AddReleasesCtrl);


    /** @ngInject */
    function AddReleasesCtrl($scope, $filter, $http, $q, editableOptions, editableThemes) {



        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            //$scope.releases = data;
            for (var key in data)
                $scope.releases.push(data[key]);
        });
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
//            $http({
//                method: "POST",
//                url: "web/releases",
//                data: $scope.releases[index],
//                transformResponse: function (data, header) {
//                        console.log("transformResponse, header:", header());
//                        console.log("transformResponse, data:", data);                        
//                        return { data: angular.fromJson(data) };
//                    }
//            }).then(function (res) {     //first function "success"
//                console.log(res.data);
//            }, function (err) {          //second function "error"
//                console.log("err:" + err + ":" +  err.msg + ":" + err.status);
//                console.log("data:" + err.data +":" + err.msg +":" + err.status);
//               // for (var key in angular)
//                //    console.log("angular:" + key + "---" + angular[key]);
//            });

            $http.post("web/releases", $scope.releases[index]).success(function (data, status, headers, config)
            {
                //console.log("madhu:" + "data:" + data + " Status:" + status + " headers:" + headers + " config:" + config);
                if (status === 200) {
                    rowform.$setError(rowform.saveButton, "Success: Record added successfully");
                } else {
                    rowform.$setError(rowform.saveButton, "Error occurred while saving record: staus : " + status);
                    return $q.reject("Error");
                }
            }).error(function (data, status, header, config) {
                //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                rowform.$setError(rowform.saveButton, "Error: Problem while adding record");
                return $q.reject('Server error!');
            });

            return d.promice;
        };


        // editableOptions.theme = 'bs3';
        // editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        // editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
    }
})();