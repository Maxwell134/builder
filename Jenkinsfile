pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pull the latest code from the repository
                checkout scm
            }
        }

        stage('Read Configuration') {
            steps {
                script {
                    // Read the configuration from pipeline.json
                    def config = readJSON file: 'pipeline.json'
                    env.DOCKER_IMAGE = config.dockerImage
                    env.DEPLOYMENT_PORT = config.deploymentPort
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Load the sample.groovy script and build the Docker image
                    def dockerScript = load 'sample.groovy'
                    dockerScript.buildDockerImage(env.DOCKER_IMAGE, env.BUILD_NUMBER)
                }
            }
        }

       
        stage('Run Docker Container') {
            steps {
                script {
                    // Load the sample.groovy script and run the Docker container
                    def dockerScript = load 'sample.groovy'
                    dockerScript.runDockerContainer(env.DOCKER_IMAGE, env.BUILD_NUMBER, env.DEPLOYMENT_PORT)
                }
            }
        }
    }

    post {
        always {
            // Clean up the workspace
            cleanWs()
        }
    }
}
