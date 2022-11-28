import { Component } from '@angular/core';
import {Cookies, CookieService} from "../../service/cookie.service";
import {File, UserPayload} from "../interfaces/interfaces";
import {UtilService} from "../../service/util.service";
import {Endpoints} from "../../service/endpoints";
import axios from "axios";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  userPayload: UserPayload;
  authorizationToken;

  fileName = '';
  files: File[] = [];

  constructor() {
    this.authorizationToken = CookieService.readCookie(Cookies.AUTH);
    this.userPayload = UtilService.getUserPayloadFromToken(this.authorizationToken);

    this.refreshFiles();
  }

  async onFileFormSubmit(event: Event) {
    event.preventDefault();

    const form = event.currentTarget as HTMLFormElement;
    const formData = new FormData(form);

    try {
      const response = await axios.post(Endpoints.AUTHOR_FILES, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': this.authorizationToken,
        }
      });

      await this.refreshFiles();
    } catch (e) {
      console.log(e);
    } finally {

    }
  }

  async refreshFiles() {
    try {
      const response = await axios.get(Endpoints.AUTHOR_FILES, {
        headers: {
          'Authorization': this.authorizationToken,
        }
      });

      this.files = response.data;
    } catch (e) {

    } finally {

    }
  }

  async onFileDownload(file: File) {
    try {
      const response = await axios.get(`${Endpoints.AUTHOR_FILES}/${file.documentId}`, {
        headers: {
          'Authorization': this.authorizationToken,
        }
      });

      UtilService.downloadBuffer(response.data, file.name);
    } catch (e) {
      console.log(e);
    } finally {

    }
  }

  async onFileRemove(file: File) {
    try {
      const response = await axios.delete(`${Endpoints.AUTHOR_FILES}/${file.documentId}`, {
        headers: {
          'Authorization': this.authorizationToken,
        }
      });

      await this.refreshFiles();
    } catch (e) {
      console.log(e);
    } finally {

    }
  }

  onFileAdded(fileInput: HTMLInputElement) {
    const files = fileInput.files;

    if (files != null) {
     this.fileName = files[0].name;
    }
  }

}
