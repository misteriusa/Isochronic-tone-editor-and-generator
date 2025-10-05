import ToneTable from "@/components/ui/tone-table";
import { retryWithBackoff } from "@/lib/api";

async function fetchTones() {
  const response = await retryWithBackoff(() =>
    fetch("http://localhost:8000/tones/", { cache: "no-store" })
  ); // why: disable Next.js caching to show latest tone edits
  if (!response.ok) {
    throw new Error("Failed to load tones");
  }
  return (await response.json()) as Array<{ id: number; name: string; carrier_hz: number; beat_hz: number }>;
}

export default async function DashboardPage() {
  const tones = await fetchTones();
  return (
    <main className="mx-auto flex min-h-screen max-w-5xl flex-col gap-6 p-8">
      <header>
        <h1 className="text-3xl font-semibold">Dashboard</h1>
        <p className="text-slate-300">Track and audition saved tone recipes.</p>
      </header>
      <ToneTable tones={tones} />
    </main>
  );
}
