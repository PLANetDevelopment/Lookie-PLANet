//package com.planet.develop.config;
//
//import com.planet.develop.Entity.MissionComplete;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.persistence.EntityManagerFactory;
//
//@Slf4j
//@RequiredArgsConstructor
//@Configuration
//public class missionJobConfig {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final EntityManagerFactory entityManagerFactory;
//    private static final int chunkSize = 10;
//
//    @Bean
//    public Job missionCompleteJob(){
//        return jobBuilderFactory.get("missionCompleteJob")
//                .start(missionCompleteStep())
//                .build();
//    }
//
//    @Bean
//    public Step missionCompleteStep(){
//        return stepBuilderFactory.get("missionCompleteStep")
//                .<MissionComplete, LastMissionComplete>chunk(chunkSize)
//                .reader(missionReader())
//                .processor(missionProcessor())
//                .writer(missionCompleteJpaItemWriter())
//                .build();
//
//    }
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<MissionComplete> missionReader() {
//        JpaPagingItemReader<MissionComplete> reader = new JpaPagingItemReader<MissionComplete>() {
//            @Override
//            public int getPage() {
//                return 0;
//            }
//        };
//        reader.setQueryString("select m FROM MissionComplete m");
//        reader.setName("missionReader");
//        reader.setEntityManagerFactory(entityManagerFactory);
//        reader.setPageSize(chunkSize);
//        return reader;
//    }
//
//
//    @Bean
//    @StepScope
//    public ItemProcessor<MissionComplete, LastMissionComplete> missionProcessor(){
//       return MissionComplete-> new LastMissionComplete(MissionComplete.getId(),MissionComplete.getEmoji(),MissionComplete.getName(),MissionComplete.getUser(),1);
//    }
//
//    @Bean
//    public JpaItemWriter<LastMissionComplete> missionCompleteJpaItemWriter() {
//        JpaItemWriter<LastMissionComplete> jpaItemWriter = new JpaItemWriter<>();
//        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
//        return jpaItemWriter;
//    }
//
//}
