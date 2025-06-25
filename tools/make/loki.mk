# Entry point
loki-install:
	@echo "👉 Checking if Loki is already installed..."
	@if helm ls -q | grep -q '^loki$$'; then \
		echo "✅ Loki is already installed."; \
	else \
		echo "🚀 Installing Loki stack (Grafana + Loki + Promtail)..."; \
		helm repo add grafana https://grafana.github.io/helm-charts; \
		helm repo update; \
		helm install loki grafana/loki-stack \
			--set grafana.enabled=true \
			--set promtail.enabled=true \
			--set loki.enabled=true; \
		echo "✅ Loki stack installed successfully."; \
	fi

# Check if Helm is installed, if not, install it
check-helm:
	@echo "🔍 Checking Helm..."
	@if ! command -v helm >/dev/null 2>&1; then \
		echo "🛠️  Helm not found. Installing..."; \
		$(MAKE) helm-install; \
	else \
		echo "✅ Helm is already installed."; \
	fi

# Install Helm (supports macOS and Linux)
helm-install:
	@UNAME_S=$$(uname -s); \
	if [ "$$UNAME_S" = "Darwin" ]; then \
		echo "🍎 Installing Helm on macOS..."; \
		brew install helm; \
	elif [ "$$UNAME_S" = "Linux" ]; then \
		echo "🐧 Installing Helm on Linux..."; \
		curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash; \
	else \
		echo "❌ Unsupported OS: $$UNAME_S"; \
		exit 1; \
	fi

# Install Prometheus if not already installed
prometheus-install:
	@echo "👉 Checking if Prometheus is already installed..."
	@if helm ls -q | grep -q '^prometheus$$'; then \
		echo "✅ Prometheus is already installed."; \
	else \
		echo "🚀 Installing Prometheus..."; \
		helm repo add prometheus-community https://prometheus-community.github.io/helm-charts; \
		helm repo update; \
		helm install prometheus prometheus-community/prometheus; \
		echo "✅ Prometheus installed successfully."; \
	fi
