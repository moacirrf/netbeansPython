package org.netbeans.modules.python.options;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.io.IOUtils;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.netbeans.modules.lsp.client.LSPBindings;
import org.netbeans.modules.python.Installer;
import org.netbeans.modules.python.PythonUtility;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.openide.util.Pair;

final class PythonLspServerConfigsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public static String[] PACKAGES = {
        "pylsp",
        "pyls-isort",
        "pylsp-mypy",
        "pylsp-rope",
        "black",
        "python-lsp-black",
        "isort",
        "pyls-memestra",
        "ptpython",
        "ipython",
        "pytest",
        "pynguin",
        "build",
        "pdoc",
        "poetry"
    };

    private final PythonLspServerConfigsOptionsPanelController controller;

    PythonLspServerConfigsPanel(PythonLspServerConfigsOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        lspList.setCellRenderer(new PythonPackagesListRenderer());
        //  listen to changes in form fields and call controller.changed()
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.changed();
            }
        };
        lspEditorPane.getDocument().addDocumentListener(dl);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lspSplitPane = new javax.swing.JSplitPane();
        lspPanel = new javax.swing.JPanel();
        lspServerCheckBox = new javax.swing.JCheckBox();
        lspServerLabel = new javax.swing.JLabel();
        lspScrollPane = new javax.swing.JScrollPane();
        lspList = new javax.swing.JList();
        lspPythonVersionLabel = new javax.swing.JLabel();
        lspSettingsPanel = new javax.swing.JPanel();
        lspSettingsScrollPane = new javax.swing.JScrollPane();
        lspEditorPane = new javax.swing.JEditorPane();
        errroLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        lspSplitPane.setDividerSize(1);
        lspSplitPane.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(lspServerCheckBox, org.openide.util.NbBundle.getMessage(PythonLspServerConfigsPanel.class, "PythonLspServerConfigsPanel.lspServerCheckBox.text")); // NOI18N
        lspServerCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lspServerCheckBoxStateChanged(evt);
            }
        });

        lspServerLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lspServerLabel, org.openide.util.NbBundle.getMessage(PythonLspServerConfigsPanel.class, "PythonLspServerConfigsPanel.lspServerLabel.text")); // NOI18N

        lspList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lspListMouseClicked(evt);
            }
        });
        lspScrollPane.setViewportView(lspList);

        lspPythonVersionLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lspPythonVersionLabel, org.openide.util.NbBundle.getMessage(PythonLspServerConfigsPanel.class, "PythonLspServerConfigsPanel.lspPythonVersionLabel.text")); // NOI18N

        javax.swing.GroupLayout lspPanelLayout = new javax.swing.GroupLayout(lspPanel);
        lspPanel.setLayout(lspPanelLayout);
        lspPanelLayout.setHorizontalGroup(
            lspPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lspPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lspPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lspPythonVersionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(lspServerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(lspPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lspPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(lspPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lspServerCheckBox)
                        .addComponent(lspScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        lspPanelLayout.setVerticalGroup(
            lspPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lspPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lspPythonVersionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lspServerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(lspPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lspPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lspServerCheckBox)
                    .addGap(56, 56, 56)
                    .addComponent(lspScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addGap(30, 30, 30)))
        );

        lspSplitPane.setLeftComponent(lspPanel);

        lspSettingsScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PythonLspServerConfigsPanel.class, "PythonLspServerConfigsPanel.lspSettingsScrollPane.border.title"))); // NOI18N
        lspSettingsScrollPane.setPreferredSize(new java.awt.Dimension(10, 942));
        lspSettingsScrollPane.setRowHeaderView(null);
        lspSettingsScrollPane.setViewportView(null);

        lspEditorPane.setContentType("text/x-json"); // NOI18N
        lspSettingsScrollPane.setViewportView(lspEditorPane);

        errroLabel.setForeground(new java.awt.Color(255, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(errroLabel, org.openide.util.NbBundle.getMessage(PythonLspServerConfigsPanel.class, "PythonLspServerConfigsPanel.errroLabel.text")); // NOI18N

        javax.swing.GroupLayout lspSettingsPanelLayout = new javax.swing.GroupLayout(lspSettingsPanel);
        lspSettingsPanel.setLayout(lspSettingsPanelLayout);
        lspSettingsPanelLayout.setHorizontalGroup(
            lspSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lspSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errroLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(lspSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lspSettingsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lspSettingsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        lspSettingsPanelLayout.setVerticalGroup(
            lspSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lspSettingsPanelLayout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addComponent(errroLabel)
                .addContainerGap())
            .addGroup(lspSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lspSettingsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lspSettingsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGap(28, 28, 28)))
        );

        lspSplitPane.setRightComponent(lspSettingsPanel);

        add(lspSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lspServerCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lspServerCheckBoxStateChanged
        controller.changed();
    }//GEN-LAST:event_lspServerCheckBoxStateChanged

    private void lspListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lspListMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            Pair<String, Boolean> selected = (Pair<String, Boolean>) lspList.getSelectedValue();
            if (!selected.second()) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem pm = new JMenuItem("Install");
                pm.addActionListener((ActionEvent e) -> {
                    String[] cmd = {
                        PythonUtility.getLspPythonExe(), "-m", "pip", "install",
                        "--upgrade", selected.first()
                    };
                    PythonUtility.processExecutor(cmd, String.format("%s %s", "Installing", selected.first()));
                });
                menu.add(pm);
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }

        }
    }//GEN-LAST:event_lspListMouseClicked

    void load() {
        if (errors != null && !errors.isEmpty()) {
            return;
        }
        try {
            lspServerCheckBox.setSelected(NbPreferences.root().getBoolean("autoUpdate", false));
            lspServerLabel.setText(String.format("%s%s", "Current Version: ", PythonUtility.getServerVersion()));
            lspPythonVersionLabel.setText(String.format("%s%s", "Python Version: ", PythonUtility.getVersion(PythonUtility.getLspPythonExe())));
            String pipListOutput = PythonUtility.getPipList(PythonUtility.getLspPythonExe());

            DefaultListModel<Pair<String, Boolean>> model = new DefaultListModel<>();

            for (String pipPackage : PACKAGES) {
                if (pipListOutput.contains(pipPackage)) {
                    model.addElement(Pair.of(pipPackage, true));

                } else {
                    model.addElement(Pair.of(pipPackage, false));

                }

            }
            lspList.setModel(model);

            lspEditorPane.setText(Files.readString(PythonUtility.SETTINGS.toPath()));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    static final File settingsSchema = PythonUtility.SETTINGS_SCHEMA;
    static final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    static Set<ValidationMessage> errors;

    void store() {
        if (controller.isChanged()) {
            errroLabel.setText("");
            try {
                String jsonSettings = lspEditorPane.getText();

                if (!settingsSchema.exists()) {
                    Files.writeString(settingsSchema.toPath(),
                            IOUtils.resourceToString("org/netbeans/modules/python/schema.json",
                                    StandardCharsets.UTF_8, Installer.class.getClassLoader())
                    );
                }
                JsonSchema jsonSchema = factory.getSchema(Files.readString(settingsSchema.toPath()));
                JsonNode jsonNode = new ObjectMapper().readTree(jsonSettings);
                errors = jsonSchema.validate(jsonNode);
                if (!errors.isEmpty()) {
                    errroLabel.setText(errors.iterator().next().getMessage());
                    return;
                }

                NbPreferences.root().putBoolean("autoUpdate", lspServerCheckBox.isSelected());
                Files.writeString(PythonUtility.SETTINGS.toPath(),
                        jsonSettings);
                Gson create = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL).create();
                Map<String, Object> settings = create
                        .fromJson(jsonSettings,
                                new TypeToken<HashMap<String, Object>>() {
                                }.getType());
                if (settings != null) {
                    Map<String, Object> paramsObject = new HashMap<>();
                    NbPreferences.root().putBoolean("auto_pop_completion",
                            (boolean) settings.getOrDefault("auto_pop_completion", true));
                    settings.remove("auto_pop_completion");
                    paramsObject.put("pylsp", settings);
                    DidChangeConfigurationParams params = new DidChangeConfigurationParams(paramsObject);
                    LSPBindings.getAllBindings().forEach(server -> {
                        server.getWorkspaceService().didChangeConfiguration(params);
                    });
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errroLabel;
    private javax.swing.JEditorPane lspEditorPane;
    private javax.swing.JList lspList;
    private javax.swing.JPanel lspPanel;
    private javax.swing.JLabel lspPythonVersionLabel;
    private javax.swing.JScrollPane lspScrollPane;
    private javax.swing.JCheckBox lspServerCheckBox;
    private javax.swing.JLabel lspServerLabel;
    private javax.swing.JPanel lspSettingsPanel;
    private javax.swing.JScrollPane lspSettingsScrollPane;
    private javax.swing.JSplitPane lspSplitPane;
    // End of variables declaration//GEN-END:variables

}
