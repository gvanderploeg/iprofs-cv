var CV = (function () {

  var populateKeywordList = function (selector, name, items) {

    var listItems = "";
    if (!items) {
      return;
    }
    $.each(items, function (index, value) {
      listItems += "<li>" + value + "</li>";
    });

    $("<h3>" + name + "</h3>").appendTo(selector);
    $("<ul>" + listItems + "</ul>").appendTo(selector);


  },


  newExperience = function (exp) {
      var container = $('<div class="row-fluid experienceContainer"></div>')
      .append(
          $('<div class="span8" />')
          .append('<h3>' + exp.company + ' <small>' + CV.fmtDate(new Date(exp.from), 'M y')  + ' - ' + CV.fmtDate(new Date(exp.until), 'M y') + '</small></h3>')
          .append('<p>' + exp.introduction + '</p>')
          .append('<p>' + exp.body.replace(/\n/g, "<br />") + '</p>'));

//    container.append();
    var keywordsContainer = $('<div class="span4" />');
     $(exp.keywordCategories).each(function() {
       keywordsContainer
           .append('<h4>' + this.name + '</h4>')
           .append('<ul>' + $(this.keywords).map(function() { return "<li>" + this + "</li>";}).get().join('') + '</ul>');
     });
    container.append(keywordsContainer);
    return container;
  },

  fmtDate = function(date, fmt) {
    var months = ['januari', 'februari', 'maart', 'april', 'mei', 'juni', 'juli', 'augustus', 'september', 'oktober', 'november', 'december'];
    var result = fmt;
    result = result.replace(/d/g, date.getDate());
    result = result.replace(/m/g, date.getMonth() + 1);
    result = result.replace(/M/g, months[date.getMonth()]);
    result = result.replace(/y/g, date.getYear()+1900);
    return result;
  };



  return {
    fmtDate: fmtDate,

    populate:function (cv) {
      $("#headerHero h1").html(cv.firstName + " " + cv.familyName);
      $("p#headerIntroduction").text(cv.introduction);

      var keywordCategoriesByType = {};
      $(cv.keywordCategories).map(function () {
        keywordCategoriesByType[this.name] = this.keywords;
      });
      populateKeywordList("#container-programmeertalen", "Programmeertalen", keywordCategoriesByType['Programmeertalen']);
      populateKeywordList("#container-scripting", "Scripting", keywordCategoriesByType['Scripting']);
      populateKeywordList("#container-applicationservers", "Application servers", keywordCategoriesByType['Application Servers']);
      populateKeywordList("#container-ides", "IDE's", keywordCategoriesByType["IDE's"]);

      $(".experienceContainer").remove();
      $(cv.experiences).each(function() {
        $("#experiencesHeader").after(newExperience(this));
      });
    },


    getCV:function (name, success) {
      $.getJSON("/rest/cvs/" + encodeURIComponent(name) + ".json")
          .success(function (data) {
            console.log("Got data from API: " + JSON.stringify(data));
            success(data);
          })
    }
  }
})();

$(function () {
   var cv = CV.getCV("geert", CV.populate);

   $(".keywordCategory").hover(function() {
   $(this).append('<a id="addbutton" href="#addKeywordModal" class="btn"><i class="icon-plus"></i> Add</a>');
   }, function() {
   $("#addbutton").remove();
   });
});
