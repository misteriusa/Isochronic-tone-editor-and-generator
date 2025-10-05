"""Seed default tones into the database."""
from __future__ import annotations

from backend.app.schemas import ToneCreate
from backend.db.session import session_scope
from backend.app.repositories import tone as repo

SEED_TONES = [
    ToneCreate(name="Deep Focus", carrier_hz=200.0, beat_hz=8.0),
    ToneCreate(name="Calm Evening", carrier_hz=180.0, beat_hz=6.0),
]


def main() -> None:
    """Insert sample tones for local testing."""

    with session_scope() as session:
        for seed in SEED_TONES:
            repo.create_tone(session, seed)  # why: seed baseline presets for UI demos


if __name__ == "__main__":
    main()
