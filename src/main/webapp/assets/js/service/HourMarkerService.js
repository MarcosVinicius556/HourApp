$(() => {

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

    const showExtraHours = (extraHours) => {
        let newHtml = '';
        if (!Array.isArray(extraHours) || extraHours.length === 0) {
            Toast.fire({
                title: 'Atenção',
                text: 'Nenhum registro de hora extra encontrado!',
                icon: 'warning'
            });

            $('#summary-extra-table').html(newHtml);

            return;
        }

        extraHours.map((summary) => {
            newHtml += `
            <tr>
                <td>${summary.summaryId}</td>
                <td>${summary.totalHours}</td>
                <td>
                    <a class="text-lg text-danger summary-extra-delete" data-id='${summary.summaryId}'>
                        <i class="far fa-trash-alt"></i>
                    </a>
                </td>
            <tr>
            `;
        });

        $('#summary-extra-table').html(newHtml);
    }

    const showLateHours = (lateHours) => {
        let newHtml = '';
        if (!Array.isArray(lateHours) || lateHours.length === 0) {
            Toast.fire({
                title: 'Atenção',
                text: 'Nenhum registro de hora extra encontrado!',
                icon: 'warning'
            });

            $('#summary-late-table').html(newHtml);

            return;
        }

        lateHours.map((summary) => {
            newHtml += `
            <tr>
                <td>${summary.summaryId}</td>
                <td>${summary.totalHours}</td>
                <td>
                    <a class="text-lg text-danger summary-late-delete" data-id='{summary.summaryId}'>
                        <i class="far fa-trash-alt"></i>
                    </a>
                </td>
            <tr>
            `;
        });

        $('#summary-late-table').html(newHtml);
    }

    const findAllSummaries = async () => {
        await fetch('SummaryHours', {
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
           let extraHoursList = result.filter(({hourType}) => hourType === "EXTRA");
           let lateHoursList = result.filter(({hourType}) => hourType === "ATRASO");

           showExtraHours(extraHoursList);
           showLateHours(lateHoursList);
       }).catch((error) => {
           Toast.fire({
               icon: 'error',
               title: 'Atenção',
               text: 'Erro ao buscar horários de trabalho'
           });
           console.log(error)
       });
   }

    const getSelectedSchedules = async () => {
        let schedules = $('#schedule-table input[type="checkbox"]:checked').map(function() {
            return this.value;
        }).get();
        if(!Array.isArray(schedules) || schedules.length === 0){
            await Swal.fire({
                    title: "Atenção!",
                    text: `Não é possível calcular sem selecionar ao menos 1 horário`,
                    icon: "error"
                }); 
            return undefined;
        }
        return schedules;
    }

    const calculate = async (marker) => {
        
        /**
         * Buscar os horários de trabalho
         */
        let schedules = await getSelectedSchedules();
        if(!schedules) return;

        let calcMode = $('input[name="calculator-mode"]:checked').val();

        let dataToSend = JSON.stringify({
            scheduleIds: schedules,
            marker: marker
        });
       
        await fetch(`SummaryHours?action=calculate&mode=${calcMode}`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: dataToSend
        }).then((response) => {
            if(!response.ok){
                throw new Error(`Erro na requisição. status: ${response.status}`)
            }
            return response.json();
        }).then(async () => {
            await Toast.fire({
                title: "Sucesso",
                text: "O cálculo foi realizado com sucesso! Verifique o resultado nas tabelas abaixo...",
                icon: "success"
            });
            findAllSummaries();
        }).catch(async (error) => {
            await Swal.fire({
                title: "Atenção",
                text: `Não foi possível realizar o cálculo. Motivo: ${error}`,
                icon: "error",
                showCancelButton: false,
                showConfirmButton: true,
                text: "Fechar",
            });
        });
    }

    const createMarker = async () => {
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

                data = {
                    markerId: response.markerId,
                    ...data,
                }

                console.log(data);
                findAllMarkers();
                calculate(data);
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
            $('#marker-entryHour').focus();
        }); ;
    }

    const findAllMarkers = async () => {

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
                    <td>${hourMarker.entryHour}</td>
                    <td>${hourMarker.departureTime}</td>
                    <td>
                        <a class="btn me-3 text-lg text-success marker-update" data-id='${hourMarker.markerId}'>
                            <i class="far fa-edit"></i>
                        </a>
                        <a class="text-lg text-danger marker-delete" data-id='${hourMarker.markerId}'>
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

    const updateMarker = async (e) => {
        e.preventDefault();
        let id = e.currentTarget.dataset.id;
        let old = await findMarkerById(id);

        /**
         * Buscar os horários de trabalho
         */
        await Swal.fire({
            title: "Atualizar Marcação",
            html: `
            <label class="swal2-label">Horário de Entrada</label>
            <input type="text" id="marker-update-entryHour" class="swal2-input" value='${old.entryHour}'>

            <label id="swal-label1" class="swal2-label">Horário de Saída</label>
            <input type="text" id="marker-update-departureTime" id="swal-input2" class="swal2-input" value='${old.departureTime}'>
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

    const deleteMarker = async (e) => {
        e.preventDefault();
        let id =  e.currentTarget.dataset.id;

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
    $('#marker-departureTime').keydown((event) => {
        if (event.which === 13 || event.which === 9) {
            event.preventDefault();
            createMarker(event);
        }
    });
    $(document).on('click', '.marker-update', updateMarker);
    $(document).on('click', '.marker-delete', deleteMarker);

    /**
     * Ao iniciar busca todos e lista na tabela de horários
     */
    findAllMarkers();

    $(document).ready(() => {
        $('#marker-entryHour').inputmask('99:99', { placeholder: '__:__' });
        $('#marker-departureTime').inputmask('99:99', { placeholder: '__:__' });
    });
});



