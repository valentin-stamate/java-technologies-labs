import { Component } from '@angular/core';
import axios from "axios";
import {Endpoints} from "../../service/endpoints";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  async onFormSubmit(event: Event) {
    event.preventDefault();

    const form = event.currentTarget as HTMLFormElement;
    const formData = new FormData(form);

    try {
      const response = await axios.post(Endpoints.LOGIN, formData, {
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
