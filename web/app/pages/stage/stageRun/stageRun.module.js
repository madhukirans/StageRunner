/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.shiphomes.announcement', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('shiphomes.announcement', {
        url: '/shiphomeAnnouncement',
        templateUrl: 'app/pages/shiphomes/shiphomeAnnouncement/shiphomeAnnouncement.html',
          title: 'Announcement',
          sidebarMeta: {
            icon: 'ion-edit',
            order: 1
          }
      });
  }
})();