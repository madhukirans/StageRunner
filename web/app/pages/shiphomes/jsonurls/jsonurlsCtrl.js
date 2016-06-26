/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.shiphomes.jsonurls').controller('JsonUrlsCtrl', JsonUrlsCtrl);
    angular.module('BlurAdmin.pages.shiphomes.jsonurls').filter('prettyJSON', prettyJSON);

    /** @ngInject */
    function JsonUrlsCtrl($scope, $http) {

        $scope.shiphomes = {};
        $scope.tempShiphomes = {};
        $scope.jsonurl = "";

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


        $scope.selectProducts = function () {
            $http.get("web/shiphomes/stage/" + $scope.selectedStage).success(function (data) {
                $scope.shiphomes = data;
                $scope.tempShiphomes = data;

                $scope.jsonurl = window.location.protocol + "//" + window.location.host + "/sr/web/shiphomes/stage/" + $scope.selectedStage;
                $scope.products = {};
                $scope.platforms = {};
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        console.log($scope.shiphomes[key].id);
                        $scope.products [$scope.shiphomes[key].product.productName] = $scope.shiphomes[key].product;
                        $scope.platforms [$scope.shiphomes[key].platform.name] = $scope.shiphomes[key].platform;
                    }
                }

                console.log("Products:" + $scope.products);
                console.log("Platforms:" + $scope.platforms);
            });
        };


        $scope.displayShiphomes = function () {
            $scope.tempShiphomes = {};

            console.log(" selected product:" + $scope.selectedProduct +
                    " selected platform:" + $scope.selectedPlatform + "Boolean:" + ($scope.selectedProduct) + ($scope.selectedPlatform));

            if (!$scope.selectedProduct && !$scope.selectedPlatform) {
                $scope.tempShiphomes = $scope.shiphomes;
            } 
            else if ($scope.selectedProduct && !$scope.selectedPlatform) {
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        if ($scope.shiphomes[key].product.productName === $scope.selectedProduct)
                            $scope.tempShiphomes[key] = $scope.shiphomes[key];
                    }
                }
            }
            else if (!$scope.selectedProduct && $scope.selectedPlatform) {
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        if ($scope.shiphomes[key].platform.name  === $scope.selectedPlatform)
                            $scope.tempShiphomes[key] = $scope.shiphomes[key];
                    }
                }
            }
            else  {
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        if ($scope.shiphomes[key].product.productName === $scope.selectedProduct && $scope.shiphomes[key].platform.name  === $scope.selectedPlatform)
                            $scope.tempShiphomes[key] = $scope.shiphomes[key];
                    }
                }
            }          
            
            
        };
    }
    
    /** @ngInject */
    function prettyJSON() {
        function prettyPrintJson(json) {
            return JSON ? JSON.stringify(json, null, '  ') : 'your browser doesnt support JSON so cant pretty print';
        }
        return prettyPrintJson;
    }
})();