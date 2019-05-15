function Canvas(bgImage, width, height) {
  console.log('--- Canvas.js: New canvas created');

  /*** ...damit in Events (neuer Kontext) das this auch noch auf dieses Canvas-Objekt zeigt,
   die Selbstreferenz nochmal extra in self speichern ***/
  let self = this;

  self.stage = new Konva.Stage({
    container: 'canvas',
    width: width,
    height: height,
    draggable: true
  });

  self.bgImageLayer = new Konva.Layer();
  self.bgImageLayer.add(bgImage);
  // self.bgImageLayer.draw();
  // self.stage.add(self.bgImageLayer);

  self.pathLayer = new Konva.Layer(); //drawing pathes under waypoints

  self.waypointLayer = new Konva.Layer();
  self.waypoints = [] //collecting all waypoints on canvas here

  self.labelLayer = new Konva.Layer();
  self.labels = [] //collecting all lables on canvas here

  self.stage.add(self.bgImageLayer, self.pathLayer, self.waypointLayer, self.labelLayer);

  //void onClickModeScan()
  self.stage.on('click', function(e) {

    if (document.getElementById("waypointRadio").checked == true) {
      self.addWaypoint();
    }
    // if(document.getElementById("pathRadio").checked == true){
    //   self.pathMaker();
    // }
    if (document.getElementById("labelRadio").checked == true) {
      self.addLabel();
    }
  });

  //reset preview line + lastSelection when mode is changed
  let radios = document.forms["modes"].elements["mode"];
  for (let i = 0; i < radios.length; i++) {
    radios[i].onclick = function() {
      if (tempLine) {
        tempLine.destroy();
        self.pathLayer.draw();
      }
      self.lastSelected = null;
    }
  }

  //void addWaypoint()
  self.addWaypoint = function() {
    console.log('--- Canvas.js: Clicked: Adding new waypoint...');
    self.waypoints.push(new Waypoint(self.transform().x, self.transform().y, self));
    self.waypointLayer.add(self.waypoints[self.waypoints.length - 1].getShape());
    //adding drag listener for pathupdate
    self.update(false);
  }

  //void addLabel()
  self.addLabel = function() {
    console.log('--- Canvas.js: Clicked: Adding new label...');
    self.labels.push(new Label(self.transform().x, self.transform().y));
    self.labelLayer.add(self.labels[self.labels.length - 1].getShape());
    self.update(false);
  }

  //helping variables
  let tempLine;
  self.lastSelected;
  //preview line in pathing-mode
  self.stage.on('mousemove', function() {
    if (document.getElementById("pathRadio").checked == true) {
      if (self.lastSelected) {
        if (tempLine) {
          tempLine.destroy();
        }
        tempLine = new Konva.Line({
          points: [self.lastSelected.getX(), self.lastSelected.getY(), self.transform().x, self.transform().y],
          stroke: 'black',
          strokeWidth: 2,
          lineCap: 'round'
        });
        self.pathLayer.add(tempLine);
        self.pathLayer.draw();
      }
    }
  });

  //[x, y] transform([x, y])
  self.transform = function(pos) {
    let transform = bgImage.getParent().getAbsoluteTransform().copy();
    transform.invert(); // checken ob das nicht chainbar ist
    if (pos) {
      return transform.point(pos);
    } else {
      pos = self.stage.getPointerPosition();
    }
    return transform.point(pos);
  }


  //void update(boolean rebuildPathes)
  self.update = function(rebuildPathes) {
    console.log('--- Canvas.js: Redrawing layers');

    if (rebuildPathes) {
      console.log('--- Canvas.js: Clicked: Rebuildung pathes');
      self.pathLayer.destroyChildren();
      for (let i = 0; i < self.waypoints.length; i++) {
        for (let j = 0; j < self.waypoints[i].connectedTo.length; j++) {
          self.pathLayer.add(new Konva.Line({
            points: [self.waypoints[i].getX(), self.waypoints[i].getY(), self.waypoints[i].connectedTo[j].getX(), self.waypoints[i].connectedTo[j].getY()],
            stroke: 'black',
            strokeWidth: 2,
            lineCap: 'round'
          }));
        }
      }
    }
    self.pathLayer.batchDraw();
    self.bgImageLayer.draw();
    self.waypointLayer.draw();
    self.labelLayer.draw();
  }

  //zoom
  self.stage.on('wheel', e => {
    let scaleBy = 1.2;

    e.evt.preventDefault();
    let oldScale = self.stage.scaleX();

    let mousePointTo = {
      x: self.stage.getPointerPosition().x / oldScale - self.stage.x() / oldScale,
      y: self.stage.getPointerPosition().y / oldScale - self.stage.y() / oldScale
    };

    let newScale =
      e.evt.deltaY < 0 ? oldScale * scaleBy : oldScale / scaleBy;
    self.stage.scale({
      x: newScale,
      y: newScale
    });

    let newPos = {
      x:
        -(mousePointTo.x - self.stage.getPointerPosition().x / newScale) *
        newScale,
      y:
        -(mousePointTo.y - self.stage.getPointerPosition().y / newScale) *
        newScale
    };
    self.stage.batchDraw();
  });

  self.setLastSelected = function(waypoint) {
    self.lastSelected = waypoint;
  }

  self.getLastSelected = function() {
    return self.lastSelected;
  }
  return this;
}
