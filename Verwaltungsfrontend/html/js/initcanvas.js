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
var initialized = false;

var stage;

var bgLayer = new Konva.Layer();
var pathLayer = new Konva.Layer();
var eslLayer = new Konva.Layer();

var img = new Image();
var bgImg;

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

  //initalize Stage with bg size
  stage = new Konva.Stage({
    container: 'container',
    width: img.width,
    height: img.height,
    draggable: true
  });

  stage.on('click', function(e) {
    var transform = bgImg
      .getParent()
      .getAbsoluteTransform()
      .copy();

    // to detect relative position we need to invert transform
    transform.invert();
    var pos = stage.getPointerPosition();
    var circlePos = transform.point(pos);

    var circle = new Konva.Circle({
      x: circlePos.x,
      y: circlePos.y,
      radius: 3,
      fill: 'red',
      stroke: 'black',
      strokeWidth: 1,
      draggable: true
    });
    pathLayer.add(circle);
      pathLayer.draw();
  });

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



  bgLayer.draw();
  stage.add(bgLayer, pathLayer);

  initialized = true;
}

img.src = 'js/test.JPG';
