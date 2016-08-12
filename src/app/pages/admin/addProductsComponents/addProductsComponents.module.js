/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addProductsComponents', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('admin.AddProductsComponents', {
        url: '/AddProductsComponents',
        templateUrl: 'app/pages/admin/addProductsComponents/addProductsComponents.html',
          title: 'AddProductsComponents',
          sidebarMeta: {
            icon: 'ion-ios-pulse',
            order: 10
          }
      });
  }
  
})();
