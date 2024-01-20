$(() => {
  
    const createSchedule = async (e) => {
        await Swal.fire({
            title: "Novo Horário",
            html: `
            <label class="swal2-label">Descrição</label>
            <input id="schedule-description" class="swal2-input" type="textarea">
            
            <label class="swal2-label">Horário de Entrada</label>
            <input id="schedule-entryHour" class="swal2-input">

            <label id="swal-label1" class="swal2-label">Horário de Saída</label>
            <input id="schedule-departureTime" id="swal-input2" class="swal2-input">
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            preConfirm: () => {
              let description = $('#schedule-description').val();
              let entryHour = $('#schedule-entryHour').val();
              let departureTime = $('#schedule-departureTime').val();
              
              let data = {
                description,
                entryHour,
                departureTime
              };

              return data;
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if(result.isConfirmed){
                $ajax({
                    type: 'POST',
                    data: JSON.stringify(data),
                    url: 'WorkSchedule',
                    success: () => {
                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Atualizado.",
                            icon: "success"
                          });
                    },
                    error: (error) => {
                        Swal.fire({
                            title: "Atenção!",
                            text: `Não foi possível salvar o registro. Motivo: ${error}`,
                            icon: "success"
                          });
                        console.log(error);
                    }
                }); 
            } else {
                Toast.fire({
                    icon: "error",
                    title: "Operação Cancelada!"
                });
            }
        });
    }

    const findAllSchedule = async () => {
        await $.ajax({
                type: 'GET',
                url: 'WorkSchedules',
                success: (response) => {
                    if(response) {
                        response.map((workSchedule) => {
                            $('schedule-table').append(`
                            <tr>
                                    <td>${workSchedule.scheduleId}</td>
                                    <td>${workSchedule.descritpion}</td>
                                    <td>${workSchedule.entryHour}</td>
                                    <td>${workSchedule.departureTime}</td>
                                    <td>
                                        <a class="btn me-3 text-lg text-success" id="schedule-update" data-id='${workSchedule.scheduleId}'>
                                            <i class="far fa-edit"></i>
                                        </a>
                                        <a class="text-lg text-danger" id="schedule-delete" data-id='${workSchedule.scheduleId}'>
                                            <i class="far fa-trash-alt"></i>
                                        </a>
                                    </td>
                            </tr>
                            `);
                        });
                    }
                },
                error: (error) => {
                    Swal.fire({
                        title: "Atenção!",
                        text: `Não foi possível buscar os registros de Horários. Motivo: ${error}`,
                        icon: "error"
                    });
                }
            });
    }

    const findScheduleById = async (id) => {
        $.ajax({
            type: 'GET',
            url: `WorkSchedules/action=byId&scheduleId=${id}`,
            success: (response) => { 
                return {
                    scheduleId: response.scheduleId,
                    description: response.description,
                    entryHour: response.entryHour,
                    departureTime: response.departureTime
                }; 
            },
            error: (error) => {
                console.log(error);
                Toast.fire({
                    icon: 'error',
                    text: `Não foi possível buscar o Horário escolhido. Motivo: ${error}`
                });
            }
        });
    }

    const updateSchedule = async () => {
        let id = $('#schedule-update').data('id');
        let old = findScheduleById(id);

        await Swal.fire({
            title: "Atualizar Horário",
            html: `
              <label class="swal2-label">Descrição</label>
              <input id="schedule-description" class="swal2-input" type="textarea" value='${old.description}'>
              
              <label class="swal2-label">Horário de Entrada</label>
              <input id="schedule-entryHour" class="swal2-input" value='${old.entryHour}'>

              <label id="swal-label1" class="swal2-label">Horário de Saída</label>
              <input id="schedule-departureTime" id="swal-input2" class="swal2-input" value='${old.departureTime}'>
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            preConfirm: () => {
              let description = $('#schedule-description').val();
              let entryHour = $('#schedule-entryHour').val();
              let departureTime = $('#schedule-departureTime').val();
              
              let data = {
                scheduleId: id,
                description,
                entryHour,
                departureTime
              };

              return data;
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if(result.isConfirmed){
                $ajax({
                    type: 'PUT',
                    data: JSON.stringify(data),
                    url: 'WorkSchedules',
                    success: () => {
                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Atualizado.",
                            icon: "success"
                          });
                    },
                    error: (error) => {
                        Swal.fire({
                            title: "Atenção!",
                            text: `Não foi possível atualizar o registro. Motivo: ${error}`,
                            icon: "success"
                          });
                        console.log(error);
                    }
                });
                    
            } else {
                Toast.fire({
                    icon: "error",
                    title: "Operação Cancelada!"
                });
            }
        });
    }

    const deleteSchedule = async () => {
        let id = $('#schedule-delete').data('id');

        Swal.fire({
            title: "Tem certeza?",
            text: "Você não poderá reverter esta ação!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Sim, Delete isso!"
        }).then(async (result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'DELETE',
                    url: `WorkSchedules/scheduleId=${id}`,
                    success: () => {
                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Removido.",
                            icon: "success"
                          });
                    },
                    
                    error: (error) => {
                        Swal.fire({
                            title: "Atenção!",
                            text: `Não foi possível remover o registro. Motivo: ${error}`,
                            icon: "error"
                          });
                    }
                });
            } else {
                Toast.fire({
                    icon: "error",
                    title: "Operação Cancelada!"
                });
            }
          });
    }

    /**
     * Atribuir eventos das funções
     */
    $('#schedule-create').on('click', createSchedule);
    $('#schedule-update').on('click', updateSchedule);
    $('#schedule-delete').on('click', deleteSchedule);

    /**
     * Ao iniciar busca todos e lista na tabela de horários
     */
    findAllSchedule();
});



