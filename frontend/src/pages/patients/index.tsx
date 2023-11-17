import { useState } from "react";
import PhysiciansTable from "./components/PhysiciansTable";
import PatientsTable from "./components/PatientsTable";

const PatientsPage = () => {
  const [queryType, setQueryType] = useState("");
  const [queryResult, setQueryResult] = useState("");
  const [patientId, setPatientId] = useState("");
  const [physicianId, setPhysicianId] = useState("");

  const handleGetPhysicians = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/clinic/getPhysiciansForPatient?patientId=${patientId}`,
        { credentials: "include" }
      );
      if (!response.ok) throw new Error("API error.");
      const data = await response.json();
      setQueryResult(JSON.stringify(data));
      setQueryType("getPhysicians");
    } catch (error) {
      setQueryResult(`Error: ${error}`);
    }
  };

  const handleGetPatients = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/clinic/getPatientsForPhysician?physicianId=${physicianId}`,
        { credentials: "include" }
      );
      if (!response.ok) throw new Error("API error.");
      const data = await response.json();
      setQueryResult(JSON.stringify(data));
      setQueryType("getPatients");
    } catch (error) {
      setQueryResult(`Error: ${error}`);
    }
  };

  return (
    <main className="flex-grow flex justify-center items-start">
      <div className="container mx-auto p-4 h-full">
        <h1 className="text-2xl font-bold mb-4">Patient Physician Search</h1>
        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">
            Physician(s) of a patient
          </h3>
          <form
            onSubmit={handleGetPhysicians}
            className="flex gap-3 items-center"
          >
            <input
              type="text"
              required
              value={patientId}
              onChange={(e) => setPatientId(e.target.value)}
              placeholder="Patient ID"
              className="p-2 border border-gray-300 rounded"
            />
            <button
              type="submit"
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors"
            >
              Search
            </button>
          </form>
        </div>
        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">
            Patient(s) of a physician
          </h3>
          <form
            onSubmit={handleGetPatients}
            className="flex gap-3 items-center"
          >
            <input
              type="text"
              required
              value={physicianId}
              onChange={(e) => setPhysicianId(e.target.value)}
              placeholder="Physician ID"
              className="p-2 border border-gray-300 rounded"
            />
            <button
              type="submit"
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors"
            >
              Search
            </button>
          </form>
        </div>

        {queryResult && (
          <div className="mt-12 bg-gray-100 rounded">
            <h2 className="text-xl font-semibold mb-2">Query Results:</h2>
            {queryType === "getPhysicians" &&
              (() => {
                try {
                  const resultsArray = JSON.parse(queryResult);
                  if (Array.isArray(resultsArray) && resultsArray.length > 0) {
                    return <PhysiciansTable physicians={resultsArray} />;
                  } else {
                    return <p>No physicians available.</p>;
                  }
                } catch (error) {
                  return <p>Error parsing physicians data.</p>;
                }
              })()}
            {queryType === "getPatients" &&
              (() => {
                try {
                  const resultsArray = JSON.parse(queryResult);
                  if (Array.isArray(resultsArray) && resultsArray.length > 0) {
                    return <PatientsTable patients={resultsArray} />;
                  } else {
                    return <p>No patients available.</p>;
                  }
                } catch (error) {
                  return <p>Error parsing patients data.</p>;
                }
              })()}
          </div>
        )}
      </div>
    </main>
  );
};

export default PatientsPage;
