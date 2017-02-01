import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Um {

  private int[] circularTape;
  private int tapeLocation;
  private final int tapeSize = 65535;
  
  
  public Um() {
    this.circularTape = new int[tapeSize];
    this.tapeLocation = 0;
  }
  

  private static StringBuffer readFile(String fileName) {
    StringBuffer lines = new StringBuffer();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String currentLine;

      while ((currentLine = br.readLine()) != null) {
        lines.append(currentLine);
        lines.append("\n");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return lines;
  }

  
  private static String trim(StringBuffer fileContent) {
    String content = fileContent.toString();
    return content.trim().toLowerCase();
  }

  
  private static String[] getTokens(String sourceCode) {
    String[] cleanedCode = sourceCode.split("\\s+");
    String[] tokens = tokenize(cleanedCode).toArray(new String[0]);
    return tokens;
  }


  private static ArrayList<String> tokenize(String[] code) {
    int numTokens = code.length;
    ArrayList<String> tokenized = new ArrayList<String>();

    for (int i = 0; i < numTokens; i++) {
      if (isValidFiller(code[i])) {
        tokenized.add(code[i]);
      }
    }

    return tokenized;
  }


  private static boolean isValidFiller(String word) {
    boolean isValid = false;
    switch(word) {
      case "um":
      case "uh":
      case "er":
      case "ah":
      case "ok":
      case "so":
      case "well":
      case "like":
        isValid = true;
        break;
      default:
        isValid = false;
    }

    return isValid;
  }


  public String[] readProgram(String fileName) {
    StringBuffer fileContent = readFile(fileName);
    String trimmedContent = trim(fileContent);
    String[] tokens = getTokens(trimmedContent);
    return tokens;
  }

  
  public void eval(String[] tokens) {
    int numTokens = tokens.length;
    int loop = 0;

    try {
      for (int i = 0; i < numTokens; i++) {
        switch(tokens[i]) {
          
          case "um":
            this.tapeLocation++;
            break;
          
          case "uh":
            this.tapeLocation--;
            break;
          
          case "er":
            this.circularTape[this.tapeLocation]++;
            break;
          
          case "ah":
            this.circularTape[this.tapeLocation]--;
            break;
          
          case "ok":
            System.out.print((char)this.circularTape[this.tapeLocation]);
            break;
          
          case "so":
            this.circularTape[this.tapeLocation] = (int) System.in.read();
            break;
          
          case "well":
            if (this.circularTape[this.tapeLocation] == 0) {
              loop = 1;
              while (loop > 0) {
                i++;
                String token = tokens[i];
                if (token.equals("well")) {
                  loop++;
                }
                else if (token.equals("like")) {
                  loop--;
                }
              }
            }
            break;

          case "like":
            loop = 1;
            while (loop > 0) {
              i--;
              String token = tokens[i];
              if (token.equals("well")) {
                loop--;
              }
              else if (token.equals("like")) {
                loop++;
              }
            }
            i--;
            break;
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  
  public static void main(String args[]) {
    Um umm = new Um();
    String[] tokens = umm.readProgram(args[0]);
    umm.eval(tokens);
  }
}