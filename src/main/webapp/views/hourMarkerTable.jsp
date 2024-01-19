<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-lg-6 col mb-4">
    <div class="card card-table h-100">
        <div class="card-header">
            <h5 class="card-heading">Marcações de Horário</h5>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-borderless table-hover mb-0">
                    <thead class="light">
                        <tr>
                            <th># </th>
                            <th>Horário de Trabalho</th>
                            <th>Entrada</th>
                            <th>Saída</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle" id="marker-table"></tbody>
                </table>
            </div>
        </div>
        <div class="card-footer text-end">
            <button class="btn btn-primary" id="marker-create">Incluir Marcação</button>
            <a class="btn btn-secondary" id="marker-print">Imprimir Relatório de Marcações</a>
        </div>
    </div>
</div>