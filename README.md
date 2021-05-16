***Help Queue Spring Boot Web Application***<br>
**Author**: Yash Shah<br>

**Application Details**

The Application allows user to create ticket by providing ticket name,author,description. 
The user can view all un-resolved (new) and resolved tickets in view pane.
They can also update ticket attributes or can even delete tickets from UI.
The user can mark the ticket as DONE or resolved and the ticket will move from active to resolved tickets pane.

**Video Link**: https://drive.google.com/file/d/1IbRw7W_Jh2GG78oOM2vXKh8jmCWiv9Z2/view?usp=sharing

**JIRA board**: https://barclays-t-shaped-program.atlassian.net/jira/software/c/projects/BTSP/boards/1/roadmap?shared=&atlOrigin=eyJpIjoiNDczOWFjNGViOGM5NDI1OTg0ZmM3NDlkNWQ2YmNkNGQiLCJwIjoiaiJ9

**JIRA Invite Link**: https://id.atlassian.com/invite/p/jira-software?id=n8iaYpY_TMeDu7Yb455gBA

**DockerHub Link**:https://hub.docker.com/repository/docker/yashah19/help-queue

**Technology Used**:
  1. Mysql for db
  2. Thymeleaf,css for front-end
  3. Java,spring for backend
  4. Maven 

**CI/CD pipeline configurations**
  1. EKS cluster files (cluster-create,workload-deploy,service yaml files)
  2. Docker (Dockerfile)
  3. Jenkinsfile
  4. Terraform (providers.tf and main.tf to create ec2 and rds instances)
  5. Ansible (playbooks to configure ec2 instance)
  6. Secrets (used in eks for dockerhub, used in jenkins for git,dockerhub)
