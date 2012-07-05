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
      foo = function () {

      };


  return {
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

/*
With Ember:
 */
/*
Ember.LOG_BINDINGS = true;

var App = Em.Application.create();

App.set('cvDetailController', Ember.Controller.create({
  content: null
}));

App.CvDetailView = Ember.View.extend({
  contentBinding: 'App.cvDetailController.content'
});


App.set('store', Ember.Object.create({

  find: function(id, successHandler) {
    $.getJSON("/rest/cvs/%@.json".fmt(id), successHandler);
  },

  store: function(id, cv) {
    $.ajax("/rest/cvs/i%@.json".fmt(id), {
      method: "post",
      data: cv.toJSON()
    });
  }
}));



var cvFromJSON = function(json) {
  var cv = new App.CV();
  cv.set('firstName', json.firstName);
  cv.set('familyName', json.familyName);
  cv.set('introduction', json.introduction);
  return cv;
};


App.CV = Ember.Object.extend({

  firstName: "first name",
  familyName: "family name",
  introduction: "Introduction",

  fullName:function () {
    return this.get('firstName') + ' ' + this.get('familyName');
  }.property('firstName', 'familyName')

});


App.reopen({
  ready: function() {
    console.log("App ready");
    this.store.find("geert", function(data) {
      App.get('cvDetailController').set('content', cvFromJSON(data));
      App.CvDetailView.create().appendTo("body");
    });
  }
});


*/
