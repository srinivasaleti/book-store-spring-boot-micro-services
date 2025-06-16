rabbitmq-k8s-deploy:
	kubectl apply -f deployments/k8s/rabbitmq

rabbitmq-k8s-destroy:
	kubectl delete -f deployments/k8s/rabbitmq
