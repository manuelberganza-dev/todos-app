const form = document.getElementById("formularioRegistro");
const usernameInp = document.getElementById("username");
const passwordInp = document.getElementById("password");

const span = document.querySelector("span");

form.addEventListener("submit", validarFormulario);


function validarFormulario(e) {
	
	validarCaracteres(e, usernameInp.value);
	validarPassword(e, passwordInp.value);

}

function validarCaracteres(event, username) {
    const errUsername = document.getElementById('errUsername');
	const regex = /^[a-zA-Z0-9]*$/;

	if(!regex.test(username)) {
		event.preventDefault();
				
		mostarError(errUsername, 'El usuario solo puede contener letras y números.');
	}

    usernameInp.addEventListener("focus", () => limpiarError(errUsername));
}

function validarPassword(event, password) {
    const errPass = document.getElementById('errPass');

	if(password.length < 8) {
		event.preventDefault();
		
		mostarError(errPass, 'La contraseña debe tener al menos 8 caracteres.');
	}

    passwordInp.addEventListener("focus", () => limpiarError(errPass));
}

function mostarError(elem, msj) {
    elem.classList.add("text-danger", "mb-0", "error");
    elem.innerText = msj;
}

function limpiarError(elem) {
    elem.innerText = '';
}