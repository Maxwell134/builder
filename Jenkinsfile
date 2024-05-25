import groovy.json.JsonSlurper

pipeline {
    agent any

    stages {
        stage('Deploy') {
            steps {
                script {
                    // Define the deploy method within the script block
                    def deploy = { inputFile ->
                        // Read inputs from JSON file
                        def input = new JsonSlurper().parse(new File(inputFile))

                        def dockerImage = input.docker.image
                        def dockerTag = input.docker.tag
                        def containerName = input.docker.container_name

                        // Check if the container is running
                        def containerExists = sh(
                            script: "docker ps -q -f name=${containerName}",
                            returnStdout: true
                        ).trim()

                        // If the container exists, stop and remove it
                        if (containerExists) {
                            echo "Container with name ${containerName} exists. Stopping and removing it."
                            sh "docker stop ${containerName}"
                            sh "docker rm ${containerName}"
                        } else {
                            echo "No container with name ${containerName} found."
                        }

                        // Build Docker image
                        sh "docker build -t ${dockerImage}:${dockerTag} ."

                        // Run Docker container
                        sh """
                        docker run --rm -d --name ${containerName} -p 80:8080 ${dockerImage}:${dockerTag}
                        """
                    }

                    // Call the deploy method with the path to the input JSON file
                    deploy('input.json')
                }
            }
        }
    }
}
