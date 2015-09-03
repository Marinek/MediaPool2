import Ember from 'ember';

export default Ember.Route.extend({
  model: function(params) {
    console.log(params.product_id);
    return this.store.findRecord('movie', params.product_id);
  },

  setupController: function (controller, model) {
       controller.set('model', model);
   }
});
