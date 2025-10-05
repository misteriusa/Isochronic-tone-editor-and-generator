"""Backend integration tests for tone routes."""
from __future__ import annotations

from typing import Any

import os
import sys
from pathlib import Path

os.environ["DATABASE_URL"] = "sqlite:///./test.db"

sys.path.append(str(Path(__file__).resolve().parents[2]))  # why: allow tests to import backend package locally

from fastapi.testclient import TestClient
from sqlmodel import SQLModel

from backend.app.main import app
from backend.db.session import ENGINE, init_db


def setup_module(module: Any) -> None:
    """Recreate tables for isolated tests."""

    SQLModel.metadata.drop_all(ENGINE)
    init_db()


def test_create_and_list_tone() -> None:
    """Tone creation should persist and be retrievable."""

    client = TestClient(app)
    response = client.post(
        "/tones/",
        json={"name": "Focus", "carrier_hz": 220.0, "beat_hz": 10.0},
    )
    assert response.status_code == 201
    data = response.json()
    assert data["name"] == "Focus"

    list_response = client.get("/tones/")
    assert list_response.status_code == 200
    assert len(list_response.json()) == 1
