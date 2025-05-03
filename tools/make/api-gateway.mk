.PHONY: api-gateway-build  ## Build API Gateway JAR and Docker image

API_GATEWAY_DIR=api-gateway
IMAGE_NAME=api-gateway:latest

api-gateway-build:  ## Build Build API Gateway JAR and Docker image
	cd $(API_GATEWAY_DIR) && mvn clean package
	cd $(API_GATEWAY_DIR) && docker buildx build --platform linux/amd64 -t api-gateway -f Dockerfile .

