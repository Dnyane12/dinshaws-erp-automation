pipeline {
    agent any

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/yourusername/yourrepo.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Automation Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Automation Execution Successful'
        }

        failure {
            echo 'Automation Execution Failed'
        }
    }
}