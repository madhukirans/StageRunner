/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addProductsComponents')
      .directive('addProductsComponents', addProductsComponents);


  /** @ngInject */
  function addProductsComponents() {
    return {
      restrict: 'E',
      controller: 'AddProductsComponentsCtrl',
      templateUrl: 'app/pages/admin/addProductsComponents/addProductsComponents.html'
    };
  }
})();


