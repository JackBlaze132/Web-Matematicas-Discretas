
$(".round").draggable();

var LineController = connect();
let numNodos = parseInt(document.getElementById("numNodos").textContent);
let nodes = [];
for (let i = 0; i < numNodos; i++) {
    nodes.push(9)
}
let contador = 0;


console.log(nodes)


for(let index in nodes){
    LineController.drawLine({
        left_node: String('c' + (index++)),
        right_node: String('c' + (index)),
        col: "#6610f2",
        width: 2,
        gtype: "D"
    })

    $( '#'+ 'c' + String(index) ).draggable({
        drag: function(event, ui){LineController.redrawLines();}
    });

}