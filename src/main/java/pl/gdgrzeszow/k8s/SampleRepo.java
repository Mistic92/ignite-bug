package pl.gdgrzeszow.k8s;


import com.jmethods.catatumbo.DefaultDatastoreKey;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = {"sample-cache"})
public class SampleRepo {

    public static final String CHALLENGE_KEY = "partition_id+%7B%0A++project_id%3A+%22sample%22%0A%7D%0Apath+%7B%0A++kind%3A+%22SampleEntity%22%0A++name%3A+%22alluploaded%22%0A%7D%0A";


    @Cacheable(cacheNames = "sample-cache")
    public SampleEntity getSampleEntity() {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setKey(new DefaultDatastoreKey(CHALLENGE_KEY));
        return sampleEntity;
    }
}

