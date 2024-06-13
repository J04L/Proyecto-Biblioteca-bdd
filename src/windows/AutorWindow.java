package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Items.Autor;
import principal.Main;

public class AutorWindow extends JFrame{
    private JPanel mainPanel;
    private JButton actionButton;
    private JButton cancelarButton;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField idTexto;
    private Autor autorToUpdate;
    private JTable tabla;
    public AutorWindow(Autor autorToUpdate, JTable tabla){
        this.autorToUpdate = autorToUpdate;
        this.tabla = tabla;
        idTexto.setText(autorToUpdate.getId()+"");
        nombreTexto.setText(autorToUpdate.getNombre());
        apellidoTexto.setText(autorToUpdate.getApellido());
        actionButton.setText("ACTUALIZAR");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAutor(autorToUpdate);
                dispose();
            }
        });
        run();
    }
    public AutorWindow(JTable tabla){
        this.autorToUpdate = null;
        this.tabla = tabla;
        idTexto.setText(Autor.idMayor+"");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(datosEstanCorrectos()){
                    Autor autor = new Autor(Integer.parseInt(idTexto.getText()),
                            nombreTexto.getText(),
                            apellidoTexto.getText());
                    addAutor(autor);
                    idTexto.setText(Autor.idMayor + "");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Faltan campos por rellenar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        run();
    }
    private void run(){
        add(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        idTexto.setEditable(false);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    private boolean datosEstanCorrectos(){
        return !nombreTexto.getText().isEmpty() && !apellidoTexto.getText().isEmpty();
    }
    public void addAutor(Autor autor){
        Main.listaAutores.add(autor);
        Autor.idMayor = Main.biggerAutorId()+1;
        ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{autor.getId(), autor.getNombre(), autor.getApellido()});
    }
    public void updateAutor(Autor autor){
        autor.setNombre(nombreTexto.getText());
        autor.setApellido(apellidoTexto.getText());
        //se actualiza los datos del autor

        DefaultTableModel modeloTabla = ((DefaultTableModel) tabla.getModel());
        int filaSeleccionada = tabla.getSelectedRow();

        modeloTabla.setValueAt(autor.getNombre(), filaSeleccionada, 1);
        modeloTabla.setValueAt(autor.getApellido(), filaSeleccionada, 2);
        //se actualiza los valores de la fila
    }
}
