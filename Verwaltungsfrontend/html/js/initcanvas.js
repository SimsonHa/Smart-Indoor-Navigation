// --- not working atm
// function setBackground() {
//   var file = document.querySelector('input[type=file]').files[0];
//   var reader = new FileReader();
//   var tImg = new Image();
//
//   reader.onloadend = function() {
//     tImg.src = reader.result;
//     console.log("URL: " + window.URL.createObjectURL(file).revokeObjectURL());
//   };
//
//   if(file) {
//     reader.readAsDataURL(file);
//   } else {
//     tImg.src = "";
//   }
// }

// stage (top node which contains layers)
var stage;

//layers bgLayer = background image, pathLayer = waypoints and connections, eslLayer = esl shapes
var bgLayer = new Konva.Layer();


var pathLayer = new Konva.Layer();
var waypoints = [];

var labelLayer = new Konva.Layer();
var labels = [];

//load bg-image first and create a stage with the images dimension afterwards -> perfect size + resolution
var img = new Image();
var bgImg;

//other
var lastSelected;

//image resource, later changed to uploaded image
img.src = 'js/test.JPG';

//fires when the image is uploaded successfull
img.onload = function() {
  console.log("Loaded");
  bgImg = new Konva.Image({
    x: 0,
    y: 0,
    image: img,
    width: img.width,
    height: img.height
  });
  bgLayer.add(bgImg);

  bgImg.on('mouseenter', function() {
    stage.container().style.cursor = 'crosshair';
  });

  bgImg.on('mouseleave', function() {
    stage.container().style.cursor = 'default';
  });

  //initalize Stage with bg-image-size
  stage = new Konva.Stage({
    container: 'container',
    width: img.width,
    height: img.height,
    draggable: true
  });

  //add shapes on click
  stage.on('click', function(e) {
    //transform coordinates (changed by zoom + drag)
    var transform = bgImg
      .getParent()
      .getAbsoluteTransform()
      .copy();

    transform.invert();
    var pos = stage.getPointerPosition();
    var circlePos = transform.point(pos);

    // switch for mode
    if(document.getElementById("waypointRadio").checked == true) {
      waypoints.push(new Waypoint(new Konva.Circle({
        x: circlePos.x,
        y: circlePos.y,
        radius: 4,
        fill: 'red',
        stroke: 'black',
        strokeWidth: 1,
        draggable: true
      })));
    };

    if(document.getElementById("pathRadio").checked == true) {

    };

    if(document.getElementById("labelRadio").checked == true) {
      labels.push(new Label(new Konva.Rect({
        x: circlePos.x,
        y: circlePos.y,
        width: 3,
        height: 2,
        fill: 'blue',
        draggable: true
      })));
    };
    // end of mode switch

    pathLayer.draw();
    labelLayer.draw();
  });

    //add circle (later switch for waypoints or esl)

  // zoom scale
  var scaleBy = 1.2;
  stage.on('wheel', e => {
    e.evt.preventDefault();
    var oldScale = stage.scaleX();

    var mousePointTo = {
      x: stage.getPointerPosition().x / oldScale - stage.x() / oldScale,
      y: stage.getPointerPosition().y / oldScale - stage.y() / oldScale
    };

    var newScale =
      e.evt.deltaY < 0 ? oldScale * scaleBy : oldScale / scaleBy;
    stage.scale({
      x: newScale,
      y: newScale
    });

    var newPos = {
      x:
        -(mousePointTo.x - stage.getPointerPosition().x / newScale) *
        newScale,
      y:
        -(mousePointTo.y - stage.getPointerPosition().y / newScale) *
        newScale
    };
    stage.position(newPos);
    stage.batchDraw();
  });
  //end of zoom


  //drawing and adding layers to the stage (top node)
  bgLayer.draw();
  stage.add(bgLayer, pathLayer, labelLayer);
}
