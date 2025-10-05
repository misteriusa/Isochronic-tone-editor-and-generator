"""Pydantic schemas for API I/O."""
from __future__ import annotations

from datetime import datetime
from typing import Optional

from pydantic import BaseModel, Field


class ToneBase(BaseModel):
    """Shared attributes between Tone payloads."""

    name: str = Field(max_length=100)
    carrier_hz: float = Field(ge=1.0)
    beat_hz: float = Field(ge=0.1, le=40.0)


class ToneCreate(ToneBase):
    """Payload for creating a tone."""


class ToneRead(ToneBase):
    """Response payload for tone resources."""

    id: int
    created_at: datetime


class ToneUpdate(BaseModel):
    """Patch payload for tone fields."""

    name: Optional[str] = Field(default=None, max_length=100)
    carrier_hz: Optional[float] = Field(default=None, ge=1.0)
    beat_hz: Optional[float] = Field(default=None, ge=0.1, le=40.0)
