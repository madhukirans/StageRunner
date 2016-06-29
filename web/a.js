/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('myApp',['smart-table']);
app.controller('paginationCtrl', ['$scope', '$http', function (scope, http) {
   var ctrl = this;

  this.displayed = [];

  this.callServer = function callServer(tableState) {

    ctrl.isLoading = true;

    var pagination = tableState.pagination;

    var start = pagination.start || 0;     // This is NOT the page number, but the index of item in the list that you want to use to display the table.
    var number = pagination.number || 5;  // Number of entries showed per page.
    
       http.get("web/releases/" + start + "/" + (start+5)).success(function (data) {
            ctrl.displayed = data;
            tableState.pagination.numberOfPages = data.numberOfPages;//set the number of pages so the pagination can update
            
            ctrl.isLoading = false;
        });
    };
}]);

