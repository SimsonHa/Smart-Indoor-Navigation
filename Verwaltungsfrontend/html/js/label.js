class Label {

  // static wpCounter = 0;

  constructor(marker) {
    this.marker = marker;
    this.marker.on('click', function() {
      console.log("waypoint clicked");
      });
    labelLayer.add(marker);
    this.id;
        // wpCounter++;
    // id = wpCounter;
  }
}
