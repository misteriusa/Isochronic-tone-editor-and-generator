"""Data access helpers for tones."""
from __future__ import annotations

from typing import Iterable, Optional

from sqlmodel import Session, select

from backend.app.models import Tone
from backend.app.schemas import ToneCreate, ToneUpdate


def list_tones(session: Session) -> Iterable[Tone]:
    """Return all tones ordered by creation time."""

    statement = select(Tone).order_by(Tone.created_at.desc())
    return session.exec(statement)


def get_tone(session: Session, tone_id: int) -> Optional[Tone]:
    """Fetch a tone by primary key."""

    return session.get(Tone, tone_id)


def create_tone(session: Session, payload: ToneCreate) -> Tone:
    """Insert a new tone into the database."""

    tone = Tone(**payload.model_dump())
    session.add(tone)
    session.commit()  # why: persist before refresh for cross-request consistency
    session.refresh(tone)
    return tone


def update_tone(session: Session, tone: Tone, payload: ToneUpdate) -> Tone:
    """Apply partial updates to an existing tone."""

    data = payload.dict(exclude_unset=True)
    for key, value in data.items():
        setattr(tone, key, value)
    session.add(tone)
    session.commit()  # why: persist change immediately for tests using new sessions
    session.refresh(tone)
    return tone


def delete_tone(session: Session, tone: Tone) -> None:
    """Remove a tone instance."""

    session.delete(tone)
    session.commit()  # why: finalize deletion before dependency closes session
