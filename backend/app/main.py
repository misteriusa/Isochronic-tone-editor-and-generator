"""FastAPI application entrypoint."""
from __future__ import annotations

from datetime import datetime, timedelta
from typing import Annotated

from fastapi import Depends, FastAPI, Response, status
from fastapi.middleware.cors import CORSMiddleware
from fastapi.security import OAuth2PasswordRequestForm
from jose import jwt
from passlib.context import CryptContext

from backend.app.routes import tone
from backend.core.config import SETTINGS
from backend.db.session import init_db

pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")
app = FastAPI(title=SETTINGS.app_name)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.on_event("startup")
def on_startup() -> None:
    """Ensure tables exist on boot."""

    init_db()


@app.post("/auth/login")
def login(
    response: Response,
    form_data: Annotated[OAuth2PasswordRequestForm, Depends()],
) -> dict[str, str]:
    """Mock login issuing JWT tokens."""

    # REVIEW: replace with real user lookup
    if form_data.username != "demo" or not pwd_context.verify(
        form_data.password,
        pwd_context.hash("demo"),
    ):  # why: use bcrypt hash check even for mock user to mirror production flow
        response.status_code = status.HTTP_401_UNAUTHORIZED
        return {"detail": "Invalid credentials"}

    access_payload = {"sub": form_data.username, "exp": datetime.utcnow() + timedelta(minutes=SETTINGS.access_token_expiry_minutes)}
    refresh_payload = {"sub": form_data.username, "exp": datetime.utcnow() + timedelta(minutes=SETTINGS.refresh_token_expiry_minutes)}
    access_token = jwt.encode(access_payload, SETTINGS.secret_key, algorithm="HS256")
    refresh_token = jwt.encode(refresh_payload, SETTINGS.secret_key, algorithm="HS256")
    response.set_cookie(
        "refresh_token",
        refresh_token,
        httponly=True,
        secure=False,
    )  # why: persist refresh token in httpOnly storage for demo flow
    return {"access_token": access_token, "token_type": "bearer"}


@app.get("/health")
def healthcheck() -> dict[str, str]:
    """Simple health endpoint for uptime checks."""

    return {"status": "ok"}


app.include_router(tone.router)
