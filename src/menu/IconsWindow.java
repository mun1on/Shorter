package menu;

import menu.file_Reader_Writer.MyFileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static menu.MenuPanel.*;

class IconsWindow extends MenuBar{

    private List<String> pngFiles, websiteUrl;
    private static JFrame shorterJFrame;
    private JPanel shorterJPanel;
    private JLabel iconJLabel;
    private JLabel optionsJLabel;
    private static boolean fileExist;
   // private MenuBar mb;

    //Constructor
    IconsWindow() {
        prepareWindow();
        new MenuBar();
    }

    //GUI
    private void prepareWindow() {
        isFileExist(); // sprawdza czy są stworzone pliki
        if(fileExist){ // są stworzone
            getJFrameMenuBar().setState(JFrame.ICONIFIED); // chowa okno menuBar;
        }
        else{ // nie są stworzone
            createMkDir();// tworzy potrzebne foldery
            getJFrameMenuBar().setState(JFrame.NORMAL); // otwiera okno menuBar;
            getJFrameMenuBar().setVisible(true);
            synchronized (this){
                while(!fileExist){
                    try {
                        wait(1);
                    } catch (InterruptedException e) {
                        System.out.println("Message: "+e.getMessage());
                    }
                }
            }
        }
        setPngFiles(); // tworzy listę istniejących png
        setWebsiteUrl(); // tworzy listę stron www
        setOptionsJLabel(new JLabel()); // tworzy tools.png
        setJFrameForApp(); // tworzy okno dla aplikacji
        setJPanelForApp(); // tworzy panel dla aplikacji
        shorterJFrame.add(shorterJPanel); // dodaje panel do okna
        shorterJFrame.pack(); // pakuje w najmniejszy możliwy rozmiar
        shorterJFrame.setVisible(true); // ustawia widoczność okna
    }

    private void setJFrameForApp() {
        shorterJFrame = new JFrame();
        shorterJFrame.setUndecorated(true);
        shorterJFrame.setBackground(new Color(0, 0, 0, 0f));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        shorterJFrame.setLocation((width / 2) - (pngFiles.size() * 32) - 12, height - 120);
    }

    private void setJPanelForApp() {
        shorterJPanel = new JPanel();
        shorterJPanel.setBackground(new Color(0, 0, 0, 0f));

        for (Integer i = 0; i < getPngFiles().size(); i++) {
            setIconJLabel(i);
            shorterJPanel.add(iconJLabel);
        }
        setOptionsJLabel();
        shorterJPanel.add(optionsJLabel);
        shorterJPanel.setVisible(true);
    }

    private void setIconJLabel(int i) {

        ImageIcon imageIcon = new ImageIcon(getImagePathFile() + getPngFiles().get(i));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  // transform it back
        iconJLabel = new JLabel(imageIcon);
        String x = websiteUrl.get(i);
        iconJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(new URI(x));
                } catch (IOException | URISyntaxException e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    private void setOptionsJLabel(){

        MouseListener optionsLabelMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(getJFrameMenuBar().getState() == 1) // 1 - (JFrame.ICONIFIED);
                {
                    getJFrameMenuBar().setState(JFrame.NORMAL);  // setState(0);
                }
                else if(!getJFrameMenuBar().isVisible())
                {
                    getJFrameMenuBar().setVisible(true);
                }
            }
        };
        optionsJLabel.addMouseListener(optionsLabelMouseListener);
    }

    private void setPngFiles() {
        pngFiles = new ArrayList<>();
        new MyFileReader().readFile(getCommonPathFile(), getReadmeFileName(), pngFiles);
    }

    private void setWebsiteUrl() {
        websiteUrl = new ArrayList<>();
        new MyFileReader().readFile(getWebsitePathFile(), getWebsiteFileName(), websiteUrl);
    }

    private void setOptionsJLabel(JLabel optionsJLabel) {
        ImageIcon imageIcon = new ImageIcon("tools.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  // transform it back
        optionsJLabel.setIcon(imageIcon);

        this.optionsJLabel = optionsJLabel;
    }

    private List<String> getPngFiles() {
        return pngFiles;
    }

    static JFrame getShorterJFrame(){
        return shorterJFrame;
    }

    private void createMkDir() {
        File f;
        List<String> three = Arrays.asList("Images", "Common", "Websites");
        for (int i = 0; i < 3; i++) {
            f = new File(three.get(i));
            if (!f.exists())
                f.mkdir();
            else
                System.out.println(f.getName() + " exist");
        }
    }

    static boolean isFileExist()
    {
        File file = new File(getCommonPathFile()+getReadmeFileName());
        return fileExist = file.exists();
    }
}