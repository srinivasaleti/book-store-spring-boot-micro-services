install: ui-install  ## Install all packages
	yarn

k8s-up: catalog-db-k8s-deploy ## Deploy  k8s

k8s-down: catalog-db-k8s-destroy ## Deploy  k8s