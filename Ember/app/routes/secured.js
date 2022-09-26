import Route from '@ember/routing/route';
import { tracked } from '@glimmer/tracking';
import { service } from '@ember/service';

export default class SecuredRoute extends Route {
  @tracked marks;
  @tracked students;
  @service dummy;

  model() {
    let self = this;
    var req = new XMLHttpRequest();

    req.onload = function () {
      console.log(this.responseText);
      self.marks = JSON.parse(this.responseText).marks;
      self.students = JSON.parse(this.responseText).students;
    };

    req.open(
      'POST',
      'http://localhost:8080/MarkManagement/secured/get-marks',
      false
    );
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send();

    return self.marks;
  }
}
