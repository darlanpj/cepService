package com.cep.service.Config;


import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.SocketUtils;
import org.springframework.util.StreamUtils;
import de.flapdoodle.embed.mongo.distribution.Version;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;


/**
 * Created by aluno on 28/03/2017.
 */
@Configuration
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MongoConfig{
    @Autowired
    private MongoProperties properties;

    @Lazy
    @Autowired
    MongoTemplate mongoClient;

    @PostConstruct
    public void after() {
        ClassLoader classLoader = getClass().getClassLoader();
        File collections = new File(classLoader.getResource("collections").getFile());
        Arrays.asList(collections.listFiles())
                .stream().forEach(file -> {
            try (
                    FileInputStream fileInputStream = new FileInputStream(file)) {
                String content = StreamUtils.copyToString(fileInputStream, Charset.defaultCharset());
                final BasicDBList collection = (BasicDBList) JSON.parse(content);
                mongoClient.insert(collection, file.getName().replace(".json", ""));
            } catch (Exception e) {
                throw new RuntimeException("erro configurar mongo", e);
            }

        });

    }


    @Bean(destroyMethod = "close")
    public MongoClient mongo(MongodProcess mongodProcess) throws IOException {
        Net net = mongodProcess.getConfig().net();
        properties.setHost(net.getServerAddress().getHostName());
        properties.setPort(net.getPort());
        return new MongoClient(properties.getHost(), properties.getPort());
    }

    @Bean(destroyMethod = "stop")
    public MongodProcess mongodProcess(MongodExecutable mongodExecutable) throws IOException {
        return mongodExecutable.start();
    }

    @Bean(destroyMethod = "stop")
    public MongodExecutable embeddedMongoServer(MongodStarter mongodStarter, IMongodConfig iMongodConfig) throws IOException {
        return mongodStarter.prepare(iMongodConfig);
    }

    @Bean
    public IMongodConfig mongodConfig() throws IOException {
        IMongoCmdOptions cmdOptions = new MongoCmdOptionsBuilder()
                .useStorageEngine("mmapv1")
                .build();

        return new MongodConfigBuilder()
                .net(new Net(SocketUtils.findAvailableTcpPort(), Network.localhostIsIPv6()))
                .cmdOptions(cmdOptions)
                .version(Version.Main.V3_2).build();
    }

    @Bean
    public MongodStarter mongodStarter() {
        return MongodStarter.getDefaultInstance();
    }
}