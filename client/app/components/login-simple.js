import Ember from 'ember';

export default Ember.Component.extend({
  actions: {
    login : function() {
      var data = this.getProperties('username', 'password');

      this.get('session').authenticate('authenticator:rest', data);

    },
    invalidateSession : function() {
      this.get('session').invalidate();
    }
  }
});
