/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.about', [])
            .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
    $stateProvider
        .state('about', {
          url: '/about',
          templateUrl: 'app/pages/about/about.html',
          title: 'About',
          sidebarMeta: {
            icon: 'ion-information-circled',
            order: 11,
          },
        });
  }

})();
