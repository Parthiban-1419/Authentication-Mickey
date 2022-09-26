import Controller from '@ember/controller';
import { action } from '@ember/object';

export default class SecuredController extends Controller {
  @action
  fun() {
    console.log('fun()');
  }
}
