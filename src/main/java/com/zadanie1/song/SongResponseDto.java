package com.zadanie1.song;

import java.util.Map;

public record SongResponseDto(Map<Integer, Song> songs) {
}
