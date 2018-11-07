package menu;

import menu.file_Reader_Writer.MyFileWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel {
    private JFrame optionFrame, jFrameMyGame;
    private JPanel optionPanel;
    private JLabel header, filePath, imagePath, websiteURL, infoImage, infoApp;
    private JButton filePathButton, imagePathButton, saveButton;
    private JTextField websiteURLjTF;
    private BufferedImage image;
    private JFileChooser jFileChooserImage, jFileChooserApp;
    private String readmeFileName = "readme.txt";
    private String websiteFileName = "websites.txt";
    private String imagePathFile = "Images\\";
    private String websitePathFile = "Websites\\";
    private String commonPathFile = "Common\\";


    public MenuPanel()
    {
    }

    public MenuPanel(int intWebsite, JFrame frame) {

        optionFrame = frame;
        getAll(intWebsite);
    }

    public String getCommonPathFile()
    {
        return commonPathFile;
    }

    public String getReadmeFileName()
    {
        return readmeFileName;
    }

    public void getAll(int intWebsite)
    {
        setAll(intWebsite);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,50,0);
        optionPanel = new JPanel(new GridBagLayout());
        optionPanel.add(header,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,0);
        if(intWebsite>=1) {
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
        else
        {
            optionPanel.add(filePath, c);
            c.gridx = 1;
            c.gridy = 1;
            optionPanel.add(filePathButton, c);
        }

    }

    public void setAll(int intWebsite)
    {
        if(intWebsite >= 1)
        {
            setHeader(new JLabel("Add new website")); // 1
            setImagePath(new JLabel("Choose Image")); // 2
            setImagePathButton(new JButton("Add Image")); // 2
            setInfoImage(new JLabel("")); // 2
            setWebsiteURL(new JLabel("Address URL")); // 3
            setWebsiteURLjTF(new JTextField()); // 3
            setSaveButton(new JButton("Save"));
        }
        else if(intWebsite < 1)
        {
            setHeader(new JLabel("Add new App"));
            setImagePath(new JLabel("Choose Image"));
            setInfoImage(new JLabel(""));
            setFilePath(new JLabel("Choose App"));
            setInfoApp(new JLabel("")); // edit
            setFilePathButton(new JButton("Add Application"));
        }
    }

    public JPanel getOptionPanel()
    {
        return optionPanel;
    }

    public void setOptionPanel(JPanel panel)
    {
        panel.setLayout(new GridBagLayout());
    }

    public JLabel getHeader() {
        return header;
    }

    public void setHeader(JLabel header) {
        header.setFont(new Font("serif", Font.BOLD, 24));
        this.header = header;
    }

    public JLabel getInfoImage() {
        return infoImage;
    }

    public void setInfoImage(JLabel infoImage) {
        infoImage.setFont(new Font("serif", Font.PLAIN, 14));
        this.infoImage = infoImage;
    }

    public JLabel getInfoApp() {
        return infoApp;
    }

    public void setInfoApp(JLabel infoApp) {
        this.infoApp = infoApp;
    }

    public JLabel getFilePath() {
        return filePath;
    }

    public void setFilePath(JLabel filePath) {
        this.filePath = filePath;
    }

    public JLabel getImagePath() {
        return imagePath;
    }

    public void setImagePath(JLabel imagePath) {
        this.imagePath = imagePath;
    }

    public JLabel getWebsiteURL() {
        return websiteURL;
    }

    public JTextField getWebsiteURLjTF() {
        return websiteURLjTF;
    }

    public void setWebsiteURLjTF(JTextField websiteURLjTF) {
        websiteURLjTF.setFont(new Font("serif", Font.PLAIN, 14));
        websiteURLjTF.setPreferredSize(new Dimension(75,25));
        this.websiteURLjTF = websiteURLjTF;
    }

    public JButton getFilePathButton() {
        return filePathButton;
    }

    public void setFilePathButton(JButton filePathButton) {
        ActionListener filePathButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setjFileChooserApp(new JFileChooser());
            }
        };
        filePathButton.addActionListener(filePathButtonActionListener);
        this.filePathButton = filePathButton;
    }

    public JButton getImagePathButton() {
        return imagePathButton;
    }

    public void setImagePathButton(JButton imagePathButton) {
        ActionListener imagePathButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setjFileChooserImage(new JFileChooser());
            }
        };
        imagePathButton.addActionListener(imagePathButtonActionListener);
        this.imagePathButton = imagePathButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        ActionListener saveButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(websiteURLjTF.getText().contains("http://"))
                {
                    //imageToArray();
                    String imageName = getjFileChooserImage().getSelectedFile().getName();
                    File file = new File(getImagePathFile()+"\\"+imageName);

                    try {
                        ImageIO.write(image, "png", file);
                        MyFileWriter fw = new MyFileWriter(getWebsitePathFile(), getWebsiteFileName(), true);
                        fw.writeToFile(websiteURLjTF.getText());
                        MyFileWriter mfw = new MyFileWriter(commonPathFile, readmeFileName, true);
                        mfw.writeToFile(jFileChooserImage.getSelectedFile().getName());
                        // FIXME - save url to array
                        optionPanel.setVisible(false);
                        optionFrame.setVisible(false);
                        MyGame myGame = new MyGame();
                        myGame.getGUI();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    infoImage.setText("Saved Image");
                }
                else
                    infoImage.setText("Add address URL!");
            }
        };
        saveButton.addActionListener(saveButtonActionListener);
        this.saveButton = saveButton;
    }

    public void setWebsiteURL(JLabel websiteURL) {
        this.websiteURL = websiteURL;
    }

    public JFileChooser getjFileChooserImage() {
        return jFileChooserImage;
    }

    public void setjFileChooserImage(JFileChooser jFileChooser) {
        jFileChooser.setCurrentDirectory(new File("C:\\Users\\Kate i Muniu\\Desktop\\JavaProjekt\\Shorter"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG image", "png");
        jFileChooser.setFileFilter(filter);
        int returnVal = jFileChooser.showSaveDialog(optionPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            try {
                image = ImageIO.read(jFileChooser.getSelectedFile());
                infoImage.setForeground(new Color(0,255,0));

                infoImage.setText("Loaded Image");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //save this image to main project folder
        }
        else
        {
            infoImage.setForeground(new Color(255,0,0));
            infoImage.setText("No file choosen!");
        }
        this.jFileChooserImage = jFileChooser;
    }

    public JFileChooser getjFileChooserApp() {
        return jFileChooserApp;
    }

    public void setjFileChooserApp(JFileChooser jFileChooser) {
        jFileChooser.setCurrentDirectory(new File("c:\\temp"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Only EXE files", "exe");
        jFileChooser.setFileFilter(filter);
        int returnVal = jFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Wybrałeś plik " + jFileChooser.getName());

        }
        this.jFileChooserApp = jFileChooser;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getWebsiteFileName() {
        return websiteFileName;
    }

    public String getImagePathFile() {
        return imagePathFile;
    }

    public String getWebsitePathFile() {
        return websitePathFile;
    }
}
