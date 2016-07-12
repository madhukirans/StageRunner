/**
 * @author a.demeshko
 * created on 1/12/16
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.stage.stagerun', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('stage.stageRun', {
        url: '/stageRun',
        templateUrl: 'app/pages/stage/stageRun/stageRun.html',
          title: 'TriggerStage',
          sidebarMeta: {
            icon: 'ion-edit',
            order: 1
          }
      });
  }
})();