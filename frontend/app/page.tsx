import Link from "next/link";

export default function HomePage() {
  return (
    <main className="mx-auto flex min-h-screen max-w-3xl flex-col items-center justify-center gap-8 text-center">
      <h1 className="text-4xl font-bold">Isochronic Tone Studio</h1>
      <p className="text-lg text-slate-300">
        Craft, audition, and share isochronic tone sessions with a modern toolchain.
      </p>
      <Link
        href="/login"
        className="rounded bg-brand px-4 py-2 text-brand-foreground transition hover:opacity-90"
      >
        Launch App
      </Link>
    </main>
  );
}
