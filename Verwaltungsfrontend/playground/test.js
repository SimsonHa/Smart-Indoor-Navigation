//======== KLASSE 1 =========

// console.log("Welcome on the playground");
//
// var waypoint = function(x, y) {
//   let connected = [];
//
//   return {
//     setX: function(newX) {
//       x = newX;
//     },
//
//     getX: function() {
//       return x;
//     },
//
//     setY: function(newY) {
//       y = newY;
//     },
//
//     getY: function() {
//       return y;
//     },
//
//     addConnected: function(waypoint) {
//       connected.push(waypoint);
//     },
//
//     getConnected: function() {
//       return connected;
//     }
//   }
// }
//
// var points = [];
//
// points.push(waypoint(1, 2));
// points.push(waypoint(2, 3));
//
// for(var i = 0; i < points.length; i++) {
//   console.log(points[i].getX(), points[i].getY());
// }
//
// console.log(points.length);


//======== KLASSE 2 =========


let waypointCounter = 0;

function Waypoint(x1) {
  this.x = {id: 10};
  this.id = waypointCounter++;

  this.getX = function () {
    return this.func();
  }

  this.func = function () {
    console.log(this.x);
    if(true) {
      return this.x.id;
    }
  }
}

var x = new Waypoint(10);
var y = new Waypoint(12);

console.log(x.getX());
console.log(y.getX());

//======= IDs =========

// var idCounter = 1;
//
// function Employee(name, dept) {
//    this.name = name || '';
//    this.dept = dept || 'general';
//    this.id = idCounter++;
// }
