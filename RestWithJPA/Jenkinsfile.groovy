pileline{
  agent any
    stages{
       step("build"){
         steps{
            echo 'Starting SCM checkout'
            git credentialsId: 'github-cred' , url:'https://github.com/madeshp/Springboot.git'
         }
      }
    }

}