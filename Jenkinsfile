import groovy.json.JsonSlurper

pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        DOCKER_CONFIG_FILE = 'pipeline.json'
    }

    stages {
        stage('Deploy') {
            steps {
                script {
                    deploy(DOCKER_CONFIG_FILE)
                }
            }
        }
    }
}
