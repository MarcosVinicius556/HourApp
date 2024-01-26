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

    findAllSummaries();

});
