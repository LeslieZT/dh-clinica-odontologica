const form = document.getElementById("agregarForm");

form.addEventListener("submit", function (event) {
  event.preventDefault();

  const numeroMatricula = document.getElementById("numeroMatricula").value;
  const apellido = document.getElementById("apellido").value;
  const nombre = document.getElementById("nombre").value;

  // llamando al endpoint de agregar
  const datosFormulario = {
    numeroMatricula,
    apellido,
    nombre,
  };

  fetch(`odontologos/guardar`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datosFormulario),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontologo agregado con éxito");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando odontologo:", error);
    });
});
