package windows;

import Items.*;
import principal.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class accionesSQLWindow extends JFrame{
    private JPanel mainPanel;
    private JComboBox autorLibroCombobox;
    private JTable tabla;
    private JMenuBar menuAccionesSQL;
    public accionesSQLWindow(){
        add(mainPanel);
        JMenu menu = getjMenu();
        menuAccionesSQL.add(menu);

        DefaultTableModel autoresTabla = new DefaultTableModel();
        autoresTabla.addColumn("ID");
        autoresTabla.addColumn("NOMBRE");
        autoresTabla.addColumn("APELLIDO");
        tabla.setModel(autoresTabla);

        DefaultTableModel libroTabla = new DefaultTableModel();
        libroTabla.addColumn("ID");
        libroTabla.addColumn("TITULO");
        libroTabla.addColumn("GÉNERO");
        libroTabla.addColumn("AUTOR");

        autorLibroCombobox.setSelectedIndex(0);
        autorLibroCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autorLibroCombobox.getSelectedItem().equals("AUTOR")) tabla.setModel(autoresTabla);
                else tabla.setModel(libroTabla);
            }
        });

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JMenu getjMenu() {
        JMenuItem insertItem = new JMenuItem("INSERT");
        insertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autorLibroCombobox.getSelectedItem().equals("AUTOR")) new AutorWindow(tabla);
                else new LibroWindow(tabla);
            }
        });

        JMenuItem deleteItem = new JMenuItem("DELETE");
        JMenuItem updateItem = getUpdateItem();

        JMenu menu = new JMenu("ACCIONES");
        menu.add(insertItem);
        menu.add(deleteItem);
        menu.add(updateItem);
        return menu;
    }

    private JMenuItem getUpdateItem() {
        //TODO hecho
        JMenuItem updateItem = new JMenuItem("UPDATE");

        updateItem.addActionListener(new ActionListener() {
            //pincha en el menu de UPDATE y...
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    //si ha seleccionado una linea...
                    if (autorLibroCombobox.getSelectedItem().equals("AUTOR")) {
                        if(!Main.listaAutores.isEmpty()){
                            //si está marcado AUTOR...
                            Vector autorSeleccionadoVector = ((DefaultTableModel)tabla.getModel()).getDataVector().elementAt(tabla.getSelectedRow());
                            //Guarda los datos de la fila en un Vector
                            Autor autor = Main.getAutorById((int) autorSeleccionadoVector.get(0));
                            //Guardo el autor con el id de la fila
                            if (autor != null){
                                //si ha encontrado autor..
                                new AutorWindow(autor, tabla);
                                //abre la ventada de actualizar autor
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No hay Autores insertados", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        if(!Main.listaLibros.isEmpty()){
                            //si está marcado AUTOR...
                            Vector libroSeleccionadoVector = ((DefaultTableModel)tabla.getModel()).getDataVector().elementAt(tabla.getSelectedRow());
                            //Guarda los datos de la fila en un Vector
                            Libro libro = Main.getLibroById((int) libroSeleccionadoVector.get(0));
                            //Guardo el libro con el id de la fila
                            if (libro != null){
                                //si ha encontrado libro...
                                new LibroWindow(libro, tabla);
                                //abre la ventada de actualizar libro
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No hay Libros insertados", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    //si no ha seleccionado fila muestra un mensaje de error
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un " + ((String) autorLibroCombobox.getSelectedItem()).toLowerCase() + " antes", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return updateItem;
    }
    public void addLibro(Libro libro){

    }
    public void updateLibro(Libro libro){

    }
    public void deleteAutor(Autor autor){

    }
    public void deleteLibro(Libro libro){

    }
}
