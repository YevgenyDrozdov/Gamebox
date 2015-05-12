var StartButton = Class.create(Component, {

    initialize: function($super) {
        $super();
        this._container = jQuery("<div/>");
        this._container.addClass("start-button");
    }

});

var GameState = {
    NOT_STARTED: "NotStarted",
    ACTIVE: "Active",
    PAUSED: "Paused",
    FINISHED: "Finished"
};

var GameComponent = Class.create(Component, {

    initialize: function($super) {
        $super();

        this._gameState = GameState.NOT_STARTED;

        this._container = jQuery("<div/>");
        this._container.addClass("game-view-container");

        this._gameViewport = jQuery("<iframe/>");
        this._container.append(this._gameViewport);

        this._startButton = new StartButton();
        this.add(this._startButton);
        this._startButton.on("click", this.startGame, this);
    },

    startGame: function() {
        this._gameState = GameState.ACTIVE;

        var gameId = this.getModel().getModelAttribute("gameId");
        var that = this;

        jQuery.ajax("/rest/action/startGame/" + gameId, {
            success: function(data) {
                that._gameViewport.attr("src", "/rest/" + gameId + "/render");
                that._gameViewport.focus();
            }
        });

        this.render();
    },

    updateGameViewport: function() {

    },

    render: function($super) {

        if (this._gameState == GameState.NOT_STARTED) {
            this.getContainer().css("background-image", "url('" + this.getModel().getMiniatureUrl() + "')");
            this.getContainer().attr("state", "not-started");
        } else {
            this.getContainer().css("background-image", "");
            this.getContainer().attr("state", "active");
        }

        $super();
    }

});