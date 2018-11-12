package menu;

import menu.file_Reader_Writer.MyFileWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static menu.IconsWindow.getShorterJFrame;
import static menu.IconsWindow.isFileExist;

class MenuPanel {
    private JFrame optionFrame;
    private JPanel optionPanel;
    private JLabel header, imagePath, websiteURL, infoImage;
    private JButton imagePathButton, saveButton;
    private JTextField websiteURLjTF;
    private BufferedImage image;
    private JFileChooser jFileChooserImage;
    private static final String readmeFileName = "readme.txt";
    private static final String websiteFileName = "websites.txt";
    private static final String imagePathFile = "Images\\";
    private static final String websitePathFile = "Websites\\";
    private static final String commonPathFile = "Common\\";

    MenuPanel(JFrame frame) {

        optionFrame = frame;
        getAll();
    }

    private void getAll() {
        setAll();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 50, 0);
        optionPanel = new JPanel(new GridBagLayout());
        optionPanel.add(header, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);

        optionPanel.add(imagePath, c);
        c.gridx = 1;
        c.gridy = 1;
        optionPanel.add(imagePathButton, c);
        c.gridx = 2;
        c.gridy = 1;
        optionPanel.add(infoImage, c);
        c.gridx = 0;
        c.gridy = 2;
        optionPanel.add(websiteURL, c);
        c.gridx = 1;
        c.gridy = 2;
        optionPanel.add(websiteURLjTF, c);
        c.gridx = 1;
        c.gridy = 3;
        optionPanel.add(saveButton, c);
    }

    private void setAll() {

        setHeader(new JLabel("Add new website")); // 1
        setImagePath(new JLabel("Choose Image")); // 2
        setImagePathButton(new JButton("Add Image")); // 2
        setInfoImage(new JLabel("")); // 2
        setWebsiteURL(new JLabel("Address URL")); // 3
        setWebsiteURLjTF(new JTextField()); // 3
        setSaveButton(new JButton("Save"));
    }

    private void setHeader(JLabel header) {
        header.setFont(new Font("serif", Font.BOLD, 24));
        this.header = header;
    }

    private void setInfoImage(JLabel infoImage) {
        infoImage.setFont(new Font("serif", Font.PLAIN, 14));
        this.infoImage = infoImage;
    }

    private void setImagePath(JLabel imagePath) {
        this.imagePath = imagePath;
    }

    private void setWebsiteURLjTF(JTextField websiteURLjTF) {
        websiteURLjTF.setFont(new Font("serif", Font.PLAIN, 14));
        websiteURLjTF.setPreferredSize(new Dimension(75, 25));
        this.websiteURLjTF = websiteURLjTF;
    }

    private void setImagePathButton(JButton imagePathButton) {
        ActionListener imagePathButtonActionListener = e -> setJFileChooserImage(new JFileChooser());
        imagePathButton.addActionListener(imagePathButtonActionListener);
        this.imagePathButton = imagePathButton;
    }

    private void setSaveButton(JButton saveButton) {
        ActionListener saveButtonActionListener = e -> {
            if (websiteURLjTF.getText().contains("http://")) {
                String imageName = getJFileChooserImage().getSelectedFile().getName();
                File file = new File(imagePathFile + imageName);

                try {
                    ImageIO.write(image, "png", file);
                    MyFileWriter fw = new MyFileWriter(websitePathFile, websiteFileName, true);
                    fw.writeToFile(websiteURLjTF.getText());
                    MyFileWriter mfw = new MyFileWriter(commonPathFile, readmeFileName, true);
                    mfw.writeToFile(jFileChooserImage.getSelectedFile().getName());
                    optionPanel.setVisible(false);
                    optionFrame.setVisible(false);
                    isFileExist();
//                    getShorterJFrame().setVisible(true);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                infoImage.setText("Saved Image");
            } else
                infoImage.setText("Add address URL!");
        };
        saveButton.addActionListener(saveButtonActionListener);
        this.saveButton = saveButton;
    }

    private void setWebsiteURL(JLabel websiteURL) {
        this.websiteURL = websiteURL;
    }

    private void setJFileChooserImage(JFileChooser jFileChooser) {
        jFileChooser.setCurrentDirectory(new File("C:\\Users\\Kate i Muniu\\Downloads"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG image", "png");
        jFileChooser.setFileFilter(filter);
        int returnVal = jFileChooser.showSaveDialog(optionPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            try {
                image = ImageIO.read(jFileChooser.getSelectedFile());
                infoImage.setForeground(new Color(0, 255, 0));

                infoImage.setText("Loaded Image");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //save this image to main project folder
        } else {
            infoImage.setForeground(new Color(255, 0, 0));
            infoImage.setText("No file choosen!");
        }
        this.jFileChooserImage = jFileChooser;
    }

    private JFileChooser getJFileChooserImage() {
        return jFileChooserImage;
    }

    JPanel getOptionPanel() {
        return optionPanel;
    }

    static String getWebsiteFileName() {
        return websiteFileName;
    }

    static String getWebsitePathFile() {
        return websitePathFile;
    }

    static String getReadmeFileName() {
        return readmeFileName;
    }

    static String getImagePathFile() {
        return imagePathFile;
    }

    static String getCommonPathFile() {
        return commonPathFile;
    }
}
