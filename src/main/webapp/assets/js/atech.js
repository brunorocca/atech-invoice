angular.module('Atech', [])
.controller('InvoiceController', function($scope, $http) {
    $http.get('http://localhost:8080/atech-invoice/invoice/list.do').
        then(function(response) {
            $scope.invoices = response.data;
        });
});