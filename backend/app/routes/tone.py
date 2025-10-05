"""Tone API routes."""
from __future__ import annotations

from fastapi import APIRouter, HTTPException, Response, status

from backend.app import deps
from backend.app.schemas import ToneCreate, ToneRead, ToneUpdate
from backend.app.services import tone as service

router = APIRouter(prefix="/tones", tags=["tones"])


@router.get("/", response_model=list[ToneRead])
def list_tones(session=deps.SessionDep) -> list[ToneRead]:
    """Return a list of available tones."""

    return list(service.list_tones(session))


@router.post("/", response_model=ToneRead, status_code=status.HTTP_201_CREATED)
def create_tone(payload: ToneCreate, session=deps.SessionDep) -> ToneRead:
    """Create a new tone record."""

    return service.create_tone(session, payload)


@router.get("/{tone_id}", response_model=ToneRead)
def read_tone(tone_id: int, session=deps.SessionDep) -> ToneRead:
    """Retrieve a single tone."""

    tone = service.get_tone(session, tone_id)
    if tone is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Tone not found")
    return tone


@router.patch("/{tone_id}", response_model=ToneRead)
def update_tone(tone_id: int, payload: ToneUpdate, session=deps.SessionDep) -> ToneRead:
    """Update a tone resource."""

    tone = service.update_tone(session, tone_id, payload)
    if tone is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Tone not found")
    return tone


@router.delete(
    "/{tone_id}",
    status_code=status.HTTP_204_NO_CONTENT,
    response_class=Response,
)
def delete_tone(tone_id: int, session=deps.SessionDep) -> Response:
    """Delete a tone resource."""

    deleted = service.delete_tone(session, tone_id)
    if not deleted:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Tone not found")
    return Response(status_code=status.HTTP_204_NO_CONTENT)  # why: explicit response avoids FastAPI 204 body assertion
