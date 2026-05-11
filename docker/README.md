# Docker 文件说明

本目录用于说明企鹅蛋糕项目的容器化启动方式。

```bash
cp .env.example .env
./scripts/start-docker.sh
```

启动后：

- MySQL: `localhost:3306`
- Redis: `localhost:6379`
- RabbitMQ 管理台: `http://localhost:15672`，账号 `penguin/penguin123`
- MinIO 控制台: `http://localhost:9001`，账号 `penguin/penguin123456`

页面预览图见 `docker/images/` 与 `docs/images/`。
