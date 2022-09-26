import Route from '@ember/routing/route';
import { tracked } from '@glimmer/tracking';
import { service } from '@ember/service';

export default class HomeRoute extends Route {
  @tracked marks;
  @tracked students;
  
  @service store;
  @service router;

  model(param) {
    let { user } = param;
    let self = this;
    var req = new XMLHttpRequest(), json;

    req.onload = function () {
        try{
            console.log(this.responseText);
            json = JSON.parse(this.responseText);
            if(json.user === '')
              self.router.transitionTo('index');
            self.store.user = json.user;
            self.marks = json.marks;
            self.students = json.students;
        }
        catch(e){
            self.router.transitionTo('index');
        }
    };

    req.open('POST', 'http://localhost:8080/StudentManagement/get-marks', false);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send();

    
    req.onload = function () {
      let response = this.responseText;
      console.log(this.responseText);
      $(document).ready(function(){
        if(response === 'Student'){
          $('td #add').hide();
          $('td #edit').hide();
          $('td #delete').hide();
        }
        else if(response === 'Staff'){
          $('td #delete').remove();
        }
        else if(response === 'HOD'){
          $('td #add').hide();
          $('td #delete').hide();
        }
      });
    }
    req.open('POST', 'http://localhost:8080/StudentManagement/get-role', false);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send('name='  + param.role);
    return self.marks;
  }
}
