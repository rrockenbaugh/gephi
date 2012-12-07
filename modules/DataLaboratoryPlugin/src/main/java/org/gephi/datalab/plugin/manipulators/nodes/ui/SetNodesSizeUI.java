/*
Copyright 2008-2010 Gephi
Authors : Eduardo Ramos <eduramiba@gmail.com>
Website : http://www.gephi.org

This file is part of Gephi.

DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

Copyright 2011 Gephi Consortium. All rights reserved.

The contents of this file are subject to the terms of either the GNU
General Public License Version 3 only ("GPL") or the Common
Development and Distribution License("CDDL") (collectively, the
"License"). You may not use this file except in compliance with the
License. You can obtain a copy of the License at
http://gephi.org/about/legal/license-notice/
or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
specific language governing permissions and limitations under the
License.  When distributing the software, include this License Header
Notice in each file and include the License files at
/cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
License Header, with the fields enclosed by brackets [] replaced by
your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"

If you wish your version of this file to be governed by only the CDDL
or only the GPL Version 3, indicate your decision by adding
"[Contributor] elects to include this software in this distribution
under the [CDDL or GPL Version 3] license." If you do not indicate a
single choice of license, a recipient has the option to distribute
your version of this file under either the CDDL, the GPL Version 3 or
to extend the choice of license to its licensees as provided above.
However, if you add GPL Version 3 code and therefore, elected the GPL
Version 3 license, then the option applies only if the new code is
made subject to such option by the copyright holder.

Contributor(s):

Portions Copyrighted 2011 Gephi Consortium.
 */
package org.gephi.datalab.plugin.manipulators.nodes.ui;

import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.gephi.datalab.plugin.manipulators.nodes.SetNodesSize;
import org.gephi.datalab.spi.DialogControls;
import org.gephi.datalab.spi.Manipulator;
import org.gephi.datalab.spi.ManipulatorUI;

/**
 * UI for SetNodesSize nodes manipulator.
 * @author Eduardo Ramos <eduramiba@gmail.com>
 */
public class SetNodesSizeUI extends javax.swing.JPanel implements ManipulatorUI {

    private SetNodesSize manipulator;

    /** Creates new form SetNodesSizeUI */
    public SetNodesSizeUI() {
        initComponents();
        sizeText.setDocument(new FloatJTextFieldFilter());
    }

    public void setup(Manipulator m, DialogControls dialogControls) {
        this.manipulator = (SetNodesSize) m;
        sizeText.setText(String.valueOf(manipulator.getSize()));
    }

    public void unSetup() {
        manipulator.setSize(Float.parseFloat(sizeText.getText()));
    }

    public String getDisplayName() {
        return manipulator.getName();
    }

    public JPanel getSettingsPanel() {
        return this;
    }

    public boolean isModal() {
        return true;
    }

    /**
     * Filter for only allowing float numbers in a JTextField.
     */
    class FloatJTextFieldFilter extends PlainDocument {

        public static final String POSITIVE_FLOAT_ACCEPTED_CHARS = "0123456789.";

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            for (int i = 0; i < str.length(); i++) {
                if (POSITIVE_FLOAT_ACCEPTED_CHARS.indexOf(String.valueOf(str.charAt(i))) == -1) {
                    return;
                }
            }

            if (str.indexOf(".") != -1) {
                if (getText(0, getLength()).indexOf(".") != -1) {
                    return;
                }
            }

            super.insertString(offs, str, a);
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            String str = getText(0, getLength());
            str = str.substring(0, offs) + str.substring(offs + len);
            if (!str.equals("") && !str.equals(".")) {
                super.remove(offs, len);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sizeLabel = new javax.swing.JLabel();
        sizeText = new javax.swing.JTextField();

        sizeLabel.setText(org.openide.util.NbBundle.getMessage(SetNodesSizeUI.class, "SetNodesSizeUI.sizeLabel.text")); // NOI18N

        sizeText.setText(org.openide.util.NbBundle.getMessage(SetNodesSizeUI.class, "SetNodesSizeUI.sizeText.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sizeText, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeLabel)
                    .addComponent(sizeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JTextField sizeText;
    // End of variables declaration//GEN-END:variables
}