pipeline {

    agent any

    tools {
        jdk 'MyJDK'
        maven 'MyMaven'
    }

    options {
        timestamps()

        buildDiscarder(logRotator(
            daysToKeepStr: '7',
            numToKeepStr: '10'
        ))
    }

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                url: 'https://github.com/Dnyane12/dinshaws-erp-automation.git'
            }
        }

        stage('Build & Execute Tests') {
            steps {
                bat 'cd AIIMS_Project && mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {

                allure includeProperties: false,
                jdk: '',
                results: [[path: 'AIIMS_Project/target/allure-results']]
            }
        }
    }

    post {

        always {

            archiveArtifacts artifacts: 'AIIMS_Project/reports/**/*.png',
            fingerprint: true

            archiveArtifacts artifacts: 'AIIMS_Project/reports/**/*.html',
            fingerprint: true
        }

        success {
            echo 'Build Passed'
        }

        failure {
            echo 'Build Failed'
        }
    }
}