ui-install:  ## Install UI package
	cd ui && yarn install

UI_DIR=ui

.PHONY: ui-build  ## Build UI docker image
ui-build:  ## Build UI Docker image
	cd $(UI_DIR) && docker buildx build --platform linux/amd64 -t bookstore-ui -f Dockerfile .

ui-k8s-deploy:
	kubectl apply -f deployments/k8s/ui/

ui-k8s-destroy:
	kubectl delete -f deployments/k8s/ui/