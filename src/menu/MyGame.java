package menu;

import menu.file_Reader_Writer.MyFileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class MyGame extends MenuBar{
    private List<String> pngFiles, websiteUrl;
    static JFrame shorter,menuBarFrame;
    static JPanel shorterPanel;
    JLabel optionsJLabel;

    public static void main(String[] args) {
        MyGame method = new MyGame();
//        method.setPngFiles(new ArrayList<>());
//        method.getList(method.getPngFiles());
        method.getGUI();
    }

    // gui
    public JFrame getGUI() {
        createMkDir();
        setPngFiles(new ArrayList<>());
        setWebsiteUrl(new ArrayList<>());
        setOptionsJLabel(new JLabel());
        setShorter(new JFrame("Shorter Version 1.0"));
        shorter.add(getPanel(new JPanel()));
        shorter.pack();
        shorter.setVisible(true);
        return shorter;
    }

    public static JFrame getShorter() {
        return shorter;
    }

    public void setShorter(JFrame shorter) {
        shorter = new JFrame();
        shorter.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        shorter.setLocation((width / 2) - (getPngFiles().size() * 32) - 12, height - 120);
        shorter.setBackground(new Color(0, 0, 0, 0f));

        MyGame.shorter = shorter;
    }

    protected JPanel getPanel(JPanel panel) {
        panel.setBackground(new Color(0, 0, 0, 0f));

        for (Integer i = 0; i < getPngFiles().size(); i++) {
            ImageIcon imageIcon = new ImageIcon("Images\\" + getPngFiles().get(i));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);  // transform it back
            JLabel label = new JLabel(imageIcon);
            panel.add(label);
            String x = websiteUrl.get(i);
            label.addMouseListener(new MouseAdapter() {
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
            optionsJLabel.setLocation(0,-8);
            panel.add(optionsJLabel);

            optionsJLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new MenuBar();
                }
            });
        }
        panel.setVisible(true);
        this.shorterPanel = panel;
        return shorterPanel;
    }

    public void setPngFiles(List<String> listPngFiles) {

        try {
            pngFiles = new MyFileReader().readFile("Common\\", "readme.txt", listPngFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPngFiles() {
        return pngFiles;
    }

    public List<String> getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(List<String> websiteUrlTemp) {
        MenuPanel mp = new MenuPanel();
        try {
            websiteUrl = new MyFileReader().readFile(mp.getWebsitePathFile(), mp.getWebsiteFileName(), websiteUrlTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JLabel getOptionsJLabel() {
        return optionsJLabel;
    }

    public void setOptionsJLabel(JLabel optionsJLabel) {
        ImageIcon imageIcon = new ImageIcon("tools.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  // transform it back
        optionsJLabel.setIcon(imageIcon);

        this.optionsJLabel = optionsJLabel;
    }

    public void createMkDir() {
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

    public void getList(List<String> listOfStrings) {
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (i == 0)
                System.out.print("[" + listOfStrings.get(i));
            else if (i > 0 && i < listOfStrings.size() - 1)
                System.out.print(", " + listOfStrings.get(i));
            else
                System.out.println(", " + listOfStrings.get(i) + "]");
        }
    }
}