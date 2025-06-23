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

k8s-up: start-minikube catalog-k8s-deploy rabbitmq-k8s-deploy order-service-k8s-deploy api-gateway-k8s-deploy \
ui-k8s-deploy ingress-deploy ## Deploy k8s

k8s-down: catalog-k8s-destroy order-service-k8s-destroy api-gateway-k8s-destroy rabbitmq-k8s-destroy \
ui-k8s-destroy ingress-destory ## Deploy  k8s

k8s-build: start-minikube
	@echo "Using Minikube's Docker daemon..."
	@eval $$(minikube docker-env) && \
	make catalog-service-build && \
	make order-service-build && \
	make api-gateway-build && \
	make ui-build