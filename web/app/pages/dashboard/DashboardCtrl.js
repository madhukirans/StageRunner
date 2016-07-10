/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function ()
{
    'use strict';
    angular.module('BlurAdmin.pages.dashboard').
            controller('DashboardCtrl', DashboardCtrl);
    /** @ngInject */
    function DashboardCtrl($http, $scope, $element, $document, layoutPaths, baConfig)
    {
        var layoutColors = baConfig.colors;
        $scope.regress = [];
        var pieChartDataProvider = [];
        var productSet = [];

        var dataProvider2 = [
            {
                "product": "OTD",
                "value": 9
            },
            {
                "product": "WLS",
                "value": 10
            }
        ];

        $scope.pieChart = {};
        // $scope.getRegress = function () {
        $http.get("web/runregress").success(function (data) {
            $scope.regress = data;

            for (var x in $scope.regress) {
                if (productSet[$scope.regress[x].product.productName])
                    productSet[$scope.regress[x].product.productName] = productSet[$scope.regress[x].product.productName] + 1;
                else
                {
                    productSet[$scope.regress[x].product.productName] = 1;
                    $scope.stageName = "Stage :[" + $scope.regress[x].stageId.stageName + "] Release:[" + $scope.regress[x].stageId.releaseEntity.releaseName + "]";
                }
            }

            // var i = 0;
            for (var x in productSet) {
                var data = {
                    product: x,
                    value: productSet[x]
                };
                pieChartDataProvider.push(data);
            }

            $scope.pieChart = AmCharts.makeChart('pieChart', {
                "type": "pie",
                "theme": "default",
                "titles": [
                    {
                        "id": "pieChart",
                        "text": $scope.stageName
                    }
                ],
                "legend": {
                    position: 'right',
                    marginRight: 100,
                    autoMargins: false,
                },
                "defs": {
                    filter: [
                        {
                            id: 'shadow',
                            width: '200%',
                            height: '200%',
                            feOffset: {
                                result: 'offOut',
                                in: 'SourceAlpha',
                                dx: 0,
                                dy: 0
                            },
                            feGaussianBlur: {
                                result: 'blurOut',
                                in: 'offOut',
                                stdDeviation: 5
                            },
                            feBlend: {
                                in: 'SourceGraphic',
                                in2: 'blurOut',
                                mode: 'normal'
                            }
                        }
                    ]
                },
                "dataProvider": pieChartDataProvider,
                "valueField": "value",
                "titleField": "product",
                "outlineAlpha": 0.4,
                "depth3D": 15,
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "angle": 30,
                "export": {
                    "enabled": true
                }
            });

            $scope.pieChart.clickSlice = function (event) {
                console.log(event.title);
                var productName = event.title;
                var barChartDataProvider = [];
                for (var x in $scope.regress) {
                    //console.log($scope.regress[x].product.productName);
                    if ($scope.regress[x].product.productName === productName) {
                        console.log($scope.regress[x])
                        console.log($scope.regress[x].sucCount + ":" + $scope.regress[x].difCount);
                        var data = {
                            testunit: $scope.regress[x].testunitId.testUnitName,
                            testcounts: $scope.regress[x].sucCount? $scope.regress[x].sucCount : 1 + $scope.regress[x].difCount ? $scope.regress[x].difCount : 1,
                            bellonHelp: "TestUnit:[" + $scope.regress[x].testunitId.testUnitName + "] Number of Sucs:" + ($scope.regress[x].sucCount? $scope.regress[x].sucCount : 0) + 
                                    " Diffs:" + ($scope.regress[x].difCount? $scope.regress[x].difCount : 0),
                            color: ('#' + Math.floor(Math.random() * 16777215).toString(16))
                        };
                        barChartDataProvider.push(data);
                    }
                }
                
                $scope.barChart.dataProvider = barChartDataProvider;
                $scope.barChart.validateData();
                $scope.barChart.animateAgain();
            }

            $scope.pieChart.addListener('init', handleInit);
            $scope.pieChart.addListener('rollOverSlice', function (e)
            {
                handleRollOver(e);
            });
            function handleInit()
            {
                $scope.pieChart.legend.addListener('rollOverItem', handleRollOver);
            }

            function handleRollOver(e) {
                var wedge = e.dataItem.wedge.node;
                wedge.parentNode.appendChild(wedge);
            }
        });


        $scope.barChart = AmCharts.makeChart('barChart', {
            type: 'serial',
            theme: "light",
            startDuration: 2,
            color: layoutColors.defaultText,
            depth3D: 20,
            angle: 30,
            dataProvider: $scope.barChartData,
            valueAxes: [
                {
                    axisAlpha: 0,
                    position: 'left',
                    title: 'Number of Tests',
                    gridAlpha: 0.5,
                    gridColor: layoutColors.border,
                }
            ],
            graphs: [
                {
                    balloonText: '<b>[[bellonHelp]]</b>',
                    fillColorsField: 'color',
                    fillAlphas: 0.7,
                    lineAlpha: 0.2,
                    type: 'column',
                    valueField: 'testcounts'
                }
            ],
            chartCursor: {
                categoryBalloonEnabled: false,
                cursorAlpha: 0,
                zoomable: false
            },
            categoryField: 'testunit',
            categoryAxis: {
                gridPosition: 'start',
                labelRotation: 45,
                gridAlpha: 0.5,
                gridColor: layoutColors.border,
            },
            export: {
                enabled: true
            },
            creditsPosition: 'top-right',
            pathToImages: layoutPaths.images.amChart
        });


    }//end of controller
})();

