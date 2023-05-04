$(document).ready(function () {
    $('#cancion').change(function () {
        var selectedCancionId = $(this).val();
        $.ajax({
            type: "GET",
            url: "/playList/cancion/" + selectedCancionId,
            success: function (cancion) {
                $('#id').val(cancion.id);
                $('#titulo').val(cancion.titulo);
                $('#autor').val(cancion.autor);
                $('#genero').val(cancion.genero);
                $('#duracion').val(cancion.duracion);
            }
        });
    });
});

$(document).ready(function () {
    $('#cancion').change(function () {
        var selectedCancionId = $(this).val();
        $('#idCancion').val(selectedCancionId);
    });
});