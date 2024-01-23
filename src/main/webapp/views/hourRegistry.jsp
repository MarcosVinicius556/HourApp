<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-6 col">
    <div class="card">
        <div class="card-header">
            Cadastro de Marcações
        </div>
        <div class="card-body">
            <div class="form-check form-check">
                <input class="form-check-input" type="radio" name="calculator-mode" value="1" checked="true">
                <label class="form-check-label" for="mode-calculator">Calcular Marcação - Hora de Trabalho</label>
            </div>
              
            <div class="form-check form-check mb-1">
                <input class="form-check-input" type="radio" name="calculator-mode" value="2">
                <label class="form-check-label" for="mode-calculator">Calcular Hora de Trabalho - Marcação</label>
            </div>

            <label class="swal2-label">Horário de Entrada</label>
            <input type="text" id="marker-entryHour" class="form-control" placeholder="hh:mm">

            <label id="swal-label1" class="swal2-label">Horário de Saída</label>
            <input type="text" id="marker-departureTime" class="form-control mb-3" placeholder="hh:mm">

            <a class="btn btn-primary" id="marker-create">Inserir novo Horário de Trabalho</a>
        </div>
    </div>
</div>