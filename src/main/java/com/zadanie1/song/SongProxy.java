package com.zadanie1.song;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "song-proxy")
@Component
public interface SongProxy {

    //display all elements map
    @GetMapping("/songs")
    SongResponseDto getAllSongs();

    //display elements with limit
    @GetMapping("/songs")
    SongResponseDto getSongsWithLimit(@RequestParam("limit") Integer limit);

    //display elements getById
    @GetMapping("/songs/{id}")
    SongSingleResponseDto getSongWithById(@PathVariable("id") Integer id);

    //create new song
    @PostMapping("/songs")
    SongSingleResponseDto createNewSong(@RequestBody CreateSongRequestDto request);

    //delete song byId
    @DeleteMapping("/songs/{id}")
    DeleteSongResponseDto deleteSongById(@PathVariable("id") Integer id);

    //update songs
    @PutMapping("/songs/{id}")
    UpdateSongResponseDto updateSongById(@PathVariable("id") Integer id, @RequestBody CreateSongRequestDto request);

    //update songs
    @PatchMapping("/songs/{id}")
    PartiallyUpdateSongResponseDto modifySongById(@PathVariable("id") Integer id, @RequestBody CreateSongRequestDto request);
}
