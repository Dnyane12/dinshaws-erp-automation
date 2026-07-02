pipeline {

    agent any

    tools {
        jdk 'MyJDK'
        maven 'MyMaven'
    }

    options {
        timestamps()

        buildDiscarder(logRotator(
            daysToKeepStr: '30',
            numToKeepStr: '20'
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
                git (
				branch:'master',
                credentialsId:'github-pat-dinshaws'
                url:'https://github.com/Dnyane12/dinshaws-erp-automation.git'
                )
            }
        }

        stage('Build & Execute Tests') {
            steps {
                dir('AIIMS_Project') {
                    bat 'mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng-regression.xml'
               }
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