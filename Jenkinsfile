import groovy.json.JsonSlurperClassic

pipeline {
    agent any
    stages {
        stage('Initialization') {
            steps {
                script {
                    def inputFileContent = readFile("${env.WORKSPACE}/pipeline.json")
                    def parserJson = new JsonSlurperClassic().parseText(inputFileContent)
                    println "Done reading JSON object"
                    // Stash the JSON content
                    writeFile(file: 'pipeline.json', text: inputFileContent)
                    stash includes: 'pipeline.json', name: 'jsonData'
                }
            }
        }

        stage('Run Builder Script') {
            steps {
                script {
                    // Unstash the JSON content
                    unstash 'jsonData'
                    def inputFileContent = readFile('pipeline.json')
                    def jsonData = new JsonSlurperClassic().parseText(inputFileContent)

                    // Load the builder.groovy script
                    def gv = load 'builder.groovy'

                    if (gv == null) {
                        error "Failed to load 'builder.groovy'"
                    }

                    // Call the deploy method from builder.groovy
                    gv.deploy(jsonData)
                }
            }
        }
    }
}
