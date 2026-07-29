// Call the dataTables jQuery plugin
$(document).ready(function() {
  const tabla = $('#dataTable');
  const opciones = {
    autoWidth: true
  };

  if (tabla.data('preservar-orden')) {
    opciones.order = [];
  }

  tabla.DataTable(opciones);
});
