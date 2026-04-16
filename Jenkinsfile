// library 'pipeline'
//
// pipeline {
//     agent {
//         kubernetes {
//             defaultContainer 'jnlp'
//             label 'pipeline-jdk17'
//         }
//     }
//
//     options {
//         timeout( time: 2, unit: 'HOURS' )
//     }
//
//     environment {
//         MNEMONIC        = 'acl'
//         APP_NAME        = 'acl-operations'
//         PRIMARY_BRANCH  = 'release/1.0'
//         DEPLOY_PROJECT  = 'acl'
//         DEPLOY_REPO     = 'acl-helm-deploy'
//         TARGET_BRANCH   = 'stage-rnd'
//     }
//
//     stages {
//
//         //-------Initialization--------------------------------------------
//         stage("Initialization") {
//             steps {
//                 initialization()
//             }
//         }
//
//         //-------Builds----------------------------------------------------
//         stage('Building Source') {
//             steps {
//                 buildMaven()
//             }
//         }
//
//         //-------Images----------------------------------------------------
//         stage('Build Container Image') {
//             steps {
//                 buildImage()
//             }
//         }
//
//         //-------Sonar-----------------------------------------------------
//         stage('Code Quality Scan') {
//             steps {
//                 runSonar()
//             }
//         }
//
//         //-------Deploy----------------------------------------------------
//         stage('Update Deploy Repo Helm') {
//             when { expression { env.GIT_BRANCH == env.PRIMARY_BRANCH } }
//             steps {
//                 updateHelmDeployRepo()
//             }
//         }
//
//     }
//
//     post {
//         always {
//             generalCleanup()
//         }
//     }
// }


pipeline {
    agent any

    environment {
        IMAGE_NAME = "acl-operations"
        IMAGE_TAG = "latest"
    }

    stages {

//         stage('Checkout') {
//             steps {
//                 git 'https://github.com/RAVITECHSOLUTIONS/acl-operations.git'
//             }
//         }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image with Podman') {
            steps {
                bat "podman build -t %IMAGE_NAME%:%IMAGE_TAG% ."
            }
        }

        stage('Stop Old Container') {
            steps {
                bat '''
                podman stop acl-operations-container || echo not running
                podman rm acl-operations-container || echo not exists
                '''
            }
        }

        stage('Run Container') {
            steps {
                bat """
                podman run -d -p 8080:8080 --name acl-operations-container %IMAGE_NAME%:%IMAGE_TAG%
                """
            }
        }
    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Build Failed!'
        }
    }
}
