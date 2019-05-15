let waypointCounter = 0;

function Waypoint(x, y, canvas) {
  console.log('--- Waypoint.js: New waypoint created');
  let self = this;

  self.id = waypointCounter++;

  self.shape = new Konva.Circle({
    x: x,
    y: y,
    radius: 4,
    fill: 'red',
    stroke: 'black',
    strokeWidth: 1,
    draggable: true
  });

  self.canvas = canvas;

  self.shape.on('click', function() {
    if(document.getElementById("pathRadio").checked == true) {
      let lastSelection = canvas.getLastSelected();
      if(lastSelection) { //nicht null
        if(lastSelection.getId() != self.getId()) { // nicht selbst
          if(!self.connectedTo.includes(lastSelection)) {
            self.connectedTo.push(lastSelection);
            lastSelection.addConnection(self);
            console.log("Nicht null, nicht selbst, noch nicht verbunden - jetzt verbunden!")
          }
        }
      }
      canvas.setLastSelected(self);
      canvas.update(true);
      console.log("Verbunden mit: " + self.connectedTo.length);
    }
  });

  self.shape.on('dragmove', function() {
    self.shape.setX(canvas.transform().x);
    self.shape.setY(canvas.transform().y);
    canvas.update(true);
  });

  self.connectedTo = [];

  self.getId = function() {
    return self.id;
  }

  self.getX = function() {
    return self.shape.getX();
  }

  self.getY = function() {
    return self.shape.getY();
  }

  self.getShape = function() {
    return self.shape;
  }

  self.addConnection = function(waypoint) {
    self.connectedTo.push(waypoint);
  }

  self.getConnections = function() {
    return self.connectedTo;
  }
}
