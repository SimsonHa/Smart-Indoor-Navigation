// initialize layer with Konva.Image background -> perfrom upload here before
let img = new Image();
let canvas;

img.src = 'js/test.JPG';
img.onload = function() {
  console.log('--- Main.js: js/test.JPG loaded.');
  let bgImage = new Konva.Image({
    x: 0,
    y: 0,
    image: img,
    width: img.width,
    height: img.height
  });
  canvas = new Canvas(bgImage, img.width, img.height);
  canvas.update();
}
