### Spring Batch ###
Spring Batch is a framework for batch processing – execution of a series of jobs without manual intervention

### Spring Batch Architecture ###
![Image of spring batch](images/spring-batch-model.png)

### Concept ###
1. Job : represents sequences of actions or commands that have to be executed within the batch application
2. Step :  A job consists of many steps and each step consists of a READ-PROCESS-WRITE(chunk) task or single operation task (tasklet)
   1. ItemReader :  read data from the resources (csv, xml or database)
   2. ItemProcessor :  provides a hook to apply business logic
   3. ItemWriter :  write it to other resources (csv, xml and database)

```xml
<job id="helloWorldJob" xmlns="http://www.springframework.org/schema/batch">
  <step id="step1">
    <tasklet>
      <chunk reader="multiResourceReader" writer="flatFileItemWriter"
        commit-interval="1" />
    </tasklet>
  </step>
</job>
```

3. tasklet : means doing single task only, like clean up the resources after or before a step is started or completed
4. JobLauncher : responsible for starting a Job
5. Repositories : responsible of the storing and updating of metadata information related to Job instance executions and Job contexts

### How to start ###
1. create maven java project by following command <br>
`mvn archetype:generate -DgroupId=com.javaaround -DartifactId=SpringBatch -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`

add dependency at pom.xml
```xml
<properties>
<springframework.version>4.0.6.RELEASE</springframework.version>
<springbatch.version>3.0.1.RELEASE</springbatch.version>
<junit.version>4.8.1</junit.version>
</properties>

<dependencies>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${springframework.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework.batch</groupId>
    <artifactId>spring-batch-core</artifactId>
    <version>${springbatch.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework.batch</groupId>
    <artifactId>spring-batch-infrastructure</artifactId>
    <version>${springbatch.version}</version>
</dependency>
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>${junit.version}</version>
  <scope>test</scope>
</dependency>
```
### Input File ###

create inputs file [domain-1-3.csv]
```csv
1,facebook.com
2,yahoo.com
3,google.com
```

create domain-2-3.csv
```csv
1,facebook.com
2,yahoo.com
3,google.com
```

### Model of Input data ###

create Domain.java
```java
package com.javaaround;

public class Domain {

  int id;
  String domain;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

}
```

create application-context.xml at resources/confiq where job launcher with job respository info
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
    http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

  <!-- stored job-meta in memory -->

  <bean id="jobRepository"
    class="org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean">
    <property name="transactionManager" ref="transactionManager" />
  </bean>
  
  <bean id="transactionManager"
    class="org.springframework.batch.support.transaction.ResourcelessTransactionManager" />

  <bean id="jobLauncher"
    class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
    <property name="jobRepository" ref="jobRepository" />
  </bean>

</beans>
```

create batch-jobs.xml at resources/jobs where where define each batch

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:batch="http://www.springframework.org/schema/batch"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation=
    "http://www.springframework.org/schema/batch
    http://www.springframework.org/schema/batch/spring-batch-2.2.xsd
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
  ">
  <import resource="../config/application-context.xml" />
  <bean id="domain" class="com.javaaround.Domain" />
  
  <job id="helloWorldJob" xmlns="http://www.springframework.org/schema/batch">
    <step id="step1">
      <tasklet>
        <chunk reader="multiResourceReader" writer="flatFileItemWriter"
          commit-interval="1" />
      </tasklet>
    </step>
    
  </job>

  <bean id="multiResourceReader"
    class=" org.springframework.batch.item.file.MultiResourceItemReader">
    <property name="resources" value="file:inputs/domain-*.csv" />
    <property name="delegate" ref="flatFileItemReader" />
  </bean>

  <bean id="flatFileItemReader" class="org.springframework.batch.item.file.FlatFileItemReader">

    <property name="lineMapper">
      <bean class="org.springframework.batch.item.file.mapping.DefaultLineMapper">

        <property name="lineTokenizer">
          <bean
            class="org.springframework.batch.item.file.transform.DelimitedLineTokenizer">
            <property name="names" value="id, domain" />
          </bean>
        </property>
        <property name="fieldSetMapper">
          <bean
            class="org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper">
            <property name="prototypeBeanName" value="domain" />
          </bean>
        </property>
      </bean>
    </property>

  </bean>

  <bean id="flatFileItemWriter" class="org.springframework.batch.item.file.FlatFileItemWriter">

    <property name="resource" value="file:outputs/domain.all.csv" />
    <property name="appendAllowed" value="false" />
    <property name="lineAggregator">
      <bean
        class="org.springframework.batch.item.file.transform.DelimitedLineAggregator">
        <property name="delimiter" value="," />
        <property name="fieldExtractor">
          <bean
            class="org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor">
            <property name="names" value="id, domain" />
          </bean>
        </property>
      </bean>
    </property>

  </bean>
</beans>  
```