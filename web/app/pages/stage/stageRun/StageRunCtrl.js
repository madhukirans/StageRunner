/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage.stagerun').controller('StageRunCtrl', StageRunCtrl);

    function StageRunCtrl($scope, $http) {
        //$scope.selectedProduct = "";
        //$scope.selectedTestUnit = "";

        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.getStages = function () {
            $scope.stages = [];
            $http.get("web/stages/release/" + $scope.selectedRelease).success(function (data) {
                $scope.stages = data;
            });
        };

        $scope.getProducts = function () {
            $http.get("web/shiphomes/products/" + $scope.selectedStage).success(function (data) {
                $scope.products = data;
            });
        };

        //$http.get('web/products').success(function (data) {
        //    $scope.products = data;
        //});

        $scope.selectProducts = function () {
            $scope.testUnits = [];
            $http.get('web/testunits/product/' + $scope.selectedProduct).success(function (data) {
                $scope.testUnits = data;
            });
        };

        $scope.selectTestUnits = function () {
            console.log("Selected Product:" + $scope.selectedProduct);
        };
    }
    ;
})();

