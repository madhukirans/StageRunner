/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';
    var app = angular.module('BlurAdmin.pages', [
        'BlurAdmin.pages.dashboard',
        'BlurAdmin.pages.stage',
        'BlurAdmin.pages.stage.stagerun',
        'BlurAdmin.pages.shiphomes',
        'BlurAdmin.pages.shiphomes.announcement',
        'BlurAdmin.pages.shiphomes.jsonurls',
        'BlurAdmin.pages.admin',
        'BlurAdmin.pages.admin.addreleases',
        'BlurAdmin.pages.admin.addProductsComponents',
        'BlurAdmin.pages.admin.addStages',
        'BlurAdmin.pages.admin.addTestUnits',
        'BlurAdmin.pages.help',        
        'BlurAdmin.pages.about',
    ]).config(routeConfig);
    
    app.directive('colResizeable', colResizeable);
//    app.directive('selectpicker', selectpicker);
    
//    
//    /** @ngInject */
//    function selectpicker() {
//        return {
//            restrict: 'A',
//            require: '?ngOptions',
//            priority: 1500, // make priority bigger than ngOptions and ngRepeat
//            link: {
//                pre: function (scope, elem, attrs) {
//                    elem.append('<option data-hidden="true" disabled value="">' + (attrs.title || 'Select something') + '</option>')
//                },
//                post: function (scope, elem, attrs) {
//                    function refresh() {
//                        elem.selectpicker('refresh');
//                    }
//
//                    if (attrs.ngModel) {
//                        scope.$watch(attrs.ngModel, refresh);
//                    }
//
//                    if (attrs.ngDisabled) {
//                        scope.$watch(attrs.ngDisabled, refresh);
//                    }
//
//                    elem.selectpicker({dropupAuto: false, hideDisabled: true});
//                }
//            }
//        };
//    }

    
    function colResizeable() {
        return {
            restrict: 'A',
            link: function (scope, elem) {
                setTimeout(function () {
                    elem.colResizable({
                        resizeMode:'overflow',
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
    
    app.factory('UtilFactory', function ($http) {
        return { 
            
            getAllReleasesFactory: function () {
                return $http.get('web/releases');
            },
            
            getAllProductsFactory: function () {
                return $http.get('web/product');
            },
            
            getAllComponentsFactory: function () {
                return $http.get('web/component');
            },
            
            getTestUnitsByProductFactory: function (selectedProduct) {
                return $http.get("web/testunit/product/" + selectedProduct);
            }
        };
    });

    app.service('myUtilService', function ($http, toastr) {
        this.showSuccessMsg = function (msg) {
            toastr.success(msg);
        };
        this.showInfoMsg = function (msg) {
            toastr.info(msg, 'Information');
        };
        this.showErrorMsg = function (msg) {
            toastr.error(msg, 'Error');
        };
        this.showWarningMsg = function (msg) {
            toastr.warning(msg, 'Warning');
        };

    });
    /** @ngInject */
    function routeConfig($urlRouterProvider, baSidebarServiceProvider) {
        $urlRouterProvider.otherwise('/dashboard');
//    baSidebarServiceProvider.addStaticItem({
//      title: 'Pages',
//      icon: 'ion-document',
//      subMenu: [{
//        title: 'Sign In',
//        fixedHref: 'auth.html',
//        blank: true
//      }, {
//        title: 'Sign Up',
//        fixedHref: 'reg.html',
//        blank: true
//      }, {
//        title: 'User Profile',
//        stateRef: 'profile'
//      }, {
//        title: '404 Page',
//        fixedHref: '404.html',
//        blank: true
//      }]
//    });
//    baSidebarServiceProvider.addStaticItem({
//      title: 'Menu Level 1',
//      icon: 'ion-ios-more',
//      subMenu: [{
//        title: 'Menu Level 1.1',
//        disabled: true
//      }, {
//        title: 'Menu Level 1.2',
//        subMenu: [{
//          title: 'Menu Level 1.2.1',
//          disabled: true
//        }]
//      }]
//    });
    }

})();
