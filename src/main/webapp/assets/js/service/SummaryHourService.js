$(() => {

    const findAllSummaries = async () => {
         await fetch('SumamryHours', {
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
            showExtraHours(result);
            showLateHours(result);
        }).catch((error) => {
            Toast.fire({
                icon: 'error',
                title: 'Atenção',
                text: 'Erro ao buscar horários de trabalho'
            });
            console.log(error)
        });
    }

    const getSelectedMarkers = async () => {
        let markers = $('#marker-table input[type="checkbox"]:checked').map(function() {
            return this.value;
        }).get();

        if(!Array.isArray(markers) || markers.length === 0){
            await Swal.fire({
                    title: "Atenção!",
                    text: `Não é possível calcular sem selecionar ao menos 1 marcação`,
                    icon: "error"
                }); 
            return;
        }
        return markers;
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
            return;
        }
        return schedules;
    }

    const showExtraHours = (extraHours) => {
        result.map((hourMarker) => {
            newHtml += `
            <tr>
                <td>${hourMarker.markerId}</td>
                <td>${hourMarker.scheduleId} - ${hourMarker.scheduleDescription}</td>
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
    }

    const showLateHours = (lateHours) => {
        result.map((hourMarker) => {
            newHtml += `
            <tr>
                <td>${hourMarker.markerId}</td>
                <td>${hourMarker.scheduleId} - ${hourMarker.scheduleDescription}</td>
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
    }

    const calculate = async (e) => {
        
        /**
         * Buscar as marcações
         */
        let markers = await getSelectedMarkers();
        
        
        /**
         * Buscar os horários de trabalho
         */
        let schedules = await getSelectedSchedules();

        let calcMode = $('input[name="calculator-mode"]:checked').val();

        let dataToSend = JSON.stringify({
            scheduleIds: schedules,
            markerIds: markers
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
        }).then((result) => {

        }).catch((error) => {

        });

        await Swal.fire({
            title: "Resultado do Cálculo",
            text: "Resultado1\nResultado2\nResultado3",
            focusConfirm: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Salvar Registros',
            showLoaderOnConfirm: true,
            preConfirm: () => {
              let scheduleId = $('#summary-scheduleId').val();
              let markerId = $('#summary-markerId').val();
              
              let data = {
                scheduleId,
                markerId
              };

              return data;
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then(async (result) => {
            if(result.isConfirmed){
               
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
    $('#hour-calculator').on('click', calculate);

});
