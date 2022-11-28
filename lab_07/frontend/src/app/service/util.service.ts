import {UserPayload} from "../pages/interfaces/interfaces";
import jwtDecode from "jwt-decode";

export class UtilService {
  static getUserPayloadFromToken(token: string): UserPayload {
    if (token == null) {
      location.href = "/login";
    }

    const payload = jwtDecode(token) as any;
    payload.exp = new Date(payload.exp * 1000);

    return payload as UserPayload;
  }

  static downloadBuffer(data: any, filename: string) {
    const url = window.URL.createObjectURL(new Blob([data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
  }

}
