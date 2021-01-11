package com.iniesta.sless.infra;

import com.iniesta.sless.conf.Configurator;
import com.iniesta.sless.domain.Event;
import com.iniesta.sless.domain.SlessException;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.concurrent.TimeUnit;

public class InfluxSeries {

    private final Configurator conf;
    private final InfluxDB influxDB;
    private final String database;

    public InfluxSeries(Configurator conf) {
        this.conf = conf;
        String databaseURL = conf.get("influx-url");
        String userName = conf.get("influx-user");
        String password = conf.get("influx-password");
        this.database = conf.get("influx-database", "slessdb");
        this.influxDB = InfluxDBFactory.connect(databaseURL, userName, password);
        checkInitialization();
        influxDB.enableBatch(
                conf.getInt("influx-batch-size", 100),
                conf.getInt("influx-batch-time", 200),
                TimeUnit.MILLISECONDS);
        influxDB.setDatabase(database);
    }

    private void checkInitialization() {
        if(!existsDatabase(database)){
            createDatabase(database);
        }
    }

    private boolean existsDatabase(String database) {
        QueryResult query = influxDB.query(new Query("SHOW DATABASES", ""));
        if(query.hasError()) {
            throw new SlessException(String.format("Couldn't check if database %s exists",database));
        }
        return true;
    }

    private void createDatabase(String database) {
        QueryResult query = influxDB.query(new Query(String.format("CREATE DATABASE %s", database), ""));
        if(query.hasError()){
            throw new SlessException(String.format("Couldn't create the Database %s",database));
        }
        String retentionQuery = "CREATE RETENTION POLICY oneday ON pirates DURATION 1d REPLICATION 1";
        query = influxDB.query(new Query(retentionQuery, ""));
        if(query.hasError()){
            throw new SlessException(String.format("Couldn't create the Database %s",database));
        }
    }

    public void writeEvent(Event input){
        Point point = Point.measurement(input.getSignal())
                .time(input.getTimestamp().getTime(), TimeUnit.MILLISECONDS)
                .addField("user", input.getUser())
                .addField("device", input.getDevice())
                .addField("value", input.getValue())
                .build();
        influxDB.write(point);
    }
}
