/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage', ['BlurAdmin.pages.stage.stagerun'])
            .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
                .state('stage', {
                    url: '/stage',
                    template: '<ui-view></ui-view>',
                    abstract: true,
                    title: 'StageRun',
                    sidebarMeta: {
                        icon: 'ion-android-bicycle',
                        order: 1
                    }
                });
    }

})();
