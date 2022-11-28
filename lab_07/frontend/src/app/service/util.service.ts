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

  static msToTime(ms: number) {
    const totalSeconds = ms / 1000;

    const days = Math.floor(totalSeconds / (3600 * 24));
    const hours = Math.floor((totalSeconds - days * 3600 * 24) / 3600);
    const minutes = Math.floor((totalSeconds - days * 3600 * 24 - hours * 3600) / 60);
    const seconds = Math.floor((totalSeconds - days * 3600 * 24 - hours * 3600 - minutes * 60));

    return `${days}d ${hours}h ${minutes}m ${seconds}s`;
  }
}
