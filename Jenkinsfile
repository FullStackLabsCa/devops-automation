pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven '3.5.4'
    }

    stages {
        stage('Build') {
            steps {
                // Checkout code from a GitHub repository
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/FullStackLabsCa/devops-automation']]])

                // Run Maven on a Unix agent.
                sh "mvn clean install"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Build Docker Image') {
            steps {
                script{
                  sh "/usr/local/bin/docker build -t rajat003/devops-automation ."
                }
            }
        }
        stage('Upload Docker Image') {
            steps {
                script{
                 withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
                    sh 'cat ~/myDockerPwd.txt | /usr/local/bin/docker login --username rajat003 --password-stdin'
                }
                sh "/usr/local/bin/docker push rajat003/devops-integration"
                }
            }
        }
    }
    post {
            always {
                // script that should run always after the pipeline.
                //junit '**/target/*.xml'
//                script {
//                    if (currentBuild.currentResult == 'FAILURE') {
//                       step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "skillbazar@gmail.com", sendToIndividuals: true])
//                }
                   emailext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'

            }
        }
    }
}
