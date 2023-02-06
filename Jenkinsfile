pipeline {
   agent any
  
   environment {
       DOCKER_HUB_REPO = "belevski97/dev-ops-project"
       CONTAINER_NAME = "flask"
       DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
 
   }
  
   stages {
 

       stage("Python Test") {
           steps {
               echo 'Python Testing..'
               bat 'pytest test.py && flake8'
               bat 'bandit -r app.py'
           }
       }
       stage('Build') {
           steps {
               echo 'Building..'
               bat 'docker build -t %DOCKER_HUB_REPO%:latest .'
           }
       }
       stage('Test') {
           steps {
               echo 'Testing..'
               bat 'docker stop  %CONTAINER_NAME% || true'
               bat 'docker rm  %CONTAINER_NAME% || true'
               bat 'docker run --name  %CONTAINER_NAME% %DOCKER_HUB_REPO%'
               bat 'docker scan --file Dockerfile %DOCKER_HUB_REPO%:latest'
           }
       }
       stage('Push') {
           steps {
               echo 'Pushing image..'
               bat 'echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%'
               bat 'docker push %DOCKER_HUB_REPO%:latest'
               }
           }
       stage('Deploy') {
           steps {
               echo 'Deploying....'
               bat 'kubectl apply -f deployment.yaml'
               bat 'kubectl apply -f service.yaml'
           }
       }       
   }
}