/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.shiphomes.jsonurls', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('shiphomes.jsonurls', {
        url: '/jsonurls',
        templateUrl: 'app/pages/shiphomes/jsonurls/jsonurls.html',
          title: 'JSonUrls',
          sidebarMeta: {
            icon: 'ion-ios-pulse',
            order: 2
          }
      });
  }
  
})();
