/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addStages', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('admin.AddStages', {
        url: '/AddStages',
        templateUrl: 'app/pages/admin/addStages/addStages.html',
          title: 'AddStages',
          sidebarMeta: {
            icon: 'ion-ios-pulse',
            order: 2
          }
      });
  }
  
})();
