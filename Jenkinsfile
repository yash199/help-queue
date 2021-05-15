node {
	def app
	def image = 'registry.hub.docker.com/yashah19/help-queue'
	def branch = 'master'
	
	try {
		stage('Clone repository') {               
	    	git branch: branch,
	        	credentialsId: 'git',
	        	url: 'https://github.com/yash199/help-queue.git'
	    } 
	
		stage('Build JAR') {
	    	docker.image('maven:3.6.3-jdk-11').inside('-v /root/.m2:/root/.m2') {
	        	sh 'mvn -B clean package'
	        	stash includes: '**/target/help-queue.jar', name: 'jar'
	    	}
	    }
	     
	    stage('Build Image') {
	    	unstash 'jar'
			app = docker.build image + ':$BUILD_NUMBER'
	    }
	    
	    stage('Push') {
	    	docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {            
				app.push()
	        }    
	    }
	    
	    stage('Cleanup') {
			sh 'docker rmi ' + image + ':$BUILD_NUMBER'
	    }
	} catch (e) {
		echo 'Error occurred during build process!'
		echo e.toString()
		currentBuild.result = 'FAILURE'
	} finally {
        //junit '**/target/surefire-reports/TEST-*.xml'
	}
}