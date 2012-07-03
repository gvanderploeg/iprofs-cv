var cv = (function() {

  return {
    getCV: function() {
    $.getJSON("http://cv:8080/cv/Geert van der Ploeg.json?callback=?")
        .success(function(data) {

    })
  }
  }
})();


cv;