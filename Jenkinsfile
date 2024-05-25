import groovy.json.JsonSlurper

def deploy(inputFile) {
    // Read inputs from JSON file
    def input = new JsonSlurper().parse(new File(inputFile))

    def dockerImage = input.docker.image
    def dockerTag = input.docker.tag
    def containerName = input.docker.container_name

    // Delete existing container if available 
    try {
        sh "docker rm -f ${containerName}"
        echo "Deleted container ${containerName}"
    } catch (Exception e) {
        echo "No existing container to remove or failed to remove."
    }

    // Build Docker image
    sh "docker build -t ${dockerImage}:${dockerTag} ."

    // Run Docker container
    sh """
    docker run --rm -d --name ${containerName} -p 82:80 ${dockerImage}:${dockerTag}
    """
}

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
