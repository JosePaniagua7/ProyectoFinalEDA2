package View;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Operation.IOperation;
import Operation.Operation;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	 //Declar una varialbe IOperation como global 
	static IOperation op=new Operation();
	//Cargamos los datos anteriormente guardados 
	static String path;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					op.loadData();
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void cerrar(){
		op.saveData();
		System.exit(1);
	}
	/**
	 * Create the frame.
	 */
	public MainView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cerrar();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 475, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Indexar", null, panel, null);
		
		JLabel lblNewLabel = new JLabel("Elije archivo/fichero");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(20);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//Creamos el objeto JFileChooser
				JFileChooser fc=new JFileChooser();				 
			
				//Creamos el filtro
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("pdf,txt,doc,docx,xml", "txt","pdf","doc","docx","xml");
				 
				//Le indicamos el filtro
				fc.setFileFilter(filtro);
				 
				//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
				int seleccion=fc.showOpenDialog(fc);
				if(seleccion==JFileChooser.APPROVE_OPTION){					
					File f=fc.getSelectedFile();
					path=f.getAbsolutePath();
					textField.setText(path);
				}	
			}
		});
		
		JButton btnIndexar = new JButton("Indexar");
		btnIndexar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path=textField.getText();				
				if(path.equals("")){
					JOptionPane.showMessageDialog(null,"Por favor, elije un archivo");
				}else{
					op.index(path);
					JOptionPane.showMessageDialog(null, "Se finalizó con la indexación");
					textField.setText("");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnNewButton))
						.addComponent(btnIndexar, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(btnIndexar)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Buscar Palabra", null, panel_1, null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblIntroduceLaPalabra = new JLabel("Introduce la palabra que deseas buscar:");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String palabra=textField_1.getText();
				if(palabra.equals("") || palabra.matches("^\\s+$")){
					JOptionPane.showMessageDialog(null, "Por favor ingresa una palabra");
				}else{
					String word=textField_1.getText().split("[^\\w+'*|’]+")[0].toLowerCase();
					System.out.println("La palabra a buscar es: "+word);
					ArrayList<String> results=op.search(word);
					if(results.size()==0) {
						//Removemos las respuestas de la búsqueda anterior						
						DefaultTableModel dtm = (DefaultTableModel) table.getModel();
						dtm.setRowCount(0);
						table.setModel(dtm);
						JOptionPane.showMessageDialog(null, "No se ha encontrado la palabra");						
					}else {
						//Removemos las respuestas de la búsqueda anterior
						//Removemos las respuestas de la búsqueda anterior						
						DefaultTableModel dtm = (DefaultTableModel) table.getModel();
						dtm.setRowCount(0);
						table.setModel(dtm);
						//Ahora si ya colocamos los resultados en nuestra tabla
						DefaultTableModel modelo=new DefaultTableModel();
						table.setModel(modelo);
						modelo.addColumn("Fichero");
						String[] Datos = new String[1];
						table.setModel(modelo);
						for(String s:results){
							//JOptionPane.showMessageDialog(null, s);
							Datos[0]=s;
							modelo.addRow(Datos);							
						}
						table.setModel(modelo);
					}
					
				}
			}
		});
		
		table = new JTable();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        String path=(String) table.getValueAt(row, col);
		        
		        //text file, should be opening in default text editor
		        File file = new File(path);
		        
		        //first check if Desktop is supported by Platform or not
		        if(!Desktop.isDesktopSupported()){
		            System.out.println("Desktop is not supported");
		            return;
		        }
		        
		        Desktop desktop = Desktop.getDesktop();
		        if(file.exists()) {
		        	try {
						desktop.open(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Lo sentmos no hemos encontrado el archivo");
					}
		        }else {
		        		JOptionPane.showMessageDialog(null,"No hemos podido encontrar el archivo");
		        }
					
		    }
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnBuscar))
						.addComponent(lblIntroduceLaPalabra))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addComponent(lblIntroduceLaPalabra)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
	}
}
