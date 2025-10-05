#!/usr/bin/env bash
set -euo pipefail

python -m venv .venv
source .venv/bin/activate
pip install --upgrade pip
pip install poetry==1.8.3
poetry install --with dev --directory backend  # why: ensure local tooling mirrors CI stack

pushd frontend > /dev/null
npm install  # why: hydrate node_modules for Next.js dev server
popd > /dev/null

echo "Bootstrap complete. Use 'docker compose up --build' to start services."
