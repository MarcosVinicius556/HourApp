<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-lg-6 mb-4">
    <div class="card card-table h-100">
      <div class="card-header">
        <h5 class="card-heading">Marcações</h5>
      </div>
      <div class="card-body">
        <div class="table-responsive">
          
          <table class="table table-borderless table-hover mb-0">
            <thead class="light">
              <tr>
                <th>#</th>
                <th>Horário de Trabalho</th>
                <th>Entrada</th>
                <th>Saída</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody class="align-middle">
                <tr>
                    <td>1</td>
                    <td>1° Turno</td>
                    <td>08:10</td>
                    <td>11:50</td>
                    <td>
                        <a class="me-3 text-lg text-success" href="#!">
                            <i class="far fa-edit"></i>
                        </a>
                        <a class="text-lg text-danger" href="#!">
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </td>
                </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="card-footer text-end">
        <a class="btn btn-primary" href="#!">Incluir marcação</a>
        <a class="btn btn-secondary" href="#!">Imprimir Relatório de Marcações</a>
      </div>
    </div>
</div>