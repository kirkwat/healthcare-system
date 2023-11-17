import { Physician } from "../../../types";

const PhysiciansTable = ({ physicians }: { physicians: Physician[] }) => {
  return (
    <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
      <table className="w-full text-sm text-left text-gray-500">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50">
          <tr>
            <th scope="col" className="py-3 px-6">
              Physician Name
            </th>
            <th scope="col" className="py-3 px-6">
              Number of Patients
            </th>
          </tr>
        </thead>
        <tbody>
          {physicians.map((physician) => (
            <tr key={physician.uid} className="bg-white border-b">
              <td className="py-4 px-6">{physician.name}</td>
              <td className="py-4 px-6">{physician.patientIds.length}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PhysiciansTable;
