'use strict';

var app = angular.module('app', [
  'ngRoute',
  'appControllers',
  'appServices'
]);

app.config(['$routeProvider', '$locationProvider',
  function($routeProvider, $locationProvider) {

    $routeProvider.
      when('/invoices', {
        templateUrl: 'views/invoices.html',
        controller: 'InvoiceCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });

  }]
);
