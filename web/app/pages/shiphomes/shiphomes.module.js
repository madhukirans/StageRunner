/**
 * @author k.danovsky
 * created on 15.01.2016
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.shiphomes', ['BlurAdmin.pages.shiphomes.announcement', 'BlurAdmin.pages.shiphomes.jsonurls'])
            .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
                .state('shiphomes', {
                    url: '/shiphomes',
                    template: '<ui-view></ui-view>',
                    abstract: true,
                    title: 'Shiphomes',
                    sidebarMeta: {
                        icon: 'ion-gear-a',
                        order: 2
                    }
                });
    }
    
    

})();
