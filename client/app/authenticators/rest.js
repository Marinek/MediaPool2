import Ember from 'ember';
import Base from 'simple-auth/authenticators/base';

export default Base.extend({
  restore(data) {
    alert('restore')
    return Ember.RSVP.resolve();
  },

  authenticate: function(credentials) {
      var _this = this;
      return new Ember.RSVP.Promise(function(resolve, reject) {
        Ember.$.post('http://localhost:8080/server/login', credentials).then(function(response) {
          // set the ajax header with the returned access_token object
          Ember.run(function() {
            resolve({ token: "response" });
          });

        }, function(error) {
          if (error.status === 401) {
            // if there is a authentication error the user is informed of it to try again
              reject(error.status)
          }
        });


      });
    },

  invalidate(data) {
    alert('invalidate')
    return Ember.RSVP.resolve();
  }
});
