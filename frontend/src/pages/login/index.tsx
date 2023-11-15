import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [name, setName] = useState("");
  const [code, setCode] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const { user, login } = useAuth();

  useEffect(() => {
    if (user) {
      navigate("/dashboard");
    }
  }, [user, navigate]);

  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError("");
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/auth/login?username=${username}&password=${password}`,
        {
          method: "POST",
          credentials: "include",
        }
      );
      const data = await response.json();
      if (response.ok) {
        setName(data.name);
        setIsAuthenticated(true);
      } else {
        setError(data);
      }
    } catch (error) {
      setError("Failed to login.");
    }
  };

  const handleCodeVerification = async (
    e: React.FormEvent<HTMLFormElement>
  ) => {
    e.preventDefault();
    setError("");
    try {
      const response = await fetch(
        `${
          process.env.REACT_APP_API_URL
        }/auth/mfa?username=${username}&code=${Number(code)}`,
        {
          method: "POST",
          credentials: "include",
        }
      );
      const data = await response.json();
      if (response.ok) {
        login(data);
        navigate("/dashboard");
      } else {
        setError(data);
      }
    } catch (error) {
      setError("Failed to verify code.");
    }
  };

  return (
    <div className="max-w-md w-full space-y-8 p-6 rounded shadow-lg bg-white">
      <div>
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
          {isAuthenticated ? `Welcome back ${name}` : "Sign in to your account"}
        </h2>
        {error && (
          <p className="text-red-600 text-sm text-center mt-2">{error}</p>
        )}
      </div>
      {!isAuthenticated ? (
        <form className="mt-8 space-y-6" onSubmit={handleLogin}>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Username"
            className="appearance-none rounded relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-blue-300 focus:border-blue-300 focus:z-10 sm:text-sm"
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            className="appearance-none rounded relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-blue-300 focus:border-blue-300 focus:z-10 sm:text-sm"
          />
          <button
            type="submit"
            className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-500 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-300"
          >
            Sign in
          </button>
        </form>
      ) : (
        <form className="mt-8 space-y-6" onSubmit={handleCodeVerification}>
          <input
            type="text"
            value={code}
            onChange={(e) => setCode(e.target.value)}
            placeholder="Enter MFA Code"
            className="appearance-none rounded relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-300 focus:border-blue-300 focus:z-10 sm:text-sm"
          />
          <button
            type="submit"
            className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-500 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-300"
          >
            Verify MFA Code
          </button>
        </form>
      )}
    </div>
  );
};

export default LoginPage;
