$(() => {
    
    const showLoadingIndicator = () => {
        $('#loading-indicator').show();
        $('#page-content').hide();
    };

    const hideLoadingIndicator = () => {
        $('#loading-indicator').hide();
        $('#page-content').show();
    };

    const findAvailableSchedules = async () => {
        let availableSchedules;
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
        }).then(result => {
            availableSchedules = result;
        }).catch((error) => {
            Toast.fire({
                icon: 'error',
                title: 'Atenção',
                text: 'Erro ao buscar horários de trabalho'
            });
            console.log(error)
        });
        
        return availableSchedules;
    }

    const createMarker = async (e) => {
        /**
         * Buscar os horários de trabalho
         * Se não houver nenhum horário cadastrado não será possível calcular nada....
         */
        let workSchedules = await findAvailableSchedules();
        if(!Array.isArray(workSchedules) || workSchedules.length === 0){
            await Swal.fire({
                    title: "Atenção!",
                    text: `Não é possível cadastrar uma marcação sem ter pelo menos um horário cadastrado`,
                    icon: "error"
                }); 
            return;
        }


        let entryHour = $('#marker-entryHour').val();
        let departureTime = $('#marker-departureTime').val();
        
        if(!validateHour(entryHour)){ return false; }
        if(!validateHour(departureTime)){ return false; }

        let data = {
            entryHour,
            departureTime
        };

        await fetch('HourMarkers', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.status}`);
                }

                findAllMarkers();
        }).catch((error) => {
            Swal.fire({
                title: "Atenção!",
                text: `Não foi possível salvar o registro. Motivo: ${error}`,
                icon: "error"
                });
            console.log(error);
        }).finally(() => {
            $('#marker-entryHour').val('');
            $('#marker-departureTime').val('');
        }); ;
    }

    const findAllMarkers = async () => {

        showLoadingIndicator();

        let newHtml = '';
        await fetch('HourMarkers', {
            method: 'GET',
            headers: {
                "Content-Type":"application/json"
            },
        }).then((response) => {
            if(!response.ok) {
                throw new Error('Não foi possível realizar a requisição');
            }
            return response.json();
        }).then((result) => {
            if (!Array.isArray(result) || result.length === 0) {
                Toast.fire({
                    title: 'Atenção',
                    text: 'Nenhum registro de marcação encontrado!',
                    icon: 'warning'
                });

                $('#marker-table').html(newHtml);

                return;
            }

            result.map((hourMarker) => {
                newHtml += `
                <tr>
                <td>${hourMarker.markerId}</td>
                    <td>${hourMarker.entryHour}</td>
                    <td>${hourMarker.departureTime}</td>
                    <td>
                        <a class="btn me-3 text-lg text-success" id="marker-update" data-id='${hourMarker.markerId}'>
                            <i class="far fa-edit"></i>
                        </a>
                        <a class="text-lg text-danger" id="marker-delete" data-id='${hourMarker.markerId}'>
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </td>
                </tr>
                `;
            });
            $('#marker-table').html(newHtml);
        }).catch((error) => {
            Swal.fire({
                title: "Atenção!",
                text: `Não foi possível buscar as marcações de horário. Motivo: ${error}`,
                icon: "error"
            });
        }).finally(() => {
            hideLoadingIndicator();
        });
    }

    const findMarkerById = async (id) => {
        let obj = null;
        await fetch(`HourMarkers?action=byId&markerId=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na requisição: ${response.status}`);
                }
                return response.json();
        }).then(result => {
            obj = {
                    markerId: result.markerId,
                    schedule: result.schedule,
                    entryHour: result.entryHour,
                    departureTime: result.departureTime
                };
        }).catch((error) => {
            console.log(error);
            Toast.fire({
                icon: 'error',
                text: `Não foi possível buscar a marcação escolhida. Motivo: ${error}`
            });
        });
        return obj;
    }

    const updateMarker = async () => {
        let id = $('#marker-update').data('id');
        let old = await findMarkerById(id);

        /**
         * Buscar os horários de trabalho
         */
        let workSchedules = await findAvailableSchedules();
        await Swal.fire({
            title: "Atualizar Marcação",
            html: `
            <label class="swal2-label">Horário de Entrada</label>
            <input id="marker-update-entryHour" class="swal2-input" value='${old.entryHour}'>

            <label id="swal-label1" class="swal2-label">Horário de Saída</label>
            <input id="marker-update-departureTime" id="swal-input2" class="swal2-input" value='${old.departureTime}'>
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            didOpen: () => {
                $('#marker-update-entryHour').inputmask('99:99', { placeholder: '__:__' });
                $('#marker-update-departureTime').inputmask('99:99', { placeholder: '__:__' });
            },
            preConfirm: () => {
              let entryHour = $('#marker-update-entryHour').val();
              let departureTime = $('#marker-update-departureTime').val();
              
              if(!validateHour(entryHour)){ return false; }
              if(!validateHour(departureTime)){ return false; }

              let data = {
                markerId: id,
                entryHour,
                departureTime
              };


              return data;
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then(async (result) => {
            if(result.isConfirmed){
                await fetch('HourMarkers', {
                    method: 'PUT',
                    headers: {
                        'Content-Type':'application/json'
                    },
                    body: JSON.stringify(result.value)
                }).then((response) => {
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

                    findAllMarkers();
                }).catch((error) =>{
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
                await fetch(`HourMarkers?markerId=${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then((response) => {
                        if (!response.ok) {
                            throw new Error(`Erro na requisição: ${response.status}`);
                        }

                        Swal.fire({
                            title: "Sucesso!",
                            text: "Registro Removido.",
                            icon: "success",
                            showConfirmButton: false,
                            timer: 1000
                        });

                        findAllMarkers();
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
    $(document).on('click', '#marker-create', createMarker);
    $(document).on('click', '#marker-update', updateMarker);
    $(document).on('click', '#marker-delete', deleteMarker);

    /**
     * Ao iniciar busca todos e lista na tabela de horários
     */
    findAllMarkers();

    $(document).ready(() => {
        console.log('Executando criação da máscaras para as marcações...')
        $('#marker-entryHour').inputmask('99:99', { placeholder: '__:__' });
        $('#marker-departureTime').inputmask('99:99', { placeholder: '__:__' });
    });
});



