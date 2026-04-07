library 'pipeline'

pipeline {
    agent {
        kubernetes {
            defaultContainer 'jnlp'
            label 'pipeline-jdk17'
        }
    }

    options {
        timeout( time: 2, unit: 'HOURS' )
    }

    environment {
        MNEMONIC        = 'acl'
        APP_NAME        = 'acl-operations'
        PRIMARY_BRANCH  = 'release/1.0'
        DEPLOY_PROJECT  = 'acl'
        DEPLOY_REPO     = 'acl-helm-deploy'
        TARGET_BRANCH   = 'stage-rnd'
    }

    stages {

        //-------Initialization--------------------------------------------
        stage("Initialization") {
            steps {
                initialization()
            }
        }

        //-------Builds----------------------------------------------------
        stage('Building Source') {
            steps {
                buildMaven()
            }
        }

        //-------Images----------------------------------------------------
        stage('Build Container Image') {
            steps {
                buildImage()
            }
        }

        //-------Sonar-----------------------------------------------------
        stage('Code Quality Scan') {
            steps {
                runSonar()
            }
        }

        //-------Deploy----------------------------------------------------
        stage('Update Deploy Repo Helm') {
            when { expression { env.GIT_BRANCH == env.PRIMARY_BRANCH } }
            steps {
                updateHelmDeployRepo()
            }
        }

    }

    post {
        always {
            generalCleanup()
        }
    }
}