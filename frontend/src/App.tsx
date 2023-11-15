import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Layout from "./layouts";
import LoginPage from "./pages/login";
import DashboardPage from "./pages/dashboard";
import { AuthProvider } from "./context/AuthProvider";
import { RequireAuth } from "./routes/RequireAuth";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Layout>
          <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route element={<RequireAuth />}>
              <Route path="/dashboard" element={<DashboardPage />} />
            </Route>
          </Routes>
        </Layout>
      </Router>
    </AuthProvider>
  );
};

export default App;
