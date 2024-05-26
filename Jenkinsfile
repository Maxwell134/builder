pipeline {
    agent any

    environment {
        DOCKER_DETAILS = load 'sample.groovy'
        IMAGE_NAME = "${DOCKER_DETAILS.imageName}"
        IMAGE_TAG = "${DOCKER_DETAILS.tag}"
        DOCKER_USERNAME = "7002370412" // Replace with your Docker Hub username
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

    stages {
        stage('delete old container') {
            steps {
                sh 'docker rm -f ${IMAGE_NAME}'
                echo 'Deleting all existing containers'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.image('docker:latest').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        echo "Building Docker image ${IMAGE_NAME}:${IMAGE_TAG}"
                        sh """
                            docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                        """
                    }
                }
            }
        }

        stage('Tag Docker Image') {
            steps {
                script {
                    docker.image('docker:latest').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        echo "Tagging Docker image ${IMAGE_NAME}:${IMAGE_TAG} with username ${DOCKER_USERNAME}"
                        sh """
                            docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}
                        """
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.image('docker:latest').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                            echo "Pushing Docker image ${DOCKER_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
                            sh """
                                docker push ${DOCKER_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}
                            """
                        }
                    }
                }
            }
        }
    }
}
