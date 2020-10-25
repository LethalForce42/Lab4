/**
 * @author Adil Qumar
 * 
 * This program basically reads multiple CSV files that were downloaded from 
 * SpotifyCharts and then creates a sorted playlist of all the songs that appear
 * on the downloaded charts. The program scans in the input files and then creates
 * modified text files that contain only the song titles of the songs. 
 * There are multiple try/catch methods used to catch possible exceptions
 * and throw/block them while displaying the appropriate message if necessary.
 * After creating the text files, a queue is used to gather all the text files into
 * one big file which is then transformed into a playlist. The playlist is printed
 * to an output file labeled "UnsortedPlaylist". Since the file contains duplicates and
 * is unsorted, a new file is created which is sorted alphabetically and is labeled
 * "DraftPlayist". However, the recently file created still contains duplicates of
 * songs, so therefore a hashset was created and specific code was used to delete
 * duplicate song titles within the input file. After the previous steps were taken, 
 * a new and final file was created labeled "SortedPlaylist" which contains the
 * list of songs in alphabetical order without repetition. Furthermore, as a song
 * is listened to the program tracks and lists the song to the system output which
 * is titled as "History".
 */

package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Lab4 {
    
    public static void main(String args[]) throws Exception{
        Song w = new Song();
        w.readFile();
        // The files you'll be reading stored in a data structure to make it iterable
        String myFiles[] = {"Week1.txt", "Week2.txt", "Week3.txt", "Week4.txt", "Week5.txt", 
            "Week6.txt", "Week7.txt", "Week8.txt", "Week9.txt", "Week10.txt", "Week11.txt", "Week12.txt"};
        ArrayList<MyQueue> allTheWeeks = new ArrayList<MyQueue>();
        readDataFromFiles(allTheWeeks,myFiles);
        Playlist play1 = new Playlist();
        addToPlaylist(play1,allTheWeeks);
        play1.printPlaylist();
        w.reportSongs();
        SongHistoryList history = new SongHistoryList();
        history.addSong(play1.listenToSong());
        history.addSong(play1.listenToSong());
        history.addSong(play1.listenToSong());
        history.addSong(play1.listenToSong());
        history.printHistory();
        System.out.println();
        System.out.println("Latest song listen: "+history.lastListened().getTrack());
    }
    public static void addToPlaylist(Playlist play1,ArrayList<MyQueue> allTheWeeks) {
        for (int i = 0; i < allTheWeeks.size(); i++) {
            MyQueue Q = allTheWeeks.get(i);
            LinkedList<Song> list = Q.getListOfSong();
            for(int num = 0; num<list.size(); num++) {
                play1.addSong(list.get(num));
            }
        }
    }
    public static void readDataFromFiles(ArrayList<MyQueue> allTheWeeks,String myFiles[]) {
        for (int i = 0; i < myFiles.length; i++){
        MyQueue dataExtract = new MyQueue(myFiles[i]);
        allTheWeeks.add(dataExtract);
        }
    }
}
class Song {
    int cols;
    int rows;
    String[][] data;
    private String track;
    private Song next;
    public Song() {
        track = "";
        next = null;
    }
    public Song(String track) {
        this.track=track;
        next = null;
    }
    public Song getNext() {
        return next;
    }
    public void setNext(Song next) {
       this.next = next;
    }
    public String getTrack() {
        return track;
    }
    public void setTrack(String track) {
        this.track = track;
    }
    public void readFile(){
        PrintWriter writer1 = null;
        this.cols = 5;
        this.rows = 200;
        this.data = new String[rows][cols];
        File week1 = new File("Week1.csv");
        File week2 = new File("Week2.csv");
        File week3 = new File("Week3.csv");
        File week4 = new File("Week4.csv");
        File week5 = new File("Week5.csv");
        File week6 = new File("Week6.csv");
        File week7 = new File("Week7.csv");
        File week8 = new File("Week8.csv");
        File week9 = new File("Week9.csv");
        File week10 = new File("Week10.csv");
        File week11 = new File("Week11.csv");
        File week12 = new File("Week12.csv");
        Scanner sc;
        for(int j = 1; j < 13; j++){
            if(j == 1){
                try {
                    sc = new Scanner(week1);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week1.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 2){
                try {
                    sc = new Scanner(week2);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week2.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 3){
                try {
                    sc = new Scanner(week3);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week3.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 4){
                try {
                    sc = new Scanner(week4);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week4.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 5){
                try {
                    sc = new Scanner(week5);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week5.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 6){
                try {
                    sc = new Scanner(week6);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week6.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 7){
                try {
                    sc = new Scanner(week7);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week7.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 8){
                try {
                    sc = new Scanner(week8);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week8.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 9){
                try {
                    sc = new Scanner(week9);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week9.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 10){
                try {
                    sc = new Scanner(week10);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week10.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 11){
                try {
                    sc = new Scanner(week11);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week11.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
            if(j == 12){
                try {
                    sc = new Scanner(week12);
                    int row = 0, col = 0;
                    String ignore1 = sc.nextLine();
                    String ignore2 = sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        // The next two lines basically help the program interpret the text file.
                        // The two lines say to ignore commas in between quoations and then ignore
                        // the quotations when reading in the text into the array.
                        String newLine = line.replace("\"", "");
                        String[] strArr = newLine.split(",(?![^()]*\\))(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)");
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++; 
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        data[row][col] = strArr[col];col++;
                        col=0;row++; 
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("The text file not found");
                }
                catch (NumberFormatException e) {
                    System.out.println("The text file has wrong data");
                }
                try {
                    writer1 = new PrintWriter(new FileWriter("Week12.txt"));
                }
                catch (FileNotFoundException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File not found");
                }
                catch (IOException e) {
                    // The next line of code catches an exception and blocks it.
                    System.out.println("File is not accesible");
                }
                String[] songsArr = new String[rows];
                int index = 0;
                for(int i=0;i<rows;i++){
                    songsArr[index] = data[i][1];
                    index++;
                }
                for(int i=0;i<index;i++){
                    writer1.write(songsArr[i] + "\n");
                }
                writer1.flush();
                writer1.close();
            }
        }
    }
    public int getSongsExistsIndex(String[] arr, String artist){
        int result=-1;
        for(int i=0;i<rows;i++){
            if(artist.equals(arr[i])){ 
                return i;
            }
        }
        return result;
    }

    public void reportSongs() throws Exception{
        String inputFile = "UnsortedPlaylist.txt";
	String outputFile = "DraftPlaylist.txt";
	FileReader fileReader = new FileReader(inputFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	String inputLine;
	List<String> lineList = new ArrayList<String>();
	while ((inputLine = bufferedReader.readLine()) != null) {
            lineList.add(inputLine);
	}
	fileReader.close();
	Collections.sort(lineList);
	FileWriter fileWriter = new FileWriter(outputFile);
	PrintWriter out = new PrintWriter(fileWriter);
	for (String outputLine : lineList) {
            out.println(outputLine);
        }
	out.flush();
        out.close();
	fileWriter.close();
        // https://www.geeksforgeeks.org/java-program-delete-duplicate-lines-text-file/
        // Used the following code from the above website
        PrintWriter pw = new PrintWriter("SortedPlaylist.txt"); 
        BufferedReader br = new BufferedReader(new FileReader("DraftPlaylist.txt")); 
        String line = br.readLine(); 
        // set store unique values 
        HashSet<String> hs = new HashSet<String>(); 
        // loop for each line of input.txt 
        while(line != null) { 
            // write only if not 
            // present in hashset 
            if(hs.add(line)) 
                pw.println(line); 
            line = br.readLine(); 
        } 
        pw.flush(); 
        // closing resources 
        br.close(); 
        pw.close(); 
    }
}
class Playlist {
    private Song first;
    public Playlist(){
        first = null;
    }
    public void addSong(Song s){
        if(first == null) {
            s.setNext(first);
            first = s;
            return;
        }
        Song temp = first;
        while(temp.getNext()!= null){
            temp = temp.getNext();
        }
        temp.setNext(s);
    }
    public Song listenToSong(){
        Song temp = first;
        first = first.getNext();
        return temp;
    }
    public void printPlaylist() throws Exception {
        PrintStream ps = new PrintStream("UnsortedPlaylist.txt");
        Song temp = first;
        ps.println("Playlist");
        ps.println("________________________________________");
        while(temp != null) {
            ps.println(temp.getTrack());
            temp = temp.getNext();
        }
    }
}
class MyQueue{
    private LinkedList<Song> list;
    public MyQueue(String filename){
        list = new LinkedList<Song>();
        try {
            File file = new File(filename);
            FileReader filereader = new FileReader(file);
            BufferedReader br=new BufferedReader(filereader);
            String line;
            while(true) {
                line = br.readLine();
                if(line == null)
                    break;
                Song s = new Song(line);
                list.add(s);      
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Song> getListOfSong(){
        return list;
    }
}
class SongHistoryList {
    private Song first;
    public SongHistoryList(){
        first = null;
    }
    public void addSong(Song s){
        s.setNext(first);
        first = s;
    }
    public Song lastListened(){
        Song temp = first;
        first = first.getNext();
        return temp;
    }
    public void printHistory() {
        Song temp = first;
        System.out.println("History");
        System.out.println("-----------");
        while(temp != null) {
            System.out.println(temp.getTrack());
            temp = temp.getNext();
        }
    }
}