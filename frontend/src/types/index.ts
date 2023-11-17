export interface User {
  uid: number;
  username: string;
  name: string;
  type: "physician" | "nurse" | "receptionist";
}

export interface LabTest {
  testId: number;
  patientId: number;
  testType: string;
  date: string;
  result: string;
}

export interface VisitRecord {
  visitId: number;
  patientId: number;
  patientName: string;
  physicianId: number;
  physicianName: string;
  date: string;
  time: string;
  location: string;
  notes: string;
}
