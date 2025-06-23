ingress-deploy:
	kubectl apply -f deployments/k8s/ingress

ingress-destroy:
	kubectl destroy -f deployments/k8s/ingress