/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

/**
 *
 * @author Eduardo Yaguar
 */
public class Songs {

    private int numbSong;
    private String songName;
    private String artistName;
    private String audioPath;
    private String imagePath;
    private String timeDuration;
    private String album;
    private String year;

    public Songs(int numbSong, String songName, String artistName, String audioPath, String imagePath, String timeDuration,String album,String year) {
        this.numbSong = numbSong;
        this.songName = songName;
        this.artistName = artistName;
        this.audioPath = audioPath;
        this.imagePath = imagePath;
        this.timeDuration = timeDuration;
        this.album = album;
        this.year = year;
    }

    public int getNumbSong() {
        return numbSong;
    }

    public void setNumbSong(int numbSong) {
        this.numbSong = numbSong;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }
    
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
