// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#odontologoTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentOdontologoId;

// Función para obtener y mostrar los odontólogos
function fetchOdontologos() {
  // listar los odontologos
  fetch(`odontologos/buscartodos`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
              <td>${odontologo.id}</td>
              <td>${odontologo.numeroMatricula}</td>
              <td>${odontologo.apellido}</td>
              <td>${odontologo.nombre}</td>

              <td>
                <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.numeroMatricula}','${odontologo.apellido}','${odontologo.nombre}')">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
              </td>
            `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

// Función para abrir el modal y cargar los datos del odontologo
editOdontologo = function (id, numeroMatricula, apellido, nombre) {
  currentOdontologoId = id;
  document.getElementById("editnroMatricula").value = numeroMatricula;
  document.getElementById("editApellido").value = apellido;
  document.getElementById("editNombre").value = nombre;
  editModal.show();
};

// Función para editar un odontologo
editForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const numeroMatricula = document.getElementById("editnroMatricula").value;
  const apellido = document.getElementById("editApellido").value;
  const nombre = document.getElementById("editNombre").value;

  //modificar un paciente
  fetch(`odontologos/modificar`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      id: currentOdontologoId,
      numeroMatricula,
      nombre,
      apellido,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontologo modificado con éxito");
      fetchOdontologos();
      editModal.hide();
    })
    .catch((error) => {
      console.error("Error editando odontologo:", error);
    });
});

// Función para eliminar un odontologo
deleteOdontologo = function (id) {
  if (confirm("¿Está seguro de que desea eliminar este odontologo?")) {
    // eliminar el odontologo
    fetch(`odontologos/eliminar/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("Odontologo eliminado con éxito");
        fetchOdontologos();
      })
      .catch((error) => {
        console.error("Error borrando odontologo:", error);
      });
  }
};

// Llamar a la función para obtener y mostrar los odontólogos
fetchOdontologos();
