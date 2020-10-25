# Lab4

This program basically reads multiple CSV files that were downloaded from 
SpotifyCharts and then creates a sorted playlist of all the songs that appear
on the downloaded charts. The program scans in the input files and then creates
modified text files that contain only the song titles of the songs. 
There are multiple try/catch methods used to catch possible exceptions
and throw/block them while displaying the appropriate message if necessary.
After creating the text files, a queue is used to gather all the text files into
one big file which is then transformed into a playlist. The playlist is printed
to an output file labeled "UnsortedPlaylist". Since the file contains duplicates and
is unsorted, a new file is created which is sorted alphabetically and is labeled
"DraftPlayist". However, the recently file created still contains duplicates of
songs, so therefore a hashset was created and specific code was used to delete
duplicate song titles within the input file. After the previous steps were taken, 
a new and final file was created labeled "SortedPlaylist" which contains the
list of songs in alphabetical order without repetition. Furthermore, as a song
is listened to the program tracks and lists the song to the system output which
is titled as "History".

**You need to run the Lab4.java file which is located in the src folder. Make sure when you clone repository that all the "CSV" files are located in the Lab 4 folder so that the code can read it without throwing a Nullpointer error. No other files are needed except all the present "CSV" files because the program will create the other files listed.**
