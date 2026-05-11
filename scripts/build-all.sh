#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/.."
(cd backend && mvn -DskipTests package)
(cd frontend && npm install && npm run build)
echo "构建完成"
