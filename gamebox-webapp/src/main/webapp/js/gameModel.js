var GameModel = function(model) {

    var _model = model;

    return {
        getMiniatureUrl: function() {
            return _model.attributes["miniatureUrl"];
        },

        getStartGameUrl: function() {
            return _model.attributes["startGameUrl"];
        },

        getModelAttribute: function(name) {
            return _model.attributes[name];
        }
    };

}