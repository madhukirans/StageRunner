/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.stage', [])
            .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
                .state('stage', {
                    url: '/stage',
                    templateUrl: 'app/pages/stage/stage.html',
                    title: 'Stage',
                    sidebarMeta: {
                        icon: 'ion-android-home',
                        order: 1
                    }
                });
    }

})();
