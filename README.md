# serverless-aux

![Deploy to GH Package](https://github.com/ajiniesta/serverless-aux/workflows/Deploy%20to%20GH%20Package/badge.svg)

Project with auxiliar functions to access Infrastructures in serverless functions

## Use and installation

The package is available as a maven dependency in a Github Package

To use it, first you need to add the next repository to your pom.xml

```
<repositories>
    <repository>
        <id>github</id>
        <url>https://ajiniesta:fd429b7ea852b17fd7ac3d240a81788a4e802535@maven.pkg.github.com/ajiniesta/serverless-aux</url>
    </repository>
</repositories>
```
Note that is used a PAT token that can be differ

And this dependency

```
<dependency>
  <groupId>com.iniesta.sless</groupId>
  <artifactId>serverless-aux</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Configuration

All the configuration, is injected via environment variables.

### Redis

- `redis-host`  Host of the redis server
- `redis-port`  Port of the redis server `default: 6379`

No support for tls

### Influxdb

- `influx-url"`  URL of the influx server
- `influx-user"`  User of the influx server
- `influx-password"`  Password used for the given user
- `influx-database"`  Database used for send points. If doesn't exist, will try to create it. `default: events`
- `influx-batch-size` Size of the batch to send points `default: 100`
- `influx-batch-time` Time to send points `default: 200`

### Mqtt

- `mqtt-url"`  URL of the MQTT broker
- `mqtt-topic`  Topic used to send messages in the MQTT broker `default: events`
