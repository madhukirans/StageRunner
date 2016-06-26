/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.admin', ['BlurAdmin.pages.admin.addreleases'])//, 'BlurAdmin.pages.admin.AddStages'])
            .config(routeConfig);
    
     /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
                .state('admin', {
                    url: '/admin',
                    templateUrl: 'app/pages/admin/admin.html',
                    title: 'Admin',
                    sidebarMeta: {
                        icon: 'ion-settings',
                        order: 3
                    }
                });
    }
    
   /** @ngInject */
//    function routeConfig($stateProvider) {
//        $stateProvider
//                .state('admin', {
//                    url: '/admin',
//                    template: '<ui-view></ui-view>',
//                    abstract: true,
//                    title: 'Admin',
//                    sidebarMeta: {
//                        icon: 'ion-settings',
//                        order: 3
//                    }
//                });
//    }

})();
