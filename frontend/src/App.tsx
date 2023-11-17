import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Layout from "./layouts";
import LoginPage from "./pages/login";
import DashboardPage from "./pages/dashboard";
import { AuthProvider } from "./context/AuthProvider";
import RequireAuth from "./routes/RequireAuth";
import PatientsPage from "./pages/patients";

const ROLES = {
  Physician: "physician",
  Nurse: "nurse",
  Receptionist: "receptionist",
};

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Layout>
          <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route
              element={
                <RequireAuth allowedRoles={[ROLES.Physician, ROLES.Nurse]} />
              }
            >
              <Route path="dashboard" element={<DashboardPage />} />
            </Route>
            <Route
              element={<RequireAuth allowedRoles={[ROLES.Receptionist]} />}
            >
              <Route path="patients" element={<PatientsPage />} />
            </Route>
          </Routes>
        </Layout>
      </Router>
    </AuthProvider>
  );
};

export default App;
