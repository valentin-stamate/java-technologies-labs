import { Component } from '@angular/core';
import {Endpoints} from "../../service/endpoints";
import axios from "axios";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  async onFormSubmit(event: Event) {
    event.preventDefault();

    const form = event.currentTarget as HTMLFormElement;
    const formData = new FormData(form);

    try {
      const response = await axios.post(Endpoints.SIGNUP, formData, {
        headers: {
          'Content-Type': 'application/json',
        }
      });

      console.log(response);
    } catch (e) {
      console.log(e);
    } finally {

    }
  }
}
