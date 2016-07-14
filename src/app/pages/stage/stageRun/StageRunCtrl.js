/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage.stagerun').controller('StageRunCtrl', StageRunCtrl);

    function StageRunCtrl($scope, $http, $filter, myUtilService, UtilFactory) {
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
            $scope.products = [];
            $http.get("web/shiphomes/stage/" + $scope.selectedStage + "/products").success(function (data) {
                $scope.products = data;
                console.log(data); 
            });

            $scope.loadTable();
        };

        $scope.getTestUnits = function () {
            $http.get("web/testunits/release/" + $scope.selectedRelease + "/product/" + $scope.selectedProduct).success(function (data) {
                $scope.testunits = data;
            });

            $scope.loadTable();
        };

        $scope.loadTable = function () {
            $scope.regressdetails = [];
            var URL = 'web/regressdetails/stage/' + $scope.selectedStage;

            if ($scope.selectedProduct) {
                URL = URL + "/product/" + $scope.selectedProduct;
            }

            if ($scope.selectedTestUnit) {
                URL = URL + "/testunit/" + $scope.selectedTestUnit;
            }

            $http.get(URL).success(function (data) {
                $scope.regressdetails = data;
            });
        };

        $scope.runStage = function () {
            var URL = 'web/runregress';///stage/' + $scope.selectedStage;
            
            var tempStage = $filter('filter')($scope.stages, {id: $scope.selectedStage});
            
            var tempProduct = {};
            var tempTestunit = {}; 
            if ($scope.selectedProduct) {
                 tempProduct = $filter('filter')($scope.products, {productName: $scope.selectedProduct});
            }
  
            if ($scope.selectedTestUnit) {
                tempTestunit = $filter('filter')($scope.testunits, {id: $scope.selectedTestUnit});
            }
           
            var postdata = {
                stage: tempStage,
                product: tempProduct,
                testunit: tempTestunit
            };

            console.log(tempProduct);
            console.log(tempStage);
            console.log(tempTestunit);
            $http.post(URL, postdata).success(function (data, status, headers, config)    {

            });

        };

        $scope.selectTestUnits = function () {
            console.log("Selected Product:" + $scope.selectedProduct);
        };
    }
    ;
})();

