#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/.."
docker compose up -d mysql redis rabbitmq minio
echo "企鹅蛋糕中间件已启动：MySQL 3306 / Redis 6379 / RabbitMQ 15672 / MinIO 9001"
