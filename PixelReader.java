

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PixelReader {
    private Robot robot;
    private Rectangle rectangle;
    private Dimension dimension;
    private BufferedImage bufferedImage;
    ArrayList<String> colorList;
    
    public PixelReader(){
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(PixelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        dimension= Toolkit.getDefaultToolkit().getScreenSize();
        rectangle = new Rectangle(dimension);
        bufferedImage=robot.createScreenCapture(rectangle);
    }
    
    public void refreshScreen()
    {
        bufferedImage = robot.createScreenCapture(rectangle);
    }
    
    public void readVertically(int x, int y, int width, FileSaver f){
        if(x < dimension.width && y <dimension.height && (x+width) < dimension.width){
            refreshScreen();
            colorList = new ArrayList<String>();
            for(int i = x; i < (x+width); i++){
                robot.mouseMove(i, y);
                robot.delay(5);
                System.out.println(bufferedImage.getRGB(i, y));
                colorList.add(String.valueOf(bufferedImage.getRGB(i, y)));
               
            }
            f.writeToFile(colorList.toArray(new String[colorList.size()]));
        }
        else
        {
            System.out.println("Your size is not that big, it's accually " 
                    + dimension.width+"x"+dimension.height+"...");
        }
    }
    
     public void readHorizontally(int x, int y, int height, FileSaver f){
        if(x < dimension.width && y <dimension.height && (y+height) < dimension.height){
            refreshScreen();
            colorList = new ArrayList<String>();
            for(int i = y; i < (y+height); i++){
                robot.mouseMove(x, i);
                robot.delay(5);
                System.out.println(bufferedImage.getRGB(x, i));
                colorList.add(String.valueOf(bufferedImage.getRGB(x, i)));
            }
            f.writeToFile(colorList.toArray(new String[colorList.size()]));
        }
        else
        {
            System.out.println("Your size is not that big, it's accually " 
                    + dimension.width+"x"+dimension.height+"...");
        }
    }
    
      public void readDiagonally(int x, int y, int diag, FileSaver f){
        if(x < dimension.width && y <dimension.height && (x+diag) < dimension.width && y+diag < dimension.height){
            refreshScreen();
            for(int i = x, j=y; i < (x+diag); i++, j++){
                robot.mouseMove(i, j);
                robot.delay(5);
                System.out.println(bufferedImage.getRGB(i, j));
                colorList.add(String.valueOf(bufferedImage.getRGB(i, j)));
            }
            f.writeToFile(colorList.toArray(new String[colorList.size()]));
        }
        else
        {
            System.out.println("Your size is not that big, it's accually " 
                    + dimension.width+"x"+dimension.height+"...");
        }
    }
     
//    public void findColor(int kolor){
//        refreshScreen();
//        for(int x=1;x<dimension.width;x++)
//        {
//            for(int y = 1; y<dimension.height;y++)
//            {
//                if(bufferedImage.getRGB(x,y)==kolor)
//                {
//                    robot.mouseMove(x, y);
//                    System.out.println("Here!!!");
//                    return;
//                }
//            }
//        }
//        System.out.println("No such color on you screen...");
//    }
}
