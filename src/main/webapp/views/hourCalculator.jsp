<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-lg-12 col mb-4">
    <div class="card text-center">
        <div class="card-body" id="hour-calculator-container">
          <h5 class="card-title">Cálculadora de Horas</h5>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="calculator-mode" value="1" checked="true">
            <label class="form-check-label" for="mode-calculator">Calcular Marcação - Hora de Trabalho</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="calculator-mode" value="2">
            <label class="form-check-label" for="mode-calculator">Calcular Hora de Trabalho - Marcação</label>
          </div>
          <br />
          <a class="btn btn-primary mp-4" id="hour-calculator">Calcular</a>
        </div>
    </div>
</div>