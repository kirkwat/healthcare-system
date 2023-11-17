import { useState } from "react";
import VisitRecordCard from "./VisitRecordCard";
import LabTestTable from "./LabTestTable";

const DashboardPage = () => {
  const [queryType, setQueryType] = useState("");
  const [queryResult, setQueryResult] = useState("");
  const [patientId, setPatientId] = useState("");
  const [testName, setTestName] = useState("");
  const [testDate, setTestDate] = useState("");
  const [visitDate, setVisitDate] = useState("");

  const handleAllLabTests = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (patientId === "") return setQueryResult("Please enter a patient ID.");

    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/clinic/getLabTestsForPatient?patientId=${patientId}`,
        { credentials: "include" }
      );
      if (!response.ok) throw new Error("API error.");
      const data = await response.json();
      setQueryResult(JSON.stringify(data));
      setQueryType("allLabTests");
    } catch (error) {
      setQueryResult(`Error: ${error}`);
    }
  };

  const handleParticularLabTest = async (
    e: React.FormEvent<HTMLFormElement>
  ) => {
    e.preventDefault();

    if (patientId === "") return setQueryResult("Please enter a patient ID.");

    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/clinic/getLabTestsForPatient?patientId=${patientId}&testType=${testName}`,
        { credentials: "include" }
      );
      if (!response.ok) throw new Error("API error.");
      const data = await response.json();
      setQueryResult(JSON.stringify(data));
      setQueryType("particularLabTest");
    } catch (error) {
      setQueryResult(`Error: ${error}`);
    }
  };

  const handleOneLabTestRecord = async (
    e: React.FormEvent<HTMLFormElement>
  ) => {
    e.preventDefault();

    if (patientId === "") return setQueryResult("Please enter a patient ID.");

    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/clinic/getLabTestsForPatient?patientId=${patientId}&date=${testDate}`,
        { credentials: "include" }
      );
      if (!response.ok) throw new Error("API error.");
      const data = await response.json();
      setQueryResult(JSON.stringify(data));
      setQueryType("oneLabTestRecord");
    } catch (error) {
      setQueryResult(`Error: ${error}`);
    }
  };

  const handleVisitationRecord = async (
    e: React.FormEvent<HTMLFormElement>
  ) => {
    e.preventDefault();

    if (patientId === "") return setQueryResult("Please enter a patient ID.");

    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/clinic/getVisitRecordByDate?patientId=${patientId}&date=${visitDate}`,
        { credentials: "include" }
      );
      if (!response.ok) throw new Error("API error.");
      const data = await response.json();
      setQueryResult(JSON.stringify(data));
      setQueryType("visitationRecord");
    } catch (error) {
      setQueryResult(`Error: ${error}`);
    }
  };

  return (
    <main className="flex-grow flex justify-center items-start">
      <div className="container mx-auto p-4 h-full">
        <h1 className="text-2xl font-bold mb-4">Patient Information Search</h1>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">Patient ID to query</h3>
          <input
            type="text"
            value={patientId}
            onChange={(e) => setPatientId(e.target.value)}
            placeholder="Patient ID"
            className="p-2 border border-gray-300 rounded"
          />
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">
            Get patient's all lab tests
          </h3>
          <form
            onSubmit={handleAllLabTests}
            className="flex gap-3 items-center"
          >
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
            Get patient's particular lab tests
          </h3>
          <form
            onSubmit={handleParticularLabTest}
            className="flex gap-3 items-center"
          >
            <input
              type="text"
              required
              value={testName}
              onChange={(e) => setTestName(e.target.value)}
              placeholder="Lab Test Type"
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
            Get one particular record of a lab test for a patient on a specific
            date
          </h3>
          <form
            onSubmit={handleOneLabTestRecord}
            className="flex gap-3 items-center"
          >
            <input
              type="date"
              required
              value={testDate}
              onChange={(e) => setTestDate(e.target.value)}
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
            Get visitation record of a patient on a specified date/time
          </h3>
          <form
            onSubmit={handleVisitationRecord}
            className="flex gap-3 items-center"
          >
            <input
              type="date"
              required
              value={visitDate}
              onChange={(e) => setVisitDate(e.target.value)}
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
            {queryType === "visitationRecord" &&
              (() => {
                try {
                  const record = JSON.parse(queryResult);
                  if (
                    record &&
                    typeof record === "object" &&
                    !Array.isArray(record)
                  ) {
                    return <VisitRecordCard record={record} />;
                  } else {
                    return <p>No visitation record available.</p>;
                  }
                } catch (error) {
                  return <p>Error parsing visitation record.</p>;
                }
              })()}
            {(queryType === "allLabTests" ||
              queryType === "particularLabTest") &&
              (() => {
                try {
                  const resultsArray = JSON.parse(queryResult);
                  if (Array.isArray(resultsArray) && resultsArray.length > 0) {
                    return <LabTestTable labTests={resultsArray} />;
                  } else {
                    return <p>No lab tests available.</p>;
                  }
                } catch (error) {
                  return <p>Error parsing lab test data.</p>;
                }
              })()}
            {queryType === "oneLabTestRecord" &&
              (() => {
                try {
                  const resultsArray = JSON.parse(queryResult);
                  if (Array.isArray(resultsArray) && resultsArray.length > 0) {
                    return <LabTestTable labTests={[resultsArray[0]]} />;
                  } else {
                    return <p>No lab tests available.</p>;
                  }
                } catch (error) {
                  return <p>Error parsing lab test data.</p>;
                }
              })()}
          </div>
        )}
      </div>
    </main>
  );
};
export default DashboardPage;
