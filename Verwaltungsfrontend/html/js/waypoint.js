class Waypoint {


  constructor(marker) {
    this.marker = marker;
    this.connectedTo = [];


    this.marker.on('click', function(e) {
      console.log("Waypoint clicked");

      if (document.getElementById("pathRadio").checked == true) {

        console.log(e.target.attrs.x);

        if(lastSelected != null) {
          //adding selections
          // this.connectedTo.push(lastSelected);
          // lastSelected.attrs.connectedTo.push(e.target);
          pathLayer.add(new Konva.Line({
            points: [e.target.attrs.x, e.target.attrs.y, lastSelected.attrs.x, lastSelected.attrs.y],
            stroke: 'black',
            strokeWidth: 2,
            lineCap: 'round'
          }));
          pathLayer.draw();
        }

        lastSelected = e.target;
      };
    });

    pathLayer.add(marker);
  }
}
