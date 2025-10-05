"""Business logic for tone operations."""
from __future__ import annotations

from typing import Iterable, Optional

from backend.app.repositories import tone as repo
from backend.app.schemas import ToneCreate, ToneRead, ToneUpdate
from backend.app.models import Tone
from sqlmodel import Session


def list_tones(session: Session) -> Iterable[ToneRead]:
    """Return a sequence of tone DTOs."""

    return [
        ToneRead.model_validate(row, from_attributes=True)
        for row in repo.list_tones(session)
    ]  # why: convert ORM rows to schema objects early for isolation


def create_tone(session: Session, payload: ToneCreate) -> ToneRead:
    """Create and return a tone DTO."""

    tone = repo.create_tone(session, payload)
    return ToneRead.model_validate(tone, from_attributes=True)


def get_tone(session: Session, tone_id: int) -> Optional[ToneRead]:
    """Retrieve a tone or None when missing."""

    tone = repo.get_tone(session, tone_id)
    return ToneRead.model_validate(tone, from_attributes=True) if tone else None


def update_tone(session: Session, tone_id: int, payload: ToneUpdate) -> Optional[ToneRead]:
    """Update a tone if it exists."""

    tone = repo.get_tone(session, tone_id)
    if tone is None:
        return None
    updated = repo.update_tone(session, tone, payload)
    return ToneRead.model_validate(updated, from_attributes=True)


def delete_tone(session: Session, tone_id: int) -> bool:
    """Delete a tone and signal success."""

    tone = repo.get_tone(session, tone_id)
    if tone is None:
        return False
    repo.delete_tone(session, tone)
    return True
