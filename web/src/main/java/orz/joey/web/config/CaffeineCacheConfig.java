package orz.joey.web.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "myown.caching.caffeine")
public class CaffeineCacheConfig {
    private Map<String, String> specs;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        if (specs!=null && !specs.isEmpty()) {
            Set<CaffeineCache> caches = specs.entrySet().stream()
                    .map(entry -> buildCache(entry.getKey(), entry.getValue())).collect(Collectors.toSet());
            manager.setCaches(caches);
        }
        
        return manager;
    }

    private CaffeineCache buildCache(String cacheName, String cacheSpec) {
        return new CaffeineCache(cacheName, Caffeine.from(cacheSpec).build());
    }

    public Map<String, String> getSpecs() {
        return specs;
    }

    public void setSpecs(Map<String, String> specs) {
        this.specs = specs;
    }
}
