# syntax=docker/dockerfile:1.6

FROM node:20.12-alpine AS frontend-builder
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm install --ignore-scripts
COPY frontend ./
RUN npm run build

FROM python:3.11-slim AS backend-base
ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1
WORKDIR /app
COPY backend/pyproject.toml backend/poetry.lock* ./
RUN pip install --no-cache-dir poetry==1.8.3 \
    && poetry config virtualenvs.create false \
    && poetry install --without dev --no-root
COPY backend ./backend
RUN poetry install --without dev

FROM python:3.11-slim AS runtime
WORKDIR /app
ENV PORT=8000
COPY --from=backend-base /app /app
COPY --from=frontend-builder /app/.next ./frontend/.next
COPY --from=frontend-builder /app/public ./frontend/public
COPY scripts ./scripts
EXPOSE 8000
HEALTHCHECK CMD curl --fail http://localhost:8000/health || exit 1
CMD ["poetry", "run", "uvicorn", "backend.app.main:app", "--host", "0.0.0.0", "--port", "8000"]
