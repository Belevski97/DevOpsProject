# DevOpsProject
Building a simple CI/CD pipeline from scratch using tools like Flask, Docker, Git, GitHub, Jenkins and Kubernetes.

## TASK
Build and deploy of a simple flask application.

## Preconditions
* Python
* Flask
* Docker
* Git
* GitHub
* Jenkins
* Kubernetes
* Windows machine

## Structure
* Dockerfile - Contains commands to build and run the docker image.
* Jenkinsfile - Contains the pipeline script which is involved in building, testing and deploying the application.
* app.py - Flask application.
* test.py - Test cases for the application.
* deployment.yaml - Kubernetes deployment file for the application.
* service.yaml - Kubernetes service file for the application.

## CI/CD pipeline steps
1. Create the Flask application.
2. Create a github repository and push code to it.
3. Write basic test cases.
4. Dockerise the application.
6. Start a Jenkins server on the host.
7. Write a Jenkins pipeline to build, test and push the Docker image to Docker Hub.
8. Set up Kubernetes on the host using Minikube.
9. Create a Kubernetes deployment and service for the application.
10. Use of Jenkins to deploy the application on Kubernetes.