/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

   var app =  angular.module('BlurAdmin.pages.admin.addTestUnits');
            app.directive('addTestUnits', addTestUnits);

    /** @ngInject */
    function addTestUnits() {
        return {
            restrict: 'E',
            controller: 'AddTestUnitsCtrl',
            templateUrl: 'app/pages/admin/addStages/addTestUnits.html'
        };
    }
    

})();


