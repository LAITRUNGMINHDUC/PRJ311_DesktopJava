/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManualTree;

import java.awt.*;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author duclt
 */
public class Manager extends JPanel {

    ////////// UI //////////////////
    public Manager() {
        setLayout(new GridLayout(1, 2));
        initComponents();

        root = (DefaultMutableTreeNode) this.tree.getModel().getRoot();
        MethodSupport.loadData(
                new DefaultMutableTreeNode[]{root, curDepNode, curEmpNode}, DepCodes, EmpCodes);
        TreePath path = new TreePath(root);
        tree.expandPath(path);
        MethodSupport.disableTxtbox(new JTextField[]{txtDepCode, txtDepName, txtEmpCode, txtEmpSalary, txtEmpName});

        add(leftPanel);
        add(rightPanel);
    }

    private void initComponents() {
        initComponentsAuto();
        controlLeftPanel();
        controlRightPanel();
    }

    private void controlLeftPanel() {
        leftPanel.setLayout(new BorderLayout());

        jScrollPane1.setViewportView(tree);
        jScrollPane1.setSize(80, 200);

        leftPanel.add(jScrollPane1, BorderLayout.CENTER);
        leftPanel.add(btnSaveFile, BorderLayout.SOUTH);
    }

    private void controlRightPanel() {
        rightPanel.setLayout(new GridLayout(2, 1));

        controlDepPanel();
        controlEmpPanel();

        rightPanel.add(depPanel);
        rightPanel.add(empPanel);
    }

    private void controlDepPanel() {
        depPanel.setLayout(new GridBagLayout());
        depPanel.setBorder(BorderFactory.createTitledBorder("Department Detail"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.insets.set(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        depPanel.add(lblDepCode, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        depPanel.add(txtDepCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        depPanel.add(lblDepName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        depPanel.add(txtDepName, gbc);

        JPanel depButtonPanel = new JPanel();
        depButtonPanel.setLayout(new GridLayout(1, 3, 2, 1));
        depButtonPanel.add(btnNewDep);
        depButtonPanel.add(btnRemoveDep);
        depButtonPanel.add(btnSaveDep);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets.set(20, 0, 10, 0);

        depPanel.add(depButtonPanel, gbc);
    }

    private void controlEmpPanel() {
        empPanel.setLayout(new GridBagLayout());
        empPanel.setBorder(BorderFactory.createTitledBorder("Employee Detail"));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        empPanel.add(lblEmpCode, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        empPanel.add(txtEmpCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        empPanel.add(lblEmpName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        empPanel.add(txtEmpName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        empPanel.add(lblEmpSalary, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        empPanel.add(txtEmpSalary, gbc);

        JPanel empButtonPanel = new JPanel();
        empButtonPanel.setLayout(new GridLayout(1, 3));
        empButtonPanel.add(btnNewEmp);
        empButtonPanel.add(btnRemoveEmp);
        empButtonPanel.add(btnSaveEmp);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.insets.set(20, 0, 10, 0);
        empPanel.add(empButtonPanel, gbc);
    }

    public static void main(String[] args) {
        JFrame Frame = new JFrame();
        Frame.getContentPane().add(new Manager());
        Frame.pack();
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(false);
        Frame.setLocationRelativeTo(null);
    }

    ////////// Backend Logic //////////////////
    /////////////////////////////////////////////////////////////////////
    private void initComponentsAuto() {
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Department");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });

        btnNewDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewDepActionPerformed(evt);
            }
        });

        btnRemoveDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveDepActionPerformed(evt);
            }
        });

        btnSaveDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveDepActionPerformed(evt);
            }
        });

        btnNewEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewEmpActionPerformed(evt);
            }
        });

        btnSaveEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEmpActionPerformed(evt);
            }
        });

        btnRemoveEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveEmpActionPerformed(evt);
            }
        });

        btnSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFileActionPerformed(evt);
            }
        });
    }

    private void viewDeptAndEmp() {
        Department curDep = null;
        Employee curEmp = null;
        if (curDepNode != null) {
            curDep = (Department) (curDepNode.getUserObject());
        }
        if (curEmpNode != null) {
            curEmp = (Employee) (curEmpNode.getUserObject());
        }
        this.txtDepCode.setText(curDep != null ? curDep.getDepCode() : "");
        this.txtDepName.setText(curDep != null ? curDep.getDepName() : "");
        this.txtEmpCode.setText(curEmp != null ? curEmp.getEmpCode() : "");
        this.txtEmpName.setText(curEmp != null ? curEmp.getEmpName() : "");
        this.txtEmpSalary.setText("" + (curEmp != null ? curEmp.getSalary() : ""));
        this.txtDepCode.setEditable(false);
        this.txtDepCode.setBackground(Color.GRAY);
        this.txtEmpCode.setEditable(false);
        this.txtEmpCode.setBackground(Color.GRAY);
    }

    private void btnNewDepActionPerformed(java.awt.event.ActionEvent evt) {

        MethodSupport.clearTxtbox(new JTextField[]{txtDepCode, txtDepName});
        addNewDep = true;
    }

    private void btnSaveDepActionPerformed(java.awt.event.ActionEvent evt) {

        if (txtDepCode.getBackground() != Color.WHITE && txtDepName.getBackground() != Color.WHITE) {
            JOptionPane.showMessageDialog(null, "Cannot save without data");
            return;
        }

        String code = this.txtDepCode.getText().trim().toUpperCase();
        String name = this.txtDepName.getText().trim();
        txtDepCode.setText(code);
        txtDepName.setText(name);

        // Validate
        if (!Validator.checkTxtbox(code, "DEPCODE")) {
            JOptionPane.showMessageDialog(null, "Must Numbers/ Characters ; Length: 2-5");
            txtDepCode.setFocusable(true);
            return;
        }
        if (!Validator.checkTxtbox(name, "NAME")) {
            JOptionPane.showMessageDialog(null, "Must Length: 2 - 30 ; Unicode ; No Number");
            txtDepName.setFocusable(true);
            return;
        }
        //////////////////////////////////////////////////////

        if (addNewDep == true) {
            Department newDep = new Department(code, name);
            if (DepCodes.add(newDep.getDepCode())) {
                root.add(new DefaultMutableTreeNode(newDep));
                JOptionPane.showMessageDialog(null, "Added");
                MethodSupport.disableTxtbox(new JTextField[]{txtDepCode, txtDepName});
            } else {
                JOptionPane.showMessageDialog(null, "Duplicate Code.");
                txtDepCode.setFocusable(true);
                return;
            }
        } else {
            TreePath path = tree.getSelectionPath();
            if (path == null) {
                JOptionPane.showMessageDialog(null, "Can't add Department");
                return;
            }
            ((Department) curDepNode.getUserObject()).setDepName(name);
            JOptionPane.showMessageDialog(null, "Updated");
        }

        this.tree.updateUI();
        this.addNewDep = false;
    }

    private void btnRemoveDepActionPerformed(java.awt.event.ActionEvent evt) {

        TreePath path = tree.getSelectionPath();
        if (path == null) {
            JOptionPane.showMessageDialog(this, "Nothing to remove");
            return;
        }
        if (this.curDepNode.getChildCount() > 0) {
            String msg = "Remove all employess before deleting a department.";
            JOptionPane.showMessageDialog(this, msg);
            return;
        }
        int response = JOptionPane.showConfirmDialog(this, "Delete this department- OK?");
        if (response == JOptionPane.OK_OPTION) {
            Department Dept = (Department) this.curDepNode.getUserObject();
            DepCodes.remove(Dept.getDepCode());
            root.remove(this.curDepNode);
            MethodSupport.clearTxtbox(new JTextField[]{txtDepCode, txtDepName});
            tree.clearSelection();
            tree.updateUI();
        }
        tree.setSelectionPath(null);
        addNewDep = false;
        addNewEmp = false;
    }

    private void btnNewEmpActionPerformed(java.awt.event.ActionEvent evt) {

        if (txtDepCode.getText().isEmpty() && txtDepName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose Department first");
            return;
        }
        MethodSupport.clearTxtbox(new JTextField[]{txtEmpCode, txtEmpName, txtEmpSalary});

        addNewEmp = true;
    }

    private void btnRemoveEmpActionPerformed(java.awt.event.ActionEvent evt) {

        TreePath path = tree.getSelectionPath();
        if (path == null) {
            JOptionPane.showMessageDialog(this, "Nothing to remove");
            return;
        }
        if (this.curEmpNode != null) {
            int response = JOptionPane.showConfirmDialog(this, "Delete this employee- OK?");
            if (response == JOptionPane.OK_OPTION) {
                Employee Emp = (Employee) this.curEmpNode.getUserObject();
                EmpCodes.remove(Emp.getEmpCode());
                curDepNode.remove(this.curEmpNode);
                MethodSupport.clearTxtbox(new JTextField[]{txtEmpCode, txtEmpName, txtEmpSalary});
                MethodSupport.disableTxtbox(new JTextField[]{txtEmpCode, txtEmpName, txtEmpSalary});
                tree.clearSelection();
                tree.updateUI();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing to remove");
            return;
        }
        tree.setSelectionPath(null);
        addNewEmp = false;

    }

    private void btnSaveEmpActionPerformed(java.awt.event.ActionEvent evt) {

        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return;
        }
        String code = this.txtEmpCode.getText().trim().toUpperCase();
        String name = this.txtEmpName.getText().trim();
        String salaryStr = this.txtEmpSalary.getText().trim();

        txtEmpCode.setText(code);
        txtEmpName.setText(name);
        txtEmpSalary.setText(salaryStr);

        // Validate 
        if (!Validator.checkTxtbox(code, "EMPCODE")) {
            JOptionPane.showMessageDialog(null, "Must Exxx");
            return;
        }
        if (!Validator.checkTxtbox(name, "NAME")) {
            JOptionPane.showMessageDialog(null, "Must Length: 2 - 30 ; Unicode ; No Number");
            return;
        }
        if (!Validator.checkTxtbox(salaryStr, "SALARY")) {
            JOptionPane.showMessageDialog(null, "Must Positive Integer.");
            return;
        }
        ///////////////////////////////////////////////////

        int sal = Integer.parseInt(salaryStr);
        if (addNewEmp == true) {
            Employee newEmp = new Employee(code, name, sal);
            if (EmpCodes.add(newEmp.getEmpCode())) {
                curDepNode.add(new DefaultMutableTreeNode(newEmp));
            } else {
                JOptionPane.showMessageDialog(null, "Duplicate Code.");
                return;
            }

        } else {
            Employee emp = (Employee) (curEmpNode.getUserObject());
            emp.setEmpName(name);
            emp.setSalary(sal);
        }
        this.tree.updateUI();
        this.addNewEmp = false;
        this.txtEmpCode.setEditable(false);
    }

    private void btnSaveFileActionPerformed(java.awt.event.ActionEvent evt) {
        MethodSupport.saveFile(root);
    }

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {

        tree.cancelEditing();
        // Get the clicked node at the last component of the path
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return;
        }
        DefaultMutableTreeNode selectedNode = null;
        selectedNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
        // Get the selected object in the model
        Object selectedObj = selectedNode.getUserObject();
        // Checking what is selected object
        if (selectedNode == root) {
            this.curDepNode = this.curEmpNode = null;
            MethodSupport.disableTxtbox(new JTextField[]{txtDepCode, txtDepName, txtEmpCode, txtEmpName, txtEmpSalary});
        } else if (selectedObj instanceof Department) {
            this.curDepNode = selectedNode;
            MethodSupport.disableTxtbox(new JTextField[]{txtEmpCode, txtEmpName, txtEmpSalary});
            MethodSupport.clearTxtbox(new JTextField[]{txtDepCode, txtDepName});
            this.curEmpNode = null;
        } else if (selectedObj instanceof Employee) {
            MethodSupport.clearTxtbox(new JTextField[]{txtDepCode, txtDepName, txtEmpCode, txtEmpName, txtEmpSalary});
            curEmpNode = selectedNode;
            curDepNode = (DefaultMutableTreeNode) (selectedNode.getParent());
        }
        addNewDep = false;
        addNewEmp = false;
        viewDeptAndEmp();
    }

    /////////// Variables ///////////////////
    DefaultMutableTreeNode root = null;
    DefaultMutableTreeNode curDepNode = null;
    DefaultMutableTreeNode curEmpNode = null;
    boolean addNewDep = false;
    boolean addNewEmp = false;

    HashSet<String> DepCodes = new HashSet<>();
    HashSet<String> EmpCodes = new HashSet<>();

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JPanel depPanel = new JPanel();
    private JPanel empPanel = new JPanel();

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTree tree = new JTree();

    private JLabel lblDepCode = new JLabel("Dep Code:");
    private JLabel lblDepName = new JLabel("Dep Name:");
    private JLabel lblEmpCode = new JLabel("Emp Code:");
    private JLabel lblEmpName = new JLabel("Emp Name:");
    private JLabel lblEmpSalary = new JLabel("Emp Salary:");

    private JTextField txtDepCode = new JTextField();
    private JTextField txtDepName = new JTextField();
    private JTextField txtEmpCode = new JTextField();
    private JTextField txtEmpName = new JTextField();
    private JTextField txtEmpSalary = new JTextField();

    private JButton btnNewDep = new JButton("New");
    private JButton btnNewEmp = new JButton("New");
    private JButton btnRemoveDep = new JButton("Remove");
    private JButton btnRemoveEmp = new JButton("Remove");
    private JButton btnSaveDep = new JButton("Save");
    private JButton btnSaveEmp = new JButton("Save");
    private JButton btnSaveFile = new JButton("Save File");

}
