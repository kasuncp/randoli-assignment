# ----- IMPORTANT ------
# This script assumes that it will be invoked from the devops directory.

# Deploy workloads
kubectl apply -f ./k8s/workloads.yml

# Deploy services
kubectl apply -f ./k8s/services.yml