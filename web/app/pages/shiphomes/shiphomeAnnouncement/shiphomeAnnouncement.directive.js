/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.shiphomes.announcement')
      .directive('shiphomeAnnouncement', shiphomeAnnouncement);


  /** @ngInject */
  function shiphomeAnnouncement() {
    return {
      restrict: 'E',
      controller: 'ShiphomeAnnouncementCtrl',
      templateUrl: 'app/pages/shiphomes/shiphomeAnnouncement/shiphomeAnnouncement.html'
    };
  }
})();


