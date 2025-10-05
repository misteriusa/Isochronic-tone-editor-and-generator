import Link from "next/link";

type Tone = {
  id: number;
  name: string;
  carrier_hz: number;
  beat_hz: number;
};

export default function ToneTable({ tones }: { tones: Tone[] }) {
  return (
    <div className="overflow-hidden rounded border border-slate-800 bg-slate-900">
      <table className="min-w-full divide-y divide-slate-800">
        <thead className="bg-slate-900">
          <tr>
            <th className="px-4 py-3 text-left text-sm font-medium text-slate-300">Name</th>
            <th className="px-4 py-3 text-left text-sm font-medium text-slate-300">Carrier (Hz)</th>
            <th className="px-4 py-3 text-left text-sm font-medium text-slate-300">Beat (Hz)</th>
            <th className="px-4 py-3" aria-label="actions" />
          </tr>
        </thead>
        <tbody className="divide-y divide-slate-800">
          {tones.map((tone) => (
            <tr key={tone.id}>
              <td className="px-4 py-3 text-sm font-medium text-slate-100">{tone.name}</td>
              <td className="px-4 py-3 text-sm text-slate-300">{tone.carrier_hz.toFixed(1)}</td>
              <td className="px-4 py-3 text-sm text-slate-300">
                {tone.beat_hz.toFixed(1)}
              </td>
              <td className="px-4 py-3 text-right text-sm">
                <Link className="text-brand" href={`/tones/${tone.id}`}>
                  View
                </Link>{" "/* why: keep navigation ready for future tone detail page */}
              </td>
            </tr>
          ))}
          {tones.length === 0 && (
            <tr>
              <td colSpan={4} className="px-4 py-6 text-center text-sm text-slate-400">
                No tones saved yet.{" "/* why: reassure users the table will populate after saving */}
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
