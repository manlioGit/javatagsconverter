# javatagsconverter

[online converter](https://javatagsconverter.herokuapp.com) for [JavaTags](https://github.com/manlioGit/javatags) and [Ruby-Tags](https://github.com/manlioGit/ruby-tags) 


### Heroku deployment:

* SetUp. See [WAR Deployment](https://devcenter.heroku.com/articles/war-deployment)

```sh
heroku plugins:install heroku-cli-deploy
```

* deploy

```sh
sbt package
heroku war:deploy target/scala-2.12/javatagsconverter_2.12-1.0.0-SNAPSHOT.war --app javatagsconverter
```

* Logs

```sh
heroku logs --tail
```

--- 

### Local run:
- Given that heroku has no more free plan, do the following to execute locally 

```sh
sbt package
docker run --rm -v $PWD/target/scala-2.12:/usr/local/tomcat/webapps -p 8080:8080 tomcat:8.5.84-jre8
```
go to http://localhost:8080