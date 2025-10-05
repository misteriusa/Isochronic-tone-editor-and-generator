"""Database session utilities."""
from __future__ import annotations

from contextlib import contextmanager
from typing import Iterator

from sqlmodel import Session, SQLModel, create_engine

from backend.core.config import SETTINGS

ENGINE = create_engine(str(SETTINGS.database_url), echo=False, future=True)


def init_db() -> None:
    """Create database tables; safe for repeated calls."""

    SQLModel.metadata.create_all(ENGINE)  # why: bootstrap schema without migration run


@contextmanager
def session_scope() -> Iterator[Session]:
    """Provide transactional scope around operations."""

    session = Session(ENGINE)
    try:
        yield session
        session.commit()  # why: ensure atomic commits for each dependency scope
    except Exception:
        session.rollback()
        raise
    finally:
        session.close()
