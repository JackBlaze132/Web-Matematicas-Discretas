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