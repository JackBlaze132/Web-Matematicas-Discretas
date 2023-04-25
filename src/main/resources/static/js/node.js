

$(".card").draggable();

// Declaración de variables
var LineController = connect(); // Variable que llama al método connect de la libreria
let numNodos = parseInt(document.getElementById("numNodos").textContent);//Variable que almacena el tamaño de la lista de nodos
const footer = document.querySelector('footer');//Selecciona la etiqueta <footer> del documento donde se ejecuta el script
let contador = 0; // variable que sirve de contador de los nodos de la lisa
let lastPosition = 0;// variable que funcionara como contadorpara la posicion el footer
let nodes = [];//arreglo que almacena los nodos de la lista

//se recorre la lista de nodos y los anexa a un arreglo
for (let i = 0; i < numNodos; i++) {
    nodes.push("nodos" + (i+1))
}

//Por cada elemento en el arreglo nodes, se crea una nueva flecha entre los nodos visualizados en la página
for(let index in nodes){
    LineController.drawLine({
        left_node: String('c' + (index++)),
        right_node: String('c' + (index)),
        col: "#6610f2",
        width: 2,
        gtype: "<->"
    })
    console.log(String('c' + (index++)))
    console.log(String('c' + (index)))

    $( '#'+ 'c' + String(index-1) ).draggable({
        drag: function(event, ui){LineController.redrawLines();}
    });

}

/**
 * Función que el footer del documento siga el scrollbar del navegador, con el fin de generar un efecto similar
 * al atributo CSS "position: fixed;"
 * Se actualiza el atributo de CSS de posición "left: px;" en tiempo real con respecto al scrollbar lateral del navegador
 */
function smoothScroll() {
    const currentPosition = window.scrollX;
    const distance = currentPosition - lastPosition;
    const newPosition = lastPosition + distance; // Ajusta el factor de suavizado aquí mediante una multiplicación entre 0 y 1

    footer.style.left = `${newPosition}px`;
    lastPosition = newPosition;

    window.requestAnimationFrame(smoothScroll);
}

/**
 * Ejecuta la función por parámetro en el documento actual
 * @param soothScroll
 */
window.requestAnimationFrame(smoothScroll);