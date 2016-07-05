/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.shiphomes.announcement').controller('ShiphomeAnnouncementCtrl', ShiphomeAnnouncementCtrl);

    function ShiphomeAnnouncementCtrl($scope, $http, $filter, myUtilService, UtilFactory, editableOptions, editableThemes) {
        $scope.smartTablePageSize = 10;


        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.stages = [];
        $scope.selectStage = function () {
            $http.get("web/stages/release/" + $scope.selectedRelease).success(function (data) {
                $scope.stages = data;
            });
        };

        $scope.shiphomes = [];
        $scope.products = [];
        $scope.platforms = [];
        $scope.stage = [];
        $scope.displayShiphomes = function (data) {

            $http.get("web/stages/" + $scope.selectedStage).success(function (data) {
                $scope.stage = data;
            });


            $http.get("web/shiphomes/stage/" + $scope.selectedStage).success(function (data) {
                $scope.shiphomes = data;
            });

            $scope.editableTableData = $scope.shiphomes.slice(0, 36);

            $http.get("web/products").success(function (data) {
                $scope.products = data;
            });

            $http.get("web/platforms").success(function (data) {
                $scope.platforms = data;
            });
        };

        $scope.selectProduct = function (product) {
            var selected = [];
            if (product.productName) {
                selected = $filter('filter')($scope.products, {productName: product.productName});
            }
            return selected.length ? selected[0].productName : 'Not set';
        };

        $scope.selectPlatform = function (platform) {
            var selected = [];
            if (platform.name) {
                selected = $filter('filter')($scope.platforms, {name: platform.name});
            }
            return selected.length ? selected[0].name : 'Not set';
        };

        $scope.removeShiphome = function (index) {
            $http.delete("web/shiphomes/" + $scope.shiphomes[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        console.log("record deleted");
                    });
            $scope.shiphomes.splice(index, 1);

        };

        $scope.addShiphome = function () {
            $scope.inserted = {
                //"id":"0",
                "platform": "",
                "product": "",
                "shiphomeloc": "",
                "shiphomenames": "",
                "stageid": $scope.stage,
                "comment": ""
            };
            $scope.shiphomes.push($scope.inserted);
            console.log($scope.inserted.platform);
        };

        $scope.tempshiphomedisplay = [];
        $scope.persistShiphome = function (index, rowform) {
            $scope.tempshiphomedisplay = $scope.shiphomes[index];
            if ($scope.tempshiphomedisplay.id >= 0) {
                $http.put("web/shiphomes/" + $scope.shiphomes[index].id, $scope.shiphomes[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully"); 
                        });
            } else {
                $http.post("web/shiphomes", $scope.shiphomes[index]).success(function (data, status, headers, config) {
                    $scope.PostDataResponse = data;
                    myUtilService.showSuccessMsg("Success: Record added successfully");
                    console.log("Record added");
                });
            }
        };

        editableOptions.theme = 'bs3';
        editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';

    };


})();

