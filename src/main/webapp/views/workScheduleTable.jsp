<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-lg-6 col mb-4">
    <div class="card card-table h-100">
        <div class="card-header">
            <h5 class="card-heading">Horários de Trabalho</h5>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                
                <table class="table table-borderless table-hover mb-0">
                <thead class="light">
                    <tr>
                    <th># </th>
                    <th>Descrição</th>
                    <th>Entrada </th>
                    <th>Saída </th>
                    <th>Ações</th>
                    </tr>
                </thead>
                <tbody class="align-middle" id="workScheduleTable">
                    <tr>
                        <td>1</td>
                        <td>1° Turno</td>
                        <td>08:00</td>
                        <td>12:00</td>
                        <td>
                            <button type="button" class="btn me-3 text-lg text-success"  data-bs-toggle="modal" data-bs-target="#addWorkScheduleModal">
                                <i class="far fa-edit"></i>
                            </button>
                            <a class="text-lg text-danger" id="workScheduleDelete" >
                                <i class="far fa-trash-alt"></i>
                            </a>
                        </td>
                    </tr>
                </tbody>
                </table>
            </div>
        </div>
        <div class="card-footer text-end">
            <button class="btn btn-primary" id="addWorkSchedule">Incluir horário</button>
        </div>
    </div>
</div>