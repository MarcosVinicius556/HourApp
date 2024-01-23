/**
 * 
 * @param {Retorna false caso não seja válido} strHour 
 */
const validateHour = (strHour) => {
    let valid = true;
    if(strHour.replace('_', '').length !== 5){
        valid = false;
    }
    
    let hours = Number(strHour.split(':')[0]);
    if(hours < 0 || hours > 23){
        valid = false;
    }

    let minute = Number(strHour.split(':')[1]);
    if(minute < 0 || minute > 59){
        valid = false;
    }

    if(!valid){
        Swal.fire({
            title: "Atenção!",
            text: `Digite uma hora válida!`,
            icon: "error"
        });
    }

    return valid;
}