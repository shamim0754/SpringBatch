package com.javaaround;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/*try {

		CommandLineJobRunner.main(new String[] {
         	"spring/jobs/batch-jobs.xml","helloWorldJob"
         });
		} catch (Exception e) {
			e.printStackTrace();
		}*/
         
        String[] springConfig  =
		{
			"spring/jobs/batch-jobs.xml"
		};

		ApplicationContext context =
			new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("helloWorldJob");
		try {
		JobParameters jobParameters = new JobParametersBuilder()
		.addString("filePath", "file:inputs/domain-*.csv")
		.toJobParameters();	
		JobExecution execution = jobLauncher.run(job, jobParameters);
		System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
