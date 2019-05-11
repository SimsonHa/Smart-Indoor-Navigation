var canvas = document.querySelector('#mp');
var width = canvas.width = window.innerWidth;
var height = canvas.height = window.innerHeight;
var ctx = canvas.getContext('2d');

var curX;
var curY;

var vectors = [];

var pressed = false;

// document.onmousemove = function(e) {
//   curX = (window.Event) ? e.pageX : e.clientX + (document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft);
//   curY = (window.Event) ? e.pageY : e.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);
// }

canvas.onmousedown = function(e) {
  curX = (window.Event) ? e.pageX : e.clientX + (document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft);
  curY = (window.Event) ? e.pageY : e.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);
  vectors.push(vec = {x : curX, y : curY});
  draw();
};

function draw() {
  if(vectors.length > 1){
    for(var i = 0; i < vectors.length-1; i++) {
      ctx.beginPath();
      ctx.fillStyle = "red";
      ctx.arc(vectors[i].x, vectors[i].y, 5, 0, 2 * Math.PI);
      ctx.fill();
      ctx.stroke();
      ctx.fillStyle = "black";
      ctx.moveTo(vectors[i].x, vectors[i].y);
      ctx.lineTo(vectors[i+1].x, vectors[i+1].y);
      ctx.stroke();
    }
  }
  requestAnimationFrame(draw);
}

draw();
// ctx.fillStyle = 'rgb(255,0,0)';
// ctx.fillRect(50,50,100,150);
// ctx.fillStyle = 'rgb(0,255,0)';
// ctx.fillRect(75,75,100,100);
// ctx.fillStyle = 'rgba(255,0,255,0.75)';
// ctx.fillRect(25,100,175,50);
// ctx.strokeStyle = 'rgb(255,255,255)';
// ctx.lineWidth = 5;
// ctx.strokeRect(25,25,175,200);
