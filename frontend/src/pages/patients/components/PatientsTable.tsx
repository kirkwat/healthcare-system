import { Patient } from "../../../types";

const PatientsTable = ({ patients }: { patients: Patient[] }) => {
  return (
    <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
      <table className="w-full text-sm text-left text-gray-500">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50">
          <tr>
            <th scope="col" className="py-3 px-6">
              Patient ID
            </th>
            <th scope="col" className="py-3 px-6">
              Name
            </th>
          </tr>
        </thead>
        <tbody>
          {patients.map((patient) => (
            <tr key={patient.patientId} className="bg-white border-b">
              <td className="py-4 px-6">{patient.patientId}</td>
              <td className="py-4 px-6">{patient.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PatientsTable;
