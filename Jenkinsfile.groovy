pipeline {
   agent any
  
   environment {
       DOCKER_HUB_REPO = "belevski97/flask-hello-world"
       CONTAINER_NAME = "flask-hello-world"
 
   }
  
   stages {
       /* We do not need a stage for checkout here since it is done by default when using the "Pipeline script from SCM" option. */
      
 
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
               bat 'docker run --name  %CONTAINER_NAME% %DOCKER_HUB_REPO% /bin/bash -c "pytest test.py && flake8"'
           }
       }
       stage('Deploy') {
           steps {
               echo 'Deploying....'
               bat 'docker stop %CONTAINER_NAME% || true'
               bat 'docker rm %CONTAINER_NAME% || true'
               bat 'docker run -d -p 5000:5000 --name %CONTAINER_NAME% %DOCKER_HUB_REPO%'
           }
       }
   }
}