package com.mshri;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * MyFileProcessor defines the functions for file processing
 */
public class MyFileProcessor {

  /**
   * Sorts text rows and inserts serial of length serialLength after idLength in
   * each row
   * 
   * @param rawData      Text that needs to be processed
   * @param idLength     Lenth of the id after which serial will be added
   * @param serialLength Length of the serial number that will be inserted
   * @return String Returns the sorted and serial added text
   */
  public static String sortAndSerialize(String rawData, int idLength, int serialLength) {

    String[] textToProcess = rawData.split("\r\n");

    // Sort the string array
    Arrays.sort(textToProcess);

    // Insert serial to strings
    int count = 1;
    String prev = "";
    String serialFormat = "%0" + serialLength + "d";
    String[] processedText = new String[textToProcess.length];
    for (int i = 0; i < textToProcess.length; i++) {
      if (textToProcess[i].substring(0, idLength).equals(prev)) {
        count = count + 1;
        processedText[i] = textToProcess[i].substring(0, idLength) + String.format(serialFormat, count)
            + textToProcess[i].substring(idLength);
      } else {
        count = 1;
        processedText[i] = textToProcess[i].substring(0, idLength) + String.format(serialFormat, count)
            + textToProcess[i].substring(idLength);
      }
      prev = textToProcess[i].substring(0, idLength);
    }
    return String.join("\r\n", processedText);
  }

  /**
   * Gives a new name for file with date in file name
   * 
   * @param oldFileName Old or existing name of the file
   * @return String Returns a new name
   */
  public static String getNewFileName(String oldFileName) {
    Date date = new Date();
    SimpleDateFormat outputFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
    String newName = "Op_" + outputFormat.format(date) + oldFileName;

    return newName;
  }
}