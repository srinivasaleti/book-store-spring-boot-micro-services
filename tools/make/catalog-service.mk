.PHONY: catelog-service-build  ## Build Catalog Service JAR and Docker image

CATALOG_DIR=catalog-service
IMAGE_NAME=catalog-service:latest

catalog-service-build:  ## Build JAR and Docker image
	cd $(CATALOG_DIR) && mvn clean package
	cd $(CATALOG_DIR) && docker buildx build --platform linux/amd64 -t catalog-service -f Dockerfile .

