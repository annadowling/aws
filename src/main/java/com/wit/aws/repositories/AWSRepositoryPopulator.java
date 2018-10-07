package com.wit.aws.repositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.aws.domain.AWS;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AWSRepositoryPopulator implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private final Jackson2ResourceReader resourceReader;
    private final Resource sourceData;

    private ApplicationContext applicationContext;

    public AWSRepositoryPopulator() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        resourceReader = new Jackson2ResourceReader(mapper);
        sourceData = new ClassPathResource("aws.json");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().equals(applicationContext)) {
            AWSRepository awsRepository =
                    BeanFactoryUtils.beanOfTypeIncludingAncestors(applicationContext, AWSRepository.class);

            if (awsRepository != null && awsRepository.count() == 0) {
                populate(awsRepository);
            }
        }

    }

    @SuppressWarnings("unchecked")
    public void populate(AWSRepository repository) {
        Object entity = getEntityFromResource(sourceData);

        if (entity instanceof Collection) {
            for (com.wit.aws.domain.AWS AWS : (Collection<AWS>) entity) {
                if (AWS != null) {
                    repository.save(AWS);
                }
            }
        } else {
            repository.save((AWS) entity);
        }
    }

    private Object getEntityFromResource(Resource resource) {
        try {
            return resourceReader.readFrom(resource, this.getClass().getClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
