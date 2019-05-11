function previewFile(){
       var preview = document.querySelector('img'); //selects the query named img
       var file    = document.querySelector('input[type=file]').files[0]; //sames as here
       var reader  = new FileReader();

       reader.onloadend = function () {
           //preview.src = reader.result;

          var img = new Image();
          img.src = reader.result;
          ctx.drawImage(img, 0, 0);
       }

       if (file) {
           reader.readAsDataURL(file); //reads the data as a URL
       } else {
           preview.src = "";
       }
     }

  previewFile();  //calls the function named previewFile()
