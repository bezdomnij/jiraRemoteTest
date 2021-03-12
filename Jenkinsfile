pipeline {
    agent any

    parameters {
                string(name: 'browserToRun', defaultValue: 'both', description: 'Browsers to run: Both, Chrome, Firefox')
                string(name: 'chrome', defaultValue: 'chrome', description: 'Chrome browser')
                string(name: 'firefox', defaultValue: 'firefox', description: 'Firefox browser')
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean'
            }
        }
               
        stage('Parallel tests') {
            parallel {
                stage('run with chrome') {
                    environment {
                        BROWSER="chrome"
                    }
                    when {
                         expression { params.browserToRun == 'both' || params.browserToRun == 'chrome' }
                         }
                    steps {
                         script {withCredentials([
                               usernamePassword(
                               credentialsId: 'jiraUser10',
                               passwordVariable: 'pass',
                               usernameVariable: 'username')]) {
                                    echo 'WITH CHROME: '
                                    sh 'echo $STAGE_NAME'
                                    sh 'echo $BROWSER'
                                    sh "mvn test -Dtest=AppTest -DjiraUsername=$username -DjiraPassword=$pass -DselPw=$pass"
                               }
                         }
                    }
                    
                    
                    post {
                        always {
                            junit allowEmptyResults: true,
                            testResults: 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('run with firefox') {
                    environment {
                        BROWSER="firefox"

                    }
                    when {
                         expression { params.browserToRun == 'both' || params.browserToRun == 'firefox' }
                         }
                    steps {
                         script {withCredentials([
                               usernamePassword(
                               credentialsId: 'jiraUser10',
                               passwordVariable: 'pass',
                               usernameVariable: 'username')]) {
                                    echo 'WITH FIREFOX: '
                                    sh 'echo $STAGE_NAME'
                                    sh 'echo $BROWSER'
                                    sh "mvn test -Dtest=AppTest -DjiraUsername=$username -DjiraPassword=$pass -DselPw=$pass"
                               }
                         }
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,
                            testResults: 'target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Cleanup phase: '
            cleanWs()
        }
    }
}
