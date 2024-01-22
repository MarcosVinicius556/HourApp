$(() => {

    const showLoadingIndicator = () => {
        $('#loading-indicator').show();
        $('#page-content').hide();
    };

    const hideLoadingIndicator = () => {
        $('#loading-indicator').hide();
        $('#page-content').show();
    };

    const createSchedule = async (e) => {
            let entryHour = $('#schedule-entryHour').val();
            let departureTime = $('#schedule-departureTime').val();
            
            if(!validateHour(entryHour)){ return; }
            if(!validateHour(departureTime)){ return; }

            let data = {
                entryHour,
                departureTime
            };

            await fetch('WorkSchedules', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
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

                findAllSchedule();
        }).catch((error) => {
            console.log(error);
            Swal.fire({
                title: "Atenção!",
                text: `Não foi possível salvar o registro. Motivo: ${error}`,
                icon: "error"
            });
        }).finally(() => {
            $('#schedule-entryHour').val('');
            $('#schedule-departureTime').val('');
        }); 
    }

    const findAllSchedule = async () => {

        /**
         * Mostra o componente de loading até que tudo seja carregado
         */
        showLoadingIndicator();

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
            
            console.log(data)

            data.map((workSchedule) => {
                newHtml += `
                <tr>
                    <td>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="${workSchedule.scheduleId} checked='true' ">
                            <label class="form-check-label">
                                <strong>${workSchedule.scheduleId}</strong>
                            </label>
                        </div>
                    </td>
                    <td>${workSchedule.entryHour}</td>
                    <td>${workSchedule.departureTime}</td>
                    <td>
                        <a class="btn me-3 text-lg text-success schedule-update" data-id='${workSchedule.scheduleId}'>
                            <i class="far fa-edit"></i>
                        </a>
                        <a class="text-lg text-danger schedule-delete" data-id='${workSchedule.scheduleId}'>
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
        }).finally(() => {
            hideLoadingIndicator();
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

    const updateSchedule = async (e) => {
        let id = e.currentTarget.dataset.id;
        let old = await findScheduleById(id);


        await Swal.fire({
            title: "Atualizar Horário",
            html: `
              <label class="swal2-label">Horário de Entrada</label>
              <input id="schedule-update-entryHour" class="swal2-input" value='${old.entryHour}'>

              <label id="swal-label1" class="swal2-label">Horário de Saída</label>
              <input id="schedule-update-departureTime" id="swal-input2" class="swal2-input" value='${old.departureTime}'>
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            didOpen: () => {
                $('#schedule-update-entryHour').inputmask('99:99', { placeholder: '__:__' });
                $('#schedule-update-departureTime').inputmask('99:99', { placeholder: '__:__' });
            },
            preConfirm: () => {
              let entryHour = $('#schedule-update-entryHour').val();
              let departureTime = $('#schedule-update-departureTime').val();
              
              if(!validateHour(entryHour)){ return false; }
              if(!validateHour(departureTime)){ return false; }

              let data = {
                scheduleId: id,
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

    const deleteSchedule = async (e) => {
        let id = e.currentTarget.dataset.id;

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
    $(document).on('click', '.schedule-update', updateSchedule);
    $(document).on('click', '.schedule-delete', deleteSchedule);
    
    /**
     * Ao iniciar busca todos e lista na tabela de horários
     */
    findAllSchedule();

    $(document).ready(() => {
        console.log('Executando criação da máscaras...')
        $('#schedule-entryHour').inputmask('99:99', { placeholder: '__:__' });
        $('#schedule-departureTime').inputmask('99:99', { placeholder: '__:__' });
    });
});





