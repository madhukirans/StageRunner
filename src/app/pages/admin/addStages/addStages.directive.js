/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addStages')
      .directive('addStages', addStages);


  /** @ngInject */
  function addStages() {
    return {
      restrict: 'E',
      controller: 'AddStagesCtrl',
      templateUrl: 'app/pages/admin/addStages/addStages.html'
    };
  }
})();


