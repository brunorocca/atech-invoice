'use strict';

var appServices = angular.module('appServices', []);

appServices.factory('invoiceServices', ['$http',
  function($http) {
    return {
      all: function(success, error) {
        $http.get('/atech/invoices').success(success).error(error)
      }
    };
  }]
);
