export interface UserPayload {
  username: string;
  userType: string;
  exp: Date;
  iss: string;
}

export interface File {
  id: string;
  documentId: string;
  author: string;
  name: string;
  size: number;
}
