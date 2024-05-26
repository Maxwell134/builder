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

        stage('Delete Old Container') {
            steps {
                script {
                    sh """
                        if docker ps -a --format '{{.Names}}' | grep -Eq '^${IMAGE_NAME}\$'; then
                            docker rm -f ${IMAGE_NAME}
                        fi
                    """
                    echo 'Deleted old container if it existed'
                }
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
