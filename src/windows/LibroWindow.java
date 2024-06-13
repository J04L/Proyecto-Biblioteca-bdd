package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Items.Autor;
import Items.Libro;
import principal.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibroWindow extends JFrame{
    private JTextField idTexto;
    private JTextField tituloTexto;
    private JTextField generoTexto;
    private JList listaAutoresDeLibros;
    private JButton actionButton;
    private JButton cancelarButton;
    private JPanel mainPanel;
    private JTable tabla;
    public LibroWindow(Libro libroToUpdate, JTable tabla) {
        this.tabla = tabla;

        idTexto.setText(libroToUpdate.getId()+"");
        tituloTexto.setText(libroToUpdate.getTitulo());
        generoTexto.setText(libroToUpdate.getGenero());
        actionButton.setText("ACTUALIZAR");

        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listaAutoresDeLibros.getSelectedIndex() != -1){
                    updateLibro(libroToUpdate);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un Autor", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        run();
        listaAutoresDeLibros.setSelectedIndex(Main.listaAutores.indexOf(libroToUpdate.getAutor()));

    }
    public LibroWindow(JTable tabla){
        this.tabla = tabla;
        idTexto.setText(Libro.idMayor+"");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaAutoresDeLibros.getSelectedIndex() != -1){
                    System.out.println(listaAutoresDeLibros.getSelectedIndex());
                    Libro libro = new Libro(Integer.parseInt(idTexto.getText()),
                            tituloTexto.getText(),
                            generoTexto.getText(),
                            ((DefaultListModel<Autor>) listaAutoresDeLibros.getModel()).elementAt(listaAutoresDeLibros.getSelectedIndex()));
                    addLibro(libro);
                    idTexto.setText(Autor.idMayor + "");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un Autor", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        run();
    }
    private void updateLibro(Libro libro){
        libro.setTitulo(tituloTexto.getText());
        libro.setGenero(generoTexto.getText());
        libro.setAutor(((DefaultListModel<Autor>) listaAutoresDeLibros.getModel()).elementAt(listaAutoresDeLibros.getSelectedIndex()));
        //se actualiza los datos del autor

        DefaultTableModel modeloTabla = ((DefaultTableModel) tabla.getModel());
        int filaSeleccionada = tabla.getSelectedRow();

        modeloTabla.setValueAt(libro.getTitulo(), filaSeleccionada, 1);
        modeloTabla.setValueAt(libro.getGenero(), filaSeleccionada, 2);
        modeloTabla.setValueAt(libro.getAutor().toString(), filaSeleccionada, 3);
        //se actualiza los valores de la fila
    }
    private void updateAutoresJList(){
        DefaultListModel<Autor> modeloLista = new DefaultListModel<>();
        for(Autor autor : Main.listaAutores){
            modeloLista.addElement(autor);
        }
        listaAutoresDeLibros.setModel(modeloLista);
    }
    private void addLibro(Libro libro){
        Main.listaLibros.add(libro);
        Libro.idMayor = Main.biggerLibroId()+1;
        ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{libro.getId(), libro.getTitulo(), libro.getGenero(), libro.getAutor().toString()});
    }
    public void run(){
        add(mainPanel);
        idTexto.setEditable(false);
        updateAutoresJList();
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
