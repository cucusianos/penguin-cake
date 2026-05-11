#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/.."
echo "1. ./scripts/start-docker.sh"
echo "2. cd backend && mvn spring-boot:run"
echo "3. cd frontend && npm install && npm run dev"
