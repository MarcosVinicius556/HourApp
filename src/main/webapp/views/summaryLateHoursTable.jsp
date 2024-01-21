<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-lg-6 mb-4">
    <div class="card card-table mb-4">
        <div class="card-header">
            <h5 class="card-heading"><strong>Registro de Horas de atraso</strong></h5>
        </div>
        <div class="card-body">
            <div class="table-responsive"  style="min-height: 500px;max-height: 500px;">
                <table class="table table-hover table-borderless align-middle mb-0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Período</th>
                            <th>Data do Registro</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle" id="summary-late-table"></tbody>
                </table>
            </div>
        </div>
        <div class="card-footer text-end">
            <a class="btn btn-secondary" href="#!">Imprimir Relatório de Atrasos</a>
        </div>
    </div>
</div>