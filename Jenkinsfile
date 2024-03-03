pipeline {
    agent any

    environment {
        // Docker Hub credentials ID
        DOCKERHUB_CREDENTIALS_ID = 'vladzak'
        // Docker Hub repository name
        DOCKERHUB_REPO = 'vladzak/fartocelkelvin'
        // Docker image tag
        DOCKER_IMAGE_TAG = 'latest'
    }

    tools {
        // Maven version as configured in Jenkins
        maven 'Maven-3.9.6'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/VlazaIT/FarToCelMaven'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    jacoco(execPattern: '**/target/jacoco.exec')
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Assuming Docker is available in the PATH for the Jenkins agent
                    bat "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Assuming you have the Docker Hub credentials set up in Jenkins
                    // This step might require additional configuration to securely handle credentials
                    bat "echo Logging into Docker Hub..."
                    bat "docker login -u vladzak -p yourDockerHubPassword"
                    bat "docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                }
            }
        }
    }

    post {
        always {
            bat "echo Cleaning up Docker images..."
            bat "docker rmi ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} || echo Image not found"
        }
    }
}
