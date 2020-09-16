pipeline {
    agent { node { label 'master '} }
    stages {
        stage ('init'){
            steps {
                script {
                    String scriptFile = "${sh(script: 'echo -n $(pwd)', returnStdout: true)}" +
                            "/src/main/groovy/ru/megafon/lobachevsky/jenkins/cicd.groovy"
                    def myPipelineExecution = load(scriptFile)
                    myPipelineExecution.runPipeline(this)
                }
            }
        }
    }
}