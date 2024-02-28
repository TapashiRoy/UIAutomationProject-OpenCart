pipeline 
{
    agent any   

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 echo("Build the Automation Project")
            }           
        }   
       stage("Deploy to QA"){
            steps{
                echo("Deploy to qa")
            }
        }            
       stage('Run Regression Automation Tests') {
            steps {
                echo("Run Regression Tests in qa")                    
                }
            }  
       stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
               echo("Run Sanity Tests")
            }
        }   
        
         stage('Deploy to PROD') {
            steps {
               echo("deploy to PROD")
            }
        }   
   
    }
}
