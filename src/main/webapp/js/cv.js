var CV = (function() {

  var populateKeywordList = function(selector, name, items) {

    var listItems = "";
    if (!items) {
      return;
    }
    $.each(items, function(index, value) {
      listItems += "<li>" + value + "</li>";
        });

    $("<h3>" + name + "</h3>").appendTo(selector);
    $("<ul>" + listItems + "</ul>").appendTo(selector);


  },
  foo = function() {

  };


  return {
    populate: function(cv) {
      $("#headerHero h1").html(cv.firstName + " " + cv.familyName);
      $("p#headerIntroduction").text(cv.introduction);

      var keywordCategoriesByType = {};
      $(cv.keywordCategories).map(function(){
        keywordCategoriesByType[this.name] = this.keywords;
      });
      populateKeywordList("#container-programmeertalen", "Programmeertalen", keywordCategoriesByType['Programmeertalen']);
      populateKeywordList("#container-scripting", "Scripting", keywordCategoriesByType['Scripting']);
      populateKeywordList("#container-applicationservers", "Application servers", keywordCategoriesByType['Application Servers']);
      populateKeywordList("#container-ides", "IDE's", keywordCategoriesByType["IDE's"]);
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