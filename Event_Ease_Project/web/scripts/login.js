function myFunction(event){
    var id = document.getElementById("id");
    var password = document.getElementById("password");
    var form = document.getElementById("form");

    if(id.value.length == 0 || password.value.length == 0){
        // Prevenir el envío del formulario si los campos están vacíos
        event.preventDefault();
        alert("Por favor, completa todos los campos.");
        return;
    }

    // No necesitas llamar a form.submit() aquí porque el tipo del botón es submit
    // y el formulario se enviará automáticamente a menos que preventDefault() sea llamado
}
