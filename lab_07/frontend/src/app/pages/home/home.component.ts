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
  tokenExpirationDate: Date;

  authorizationToken;

  fileName = '';
  files: File[] = [];

  sessionCountDown: string;

  constructor() {
    this.authorizationToken = CookieService.readCookie(Cookies.AUTH);
    this.userPayload = UtilService.getUserPayloadFromToken(this.authorizationToken);
    this.tokenExpirationDate = this.userPayload.exp;

    this.refreshFiles();

    this.sessionCountDown = UtilService.msToTime(this.tokenExpirationDate.getTime() - new Date().getTime());
    this.startCountDown();
  }

  async startCountDown() {
    return new Promise((resolve, reject) => {
      setInterval(() => {
        this.sessionCountDown = UtilService.msToTime(this.tokenExpirationDate.getTime() - new Date().getTime());
      }, 1000);
    });
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
      let endpoint = Endpoints.AUTHOR_FILES;

      if (this.userPayload.userType === 'ADMIN') {
        endpoint = Endpoints.ADMIN_FILES;
      }

      const response = await axios.get(endpoint, {
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
      let endpoint = Endpoints.AUTHOR_FILES;

      if (this.userPayload.userType === 'ADMIN') {
        endpoint = Endpoints.ADMIN_FILES;
      }

      const response = await axios.get(`${endpoint}/${file.documentId}`, {
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
      let endpoint = Endpoints.AUTHOR_FILES;

      if (this.userPayload.userType === 'ADMIN') {
        endpoint = Endpoints.ADMIN_FILES;
      }

      const response = await axios.delete(`${endpoint}/${file.documentId}`, {
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
