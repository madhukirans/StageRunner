/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addShiphomeNames')
      .directive('addShiphomeNames', addShiphomeNames);


  /** @ngInject */
  function addShiphomeNames() {
    return {
      restrict: 'E',
      controller: 'AddShiphomeNamesCtrl',
      templateUrl: 'app/pages/admin/shiphomeNames/addShiphomeNames.html'
    };
  }
})();


