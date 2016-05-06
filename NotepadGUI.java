import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class NotepadGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea ta_text;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenuItem mi_file_new;
	private JMenuItem mi_file_open;
	private JMenuItem mi_file_save;
	private JMenuItem mi_file_saveAs;
	private JSeparator separator;
	private JMenuItem mi_file_page;
	private JMenuItem mi_file_print;
	private JSeparator separator_1;
	private JMenuItem mi_file_exit;
	private JMenu menu_help;
	private JMenuItem mi_help_help;
	private JSeparator separator_2;
	private JMenuItem mi_help_info;
	private NotepadManager mgr = new NotepadManager();
	private NotepadShortcuts shortcuts = new NotepadShortcuts();
	private JMenu menu_edit;
	private JMenuItem mi_undo;
	private JSeparator separator_3;
	private JMenuItem mi_tear;
	private JMenuItem mi_copy;
	private JMenuItem mi_paste;
	private JMenuItem mi_delete;
	private JSeparator separator_4;
	
	private String box; //복붙잘라내기 할 때 저장되는 공간

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotepadGUI frame = new NotepadGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NotepadGUI() {
		setTitle("\uC81C\uBAA9\uC740 \uAC70\uB4E4\uBFD0 - \uD6C4\uB2C8\uBA54\uBAA8\uC7A5");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mi_file_exit.doClick();
			}
		});
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu_file = new JMenu("\uD30C\uC77C(F)");
		menu_file.setMnemonic('f');
		menuBar.add(menu_file);
		
		mi_file_new = new JMenuItem("\uC0C8\uB85C \uB9CC\uB4E4\uAE30(N)");
		mi_file_new.addActionListener(this);
		mi_file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mi_file_new.setName("");
		mi_file_new.setMnemonic(KeyEvent.VK_N);
		menu_file.add(mi_file_new);
		
		mi_file_open = new JMenuItem("\uC5F4\uAE30(O)...");
		mi_file_open.addActionListener(this);
		mi_file_open.setMnemonic('o');
		mi_file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menu_file.add(mi_file_open);
		
		mi_file_save = new JMenuItem("\uC800\uC7A5(S)");
		mi_file_save.addActionListener(this);
		mi_file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mi_file_save.setMnemonic('s');
		menu_file.add(mi_file_save);
		
		mi_file_saveAs = new JMenuItem("\uB2E4\uB978 \uC774\uB984\uC73C\uB85C \uC800\uC7A5(A)...");
		mi_file_saveAs.addActionListener(this);
		mi_file_saveAs.setMnemonic('a');
		menu_file.add(mi_file_saveAs);
		
		separator = new JSeparator();
		menu_file.add(separator);
		
		mi_file_page = new JMenuItem("\uD398\uC774\uC9C0 \uC124\uC815(U)...");
		mi_file_page.addActionListener(this);
		mi_file_page.setMnemonic('u');
		menu_file.add(mi_file_page);
		
		mi_file_print = new JMenuItem("\uC778\uC1C4(P)");
		mi_file_print.addActionListener(this);
		mi_file_print.setMnemonic('p');
		mi_file_print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		menu_file.add(mi_file_print);
		
		separator_1 = new JSeparator();
		menu_file.add(separator_1);
		
		mi_file_exit = new JMenuItem("\uB05D\uB0B4\uAE30(X)");
		mi_file_exit.addActionListener(this);
		mi_file_exit.setMnemonic('x');
		menu_file.add(mi_file_exit);
		
		menu_edit = new JMenu("\uD3B8\uC9D1(E)");
		menu_edit.setMnemonic('e');
		menuBar.add(menu_edit);
		
		mi_undo = new JMenuItem("\uC2E4\uD589\uCDE8\uC18C(U)");
		mi_undo.addActionListener(this);
		mi_undo.setMnemonic('u');
		mi_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		menu_edit.add(mi_undo);
		
		separator_3 = new JSeparator();
		menu_edit.add(separator_3);
		
		mi_tear = new JMenuItem("\uC798\uB77C\uB0B4\uAE30(T)");
		mi_tear.addActionListener(this);
		mi_tear.setMnemonic('t');
		mi_tear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		menu_edit.add(mi_tear);
		
		mi_copy = new JMenuItem("\uBCF5\uC0AC(C)");
		mi_copy.addActionListener(this);
		mi_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mi_copy.setMnemonic('c');
		menu_edit.add(mi_copy);
		
		mi_paste = new JMenuItem("\uBD99\uC5EC\uB123\uAE30(P)");
		mi_paste.addActionListener(this);
		mi_paste.setMnemonic('p');
		mi_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		menu_edit.add(mi_paste);
		
		mi_delete = new JMenuItem("\uC0AD\uC81C(L)");
		mi_delete.addActionListener(this);
		mi_delete.setMnemonic('l');
		mi_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		menu_edit.add(mi_delete);
		
		separator_4 = new JSeparator();
		menu_edit.add(separator_4);
		
		menu_help = new JMenu("\uB3C4\uC6C0\uB9D0(H)");
		menu_help.setMnemonic('h');
		menuBar.add(menu_help);
		
		mi_help_help = new JMenuItem("\uB3C4\uC6C0\uB9D0 \uBCF4\uAE30(H)");
		mi_help_help.addActionListener(this);
		mi_help_help.setMnemonic('h');
		menu_help.add(mi_help_help);
		
		separator_2 = new JSeparator();
		menu_help.add(separator_2);
		
		mi_help_info = new JMenuItem("\uBA54\uBAA8\uC7A5 \uC815\uBCF4(A)");
		mi_help_info.addActionListener(this);
		mi_help_info.setMnemonic('a');
		menu_help.add(mi_help_info);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ta_text = new JTextArea();
		scrollPane.setViewportView(ta_text);
		
		ta_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!getTitle().contains("*")){
					setTitle(getTitle() + "(*)");
				}
			}//keyTyped()
		});
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source == mi_file_open){
			String text = mgr.file_open(this);
			if(text != ""){
				ta_text.setText(text);
				setTitle(mgr.getFileName() + " - 후니메모장");
			}
		}//open
		
		if(source == mi_file_save){
			String result = mgr.file_save(getTitle(), ta_text.getText(), this);
			if(result != null){
				setTitle(result + " - 후니메모장");
			}
		}//save
		
		if(source == mi_file_exit){
			mgr.file_exit(getTitle(), ta_text.getText(), this);
		}//exit
		
		
		if(source == mi_help_info){
			mgr.mi_help_info(this);
		}//info
		
	}//actionPerformed()

}//class
