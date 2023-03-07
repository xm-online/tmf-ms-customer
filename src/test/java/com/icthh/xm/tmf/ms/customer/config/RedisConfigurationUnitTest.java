package com.icthh.xm.tmf.ms.customer.config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


public class RedisConfigurationUnitTest {

    @Test
    public void testRedisTemplateGeneration() {
        RedisConnectionFactory mockFactory = mock(RedisConnectionFactory.class);
        RedisConfiguration redisConfiguration = new RedisConfiguration();
        RedisTemplate<String, Object> redisTemplate = redisConfiguration.redisTemplate(mockFactory);

        assertEquals(redisTemplate.getConnectionFactory(), mockFactory);
    }
}
