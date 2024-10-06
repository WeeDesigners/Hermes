app_version = $(ls -t target | grep Hermes | head -n1)

docker-build-local:
	eval $(minikube docker-env)
	mvn clean install
	docker build --no-cache --build-arg JAR_FILE=$(app_version) -t hermes .

docker-build-local-windows:
	powershell -Command "minikube -p minikube docker-env --shell powershell | Invoke-Expression"
	mvn clean install
	docker build --no-cache --build-arg JAR_FILE=$(app_version) -t hermes .

docker-build-and-push:
	mvn clean install
	docker build --no-cache --build-arg JAR_FILE=$(app_version) -t weedesigners/hermes .
	docker push weedesigners/hermes:latest