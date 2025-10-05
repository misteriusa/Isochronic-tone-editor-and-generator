"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { retryWithBackoff } from "@/lib/api";

export default function LoginPage() {
  const router = useRouter();
  const [username, setUsername] = useState("demo");
  const [password, setPassword] = useState("demo");
  const [error, setError] = useState<string | null>(null);

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setError(null);
    try {
      const response = await retryWithBackoff(() =>
        fetch("http://localhost:8000/auth/login", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: new URLSearchParams({ username, password })
        })
      );
      if (!response.ok) {
        setError("Login failed");
        return;
      }
      await response.json();
      router.push("/dashboard"); // why: route to protected area once JWT flow succeeds
    } catch (cause) {
      setError("Network error");
    }
  }

  return (
    <main className="flex min-h-screen items-center justify-center">
      <form onSubmit={handleSubmit} className="w-full max-w-sm space-y-4 rounded bg-slate-900 p-6">
        <h1 className="text-2xl font-semibold">Welcome back</h1>
        <label className="block text-sm">
          Username
          <input
            className="mt-1 w-full rounded border border-slate-700 bg-slate-950 p-2"
            value={username}
            onChange={(event) => setUsername(event.target.value)}
          />
        </label>
        <label className="block text-sm">
          Password
          <input
            type="password"
            className="mt-1 w-full rounded border border-slate-700 bg-slate-950 p-2"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />
        </label>
        {error && <p className="text-sm text-red-400">{error}</p>}
        <button className="w-full rounded bg-brand px-4 py-2 font-medium text-brand-foreground" type="submit">
          Sign In
        </button>
      </form>
    </main>
  );
}
