/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.admin', ['BlurAdmin.pages.admin.AddReleases', 'BlurAdmin.pages.admin.AddReleases'])
            .config(routeConfig);

   /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
                .state('admin', {
                    url: '/admin',
                    template: '<ui-view></ui-view>',
                    abstract: true,
                    title: 'Admin',
                    sidebarMeta: {
                        icon: 'ion-settings',
                        order: 3
                    }
                });
    }

})();
