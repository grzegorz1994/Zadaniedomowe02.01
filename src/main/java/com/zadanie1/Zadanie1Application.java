package com.zadanie1;

import com.zadanie1.song.*;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class Zadanie1Application {

    private final SongProxy songProxy;

    public Zadanie1Application(SongProxy songProxy) {
        this.songProxy = songProxy;
    }

    public static void main(String[] args) {
        SpringApplication.run(Zadanie1Application.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void display(){
        try {
            PartiallyUpdateSongResponseDto responseDto = songProxy.modifySongById(1, new CreateSongRequestDto("In my blood", "Ariana Grande"));
            log.info(responseDto);
        } catch (FeignException.FeignClientException exception){
            log.error("client exception: " + exception.status());
        } catch (FeignException.FeignServerException exception){
            log.error("server exception: " + exception.status());
        } catch (RetryableException exception){
            log.error("retryable exception: " + exception.getMessage());
        } catch (FeignException exception){
            log.error(exception.getMessage());
            log.error(exception.status());
        }
    }

}
