.PHONY: up down restart logs clean

DOCKER_COMPOSE_FILE_PATH= deployments/docker-compose/infra.yaml

build: catalog-service-build order-service-build ## Build all services

up: down ## Start the services
	docker compose -f $(DOCKER_COMPOSE_FILE_PATH) up --build -d

down: ## Stop and remove services
	docker compose -f $(DOCKER_COMPOSE_FILE_PATH) down

restart: ## Restart services
	docker compose -f $(DOCKER_COMPOSE_FILE_PATH) down && docker compose -f $(DOCKER_COMPOSE_FILE_PATH) up -d

logs: ## Show service logs
	docker compose -f i$(DOCKER_COMPOSE_FILE_PATH) logs -f

clean: ## Remove containers and volumes
	docker compose -f $(DOCKER_COMPOSE_FILE_PATH) down -v
