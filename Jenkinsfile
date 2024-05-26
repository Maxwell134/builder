
import groovy.json.JsonSlurperClassic 

pipeline {
    agent any
    stages {

        stage('Initialization') {

            steps {
                inputFile = readFile("{$env.WORKSPACE}/pipeline.json")
                parserJson = new JsonSlurperClassic().parserText(inputFile)
                println "Done reading JSON object"
                
            }
        }
        
        stage('Run Builder Script') {
            steps {
                script {
                    // Load the builder.groovy script
                        gv = load 'builder.groovy'

                    if (gv == null) {
                        error "Failed to load 'builder.groovy'"
                    }

                    // Call the deploy method from builder.groovy
                    gv.deploy(inputFile)
                    
                }
            }
        }
    }
}
