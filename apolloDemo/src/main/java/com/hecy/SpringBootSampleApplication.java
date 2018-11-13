package com.hecy;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.hecy.bean.AnnotatedBean;
import com.hecy.springBootDemo.config.SampleRedisConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
//@SpringBootApplication(scanBasePackages = {"com.hecy", "com.hecy.springBootDemo"})
@SpringBootApplication
public class SpringBootSampleApplication {

    static Config config = ConfigService.getAppConfig();
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new SpringApplicationBuilder(SpringBootSampleApplication.class).run(args);
        AnnotatedBean annotatedBean = context.getBean(AnnotatedBean.class);
        SampleRedisConfig redisConfig = null;
        try {
            redisConfig = context.getBean(SampleRedisConfig.class);
        } catch (NoSuchBeanDefinitionException ex) {
            System.out.println("SampleRedisConfig is null, 'redis.cache.enabled' must have been set to false.");
        }

        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });

        System.out.println("SpringBootSampleApplication Demo. Input any key except quit to print the values. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
            if (!Strings.isNullOrEmpty(input) && input.trim().equalsIgnoreCase("quit")) {
                System.exit(0);
            }

            System.out.println(annotatedBean.toString());
            if (redisConfig != null) {
                System.out.println(redisConfig.toString());
            }
        }
    }
}
