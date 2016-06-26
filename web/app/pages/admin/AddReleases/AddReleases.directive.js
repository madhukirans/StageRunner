/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.admin.addreleases')
      .directive('addReleases', addReleases);


  /** @ngInject */
  function addReleases() {
    return {
      restrict: 'E',
      controller: 'AddReleasesCtrl',
      templateUrl: 'app/pages/admin/addReleases/addReleases.html'
    };
  }
})();


