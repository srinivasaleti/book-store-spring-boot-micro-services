.PHONY: order-service-build  ## Build Order Service JAR and Docker image

ORDER_SERVICE_DIR=order-service
IMAGE_NAME=order-service:latest

order-service-build:  ## Build Order Service JAR and Docker image
	cd $(ORDER_SERVICE_DIR) && mvn clean package -DskipTests
	cd $(ORDER_SERVICE_DIR) && docker buildx build --platform linux/amd64 -t order-service -f Dockerfile .

order-service-k8s-deploy: ## Deploy order-db and order-service to Kubernetes
	kubectl apply -f deployments/k8s/order-db
	kubectl apply -f deployments/k8s/order-service

order-service-k8s-destroy:  ## Destroy order-db and order-service from Kubernetes
	kubectl delete -f deployments/k8s/order-service
	kubectl delete -f deployments/k8s/order-db
