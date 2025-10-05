import { render, screen } from "@testing-library/react";
import ToneTable from "@/components/ui/tone-table";

describe("ToneTable", () => {
  it("renders empty state", () => {
    render(<ToneTable tones={[]} />);
    expect(screen.getByText(/No tones saved yet/i)).toBeInTheDocument();
  });

  it("renders tone rows", () => {
    render(
      <ToneTable
        tones={[
          { id: 1, name: "Focus", carrier_hz: 220, beat_hz: 10 },
          { id: 2, name: "Relax", carrier_hz: 180, beat_hz: 7 }
        ]}
      />
    );
    expect(screen.getByText("Focus")).toBeInTheDocument();
    expect(screen.getByText("Relax")).toBeInTheDocument();
  });
});
