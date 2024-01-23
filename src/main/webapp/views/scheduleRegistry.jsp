<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-lg-6 col h-100">
    <div class="card">
        <div class="card-header">
            Cadastro de Horário de Trabalho
        </div>
        <div class="card-body">

        <div class="form-check form-check"></div>
        <div class="form-check form-check mb-1"></div>

        <label class="swal2-label">Horário de Entrada</label>
        <input type="text" id="schedule-entryHour" class="form-control" placeholder="hh:mm">

        <label id="swal-label1" class="swal2-label">Horário de Saída</label>
        <input type="text" id="schedule-departureTime" class="form-control mb-3" placeholder="hh:mm">

        <a class="btn btn-primary" id="schedule-create">Inserir novo Horário de Trabalho</a>
        </div>
    </div>
</div>