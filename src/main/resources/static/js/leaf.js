$(".leaf").draggable();


var LineController = connect();
let fathers = document.querySelectorAll(".fathertrue");

let leaves = document.querySelectorAll(".leaf")

for ( let i = 0 ; i < fathers.length; i++){
    for (let j = 0 ; j < leaves.length; j++){

        /*
        console.log("Primer entry")
        console.log(leaves[j].id)
        console.log(fathers[i].classList)
        */

        if (fathers[i].classList.contains(String(leaves[j].id))){
            /*
            console.log("Segundo entry")
            console.log(fathers[i].id)
            console.log(leaves[j].id)
            */
            LineController.drawLine({
                left_node: String(fathers[i].id),
                right_node: String(leaves[j].id),
                col: "#6610f2",
                width: 2,
                gtype: "---",
            })
        }
        $('#' + leaves[j].id).draggable({
            drag: function (event, ui) {
                LineController.redrawLines();
            }
        })
    }
}




