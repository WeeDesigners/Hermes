docker-build-local:
	./scripts/build-docker.sh

docker-build-local-windows:
	powershell -Command "minikube -p minikube docker-env --shell powershell | Invoke-Expression"
	#need to change to powershell
	./scripts/buil-docker.sh


docker-build-and-push:
	./scripts/build-docker.sh
	sudo docker push weedesigners/hermes:latest
