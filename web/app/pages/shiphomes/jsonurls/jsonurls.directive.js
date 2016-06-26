/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.shiphomes.jsonurls')
      .directive('jsonurls', jsonUrls);


  /** @ngInject */
  function jsonUrls() {
    return {
      restrict: 'E',
      controller: 'JsonUrlsCtrl',
      templateUrl: 'app/pages/shiphomes/jsonurls/jsonurls.html'
    };
  }
})();


