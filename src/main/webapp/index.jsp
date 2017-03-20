<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="request" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="Atech">
<head>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Desafio Atech</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
<script src="${contextPath}/assets/js/atech.js"></script>

<!-- Bootstrap Styles-->
<link href="${contextPath}/assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="${contextPath}/assets/css/font-awesome.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="${contextPath}/assets/css/custom-styles.css"
	rel="stylesheet" />
<!-- Google Fonts-->
<link href="${contextPath}/assets/css/jquery-ui.css" rel='stylesheet'
	type='text/css' />

<link href="${contextPath}/assets/css/featherlight.css" type="text/css"
	rel="stylesheet" />

<script src="${contextPath}/assets/js/jquery-1.10.2.js"></script>

<script src="${contextPath}/assets/js/jquery-ui.js"></script>

<script src="${contextPath}/assets/js/jquery.maskedinput.js"></script>

<script
	src="${contextPath}/assets/js/progressbutton/modernizr.custom.js"></script>

<script src="${contextPath}/assets/js/featherlight.js"
	type="text/javascript" charset="utf-8"></script>


</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-header">
							Notas Fiscais <small>lista</small>
						</h1>
					</div>
				</div>
				<!-- /. ROW  -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">Ficha do produto</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-12">
										<form>

											<div class="panel-body">
												<div class="table-responsive">
													<hr>

													<table ng-controller="InvoiceController"
														class="table table-striped table-bordered table-hover table-clickable"
														id="dataTables">
														<thead>
															<tr>
																<th>Número da Nota</th>
																<th>Cnpj Emissor</th>
																<th>Produtos</th>
															</tr>
														</thead>
														<tbody>

															<tr ng-repeat="invoice in invoices">
																<td>{{invoice.numInvoice}}</td>
																<td>{{invoice.numCnpj}}</td>
																<td>
																	<div ng-repeat="product in invoice.invoiceProducts">
																		{{product.nameProduct}}</div>
																</td>
															</tr>

														</tbody>
													</table>

												</div>
											</div>
										</form>
									</div>
								</div>
								<!-- /.row (nested) -->
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>