# Discovery Summary

## Recon

### Backend Libraries
1. FastAPI — modern async Python API framework with type hints.
2. SQLModel — SQLAlchemy + Pydantic models for typed ORM.
3. Alembic — database migrations for SQLAlchemy/SQLModel.
4. httpx — async-capable HTTP client for services.
5. Passlib — password hashing utilities.
6. PyJWT — JWT encoding/decoding library.
7. Uvicorn — ASGI server for FastAPI.
8. python-dotenv — environment variable loading helper.

### Frontend Libraries / UI Kits
1. Next.js — React meta-framework with App Router.
2. Tailwind CSS — utility-first CSS framework.
3. shadcn/ui — ready-to-compose headless UI primitives.
4. TanStack Query — async state management for React.
5. Zod — TypeScript schema validation.
6. Zustand — lightweight state store.
7. React Hook Form — performant forms.
8. Lucide Icons — icon set compatible with shadcn/ui.

### DevOps / Tooling
1. Docker — containerization for consistent environments.
2. Dev Containers — reproducible VS Code setup.
3. GitHub Actions — CI/CD automation.
4. Ruff — fast Python linter/formatter.
5. mypy — static type checker for Python.
6. Pre-commit — git hooks management.
7. ESLint — JavaScript/TypeScript linting.
8. Vitest — Vite-native unit testing for TS/JS.

### Data & Seed Resources
1. Public iso-chronic tone datasets — baseline audio samples.
2. AudioSet by Google — labeled audio events dataset.
3. FreeSound.org packs — community audio clips.
4. Sonify.org brainwave data — reference waveforms.
5. Kaggle Brainwave Entrainment dataset — EEG response samples.
6. Github.com/sox-samples — audio processing examples.
7. WaveSurfer.js sample collection — waveform JSON snippets.
8. Isochronic beat frequency tables — published clinical ranges.

## Curation Matrix (Fit-Score ≥ 70)

| Category | Asset | Fit-Score | Notes |
| --- | --- | --- | --- |
| Backend | FastAPI | 95 | Mature ASGI framework, MIT license, active community. |
| Backend | SQLModel | 88 | Typed ORM aligning with FastAPI, MIT. |
| Backend | Alembic | 85 | Battle-tested migrations, MIT. |
| Frontend | Next.js | 93 | App Router support, Vercel-backed, MIT. |
| Frontend | Tailwind CSS | 90 | Highly adopted utility CSS, MIT. |
| Frontend | shadcn/ui | 82 | Composable primitives, MIT, docs improving. |
| DevOps | Docker | 92 | Standard container platform, Apache-2.0. |
| DevOps | GitHub Actions | 88 | Native CI for GitHub repos. |
| DevOps | Ruff | 84 | Fast linting/formatting, MIT. |
| Data | FreeSound Packs | 78 | CC-BY licensed audio source. |
| Data | AudioSet | 74 | Creative Commons, requires subset usage. |
| Data | Isochronic Frequency Tables | 71 | Research-derived references for tone ranges. |

## Final Picks

| Category | Asset | License | Rationale |
| --- | --- | --- | --- |
| Backend | FastAPI | MIT | Async-first API with strong typing support. |
| Backend | SQLModel | MIT | Simplifies ORM modeling with Pydantic integration. |
| Backend | Alembic | MIT | Reliable schema migration management. |
| Frontend | Next.js | MIT | Enables hybrid rendering and App Router. |
| Frontend | Tailwind CSS | MIT | Rapid styling with design consistency. |
| Frontend | shadcn/ui | MIT | Provides reusable UI primitives for dashboards. |
| DevOps | Docker | Apache-2.0 | Ensures reproducible runtime across platforms. |
| DevOps | Ruff | MIT | Consolidates lint + format into a single tool. |
| DevOps | GitHub Actions | MIT | Built-in automation for tests and builds. |
| Data | FreeSound Packs | CC-BY | Seed tones for editor demos. |
| Data | AudioSet | CC-BY | Supplementary audio events for testing. |
| Data | Isochronic Frequency Tables | CC-BY | Ground truth for default presets. |

Notes:
- Fit-Score weights: Relevance 30%, License 15%, Maintenance 15%, Quality 15%, BusFactor 10%, Security 10%, Docs 5%.
- REVIEW: Validate dataset licensing before redistribution.
