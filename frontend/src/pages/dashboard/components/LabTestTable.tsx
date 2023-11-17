import { LabTest } from "../../../types";

const LabTestTable = ({ labTests }: { labTests: LabTest[] }) => {
  return (
    <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
      <table className="w-full text-sm text-left text-gray-500">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50">
          <tr>
            <th scope="col" className="py-3 px-6">
              Test Type
            </th>
            <th scope="col" className="py-3 px-6">
              Date
            </th>
            <th scope="col" className="py-3 px-6">
              Result
            </th>
          </tr>
        </thead>
        <tbody>
          {labTests.map((test) => (
            <tr key={test.testId} className="bg-white border-b">
              <td className="py-4 px-6">{test.testType}</td>
              <td className="py-4 px-6">{test.date}</td>
              <td className="py-4 px-6">{test.result}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LabTestTable;
