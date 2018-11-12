package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar implements ActionListener
{
    private static JFrame optionFrame;
    private JPanel optionPanel;
    private JMenuBar menuBar;
    private JMenu file, options, addItem, editItem;
    private JMenuItem quitItem, addWebsiteSubItem;

    MenuBar()
    {
        setJFrame();
    }

    private void setJMenuItems()
    {
        // Tworzenie JMenuItem - Quit
        quitItem = new JMenuItem("Quit",KeyEvent.VK_Q);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, InputEvent.ALT_MASK));
        ActionListener quitItemActionListener = e -> System.exit(0);
        quitItem.addActionListener(quitItemActionListener);

        // Tworzenie JMenuItem - Add
        addItem = new JMenu("Add");

        // Tworzenie JMenuItem - Edit
        editItem = new JMenu("Edit");

        // Tworzenie JMenuSubItem - addWebsiteSubItem
        addWebsiteSubItem = new JMenuItem("new Website");
        ActionListener addWebsiteSubItemActionListener = e -> {
            if(optionPanel != null)
                optionPanel.removeAll();

            optionPanel = new MenuPanel(optionFrame).getOptionPanel();
            optionFrame.add(optionPanel);
            optionFrame.setVisible(true);
            // SET VISIBLE TRUE?
        };
        addWebsiteSubItem.addActionListener(addWebsiteSubItemActionListener);
    }

    private void setJMenus()
    {
        // Tworzenie JMenu - File
        file = new JMenu("File");
        options = new JMenu("Options");

        setJMenuItems();
    }

    private void setJMenuBars()
    {
        menuBar = new JMenuBar();
        setJMenus();
    }

    private void addComponents()
    {
        setJMenuBars();
        // Dodanie SubItems to JMenuItems
        addItem.add(addWebsiteSubItem);
//        addItem.add(addAppSubItem);
//        editItem.add(editWebsiteSubItem);
//        editItem.add(editAppSubItem);

        // Dodanie JMenuItems do JMenu
        file.add(quitItem);
        options.add(addItem);
        options.add(editItem);

        // Dodanie JMenu do JMenuBar
        menuBar.add(file);
        menuBar.add(options);
    }

    private void setJFrame()
    {
        addComponents();
        optionFrame = new JFrame("Configuration Menu - Version: 1.0");
        //optionFrame.setIconImage(new ImageIcon("logo.ico").getImage());
        optionFrame.setJMenuBar(menuBar);
        optionFrame.setSize(new Dimension(400,400));
        //optionFrame.pack();
        optionFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        optionFrame.setLocationRelativeTo(null);
        optionFrame.setVisible(false);
    }

    static JFrame getJFrameMenuBar()
    {
        return optionFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
