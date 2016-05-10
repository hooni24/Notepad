import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NotepadManager {
	File file;	//저장경로
	BufferedReader br;
	BufferedWriter bw;
	
	public String file_open(JFrame frame){
		JFileChooser fc = new JFileChooser("./");
		String text = "";
		int a = fc.showOpenDialog(frame);
		file = fc.getSelectedFile();
		if(a == JFileChooser.APPROVE_OPTION){
			try {
				br = new BufferedReader(new FileReader(file));
				String c;
				while((c = br.readLine()) != null){
					text += c+"\r\n";
				}
																	//이 시점에서 백스페이스 한번 해야 함. 문서 끝에서 줄바꿈이 일어나고 있으므로.
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return text;
	}//file_open()

	
	//저장 할 때 split으로 나눠서 /r/n을 문장 사이사이에 집어 넣어 주고, 불러올때는 그냥 불러온다.
	
	public String file_save(String title, String text, JFrame frame){
		String result = null;
		if(!title.contains(".txt")){
			JFileChooser fc = new JFileChooser("./");		//	./는 현재 디렉토리를 뜻함
			int a = fc.showSaveDialog(frame);
			file = fc.getSelectedFile();				//choose에서 선택한 경로와 파일.
			if(a == JFileChooser.APPROVE_OPTION)	result = saving(text);
			
		}else {				//처음 저장할 때. ( 즉, 타이틀에 .txt가 없을 때.)
			result = saving(text);
		}
		return result;		//저장했으면 저장파일명 반환, 저장하지 않았으면 null반환
	}//file_save()
	
	public String saving(String message){		//저장하는 메소드
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(message);
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return file.getName();
	}//saving()
	
	public void file_exit(String title, String text, JFrame frame){
		if(title.contains("*")){
			int a = JOptionPane.showConfirmDialog(frame, "변경 내용을 저장 하시겠습니까?", "종료", JOptionPane.YES_NO_CANCEL_OPTION);
				if(a == JOptionPane.OK_OPTION){
					file_save(title, text, frame);		//이부분에서 저장 시켜야 함.
					System.exit(0);
				}else if (a == JOptionPane.NO_OPTION){
					System.exit(0);
				}
		}else {
			System.exit(0);
		}
	}//file_exit()
	
	
	public void mi_help_info(JFrame frame){
		JOptionPane.showMessageDialog(frame, "후니 메모장\n제작시작: 2016.05.05.\n제작완료: 0000.00.00.", "후니 메모장 정보", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getFileName(){
		return file.getName();
	}

}//class
