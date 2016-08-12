/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';
    angular.module('BlurAdmin.pages.admin.addProductsComponents').controller('AddProductsComponentsCtrl', AddProductsComponentsCtrl);


    /** @ngInject */
    function AddProductsComponentsCtrl($scope, $filter, $http, myUtilService, UtilFactory, editableOptions, editableThemes, $q, $timeout) {

        $scope.products = [];

        $scope.loadProductTable = function () {
            var promise = UtilFactory.getAllProductsFactory();
            promise.then(
                    function (success) {
                        $scope.products = success.data;
                    },
                    function (error) {
                        myUtilService.error('Error loading data.', error);
                    });

            promise = UtilFactory.getAllComponentsFactory();
            promise.then(
                    function (success) {
                        $scope.components = success.data;
                    },
                    function (error) {
                        myUtilService.error('Error loading data.', error);
                    });
        };

        

        $scope.loadTable = function () {
            $scope.loadProductTable();
            return $timeout(function() {}, 3000);
        }
        
        $scope.loadTable();

        $scope.smartTablePageSize = 10;

        $scope.addProduct = function () {
            $scope.inserted = {
                "name": ""
            };
            $scope.products.push($scope.inserted);
        };


        $scope.addComponent = function () {
            $scope.inserted2 = {
                "product": "",
                "name": ""
            };
            $scope.components.push($scope.inserted2);
        };
        
        $scope.selectProduct = function (product) {
            var selected = [];
            if (product.id) {
                selected = $filter('filter')($scope.products, {id: product.id});
            }
            return selected.length ? selected[0].name : 'Not set';
        };

        $scope.persistProduct = function (index, rowform) {
            var d = $q.defer();

            if ($scope.products[index].id >= 0) {
                $http.put("web/product/" + $scope.products[index].id, $scope.products[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    rowform.$setError('productNames', "Error: Problem while editing record");
                    //myUtilService.showErrorMsg("Error: Record edit failed"); 

                    return "error";// $q.reject('Server error!');
                });
            } else {

                $http.post("web/product", $scope.products[index]).success(function (data, status, headers, config)
                {
                    //console.log("madhu:" + "data:" + data + " Status:" + status + " headers:" + headers + " config:" + config);
                    if (status === 204) {
                        myUtilService.showSuccessMsg("Success: Record added successfully");
                    } else {
                        rowform.$setError('productNames', "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error while saving Record");
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    rowform.$setError('productNames', "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return "error";//$q.reject('Server error!');
                });
            }

            return d.promice;
        };

        $scope.removeProduct = function (index, productNames) {
            $http.delete("web/product/" + $scope.products[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        $scope.products.splice(index, 1);
                    }).error(function (data, status, header, config) {
                //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                //$scope.rowform.$setError('productNames', "Error: Problem while deleting record");
                myUtilService.showErrorMsg("Error: Record delete failed");

                return "error";// $q.reject('Server error!')
            });
        };

        $scope.removeComponent = function (index, componentNames) {
            $http.delete("web/component/" + $scope.components[index].id)
                    .success(function (data, status, headers, config) {
                        myUtilService.showWarningMsg("Record Deleted.");
                        $scope.components.splice(index, 1);
                    }).error(function (data, status, header, config) {
                //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                $scope.rowform.$setError('productNames', "Error: Problem while deleting record");
                myUtilService.showErrorMsg("Error: Record delete failed");
                return "error";// $q.reject('Server error!')
            });
        };

        $scope.persistComponents = function (index, rowform) {
            var d = $q.defer();

            if ($scope.components[index].id >= 0) {
                $http.put("web/component/" + $scope.components[index].id, $scope.components[index])
                        .success(function (data, status, headers, config) {
                            myUtilService.showSuccessMsg("Success: Record edited successfully");
                        }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    rowform.$setError('componentName', "Error: Problem while editing record");
                    //myUtilService.showErrorMsg("Error: Record edit failed"); 

                    return "error";// $q.reject('Server error!');
                });
            } else {

                $http.post("web/component", $scope.components[index]).success(function (data, status, headers, config)
                {
                    //console.log("madhu:" + "data:" + data + " Status:" + status + " headers:" + headers + " config:" + config);
                    if (status === 204) {
                        myUtilService.showSuccessMsg("Success: Record added successfully");
                    } else {
                        rowform.$setError('componentName', "Error occurred while saving record: staus : " + status);
                        myUtilService.showErrorMsg("Error while saving Record");
                        return $q.reject("Error");
                    }
                }).error(function (data, status, header, config) {
                    //console.log("in Error:" + " Status:" + status + " headers:" + header + " config:" + config + "data:" + data);
                    rowform.$setError('componentName', "Error: Problem while adding record");
                    myUtilService.showErrorMsg("Error while saving Record");
                    return "error";//$q.reject('Server error!');
                });
            }

            return d.promice;
        };



        // editableOptions.theme = 'bs3';
        // editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
        // editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
    }
})();