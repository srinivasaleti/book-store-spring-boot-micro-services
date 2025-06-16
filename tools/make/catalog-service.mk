.PHONY: catelog-service-build  ## Build Catalog Service JAR and Docker image

CATALOG_DIR=catalog-service
IMAGE_NAME=catalog-service:latest

catalog-service-build:  ## Build JAR and Docker image
	cd $(CATALOG_DIR) && mvn clean package -DskipTests
	cd $(CATALOG_DIR) && docker buildx build --platform linux/amd64 -t catalog-service -f Dockerfile .

catalog-k8s-deploy: ## Deploy catalog-db and catalog-service to Kubernetes
	kubectl apply -f deployments/k8s/catalog-db
	kubectl apply -f deployments/k8s/catalog-service

catalog-k8s-destroy: ## Destroy catalog-db and catalog-service from Kubernetes
	kubectl delete -f deployments/k8s/catalog-service
	kubectl delete -f deployments/k8s/catalog-db