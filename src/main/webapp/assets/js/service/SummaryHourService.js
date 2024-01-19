$(() => {

    const createSummary = (e) => {
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

    const findAllSummary = (e) => {
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

    const findSummaryById = (e) => {
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

    const updateSummary = (e) => {
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

    const deleteSummary = (e) => {
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

    /**
     * Atribuir eventos das funções
     */
});


