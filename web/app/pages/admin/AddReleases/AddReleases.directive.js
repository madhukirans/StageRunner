/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.AddReleases')
      .directive('AddReleases', Admin);


  /** @ngInject */
  function Admin() {
    return {
      restrict: 'E',
      controller: 'AddReleasesCtrl',
      templateUrl: 'app/pages/admin/AddReleases.html'
    };
  }
})();


