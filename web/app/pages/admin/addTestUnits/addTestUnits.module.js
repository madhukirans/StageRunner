/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addTestUnits', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('admin.addTestUnits', {
        url: '/addTestUnits',
        templateUrl: 'app/pages/admin/addTestUnits/addTestUnits.html',
          title: 'AddTestUnits',
          sidebarMeta: {
            icon: 'ion-ios-pulse',
            order: 2
          }
      });
  }
  
})();
