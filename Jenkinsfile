pipeline {
    agent { { label 'master '} }
    stages {
        stage ('init'){
            steps {
                script {
                    String workspace = sh(script: 'pwd', returnStdout: true)
                    echo workspace
                    def myPipelineExecution = load("src/main/groovy/ru/megafon/lobachevsky/jenkins/cicd.groovy")
                    echo "${myPipelineExecution.getClass()}"

                    myPipelineExecution.call()
                }
            }
        }
    }
}