var GameListModel = function() {

    var gameComponents = [];

    return {
        addGame: function(gameComponent) {
            gameComponents.push(gameComponent);
        },

        getAllGames: function() {
            return gameComponents;
        }
    };

}