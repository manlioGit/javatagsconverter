FROM tomcat:8.5-jre8

ENV SCALA_VERSION=2.12.15 \
    SCALA_HOME=/usr/share/scala \
    SBT_VERSION=1.6.2

WORKDIR /tmp

RUN curl -LO "https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz" \
 && tar xzf sbt-$SBT_VERSION.tgz -C /usr/share \
 && ln -s /usr/share/sbt/bin/sbt /usr/bin/

RUN curl -LO "https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz" \
 && tar xzf "scala-$SCALA_VERSION.tgz" \
 && mkdir "$SCALA_HOME" \
 && rm "/tmp/scala-$SCALA_VERSION/bin/"*.bat  \
 && mv "/tmp/scala-$SCALA_VERSION/bin" "/tmp/scala-$SCALA_VERSION/lib" "$SCALA_HOME" \
 && ln -s "$SCALA_HOME/bin/"* /usr/bin/ \
 && rm -rf /tmp/*

WORKDIR /app
COPY . /app

RUN sbt package
RUN mv target/scala-2.12/ROOT.war /usr/local/tomcat/webapps
