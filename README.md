# javatagsconverter

[online converter](https://javatagsconverter.herokuapp.com) for [JavaTags](https://github.com/manlioGit/javatags)


### Heroku deployment:

* SetUp. See [WAR Deployment](https://devcenter.heroku.com/articles/war-deployment)

```sh
heroku plugins:install heroku-cli-deploy
heroku war:deploy <path_to_war_file --app <app_name>
```

* Logs

```sh
heroku logs --tail
```