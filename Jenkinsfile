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
               bat 'pip install pytest'
               bat 'pytest test.py'
               bat 'pip install bandit'
               bat 'bandit -r ./app.py -f html -o report.html || exit 0'
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
               bat 'docker run --name  %CONTAINER_NAME% %DOCKER_HUB_REPO% /bin/bash'
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