<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <title>HourControll</title>
	    <meta name="description" content="Sistema para controle de horas">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="robots" content="noindex">
		<jsp:include page="./assets/imports/StylesImports.jsp"></jsp:include>
    </head>
  	<body>
		<!-- navbar -->
		<jsp:include page="./views/static/navbar.jsp"></jsp:include>
		<!-- /navbar -->

		<div class="page-holder bg-gray-100">
			<div class="container-fluid px-lg-4 px-xl-5">
			  <section class="mb-3 mb-lg-5">
	
				<div class="row mb-3">
					<!-- <CARDS> -->
						<jsp:include page="./views/cards.jsp"></jsp:include>
					<!-- </ CARDS> -->
				</div>	
				
				<div class="row mb-3">
					<!-- <Horários de Trabalho>-->
						<jsp:include page="./views/workScheduleTable.jsp"></jsp:include>
					<!-- </ Horários de Trabalho> -->
					
					<!-- <Marcações de Horário>-->
						<jsp:include page="./views/hourMarkerTable.jsp"></jsp:include>
					<!-- </Marcações de Horário>-->
				</div>
	
				<div class="row mb-3">
					<!-- Cálculo de Atraso e Hora Extra-->
					<jsp:include page="./views/hourCalculator.jsp"></jsp:include>
					<!-- /Cálculo de Atraso e Hora Extra-->
				</div>

				<div class="row mb-3">
					
					<!-- Registro de Horas de Atraso -->
					<jsp:include page="./views/summaryLateHoursTable.jsp"></jsp:include>
					<!-- /Registro de Horas de Atraso -->

					<!-- Registro de Horas de Extra -->
					<jsp:include page="./views/summaryExtraHoursTable.jsp"></jsp:include>
					<!-- /Registro de Horas de Extra -->
				</div>
				
			  </section>
			</div>
			<!-- Footer -->
			<jsp:include page="./views/static/footer.jsp"></jsp:include>
			<!-- /Footer -->
		  </div>
		  <!-- Script files -->
		  <jsp:include page="./assets/imports/JavaScriptImports.jsp"></jsp:include>
		  <!-- /Script files -->
	</body>
</html>