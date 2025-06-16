install: ui-install  ## Install all packages
	yarn

start-minikube:
	@STATUS=$$(minikube status --format "{{.Host}}"); \
	if [ "$$STATUS" = "Running" ]; then \
		echo "✅ Minikube is already running."; \
	else \
		echo "🚀 Starting Minikube..."; \
		minikube start; \
	fi

stop-minikube:
	@minikube stop

k8s-up: start-minikube k8s-build catalog-k8s-deploy ## Deploy  k8s

k8s-down: catalog-k8s-destroy stop-minikube ## Deploy  k8s

k8s-build: start-minikube
	@echo "Using Minikube's Docker daemon..."
	@eval $$(minikube docker-env) && \
	make catalog-service-build
