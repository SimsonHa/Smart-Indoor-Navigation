let productCounter = 0;

function Product(name, price, category) {
  this.id = productCounter++;
  this.name = name;
  this.price = price;
  this.category = category;

  this.getId = function() {
    return this.id;
  }

  this.setName = function(name) {
    this.name = name;
  }

  this.getName = function() {
    return this.name;
  }

  this.setPrice = function(price) {
    this.price = price;
  }

  this.getPrice = function() {
    return this.price;
  }

  this.setCategory = function(category) {
    this.category = category;
  }

  this.getCategory = function() {
    return this.category;
  }
}
