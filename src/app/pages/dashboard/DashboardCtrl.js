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
        var componentSet = [];
        $scope.pieChart = {};
        $scope.barChart = {};

        var barChartDataProvider = [];

        $scope.stages = [];
        $http.get("web/stage").success(function (data) {
            $scope.stages = data;
            //$scope.selectedStage = $scope.stages[$scope.stages.lenght].id;
            console.log($scope.stages);
        });


        $scope.updateChart = function () {
            var url = "web/regressdetails";

            if ($scope.selectedStage)
                url = url + "/stage/" + $scope.selectedStage;
            else
                url = url + "/recent";

            $scope.regress = [];
            pieChartDataProvider = [];
            componentSet = [];
            barChartDataProvider = [];


            $http.get(url).success(function (data) {
                $scope.regress = data;
                for (var x in $scope.regress) {
                    if (componentSet[$scope.regress[x].component.name])
                        componentSet[$scope.regress[x].component.name] = componentSet[$scope.regress[x].component.name] + 1;
                    else
                    {
                        componentSet[$scope.regress[x].component.name] = 1;
                        $scope.stageName = "Stage :[" + $scope.regress[x].stage.stageName + "] Release:[" +
                                $scope.regress[x].stage.release.name + "] \n Click on slice to get the testunits.";
                    }
                }

                // var i = 0;
                for (var x in componentSet) {
                    var data = {
                        component: x,
                        value: componentSet[x]
                    };
                    pieChartDataProvider.push(data);
                }

                $scope.pieChart.dataProvider = pieChartDataProvider;
                $scope.pieChart.titles[0].text = $scope.stageName;
                $scope.pieChart.validateData();
                $scope.pieChart.animateAgain();

                $scope.barChart.dataProvider = [];
                $scope.barChart.valueAxes[0].title = "Click on slice to get the TestUnits.";
                $scope.barChart.validateData();
                $scope.barChart.animateAgain();
            });
        }

        $scope.updateChart();

        $scope.pieChart = AmCharts.makeChart("pieChart", {
            "type": "pie",
            "theme": "blur",
            "valueField": "value",
            "titleField": "component",
            "outlineAlpha": 0.4,
            "depth3D": 15,
            "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
            "angle": 30,
            "export": {
                "enabled": true
            },
            "legend": {
                position: 'left',
                //marginRight: 100,
                autoMargins: true,
            },
            "titles": [
                {
                    "id": "pieChart",
                    "text": $scope.stageName
                }
            ]
        });

        $scope.pieChart.clickSlice = function (event) {
            console.log(event.title);
            var componentName = event.title;
            barChartDataProvider = [];
            for (var x in $scope.regress) {
                console.log("M:" + $scope.regress[x].component.name);
                if ($scope.regress[x].component.name === componentName) {
                    // console.log($scope.regress[x])
                    //  console.log($scope.regress[x].sucCount + ":" + $scope.regress[x].difCount);
                    var data = {
                        testunit: $scope.regress[x].testunit.testunitName,
                        testsucs: $scope.regress[x].sucCount ? $scope.regress[x].sucCount : 0,
                        testdiffs: $scope.regress[x].difCount ? $scope.regress[x].difCount : 0,
                        bellonSuc: "TestUnit:[" + $scope.regress[x].testunit.testUnitName + "] Sucs:" + ($scope.regress[x].sucCount ? $scope.regress[x].sucCount : 0),
                        bellonDif: "Diffs:" + ($scope.regress[x].difCount ? $scope.regress[x].difCount : 0),
                        color: ('#' + Math.floor(Math.random() * 16777215).toString(16))
                    }; 
                    barChartDataProvider.push(data);
                }
            }

            console.log(barChartDataProvider);
            
            $scope.barChart.valueAxes[0].title = "Number of Tests";
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
            theme: "blur",
            // color: layoutColors.defaultText,
            depth3D: 30,
            angle: 30,
            categoryField: 'testunit',
            startDuration: 1,
            dataProvider: barChartDataProvider,
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
                    "fixedColumnWidth": 15,
                    "valueField": "testsucs"
                },
                {
                    "balloonText": '<b>[[bellonDif]]</b>',
                    //"fillColors": "#FF0000",
                    "fillAlphas": 1,
                    "fixedColumnWidth": 15,
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
                "labelRotation": 60
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

