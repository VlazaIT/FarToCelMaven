pipeline {
     agent any
        tools {
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
                         // Publish JUnit test results
                         junit '**/target/surefire-reports/TEST-*.xml'
                         // Generate JaCoCo code coverage report
                         jacoco(execPattern: '**/target/jacoco.exec')
                     }
                 }
             }
         }
}