

    $(".leaf").draggable();

    var LineController = connect();
    let leaves = [...document.querySelectorAll(".leaf")];
    leaves.push("null");
    function getLevelsLists(){
        let levelsList = [];
        for (let i = 1; i < 5; i++){
            let level = [...document.querySelectorAll(".level" + i)];
            levelsList.push(level);
        }
        return levelsList;
    }

    console.log(getLevelsLists())


    /*
    let numNodos = parseInt(document.getElementById("numNodos").textContent);
     */
    let root = 50;
    /*console.log(leaves)*/


    for (let i = 0; i < getLevelsLists().length; i++){

        let leafValue = getLevelsLists()[i];
        let nextLeaf =  getLevelsLists()[i+1];

        console.log("primer entry")
        console.log(leafValue)
        console.log(nextLeaf)

        for (let k = 0 ; k < leafValue.length;k++){

            for (let j = 0; j < nextLeaf.length; j++){
            console.log("segundo entry")
            console.log(nextLeaf[j])
            console.log(leafValue[k])

            LineController.drawLine({

                left_node: String('c' + leafValue[k].textContent),
                right_node: String('c' + nextLeaf[j].textContent),
                col: "#6610f2",
                width: 2,
                gtype: "---",
            })
            $('#' + 'c' + nextLeaf[j].textContent).draggable({
                drag: function (event, ui) {
                    LineController.redrawLines();
                }
            })


        }

        $('#' + 'c' + leafValue[i].textContent).draggable({
            drag: function (event, ui) {
                LineController.redrawLines();
            }
        })

        /*let leafValue = leaves[i].textContent;
        let nextLeaf = leaves[i+1].textContent;*/


        /*for (let j = 0;j <5 ; j++){
            let leafValue = leaves[j].textContent;
            let nextLeaf = leaves[j+1].textContent;

            if (leafValue > root && leaves[j].className === ("card col-2 col-lg-1 leaf level"+ j + " ui-draggable ui-draggable-handle")) {
                console.log(leaves[j]);
                LineController.drawLine({

                    left_node: String('c' + leafValue),
                    right_node: String('c' + root),
                    col: "#6610f2",
                    width: 2,
                    gtype: "---",
                })

                console.log(document.querySelectorAll(String(".level" + (i + 1))))
                console.log("c" + leafValue);
                console.log("c" + nextLeaf);

                $('#' + 'c' + leafValue).draggable({
                    drag: function (event, ui) {
                        LineController.redrawLines();
                    }
                })
                $('#' + 'c' + root).draggable({
                    drag: function (event, ui) {
                        LineController.redrawLines();
                    }
                })
                root = leafValue;
                console.log(root);

        }
        }*/
    }
    }



