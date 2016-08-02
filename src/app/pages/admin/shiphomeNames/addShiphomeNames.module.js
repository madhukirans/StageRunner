/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addShiphomeNames', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('admin.AddShiphomeNames', {
        url: '/AddShiphomeNames',
        templateUrl: 'app/pages/admin/shiphomeNames/addShiphomeNames.html',
          title: 'AddShiphomeNames',
          sidebarMeta: {
            icon: 'ion-ios-pulse',
            order: 2
          }
      });
  }
  
})();
