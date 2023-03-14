/**
 * Función que permite cargar los componentes dentro de la misma
 * Carga la barra de navegación en un <div> con una "id" específica
 * Carga el footer en un <footer> con una "id" específica
 * carga un modal de bootstrap en un <div> con una "id" específica
 * @param #common-navbar
 * @param #common-footer
 * @param #common-modal
 */
window.onload = function() {
    $.get("assets/navbar.html", function (data) {
        $("#common-navbar").html(data);
        $('.navbar-nav').find('li:nth-child(2)')
            .addClass('active')
            .find('a').append('<span class="sr-only">(current)</span>');
    });
    $.get("assets/footer.html", function (data) {
        $("#common-footer").html(data);
        $('.footer').find('li:nth-child(2)')
            .addClass('active')
            .find('a').append('<span class="sr-only">(current)</span>');
    });
    $.get("assets/crearEstudiante.html", function (data) {
        $("#common-modal").html(data);
        $('.modal-body').find('li:nth-child(2)')
            .addClass('active')
            .find('a').append('<span class="sr-only">(current)</span>');
    });
}