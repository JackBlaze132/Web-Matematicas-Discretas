
$(".card").draggable();

var LineController = connect();
let numNodos = parseInt(document.getElementById("numNodos").textContent);
let nodes = [];
for (let i = 0; i < numNodos; i++) {
    nodes.push(9)
}
let contador = 0;

for(let index in nodes){
    LineController.drawLine({
        left_node: String('c' + (index++)),
        right_node: String('c' + (index)),
        col: "#6610f2",
        width: 2,
        gtype: "<->"
    })

    $( '#'+ 'c' + String(index) ).draggable({
        drag: function(event, ui){LineController.redrawLines();}
    });

}

const footer = document.querySelector('footer');

let lastPosition = 0;

function smoothScroll() {
    const currentPosition = window.scrollX;
    const distance = currentPosition - lastPosition;
    const newPosition = lastPosition + distance; // Ajusta el factor de suavizado aquí

    footer.style.left = `${newPosition}px`;
    lastPosition = newPosition;

    window.requestAnimationFrame(smoothScroll);
}

window.requestAnimationFrame(smoothScroll);