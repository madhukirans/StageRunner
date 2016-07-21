/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

   var app =  angular.module('BlurAdmin.pages.admin.addTestUnits');
            app.directive('addTestUnits', addTestUnits);
            app.directive('colResizeable', colResizeable);


    /** @ngInject */
    function addTestUnits() {
        return {
            restrict: 'E',
            controller: 'AddTestUnitsCtrl',
            templateUrl: 'app/pages/admin/addStages/addTestUnits.html'
        };
    }

    /** @ngInject */
    function selectpicker() {
        return {
            restrict: 'A',
            require: '?ngOptions',
            priority: 1500, // make priority bigger than ngOptions and ngRepeat
            link: {
                pre: function (scope, elem, attrs) {
                    elem.append('<option data-hidden="true" disabled value="">' + (attrs.title || 'Select something') + '</option>')
                },
                post: function (scope, elem, attrs) {
                    function refresh() {
                        elem.selectpicker('refresh');
                    }

                    if (attrs.ngModel) {
                        scope.$watch(attrs.ngModel, refresh);
                    }

                    if (attrs.ngDisabled) {
                        scope.$watch(attrs.ngDisabled, refresh);
                    }

                    elem.selectpicker({dropupAuto: false, hideDisabled: true});
                }
            }
        };
    }



    function colResizeable() {
        return {
            restrict: 'A',
            link: function (scope, elem) {
                setTimeout(function () {
                    elem.colResizable({
                        liveDrag: true,                        
                        gripInnerHtml: "<div class='grip'></div>",
                        draggingClass: "dragging",
                        onDrag: function () {
                            //trigger a resize event, so width dependent stuff will be updated
                            //$(window).trigger('resize');
                        }
                    });
                });
            }
        };
    }

})();


