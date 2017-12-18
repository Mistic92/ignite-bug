package pl.gdgrzeszow.k8s;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;
import lombok.extern.log4j.Log4j2;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheRebalanceMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.cache.eviction.lru.LruEvictionPolicy;
import org.apache.ignite.cache.spring.SpringCacheManager;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class CacheConfig {


    public static final int LRU_EVICTION_MAX = 10000;
    public static final int MAX_AGE_MINUTES = 60 * 24;


    @Bean
    public CacheMode provideLocalCacheMode() {
        return CacheMode.LOCAL;
    }


    @Bean
    public CacheConfiguration provideTemplateCacheConfiguration(CacheMode cacheMode) {
        CacheConfiguration config = new CacheConfiguration();
        Duration duration = new Duration(TimeUnit.MINUTES, MAX_AGE_MINUTES);

        config.setCacheMode(cacheMode)
                .setRebalanceMode(CacheRebalanceMode.ASYNC)
                .setWriteSynchronizationMode(CacheWriteSynchronizationMode.PRIMARY_SYNC)
                .setCopyOnRead(true)
                .setEvictionPolicy(new LruEvictionPolicy(LRU_EVICTION_MAX))
                .setOnheapCacheEnabled(true)
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(duration))
                .setManagementEnabled(true)
                .setRebalanceTimeout(1000 * 15)
                .setAtomicityMode(CacheAtomicityMode.ATOMIC)
        ;
        return config;
    }

    @Bean
    public IgniteConfiguration provideDevIgniteConfiguration(List<CacheConfiguration> cacheConfigs) throws IgniteCheckedException {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setIgniteInstanceName("ignite");
        return cfg;
    }


    @Bean("cacheManager")
    public SpringCacheManager provideSpringCacheManager(IgniteConfiguration igniteConfiguration, CacheConfiguration template) {
        SpringCacheManager manager = new SpringCacheManager();
        manager.setIgniteInstanceName("ignite");
        manager.setConfiguration(igniteConfiguration);
        manager.setDynamicCacheConfiguration(template);
        return manager;
    }
}

