package tk.huclele.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步配置.
 *
 * @author xuelele
 * @since 2023/9/25 13:40
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 3;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 100;
    /**
     * 队列最大长度 >=mainExecutor.maxSize
     */
    private static final int QUEUE_CAPACITY = 100;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final int KEEP_ALIVE_SECONDS = 0;
    /**
     * 线程池对拒绝任务(无线程可用)的处理策略
     */
    private final RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 线程池
     */
    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        asyncTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        asyncTaskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        asyncTaskExecutor.setThreadNamePrefix("async-tp-");
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }

    /**
     * 线程池
     *
     * @return 线程池
     */
    @Bean("asyncHookTaskExecutor")
    public AsyncTaskExecutor asyncHookTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        asyncTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        asyncTaskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        asyncTaskExecutor.setThreadNamePrefix("async-hook-tp-");
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}
