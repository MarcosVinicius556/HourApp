$(() => {

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
                    <td>${summary.created}</td>
                    <td>
                        <a class="text-lg text-danger" id="summary-extra-delete" data-id='${summary.summaryId}'>
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </td>
                </tr>
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
                    <td>${summary.created}</td>
                    <td>
                        <a class="text-lg text-danger" id="summary-late-delete" data-id='${summary.summaryId}'>
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </td>
                </tr>
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
            return undefined;
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
            return undefined;
        }
        return schedules;
    }

    const calculate = async (e) => {
        
        /**
         * Buscar as marcações
         */
        let markers = await getSelectedMarkers();
        if(!markers) return;
        
        /**
         * Buscar os horários de trabalho
         */
        let schedules = await getSelectedSchedules();
        if(!schedules) return;

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
        }).then(async () => {
            await Swal.fire({
                title: "Sucesso",
                text: "O cálculo foi realizado com sucesso! Verifique o resultado nas tabelas abaixo...",
                icon: "success",
                showCancelButton: false,
                showConfirmButton: false,
                timer: 1000,
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

    const deleteLateSummary = async () => {
        let id = $('#summary-late-delete').data('id');

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
                await fetch(`SummaryHours?summaryId=${id}`, {
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

                        findAllSummaries();
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

    const deleteExtraSummary = async () => {
        let id = $('#summary-extra-delete').data('id');

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
                await fetch(`SummaryHours?summaryId=${id}`, {
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

                        findAllSummaries();
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
    $('#hour-calculator').on('click', calculate);
    $(document).on('click', '#summary-late-delete', deleteLateSummary);
    $(document).on('click', '#summary-extra-delete', deleteExtraSummary);
});
