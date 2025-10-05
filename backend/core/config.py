"""Application configuration via environment variables."""
from __future__ import annotations

from functools import lru_cache
from typing import Final

from pydantic import Field
from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    """Typed settings for the FastAPI application."""

    app_name: str = Field(default="Isochronic Tone API")
    environment: str = Field(default="development")
    secret_key: str = Field(default="change-me")
    database_url: str = Field(
        default="postgresql+psycopg://postgres:postgres@db:5432/isochronic"
    )
    access_token_expiry_minutes: int = Field(default=15, ge=5, le=1440)
    refresh_token_expiry_minutes: int = Field(default=60 * 24, ge=60, le=60 * 24 * 14)

    class Config:
        env_file: str = ".env"
        env_file_encoding: str = "utf-8"
        case_sensitive: bool = False


@lru_cache(maxsize=1)
def get_settings() -> Settings:
    """Return cached settings instance to avoid re-parsing env vars."""

    return Settings()


SETTINGS: Final[Settings] = get_settings()
