$(() => {
  
    const createMarker = async (e) => {
        /**
         * Buscar os horários de trabalho
         */
        let workSchedules = [{}];
        $ajax({
            type: 'GET',
            url: 'WorkSchedules',
            success: (response) => {
                workSchedules = response;
            }
        });
        await Swal.fire({
            title: "Nova Marcação",
            html: `
            <label class="swal2-label">Horário de Trabalho</label>
            <select class="swal2-select" id="marker-scheduleId">
            ${
                workSchedules.map((schedule) => {
                    return(`<option value="${schedule.id}">${schedule.scheduleId} - ${schedule.description}</option>`
                            );
                        })
            }
            </select>
            
            <label class="swal2-label">Horário de Entrada</label>
            <input id="marker-entryHour" class="swal2-input">

            <label id="swal-label1" class="swal2-label">Horário de Saída</label>
            <input id="marker-departureTime" id="swal-input2" class="swal2-input">
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            preConfirm: () => {
              let markerScheduleId = $('#marker-scheduleId').val();
              let entryHour = $('#marker-entryHour').val();
              let departureTime = $('#marker-departureTime').val();
              
              let data = {
                markerScheduleId,
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
                    url: 'HourMarkers',
                    success: () => {
                        Toast.fire({
                            icon: "success",
                            title: "Cadastro Realizado com sucesso!"
                        });
                    },
                    error: (error) => {
                        Toast.fire({
                            icon: "error",
                            title: "Ocorreu um erro ao salvar o registro!"
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

    const findAllMarkers = async () => {
        await $.ajax({
                type: 'GET',
                url: 'HourMarkers',
                success: (response) => {
                    if(response) {
                        response.map((hourMarker) => {
                            $('marker-table').append(`
                            <tr>
                                    <td>${hourMarker.markerId}</td>
                                    <td>${hourMarker.schedule.scheduleId} - ${hourMarker.schedule.description}</td>
                                    <td>${hourMarker.entryHour}</td>
                                    <td>${hourMarker.departureTime}</td>
                                    <td>
                                        <a class="btn me-3 text-lg text-success" id="schedule-update" data-id='${hourMarker.markerId}'>
                                            <i class="far fa-edit"></i>
                                        </a>
                                        <a class="text-lg text-danger" id="schedule-delete" data-id='${hourMarker.markerId}'>
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
                        text: `Não foi possível buscar as marcações de horário. Motivo: ${error}`,
                        icon: "error"
                    });
                }
            });
    }

    const findMarkerById = async (id) => {
        $.ajax({
            type: 'GET',
            url: `HourMarkers/action=byId&markerId=${id}`,
            success: (response) => { 
                return {
                    markerId: response.markerId,
                    schedule: response.schedule,
                    entryHour: response.entryHour,
                    departureTime: response.departureTime
                }; 
            },
            error: (error) => {
                console.log(error);
                Toast.fire({
                    icon: 'error',
                    text: `Não foi possível buscar a Marcação de horário escolhida. Motivo: ${error}`
                });
            }
        });
    }

    const updateMarker = async () => {
        let id = $('#schedule-update').data('id');
        let old = findMarkerById(id);

        let workSchedules = [{}];
        $ajax({
            type: 'GET',
            url: 'WorkSchedules',
            success: (response) => {
                workSchedules = response;
            }
        });
        await Swal.fire({
            title: "Atualizar Marcação",
            html: `
            <label class="swal2-label">Horário de Trabalho</label>
            <select class="swal2-select" id="marker-scheduleId">
            ${
                workSchedules.map((marker) => {
                    return(`<option value="${marker.id}">${marker.schedule.scheduleId} - ${marker.description}</option>`
                            );
                        })
            }
            </select>
            
            <label class="swal2-label">Horário de Entrada</label>
            <input id="marker-entryHour" class="swal2-input" value='${old.entryHour}'>

            <label id="swal-label1" class="swal2-label">Horário de Saída</label>
            <input id="marker-departureTime" id="swal-input2" class="swal2-input" value='${old.departureTime}'>
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            preConfirm: () => {
              let markerScheduleId = $('#marker-scheduleId').val();
              let entryHour = $('#marker-entryHour').val();
              let departureTime = $('#marker-departureTime').val();
              
              let data = {
                markerId: id,
                markerScheduleId,
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
                    url: 'HourMarkers',
                    success: () => {
                        Toast.fire({
                            icon: "success",
                            title: "Cadastro Realizado com sucesso!"
                        });
                    },
                    error: (error) => {
                        Toast.fire({
                            icon: "error",
                            title: "Ocorreu um erro ao salvar o registro!"
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

    const deleteMarker = async () => {
        let id = $('#marker-delete').data('id');

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
                    url: `HourMarkers/${id}`,
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
    $('#marker-create').on('click', createMarker);
    $('#marker-update').on('click', updateMarker);
    $('#marker-delete').on('click', deleteMarker);

    /**
     * Ao iniciar busca todos e lista na tabela de horários
     */
    findAllMarkers();
});



