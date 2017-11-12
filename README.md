# javatagsconverter

[online converter](https://javatagsconverter.herokuapp.com) for [JavaTags](https://github.com/manlioGit/javatags)


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