import { VisitRecord } from "../../../types";

const VisitRecordCard = ({ record }: { record: VisitRecord }) => {
  return (
    <div className="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl">
      <div className="p-8">
        <div className="uppercase tracking-wide text-sm text-indigo-500 font-semibold">
          Visitation Record
        </div>
        <p className="block mt-1 text-lg leading-tight font-medium text-black">
          {record.patientName}
        </p>
        <p className="mt-2 text-gray-500">
          Visitation Date: {record.date} at {record.time}
        </p>
        <p className="mt-2 text-gray-500">Physician: {record.physicianName}</p>
        <p className="mt-2 text-gray-500">Location: {record.location}</p>
        <p className="mt-4 text-gray-600">Notes: {record.notes}</p>
      </div>
    </div>
  );
};

export default VisitRecordCard;
