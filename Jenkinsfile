pipeline {
    agent { node { label 'master '} }
    stages {
        stage ('init'){
            steps {
                script {
                    String workspace = sh(script: 'pwd', returnStdout: true)
                    echo workspace
                    Script myPipelineExecution = load("${workspace}/src/main/groovy/ru/megafon/lobachevsky/jenkins/cicd.groovy")
                    echo "${myPipelineExecution.getClass()}"

                    myPipelineExecution
                }
            }
        }
    }
}