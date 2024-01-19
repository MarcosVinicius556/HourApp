$(() => {
  
    const createSchedule = async (e) => {
        await Swal.fire({
            title: "Novo Horário",
            html: `
              <label class="swal2-label">Descrição</label>
              <input id="scheduleDescription" class="swal2-input" type="textarea">
              <label class="swal2-label">Hora de Entrada</label>
              <input id="scheduleEntryHour" class="swal2-input">
              <label id="swal-label1" class="swal2-label">Hora de Saída</label>
              <input id="scheduleDepartureTime" id="swal-input2" class="swal2-input">
            `,
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar',
            showLoaderOnConfirm: true,
            preConfirm: () => {
              let description = $('#scheduleDescription').val();
              let entryHour = $('#scheduleEntryHour').val();
              let departureTime = $('#scheduleDepartureTime').val();
              
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
                    success: (response) => {
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

    const findAllSchedule = async () => {
        await $.ajax({
                type: 'GET',
                url: 'WorkSchedule',
                success: (response) => {
                    console.log(response);
                    /**
                     * Popular
                     */
                    response.map((workSchedule) => {
                        $('workScheduleTable').appen(`
                        <tr>
                                <td>${workSchedule.scheduleId}</td>
                                <td>${workSchedule.descritpion}</td>
                                <td>${workSchedule.entryHour}</td>
                                <td>${workSchedule.departureTime}</td>
                                <td>
                                    <button type="button" class="btn me-3 text-lg text-success"  data-bs-toggle="modal" data-bs-target="#addWorkScheduleModal">
                                        <i class="far fa-edit"></i>
                                    </button>
                                    <a class="text-lg text-danger" id="workScheduleDelete" >
                                        <i class="far fa-trash-alt"></i>
                                    </a>
                                </td>
                        </tr>
                        `);
                    });
                },
                error: (error) => {
                    console.log(error);
                }
            });
    }

    const findScheduleById = async (e) => {
        e.preventDefault();
        
        let data = {

        };

        $.ajax({
            type: 'POST',
            data: data,
            url: 'HourMarkers',
            success: (response) => {
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    const updateSchedule = async (e) => {
        e.preventDefault();
        
        let data = {

        };

        $.ajax({
            type: 'POST',
            data: data,
            url: 'HourMarkers',
            success: (response) => {
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    const deleteSchedule = async (e) => {
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
            /**
             * Requisição com ajax
             */
            if (result.isConfirmed) {
              Swal.fire({
                title: "Sucesso!",
                text: "Registro Removido.",
                icon: "success"
              });
            }
          });
    }

    /**
     * Atribuir eventos das funções
     */
    $('#workScheduleDelete').on('click', deleteSchedule);
    $('#addWorkSchedule').on('click', createSchedule);

    findAllSchedule();
});



