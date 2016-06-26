/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.AddReleases', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('admin.AddReleases', {
        url: '/AddReleases',
        templateUrl: 'app/pages/admin/AddReleases/AddReleases.html',
          title: 'AddReleases',
          sidebarMeta: {
            icon: 'ion-ios-pulse',
            order: 1
          }
      });
  }
  
})();
