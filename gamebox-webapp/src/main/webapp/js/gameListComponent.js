var GameListComponent = Class.create(Component, {

    initialize: function($super, listUrl) {
        $super();

        this._listUrl = listUrl;

        this._container = jQuery("<div/>");
        this._container.addClass("game-list");

        this.loadGames();
    },

    loadGames: function() {
        var that = this;
        if (this._listUrl) {
            jQuery.ajax(this._listUrl, {
                success: function(data) {

                    var listModel = that.getModel();
                    if (!listModel) {
                        listModel = new GameListModel();
                        that.setModel(listModel);
                    }

                    jQuery.each(data, function(key, clientModel) {
                        var gameModel = new GameModel(clientModel);
                        var gameComponent = new GameComponent();
                        gameComponent.setModel(gameModel);

                        that.add(gameComponent);
                        that.getModel().addGame(gameComponent);
                    });

                    that.render();
                }
            });
        }
    }

});