import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Layout from "./Layout";
import LoginPage from "./pages/login";
import DashboardPage from "./pages/dashboard";

const App = () => {
  const isAuthenticated = false; // This will be dynamic based on actual authentication logic

  return (
    <Router>
      <Layout isAuthenticated={isAuthenticated}>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/dashboard" element={<DashboardPage />} />
        </Routes>
      </Layout>
    </Router>
  );
};

export default App;
