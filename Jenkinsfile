pipeline {
    agent any

    environment {
            // Define Docker Hub credentials ID - replace 'your-docker-credentials-id' with the ID you've configured in Jenkins
            DOCKERHUB_CREDENTIALS_ID = 'vladzak'
            // Define Docker Hub repository name
            DOCKERHUB_REPO = 'vladzak/fartocelkelvin'
            // Define Docker image tag
            DOCKER_IMAGE_TAG = 'latest'
    }

    tools {
        // Specify the version of Maven you have configured in Jenkins
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
                // Use Maven to build the project
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run Maven tests
                sh 'mvn test'
            }
            post {
                success {
                    // Publish JUnit test results
                    junit '**/target/surefire-reports/TEST-*.xml'
                    // Generate JaCoCo code coverage report
                    jacoco(execPattern: '**/target/jacoco.exec')
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image using the Dockerfile in the project root
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Log in to Docker Hub and push the Docker image
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images to prevent disk space issues on Jenkins agent
            sh "docker rmi \$(docker images -q ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}) --force"
        }
    }
}
