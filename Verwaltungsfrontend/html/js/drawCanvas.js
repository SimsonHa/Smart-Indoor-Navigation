var canvas = document.querySelector('#mp');
var width = canvas.width = window.innerWidth;
var height = canvas.height = window.innerHeight;
var ctx = canvas.getContext('2d');

var curX;
var curY;

var lastClickedX;
var lastClickedY;

var pressed = false;

document.onmousemove = function(e) {
  curX = (window.Event) ? e.pageX : e.clientX + (document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft);
  curY = (window.Event) ? e.pageY : e.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);
}

canvas.onmousedown = function() {
  lastClickedX = curX;
  lastClickedY = curY;
};

function draw() {
  if(lastClickedX != null && lastClickedY != null) {
    ctx.beginPath();
    ctx.moveTo(lastClickedX, lastClickedY);
    ctx.lineTo(curX, curY);
    ctx.stroke();
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
