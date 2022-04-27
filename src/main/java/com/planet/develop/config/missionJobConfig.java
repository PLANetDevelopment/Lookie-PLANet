package com.planet.develop.config;

import com.planet.develop.Entity.MissionComplete;
import com.planet.develop.Repository.MissionCompleteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class missionJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MissionCompleteRepository missionCompleteRepository;
    private static final int chunkSize = 10;

    @Bean
    public Job simpleJob(){
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1(){
        return stepBuilderFactory.get("simpleStep1")
                .<MissionComplete, MissionComplete>chunk(chunkSize)
                .reader(missionReader())
                .processor(missionProcessor())
                .writer(writer())
                .build();

    }
    @Bean
    @StepScope
    public JpaPagingItemReader<MissionComplete> missionReader() {
        return new JpaPagingItemReaderBuilder<MissionComplete>()
                .name("missionReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("select m FROM MissionComplete m")
                .build();
    }


    @Bean
    @StepScope
    public ItemProcessor<MissionComplete, MissionComplete> missionProcessor(){

        return new ItemProcessor<MissionComplete, MissionComplete>() {
            @Override
            public MissionComplete process(MissionComplete mission) throws Exception {
                missionCompleteRepository.delete(mission);
                return null;
            }
        };
    }
    @Bean
    @StepScope
    public JpaItemWriter<MissionComplete> writer(){
        return new JpaItemWriterBuilder<MissionComplete>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

}
