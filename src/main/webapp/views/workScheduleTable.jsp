<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="col-lg-6 col mb-4">
    <div class="card mb-5">
        <div class="card-header">
            Cadastro de Horário de Trabalho
          </div>
        <div class="card-body">
          <label class="swal2-label">Horário de Entrada</label>
          <input type="text" id="schedule-entryHour" class="form-control" placeholder="hh:mm">
    
          <label id="swal-label1" class="swal2-label">Horário de Saída</label>
          <input type="text" id="schedule-departureTime" class="form-control mb-3" placeholder="hh:mm">
    
          <a class="btn btn-primary" id="schedule-create">Inserir novo Horário de Trabalho</a>
        </div>
    </div>
    <div class="card card-table h-100">
        <div class="card-header">
            <h5 class="card-heading">Horários de Trabalho</h5>
        </div>
        <div class="card-body">
            <div class="table-responsive"  style="min-height: 500px;max-height: 500px;">
                <table class="table table-borderless table-hover mb-0">
                    <thead class="light">
                        <tr>
                            <th>Incluir no Calc.</th>
                            <th>Entrada</th>
                            <th>Saída</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle" id="schedule-table"></tbody>
                </table>
            </div>
        </div>
        <div class="card-footer text-end">
        </div>
    </div>
</div>