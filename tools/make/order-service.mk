.PHONY: order-service-build  ## Build Order Service JAR and Docker image

ORDER_SERVICE_DIR=order-service
IMAGE_NAME=order-service:latest

order-service-build:  ## Build Order Service JAR and Docker image
	cd $(ORDER_SERVICE_DIR) && mvn clean package -DskipTests
	cd $(ORDER_SERVICE_DIR) && docker buildx build --platform linux/amd64 -t order-service -f Dockerfile .
