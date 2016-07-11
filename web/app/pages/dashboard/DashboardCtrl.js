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
        //var layoutColors = baConfig.colors;
        $scope.regress = [];
        var pieChartDataProvider = [];
        var productSet = [];
        $scope.pieChart = {};
        $scope.barChart ={};
       
        var barChartDataProvider = [];

        $scope.stages = [];
        $http.get("web/stages").success(function (data) {
            $scope.stages = data;
        });


        $scope.updateChart = function () {
            var url = "web/runregress";
            
            if ($scope.selectedStage)
                url = url + "/stage/" + $scope.selectedStage;
            
            $scope.regress = [];
            pieChartDataProvider = [];
            productSet=[];
            barChartDataProvider=[];
            
            
            
            $http.get(url).success(function (data) {
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

                $scope.pieChart.dataProvider = pieChartDataProvider;
                $scope.pieChart.titles[0].text = $scope.stageName;
                $scope.pieChart.validateData();
                $scope.pieChart.animateAgain();
                
                $scope.barChart.dataProvider = [];
                $scope.barChart.validateData();
                $scope.barChart.animateAgain();
            });
        }

        $scope.updateChart();

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
            for (var x in $scope.regress) {
                //console.log($scope.regress[x].product.productName);
                if ($scope.regress[x].product.productName === productName) {
                    console.log($scope.regress[x])
                    console.log($scope.regress[x].sucCount + ":" + $scope.regress[x].difCount);
                    var data = {
                        testunit: $scope.regress[x].testunitId.testUnitName,
                        testsucs: $scope.regress[x].sucCount ? $scope.regress[x].sucCount : 0,
                        testdiffs: $scope.regress[x].difCount ? $scope.regress[x].difCount : 0,
                        bellonSuc: "TestUnit:[" + $scope.regress[x].testunitId.testUnitName + "] Sucs:" + ($scope.regress[x].sucCount ? $scope.regress[x].sucCount : 0),
                        bellonDif: "TestUnit:[" + $scope.regress[x].testunitId.testUnitName + "] Diffs:" + ($scope.regress[x].difCount ? $scope.regress[x].difCount : 0),
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


        $scope.barChart = AmCharts.makeChart('barChart', {
            
            type: 'serial',
            theme: "light",
           // color: layoutColors.defaultText,
            depth3D: 30,
            angle: 30,
            categoryField: 'testunit',
            startDuration: 1,
            dataProvider: $scope.barChartData,
            "trendLines": [],
            "valueAxes": [
                {
                    "id": "ValueAxis-1",
                    "stackType": "regular",
                    "title": "Number of Tests"
                }
            ],
            graphs: [
                {
                    balloonText: '<b>[[bellonSuc]]</b>',
                    //"fillColors": "#b9cdf5",
                    "fillAlphas": 1,
                    "id": "AmGraph-1",
                    "title": "graph 1",
                    "type": "column",
                    "valueField": "testsucs"
                },
                {
                    "balloonText": '<b>[[bellonDif]]</b>',
                    //"fillColors": "#FF0000",
                    "fillAlphas": 1,
                    "id": "AmGraph-2",
                    "title": "graph 2",
                    "type": "column",
                    "valueField": "testdiffs"
                }
            ],
            chartCursor: {
                categoryBalloonEnabled: false,
                //cursorAlpha: 0,
                //zoomable: false
            },
            categoryAxis: {
                gridPosition: 'start',
                //labelRotation: 45,
                //gridAlpha: 0.5,
                //gridColor: layoutColors.border,
            },
            export: {
                enabled: true
            }
            ,
            creditsPosition: 'top-right',
            //pathToImages: layoutPaths.images.amChart
        });
    }//end of controller
})();

