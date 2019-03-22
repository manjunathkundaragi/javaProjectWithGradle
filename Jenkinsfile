node {
   //def mvnHome
   def gradleHome
  stage('Checkout stage') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/gaurav1987singh/javaProjectWithGradle.git'
      echo "In Checkout stage"
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      //mvnHome = tool name: 'maven354', type: 'maven'
      gradleHome = tool name: 'gradle5', type: 'gradle' 
   }
   
   stage ('Clean'){
     if (isUnix()) {
         sh "'${gradleHome}\\bin\\gradle' clean"
      } else {
         bat script: "${gradleHome}\\bin\\gradle clean"
      }  
       
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${gradleHome}\\bin\\gradle' build -x test"
      } else {
         bat script: "${gradleHome}\\bin\\gradle build -x test"
      }
   }
   stage('UnitTest') {
     if (isUnix()) {
       sh "'${gradleHome}\\bin\\gradle' test"
       } else {
         
       bat script: "${gradleHome}\\bin\\gradle test"
      }
   }
   
   stage('SonarQube Analysis') {
      echo "SonarQube Analysis begin"
      bat script: "${gradleHome}\\bin\\gradle sonarqube -Dsonar.host.url=http://localhost:9000 -Dsonar.login=860b7e9e06174ee34335ad0f6f200f4fc8737725"
      }
      
      stage ('Deploy to Maven local'){
          echo "Deploy to Maven local begins"
          bat script: "${gradleHome}\\bin\\gradle uploadArchives -i"
          
      }
      
      stage('Deploy to Artifactory cloud'){
      def server = Artifactory.server 'gauavArtifactory'
      echo "$server"
      server.bypassProxy = true
      server.credentialsId = 'cloudArtifactory'
      server.connection.timeout = 300
      def uploadSpec = """{
 "files": [
  {
      "pattern": "${WORKSPACE}/build/libs/gradlePipeline_forJavaProject-1.0.jar",
      "target": "gauravLocalRepo/"
    }
 ]
   }"""
server.upload spec: uploadSpec
  } 
  
  stage('Deploy to Artifactory using gradle'){
      //sh label: '', script: "curl -uadmin:APAtN1w4MuidS5RwTrPPwvSmQtr -T ${WORKSPACE}/build/libs/gradlePipelineDemo_forJavaProject-1.0.jar https://artifactoryg01dy.jfrog.io/artifactoryg01dyg01dy/libs-snapshot/com/sample/program/gradlePipelineDemo_forJavaProject/1.0"
      bat script: "${gradleHome}\\bin\\gradle artifactoryDeploy"   
  }
}
