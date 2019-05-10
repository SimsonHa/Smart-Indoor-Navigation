function dateiauswahl(evt) {
    var datei = evt.target.datei; // FileObject

      // nur Bild-Dateien
      if (datei.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();

      reader.onload = (function(theFile) {
        return function(e) {
          // erzeuge Thumbnails.
          var vorschau = document.createElement('img');
		  vorschau.className = 'vorschau';
		  vorschau.src   = e.target.result;
		  vorschau.title = theFile.name;
          document.getElementById('list').insertBefore(vorschau, null);
        };
      })(f);

      // Bilder als Data URL auslesen.
      reader.readAsDataURL(f);

  }
  // Auf neue Auswahl reagieren und gegebenenfalls Funktion dateiauswahl neu ausführen.
  document.getElementById('file').addEventListener('change', dateiauswahl, false);
