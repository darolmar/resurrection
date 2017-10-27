<h1>Resurrection:ExpenseApp</h1>
This file explains architecture and technical decissions taken for this project development.
<h3>What is ExpenseApp?</h3>
<p>ExpenseApp is a SPA CRUD application using Spring Boot, AngularJS, Spring Data, JPA/Hibernate and MySQL. In addition, we have also used the notion of profiles to deploy the application into two different databases [H2 & MySQL] to emulate the local and production environment, to be more realistic.</p>
<p>The following technologies stack being used:
<ul>
<li>SpringFox-Swagger2 2.7.0</li>
<li>Cucumber 2.1.0</li>
<li>Gerkhin 4.1.3</li>
<li>Spring Boot 1.5.8.RELEASE</li>
<li>Spring 4.3.5.RELEASE [transitively]</li>
<li>Spring data JPA 1.10.6.RELEASE [transitively]</li>
<li>Hibernate 5.0.12.Final [transitively]</li>
<li>MySQL 5.1.44 [transitively]</li>
<li>H2 1.4.196</li>
<li>Hikari CP 2.4.7 [transitively]</li>
<li>AngularJS 1.5.8</li>
<li>Gradle 4.2.1</li>
<li>JDK 1.8.0_66</li>
<li>Eclipse MARS.2</li>
</ul>
</p>
<h3>Why Gradle instead of Maven as building tool?</h3>
<p>Although I've found this <a href="https://dzone.com/articles/gradle-vs-maven" target="_blank">discussion</a> about what building tool to choose to build this project, to be sincere, I've decided to use Gradle because I'm experienced at Maven and using another tool as Gradle was a challenge for me and also a change to learn other way of doing things.</p>
<h3>Why SpringBoot?</h3>
<p>At the very begining of the project I decided to use SpringBoot because it was a suggestion of the homework. However, I must admit that after having read information about it (I recommend <a href="https://developer.ibm.com/dwblog/2017/spring-boot/" target="_blank">IBM devs blog</a> or <a href="https://spring.io/guides/gs/spring-boot/" target="_blank">SpringBoot official tutorial</a>), I think it's really amazing as focuses on developer productivity by providing smart autoconfiguration modules.</p>
<h3>Why HirakiCP to improve RDBMS?</h3>
<p>HikariCP is a high performance JDBC connection pool management library that will help us to improve RDBMS performance issues by marking transparent to the developer some tasks as connection management.</p>
<p>This <a href="https://techblog.topdesk.com/coding/choosing-a-database-connection-pool/">discussion</a> about how to choose a database connection pools recommends to choose between Tomcat and HikariCP. As it can be read in the conclussions HikariCP seems to perform better and that's the main reason that have lead us to choose it.
<img src="https://raw.githubusercontent.com/wiki/brettwooldridge/HikariCP/HikariCP-bench-2.6.0.png"/>
</p>
<p>This <a href="http://www.baeldung.com/hikaricp" target="_blank">article</a> gives further information about <a href="https://brettwooldridge.github.io/HikariCP/" target="_blank">HikariCP</a>.</p>
<h3>Why Gerkhin+Cucumber to test RESTful API?</h3>
<p>In this <a href="http://testerstories.com/2014/10/why-cucumber-why-gherkin/" target="_blank">discussion</a> you'll find some reasons about the importance of Gerkhin and Cucumber in software testing. However, two reasons have lead to implement a sample scenario:
<ul>
<li>They are offer a good change to implement BDD use cases and test them</li>
<li>It's easy to integrated them with SpringBoot (I've followed the guidelines offered in this nice <a href="https://moelholm.com/2016/10/15/spring-boot-1-4-gherkin-tests/" target="_blank">tutorial</a>).</li>
</ul>
<h3>Why Swagger2 to document RESTful API?</h3>
<p><a href="https://swagger.io/" target="_blank">Swagger</a> is the most popular tool for designing, building and documenting RESTful APIs. Additionally, it has nice integration with Spring Boot.
</p>
