let labelCounter = 0;

function Label(x, y) { //add product here later
  console.log('--- Label.js: New label created');
  let self = this;

  self.id = labelCounter++;

  self.shape = new Konva.Rect({
    x: x,
    y: y,
    width: 7,
    height: 5,
    fill: 'blue',
    stroke: 'black',
    strokeWidth: 1,
    draggable: true
  });

  self.product;
  self.piRefernce;

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

  self.setProduct = function(product) {
    self.product = product;
  }

  self.getProduct = function() {
    return self.product;
  }

  self.setPiReference = function(ref) {
    self.piRefernce = ref;
  }

  self.getPiReference = function() {
    return self.piRefernce;
  }

}
