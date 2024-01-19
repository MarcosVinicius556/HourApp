$(() => {

    const createFunction = (e) => {
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

    const findAllFunction = (e) => {
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

    const findAllByIdFunction = (e) => {
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

    const updateFunction = (e) => {
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

    const deleteFunction = (e) => {
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


