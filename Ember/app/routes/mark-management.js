import Route from '@ember/routing/route';
import { tracked } from '@glimmer/tracking';
import { service } from '@ember/service';
import { action } from '@ember/object';

export default class MarkManagementRoute extends Route {
  @tracked marks;
  @tracked students;
  @service dummy;

//   model() {
//     let self = this;
//     var req = new XMLHttpRequest();

//     req.onload = function () {
//       console.log(this.responseText);
//       //   $('html').html(this.responseText);

//       //   self.marks = JSON.parse(this.responseText).marks;
//       //   self.students = JSON.parse(this.responseText).students;
//     };

//     req.open('POST', 'http://localhost:8080/MarkManagement/get-marks', false);
//     req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//     req.send();

//     return self.marks;
//   }

  @action
  logIn() {
    var req = new XMLHttpRequest();

    req.onload = function () {
      console.log(this.responseText);
    };

    req.open(
      'POST',
      'http://localhost:8080/MarkManagement/j_security_check',
      true
    );
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send('j_username=1411&j_password=Parthi123*');
  }

  @action
  getData() {
    let self = this;
    var req = new XMLHttpRequest();

    req.onload = function () {
      console.log(this.responseText);
    };

    req.open(
      'POST',
      'http://localhost:8080/MarkManagement/secured/get-marks',
      false
    );
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send();
  }
}
