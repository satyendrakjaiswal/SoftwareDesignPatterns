package DesignPattern.Structural.Facade;

// Facade
class MultimediaFacade {
    private AudioPlayer audioPlayer;
    private VideoPlayer videoPlayer;

    public MultimediaFacade() {
        this.audioPlayer = new AudioPlayer();
        this.videoPlayer = new VideoPlayer();
    }

    public void playAudio(String audioFile) {
        audioPlayer.play(audioFile);
    }

    public void playVideo(String videoFile) {
        videoPlayer.play(videoFile);
    }
}

// Subsystems
class AudioPlayer {
    public void play(String audioFile) {
        System.out.println("Playing audio: " + audioFile);
    }
}

class VideoPlayer {
    public void play(String videoFile) {
        System.out.println("Playing video: " + videoFile);
    }
}

// Client Code
public class FacadeClient {
    public static void main(String[] args) {
        MultimediaFacade facade = new MultimediaFacade();
        facade.playAudio("song.mp3");
        facade.playVideo("movie.mp4");
    }
}

