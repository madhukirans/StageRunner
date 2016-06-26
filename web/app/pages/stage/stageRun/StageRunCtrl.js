/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage').controller('StageRunCtrl', StageRunCtrl);
  
    function StageRunCtrl($scope, $http) {
        //$scope.selectedProduct = "";
        //$scope.selectedTestUnit = "";

        $scope.products = [];

        $http.get('web/products').success(function (data) {
            $scope.products = data;
        });

        $scope.selectProducts = function () {
            $scope.testUnits = [];
            $http.get('web/testunits/product/' + $scope.selectedProduct).success(function (data) {
            $scope.testUnits = data;
            });
        };
        
         $scope.selectTestUnits = function () {
            console.log("Selected Product:" + $scope.selectedProduct);
        };        
    };
})();

