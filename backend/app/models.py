"""SQLModel models for the application."""
from __future__ import annotations

from datetime import datetime, timezone
from typing import Optional

from sqlmodel import Field, SQLModel


class Tone(SQLModel, table=True):
    """Isochronic tone metadata persisted in the database."""

    id: Optional[int] = Field(default=None, primary_key=True)
    name: str = Field(index=True, max_length=100)
    carrier_hz: float = Field(ge=1.0, description="Base frequency in Hz")
    beat_hz: float = Field(ge=0.1, le=40.0, description="Isochronic beat frequency")
    created_at: datetime = Field(
        default_factory=lambda: datetime.now(timezone.utc),
        nullable=False,
    )
