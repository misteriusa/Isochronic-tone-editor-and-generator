"""create tones table"""
from __future__ import annotations

from typing import Sequence

from alembic import op
import sqlalchemy as sa

revision: str = "0001"
down_revision: str | None = None
branch_labels: Sequence[str] | None = None
depends_on: Sequence[str] | None = None


def upgrade() -> None:
    """Create tones table."""

    op.create_table(
        "tone",
        sa.Column("id", sa.Integer, primary_key=True),
        sa.Column("name", sa.String(length=100), nullable=False),
        sa.Column("carrier_hz", sa.Float, nullable=False),
        sa.Column("beat_hz", sa.Float, nullable=False),
        sa.Column("created_at", sa.DateTime(timezone=False), nullable=False),
    )
    op.create_index("ix_tone_name", "tone", ["name"])


def downgrade() -> None:
    """Drop tones table."""

    op.drop_index("ix_tone_name", table_name="tone")
    op.drop_table("tone")
