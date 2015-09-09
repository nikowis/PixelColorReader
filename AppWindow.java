
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppWindow extends JFrame{
    public static final int FRAME_WIDTH = 150;
    public static final int FRAME_HEIGHT = 200;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private ButtonGroup buttonGroup1;
    private JButton submmit;
    private JLabel fileNameLabel;
    private JTextField fileNameTextField;
    private PixelReader pixelReader;
    private JTextField xTextField;
    private JTextField yTextField;
    private JTextField pixelNumberTextField;
    private JLabel xLabel;
    private JLabel YLabel;
    private JLabel pixelNumberLabel;
    private int x,y,len;
    private String fileName;
    private FileSaver fileSaver;
    
	public AppWindow(){
            initComponents();
        }
        
        private boolean goodNumberFormat(){
            try{
                x = Integer.parseInt(xTextField.getText());
                y = Integer.parseInt(yTextField.getText());
                len = Integer.parseInt(pixelNumberTextField.getText());
            }catch(NumberFormatException e){
                return false;
            }
            return true;
        }
        
        private boolean goodFileNameFormat(){
            fileName = fileNameTextField.getText();
            char c;
            if(fileName.isEmpty())
                return false;
            for(int i = 0; i < fileName.length(); i++){
                c = fileName.charAt(i);
                if((c<'A'|| c>'Z') && (c<'a' || c > 'z') && (c<'0' || c>'9'))
                    return false;
            }
            return true;
        }
        
        private void initComponents(){
                jRadioButton1 = new JRadioButton("Horizontally");
                jRadioButton2 = new JRadioButton("Vertically");
                jRadioButton3 = new JRadioButton("Diagonally");
                buttonGroup1 = new ButtonGroup();
                submmit = new JButton("Read'em!");
                fileNameLabel = new JLabel("Name of the file :");
                fileNameTextField = new JTextField("");
                pixelReader = new PixelReader();
                xTextField = new JTextField("0");
                yTextField = new JTextField("0");
                pixelNumberTextField = new JTextField("0");
                xLabel = new JLabel("X coordinate");
                YLabel = new JLabel("Y coordinate");
                pixelNumberLabel = new JLabel("How many pixels to read?");
                
                this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.setTitle("Pixelator");
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                this.setBounds((int)dim.getWidth()/4,(int)dim.getHeight()/4, FRAME_WIDTH,FRAME_HEIGHT);
                this.setIconImage(new ImageIcon("logo.jpg").getImage());
                        
                buttonGroup1.add(jRadioButton1);
                buttonGroup1.add(jRadioButton2);
                buttonGroup1.add(jRadioButton3);
                
                getContentPane().setLayout(new GridLayout(12,1));
                
                add(jRadioButton1);
                add(jRadioButton2);
                add(jRadioButton3);
                add(xLabel);
                add(xTextField);
                add(YLabel);
                add(yTextField);
                add(pixelNumberLabel);
                add(pixelNumberTextField);
                add(fileNameLabel);
                add(fileNameTextField);
                add(submmit);
                
                
                submmit.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(goodFileNameFormat()){
                            if(goodNumberFormat()){
                                fileSaver = new FileSaver(fileNameTextField.getText());
                                if(jRadioButton1.isSelected()){
                                    pixelReader.readHorizontally(x,y,len, fileSaver);
                                }
                                else if(jRadioButton2.isSelected()){
                                    pixelReader.readVertically(x,y,len, fileSaver);
                                }
                                else if(jRadioButton3.isSelected()){
                                    pixelReader.readDiagonally(x,y,len, fileSaver);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(rootPane,
                                     "No options selected.",
                                     "Error",
                                     JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(rootPane,
                                     "Wrong number format",
                                     "Error",
                                     JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(rootPane,
                                     "Wrong file name format or empty field",
                                     "Error",
                                     JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
        }
            
        public static void main(String[] args){
            EventQueue.invokeLater(new Runnable(){
                public void run() {
                    AppWindow appWindow = new AppWindow();
                    appWindow.setVisible(true);
                    appWindow.setResizable(false);
                }
            });
        }
}
                