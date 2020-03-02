package xyz.lucasallegri.launcher.settings;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import xyz.lucasallegri.launcher.Fonts;
import xyz.lucasallegri.launcher.Language;
import xyz.lucasallegri.launcher.LauncherGUI;
import xyz.lucasallegri.logging.KnightLog;

import java.awt.Choice;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;

public class SettingsGUI {

	public static JFrame settingsGUIFrame;
	public static Choice choicePlatform;
	public static Choice choiceLanguage;
	public static JCheckBox checkboxRebuilds;
	public static JCheckBox checkboxKeepOpen;
	public static JButton forceRebuildButton;
	public static JCheckBox checkboxShortcut;
	public static JCheckBox checkboxStringDeduplication;
	public static JCheckBox checkboxG1GC;
	public static JCheckBox checkboxExplicitGC;
	public static JCheckBox checkboxUndecorated;
	public static JEditorPane argumentsPane;

	public static void compose() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsGUI window = new SettingsGUI();
					window.settingsGUIFrame.setVisible(true);
				} catch (Exception e) {
					KnightLog.logException(e);
				}
			}
		});
	}

	public SettingsGUI() {
		initialize();
	}

	private void initialize() {
		settingsGUIFrame = new JFrame();
		settingsGUIFrame.setTitle(Language.getValue("t.settings"));
		settingsGUIFrame.setBounds(100, 100, 325, 560);
		settingsGUIFrame.setResizable(false);
		settingsGUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settingsGUIFrame.getContentPane().setLayout(null);
		
		JLabel labelLauncherSettings = new JLabel(Language.getValue("m.launcher_settings"));
		labelLauncherSettings.setFont(Fonts.fontMed);
		labelLauncherSettings.setBounds(10, 16, 271, 14);
		settingsGUIFrame.getContentPane().add(labelLauncherSettings);
		
		JSeparator sepLauncherSettings = new JSeparator();
		sepLauncherSettings.setBounds(10, 36, 272, 2);
		settingsGUIFrame.getContentPane().add(sepLauncherSettings);
		
		JLabel labelChoicePlatform = new JLabel(Language.getValue("m.platform"));
		labelChoicePlatform.setBounds(15, 59, 65, 14);
		labelChoicePlatform.setFont(Fonts.fontReg);
		settingsGUIFrame.getContentPane().add(labelChoicePlatform);
		
		choicePlatform = new Choice();
		choicePlatform.setBounds(100, 55, 83, 20);
		choicePlatform.setFont(Fonts.fontReg);
		choicePlatform.setFocusable(false);
		settingsGUIFrame.getContentPane().add(choicePlatform);
		choicePlatform.add(Language.getValue("o.steam"));
		choicePlatform.add(Language.getValue("o.standalone"));
		choicePlatform.select(Settings.gamePlatform);
		choicePlatform.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				SettingsEventHandler.platformChangeEvent(event);
			}
		});
		
		JLabel labelLanguage = new JLabel(Language.getValue("m.language"));
		labelLanguage.setBounds(15, 104, 65, 14);
		labelLanguage.setFont(Fonts.fontReg);
		settingsGUIFrame.getContentPane().add(labelLanguage);
		
		choiceLanguage = new Choice();
		choiceLanguage.setBounds(100, 100, 83, 20);
		choiceLanguage.setFont(Fonts.fontReg);
		choiceLanguage.setFocusable(false);
		settingsGUIFrame.getContentPane().add(choiceLanguage);
		for(String lang : Language.AVAILABLE_LANGUAGES) {
			choiceLanguage.add(lang);
		}
		choiceLanguage.select(Language.getLangName(Settings.lang));
		choiceLanguage.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				SettingsEventHandler.languageChangeEvent(event);
			}
		});
		
		checkboxRebuilds = new JCheckBox(Language.getValue("m.rebuilds"));
		checkboxRebuilds.setBounds(11, 141, 270, 23);
		checkboxRebuilds.setFont(Fonts.fontReg);
		checkboxRebuilds.setFocusPainted(false);
		settingsGUIFrame.getContentPane().add(checkboxRebuilds);
		checkboxRebuilds.setSelected(Settings.doRebuilds);
		checkboxRebuilds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.rebuildsChangeEvent(_action);
			}
		});
		
		checkboxKeepOpen = new JCheckBox(Language.getValue("m.keep_open"));
		checkboxKeepOpen.setBounds(11, 163, 270, 23);
		checkboxKeepOpen.setFont(Fonts.fontReg);
		checkboxKeepOpen.setFocusPainted(false);
		settingsGUIFrame.getContentPane().add(checkboxKeepOpen);
		checkboxKeepOpen.setSelected(Settings.keepOpen);
		checkboxKeepOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.keepOpenChangeEvent(_action);
			}
		});
		
		forceRebuildButton = new JButton(Language.getValue("b.force_rebuild"));
		forceRebuildButton.setBounds(166, 185, 120, 23);
		forceRebuildButton.setFont(Fonts.fontMed);
		forceRebuildButton.setFocusPainted(false);
		forceRebuildButton.setFocusable(false);
		forceRebuildButton.setToolTipText(Language.getValue("b.force_rebuild"));
		settingsGUIFrame.getContentPane().add(forceRebuildButton);
		forceRebuildButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.forceRebuildEvent();
			}
		});
		
		checkboxShortcut = new JCheckBox(Language.getValue("m.create_shortcut"));
		checkboxShortcut.setBounds(11, 185, 139, 23);
		checkboxShortcut.setFont(Fonts.fontReg);
		checkboxShortcut.setFocusPainted(false);
		settingsGUIFrame.getContentPane().add(checkboxShortcut);
		checkboxShortcut.setSelected(Settings.createShortcut);
		checkboxShortcut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.createShortcutChangeEvent(_action);
			}
		});
		
		JSeparator sepExtraTxt = new JSeparator();
		sepExtraTxt.setBounds(10, 246, 272, 2);
		settingsGUIFrame.getContentPane().add(sepExtraTxt);
		
		JLabel labelExtraTxt = new JLabel(Language.getValue("m.extratxt_settings"));
		labelExtraTxt.setFont(Fonts.fontMed);
		labelExtraTxt.setBounds(10, 226, 271, 14);
		settingsGUIFrame.getContentPane().add(labelExtraTxt);
		
		JLabel labelMemory = new JLabel(Language.getValue("m.allocated_memory"));
		labelMemory.setFont(Fonts.fontReg);
		labelMemory.setBounds(15, 265, 124, 14);
		settingsGUIFrame.getContentPane().add(labelMemory);
		
		Choice choiceMemory = new Choice();
		choiceMemory.setFont(Fonts.fontReg);
		choiceMemory.setFocusable(false);
		choiceMemory.setBounds(145, 261, 135, 20);
		settingsGUIFrame.getContentPane().add(choiceMemory);
		choiceMemory.add(Language.getValue("o.memory_default"));
		choiceMemory.add(Language.getValue("o.memory_low"));
		choiceMemory.add(Language.getValue("o.memory_med"));
		choiceMemory.add(Language.getValue("o.memory_high"));
		choiceMemory.add(Language.getValue("o.memory_flex"));
		
		checkboxStringDeduplication = new JCheckBox(Language.getValue("m.use_string_deduplication"));
		checkboxStringDeduplication.setFont(Fonts.fontReg);
		checkboxStringDeduplication.setBounds(11, 295, 249, 23);
		settingsGUIFrame.getContentPane().add(checkboxStringDeduplication);
		checkboxStringDeduplication.setSelected(Settings.gameUseStringDeduplication);
		checkboxStringDeduplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.useStringDeduplicationChangeEvent(_action);
			}
		});
		
		checkboxG1GC = new JCheckBox(Language.getValue("m.use_g1gc"));
		checkboxG1GC.setFont(Fonts.fontReg);
		checkboxG1GC.setBounds(11, 317, 249, 23);
		settingsGUIFrame.getContentPane().add(checkboxG1GC);
		checkboxG1GC.setSelected(Settings.gameUseG1GC);
		checkboxG1GC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.useG1GCChangeEvent(_action);
			}
		});
		
		checkboxExplicitGC = new JCheckBox(Language.getValue("m.disable_explicit_gc"));
		checkboxExplicitGC.setFont(Fonts.fontReg);
		checkboxExplicitGC.setBounds(11, 339, 249, 23);
		settingsGUIFrame.getContentPane().add(checkboxExplicitGC);
		checkboxExplicitGC.setSelected(Settings.gameDisableExplicitGC);
		checkboxExplicitGC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.disableExplicitGCChangeEvent(_action);
			}
		});
		
		checkboxUndecorated = new JCheckBox(Language.getValue("m.undecorated_window"));
		checkboxUndecorated.setFont(Fonts.fontReg);
		checkboxUndecorated.setBounds(11, 361, 249, 23);
		settingsGUIFrame.getContentPane().add(checkboxUndecorated);
		checkboxUndecorated.setSelected(Settings.gameUndecoratedWindow);
		checkboxUndecorated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent _action) {
				SettingsEventHandler.undecoratedWindowChangeEvent(_action);
			}
		});
		
		JLabel labelArgumentsPane = new JLabel(Language.getValue("m.additional_args"));
		labelArgumentsPane.setFont(Fonts.fontMed);
		labelArgumentsPane.setBounds(13, 400, 271, 14);
		settingsGUIFrame.getContentPane().add(labelArgumentsPane);
		
		argumentsPane = new JEditorPane();
		argumentsPane.setFont(Fonts.fontReg);
		argumentsPane.setBounds(11, 421, 272, 85);
		settingsGUIFrame.getContentPane().add(argumentsPane);
		argumentsPane.setText(Settings.gameAdditionalArgs);
		
		settingsGUIFrame.setLocationRelativeTo(null);
		
		settingsGUIFrame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent windowEvent) {
		        LauncherGUI.settingsButton.setEnabled(true);
		        SettingsEventHandler.saveAdditionalArgs();
		    }
		});
		
	}
}
