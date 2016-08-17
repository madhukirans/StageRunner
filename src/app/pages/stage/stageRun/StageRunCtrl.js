/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage.stagerun').controller('StageRunCtrl', StageRunCtrl);



    function StageRunCtrl($scope, $http, $filter, myUtilService, UtilFactory, toastr) {
        //$scope.selectedProduct = "";
        //$scope.selectedTestUnit = "";

        $scope.components = [];
        $scope.testunits = [];
        $scope.releases = [];

        $http.get("web/releases").success(function (data) {
            $scope.releases = data;
        });

        $scope.getStages = function () {
            $scope.stages = [];
            $http.get("web/stage/release/" + $scope.selectedRelease).success(function (data) {
                $scope.stages = data;
            });
        };

        $scope.getProducts = function () {
            $scope.products = [];
            $http.get("web/shiphomes/stage/" + $scope.selectedStage + "/products").success(function (data) {
                $scope.products = data;
                console.log(data);
                $scope.loadTable();
            });
        };

        $scope.getComponents = function () {
            if ($scope.selectedProduct) {
                $http.get("web/component/product/" + $scope.selectedProduct).success(function (data) {
                    $scope.components = data;
                    $scope.loadTable();
                });
            }
        }

        $scope.getTestUnits = function () {

            if (!$scope.selectedProduct) {
                return;
            }

            var url = "web/testunit/release/" + $scope.selectedRelease + "/product/" + $scope.selectedProduct;

            if ($scope.selectedComponent)
                url = url + "/component/" + $scope.selectedComponent;

            $http.get(url).success(function (data) {
                $scope.testunits = data;
                $scope.loadTable();
            });
        };

        $scope.loadTable = function () {
            $scope.dataLoading  = true;
            console.log("Product :" + $scope.selectedProduct);
            console.log("Component :" + $scope.selectedComponent);
            console.log("Testunit :" + $scope.selectedTestUnit);

            $scope.regressdetails = [];
            var URL = 'web/regressdetails/stage/' + $scope.selectedStage;

            if ($scope.selectedProduct) {
                URL = URL + "/product/" + $scope.selectedProduct;
            }
            
            if ($scope.selectedComponent) {
                URL = URL + "/component/" + $scope.selectedComponent;
            }

            if ($scope.selectedTestUnit) {
                URL = URL + "/testunit/" + $scope.selectedTestUnit;
            }

            $http.get(URL).success(function (data) {
                $scope.regressdetails = data;                
            });
        };

        $scope.options = {
            autoDismiss: false,
            positionClass: 'toast-top-full-width',
            type: 'info',
            timeOut: '500000',
            extendedTimeOut: '2000',
            allowHtml: true,
            closeButton: false,
            tapToDismiss: false,
            progressBar: true,
            newestOnTop: false,
            maxOpened: 1,
            preventDuplicates: true,
            preventOpenDuplicates: true,
            title: "Please wait. ",
            msg: "Job submisstion is in progress..."
        };



        $scope.reRunTests = function (index) {
            console.log("Madhu" + $scope.regressdetails[index].stageId.id);

            var postdata = {
                stage: $scope.regressdetails[index].stageId,
                product: $scope.regressdetails[index].product,
                testunit: $scope.regressdetails[index].testunitId
            };
            $scope.dataLoading  = false;
            var URL = 'web/regressdetails';
            toastr.info($scope.options.title, $scope.options.msg, $scope.options);
            $http.post(URL, postdata).success(function (data, status, headers, config) {
                $scope.loadTable();
                toastr.clear();                
                $scope.dataLoading  = true;
            }).error(function (err) {
                toastr.clear();
            });
        }

        $scope.runStage = function () {
            var URL = 'web/regressdetails';///stage/' + $scope.selectedStage;

            var tempStage = $filter('filter')($scope.stages, {id: $scope.selectedStage});

            if (!$scope.selectedRelease || !$scope.selectedStage) {
                myUtilService.showErrorMsg("Select Release and Stage");
            }

            var tempProduct = {};
            var tempComponent = {};
            var tempTestunit = {};

            if ($scope.selectedProduct) {
                tempProduct = $filter('filter')($scope.products, {id: $scope.selectedProduct});
            }

            if ($scope.selectedComponent) {
                tempComponent = $filter('filter')($scope.components, {id: $scope.selectedComponent});
            }

            if ($scope.selectedTestUnit) {
                tempTestunit = $filter('filter')($scope.testunits, {id: $scope.selectedTestUnit});
            }

            var postdata = {
                stage: tempStage,
                product: tempProduct,
                component: tempComponent,
                testunit: tempTestunit
            };

            console.log(tempProduct);
            console.log(tempComponent);
            console.log(tempStage);
            console.log(tempTestunit);

            toastr.info($scope.options.title, $scope.options.msg, $scope.options);

            $http.post(URL, postdata).success(function (data, status, headers, config) {
                $scope.loadTable();
                toastr.clear();
            }).error(function (err) {
                toastr.clear();
            });

        };

        $scope.selectTestUnits = function () {
            console.log("Selected Product:" + $scope.selectedProduct);
        };
    }
    ;
})();

