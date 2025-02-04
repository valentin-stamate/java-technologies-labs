
export class Endpoints {
  private static HOST = "http://localhost:8080/api";

  static LOGIN = `${Endpoints.HOST}/users/login`;
  static SIGNUP = `${Endpoints.HOST}/users/signup`;

  static AUTHOR_FILES = `${Endpoints.HOST}/author/files`;

  static ADMIN_FILES = `${Endpoints.HOST}/admin/files`;
}
