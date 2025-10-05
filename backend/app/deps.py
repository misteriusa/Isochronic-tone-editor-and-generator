"""Reusable dependencies for FastAPI routes."""
from __future__ import annotations

from typing import Iterator

from fastapi import Depends
from sqlmodel import Session

from backend.db.session import ENGINE


def get_session() -> Iterator[Session]:
    """Yield a database session for request scope."""

    with Session(ENGINE) as session:
        yield session


SessionDep = Depends(get_session)
