import { Component } from '@angular/core';
import axios from "axios";
import {Endpoints} from "../../service/endpoints";
import {Cookies, CookieService} from "../../service/cookie.service";

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

      CookieService.setCookie(Cookies.AUTH, response.data);

      location.href = '/home';
    } catch (e) {
      console.log(e);
    } finally {

    }
  }
}
