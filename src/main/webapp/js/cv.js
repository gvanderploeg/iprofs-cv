var CV = (function() {

  return {
    populate: function(cv) {
      $("#headerHero h1").html(cv.firstName + " " + cv.familyName);
      $("p#headerIntroduction").text(cv.introduction);
      $("")
    },
    getCV: function(name, success) {
    $.getJSON("/rest/cv/" + encodeURIComponent(name) +".json")
        .success(function(data) {
          console.log("Got data from API: " + JSON.stringify(data));
          success(data);
    })
  }
  }
})();

$(function() {

  var cv = CV.getCV("geert", CV.populate);

});