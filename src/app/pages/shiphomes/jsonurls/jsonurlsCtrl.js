/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.shiphomes.jsonurls').controller('JsonUrlsCtrl', JsonUrlsCtrl);
    angular.module('BlurAdmin.pages.shiphomes.jsonurls').filter('prettyJSON', prettyJSON);

    /** @ngInject */
    function JsonUrlsCtrl($scope, $http, $filter) {

        $scope.shiphomes = {};
        $scope.tempShiphomes = {};
        $scope.jsonurl = "";

        $scope.releases = [];
        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.stages = [];
        $scope.selectStage = function () {
            var rid={};
            for (var r in $scope.releases){
                console.log("id:" + $scope.releases[r].name);
                if ($scope.releases[r].name === $scope.selectedRelease){
                    rid = $scope.releases[r].id;
                }
            }
            
            console.log("id1:" + rid);
            
            $http.get("web/stage/release/" + rid).success(function (data) {
                $scope.stages = data;
            });
        };


        $scope.selectProducts = function () {
            $http.get("web/shiphomes/release/"+ $scope.selectedRelease +"/stage/" + $scope.selectedStage).success(function (data) {
                $scope.shiphomes = data;
                $scope.tempShiphomes = data;
                //$scope.tempShiphomes = data;

//                var stagename = "";
//                 $scope.jsonurl = "";
//                for (var i in $scope.stages) {
//                    console.log("Madhu" + $scope.stages[i].id + ":" + $scope.selectedStage);
//                    if ($scope.stages[i].name === $scope.selectedStage)
//                    {
//                        stagename = $scope.stages[i].stageName;
                        $scope.jsonurl = window.location.protocol + "//" + window.location.host +
                                "/sr/web/shiphomes/release/"+ $scope.selectedRelease +"/stage/" + $scope.selectedStage;
//                    }                    
//                }                

                $scope.products = {};
                $scope.platforms = {};
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        console.log($scope.shiphomes[key].id);
                        $scope.products [$scope.shiphomes[key].product.name] = $scope.shiphomes[key].product;
                        $scope.platforms [$scope.shiphomes[key].platform.name] = $scope.shiphomes[key].platform;
                    }
                }

                console.log("Products:" + $scope.products);
                console.log("Platforms:" + $scope.platforms);
            });
            
           // $scope.displayShiphomes ();
            
        };

        $scope.tempShiphomes ={};
        $scope.displayShiphomes = function () {            

            console.log(" selected product:" + $scope.selectedProduct +
                    " selected platform:" + $scope.selectedPlatform + "Boolean:" + ($scope.selectedProduct) + ($scope.selectedPlatform));
            
            //if ($scope.selectedRelease && $scope.selectedStage){
            
            if (!$scope.selectedProduct && !$scope.selectedPlatform) {
                $scope.tempShiphomes = $scope.shiphomes;
            } else if ($scope.selectedProduct && !$scope.selectedPlatform) {
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        if ($scope.shiphomes[key].product.name === $scope.selectedProduct)
                            $scope.tempShiphomes[key] = $scope.shiphomes[key];
                    }
                }
            } else if (!$scope.selectedProduct && $scope.selectedPlatform) {
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        if ($scope.shiphomes[key].platform.name === $scope.selectedPlatform)
                            $scope.tempShiphomes[key] = $scope.shiphomes[key];
                    }
                }
            } else {
                for (var key in $scope.shiphomes) {
                    if ($scope.shiphomes.hasOwnProperty(key)) {
                        if ($scope.shiphomes[key].product.name === $scope.selectedProduct && $scope.shiphomes[key].platform.name === $scope.selectedPlatform)
                            $scope.tempShiphomes[key] = $scope.shiphomes[key];
                    }
                }
            }


        };
    }

    /** @ngInject */
    function prettyJSON() {
        function prettyPrintJson(json) {
            return JSON ? JSON.stringify($scope.shiphomes, null, '  ') : 'your browser doesnt support JSON so cant pretty print';
        }
        return prettyPrintJson;
    }
})();