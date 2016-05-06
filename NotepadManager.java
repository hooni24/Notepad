import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NotepadManager {
	
	public void file_exit(String text, JFrame frame){
		if(!text.equals("")){
			int a = JOptionPane.showConfirmDialog(frame, "변경 내용을 저장 하시겠습니까?", "종료", JOptionPane.YES_NO_CANCEL_OPTION);
				if(a == JOptionPane.OK_OPTION){
					System.exit(0);		//이부분에서 저장 시켜야 함.
				}
		}else {
			System.exit(0);
		}
	}//file_exit()
	
	
	
	
	public void mi_help_info(JFrame frame){
		JOptionPane.showMessageDialog(frame, "후니 메모장\n제작시작: 2016.05.05.\n제작완료: 0000.00.00.", "후니 메모장 정보", JOptionPane.INFORMATION_MESSAGE);
	}

}//class
