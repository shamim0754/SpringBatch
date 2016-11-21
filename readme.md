### Spring Batch ###
Spring Batch is a framework for batch processing – execution of a series of jobs without manual intervention

### Spring Batch Architecture ###
![Image of spring batch](images/spring-batch-model.png)

### Concept ###
1. Job : represents sequences of actions or commands that have to be executed within the batch application
2. Step :  A job consists of many steps and each step consists of a READ-PROCESS-WRITE(chunk) task or single operation task (tasklet)
   1. ItemReader :  read data from the resources (csv, xml or database)
   2. ItemProcessor :  process it
   3. ItemWriter :  write it to other resources (csv, xml and database)
3. tasklet : means doing single task only, like clean up the resources after or before a step is started or completed