'use strict';

var appControllers = angular.module('appControllers', []);

appControllers.controller('InvoiceCtrl', ['$scope', '$routeParams', 'invoiceServices',
  function($scope, $routeParams, invoiceServices) {

	// Get all invoices
	invoiceServices.all(function(data) {
	  $scope.invoices = data.content;
	});

  }]
);
