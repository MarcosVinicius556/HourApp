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
            didOpen: () => {
                $('#schedule-entryHour').inputmask('99:99', { placeholder: '__:__' });
                $('#schedule-departureTime').inputmask('99:99', { placeholder: '__:__' });
            },
            preConfirm: () => {
              let description = $('#schedule-description').val();
              let entryHour = $('#schedule-entryHour').val();
              let departureTime = $('#schedule-departureTime').val();
              
              if(!validateHour(entryHour)){ return false; }
              if(!validateHour(departureTime)){ return false; }

              let data = {
                description,
                entryHour,
                departureTime
              };

              return data;
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then(async (result) => {
            if(result.isConfirmed){
                await fetch('WorkSchedules', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(result.value)
                }).then(response => {
                    console.log(response)
                        if (!response.ok) {
                            response.text()
                                    .then((result) => {
                                        Swal.fire({
                                            title: "Atenção!",
                                            text: `Não foi possível salvar o registro. Motivo: ${result}`,
                                            icon: "error"
                                          });
                                    });
                            return;
                        }

                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Adicionado.",
                            icon: "success",
                            showConfirmButton: false,
                            timer: 1000
                          });
    
                        findAllSchedule();
                }).catch((error) => {
                    Swal.fire({
                        title: "Atenção!",
                        text: `Não foi possível salvar o registro. Motivo: ${error}`,
                        icon: "error"
                      });
                    console.log(error);
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
        let newHtml = '';
        await fetch('WorkSchedules', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.status}`);
                }
                return response.json();
        }).then(data => {
            if (!Array.isArray(data) || data.length === 0) {
                Toast.fire({
                    title: 'Atenção',
                    text: 'Nenhum registro de horário de trabalho encontrado!',
                    icon: 'warning'
                });

                $('#schedule-table').html(newHtml);

                return;
            }
            
            data.map((workSchedule) => {
                newHtml += `
                <tr>
                    <td>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="${workSchedule.scheduleId}">
                            <label class="form-check-label">
                                <strong>${workSchedule.scheduleId}</strong>
                            </label>
                        </div>
                    </td>
                    <td>${workSchedule.description}</td>
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
                `;
            });

            $('#schedule-table').html(newHtml);
        
        }).catch(error => {
            console.error(`Erro na requisição: ${error.message}`);
            Swal.fire({
                title: "Atenção!",
                text: `Não foi possível buscar os registros de Horários. Motivo: ${error.message}`,
                icon: "error"
            });
        });
    }

    const findScheduleById = async (id) => {
        let obj = null;
        await fetch(`WorkSchedules?action=byId&scheduleId=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.status}`);
                }
                return response.json();
        }).then(data => {
            obj = {
                scheduleId: data.scheduleId,
                description: data.description,
                entryHour: data.entryHour,
                departureTime: data.departureTime
            };
        }).catch((error) => {
            console.log(error);
            Toast.fire({
                icon: 'error',
                text: `Não foi possível buscar o Horário escolhido. Motivo: ${error}`
            });
        });

        return obj;
    }

    const updateSchedule = async () => {
        let id = $('#schedule-update').data('id');
        let old = await findScheduleById(id);

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
            didOpen: () => {
                $('#schedule-entryHour').inputmask('99:99', { placeholder: '__:__' });
                $('#schedule-departureTime').inputmask('99:99', { placeholder: '__:__' });
            },
            preConfirm: () => {
              let description = $('#schedule-description').val();
              let entryHour = $('#schedule-entryHour').val();
              let departureTime = $('#schedule-departureTime').val();
              
              if(!validateHour(entryHour)){ return false; }
              if(!validateHour(departureTime)){ return false; }

              let data = {
                scheduleId: id,
                description,
                entryHour,
                departureTime
              };
              return data;
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then(async (result) => {
            if(result.isConfirmed){
                await fetch('WorkSchedules', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(result.value)
                }).then(response => {
                        if (!response.ok) {
                            throw new Error(`Erro na requisição: ${response.status}`);
                        }
                        
                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Atualizado.",
                            icon: "success",
                            showConfirmButton: false,
                            timer: 1000
                        });

                        findAllSchedule();
                }).catch((error) => {
                    Swal.fire({
                        title: "Atenção!",
                        text: `Não foi possível atualizar o registro. Motivo: ${error}`,
                        icon: "error"
                      });
                    console.log(error);
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
                await fetch(`WorkSchedules?scheduleId=${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then((response) => {
                    if (!response.ok) {
                        response.text()
                                .then((result) => {
                                    Swal.fire({
                                        title: "Atenção!",
                                        text: `Não foi possível remover o registro. Motivo: ${result}`,
                                        icon: "error"
                                      });
                                });
                        return;
                    }

                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Removido.",
                            icon: "success",
                            showConfirmButton: false,
                            timer: 1000
                        });

                        findAllSchedule();
                }).catch((error) => {
                    Swal.fire({
                        title: "Atenção!",
                        text: `Não foi possível remover o registro. Motivo: ${error}`,
                        icon: "error"
                    });
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
    $(document).on('click', '#schedule-create', createSchedule);
    $(document).on('click', '#schedule-update', updateSchedule);
    $(document).on('click', '#schedule-delete', deleteSchedule);

    /**
     * Ao iniciar busca todos e lista na tabela de horários
     */
    findAllSchedule();
});



