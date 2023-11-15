export interface User {
  uid: number;
  username: string;
  name: string;
  type: "physician" | "nurse" | "receptionist";
}
