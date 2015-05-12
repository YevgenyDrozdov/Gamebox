var Component = Class.create({

    initialize: function(parent) {
        this._container = null;
        this._id = null;
        this._parent = null;
        this._children = [];
        this._model = null;
    },

    setId: function(id) {
        this._id = id;
    },

    getId: function() {
        this._id;
    },

    add: function(child) {
        this._children.push(child);
        child._parent = this;

        this._container.append(child.getContainer());
    },

    setModel: function(model) {
        this._model = model;
    },

    getModel: function() {
        return this._model;
    },

    getParent: function() {
        return this._parent;
    },

    setContainer: function(container) {
        this._container = container;
    },

    getContainer: function() {
        return this._container;
    },

    render: function() {
        this._container.attr("id", this._id);

        jQuery.each(this._children, function(index, child) {
            child.render();
        });
    },

    on: function(event, handler, context) {
        this._container.on(event, jQuery.proxy(handler, context));
    },

    hide: function() {
        this._container.hide();
    },

    show: function() {
        this._container.show();
    }

});