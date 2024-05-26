import groovy.json.JsonSlurperClassic

pipeline {
    agent any
    stages {
        stage('Initialization') {
            steps {
                script {
                    def inputFile = readFile("${env.WORKSPACE}/pipeline.json")
                    def parserJson = new JsonSlurperClassic().parseText(inputFile)
                    println "Done reading JSON object"
                    // Make the JSON data available for the next stage
                    
                }
            }
        }

        stage('Run Builder Script') {
            steps {
                script {
                    // Load the builder.groovy script
                    def gv = load 'builder.groovy'

                    if (gv == null) {
                        error "Failed to load 'builder.groovy'"
                    }

                    // Convert JSON string back to object for the deploy method
                    

                    // Call the deploy method from builder.groovy
                    gv.deploy(inputFile)
                }
            }
        }
    }
}
