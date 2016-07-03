/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage.stagerun')
            .directive('stageRun', stageRun);


    /** @ngInject */
    function stageRun() {
        return {
            restrict: 'E',
            controller: 'StageRunCtrl',
            templateUrl: 'app/pages/stage/stageRun/stageRun.html'
        };
    }
})();


