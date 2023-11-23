package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
	
	private File file;
	
	public FileIO(String fileName) {
		file = new File("c:\\tmp\\" + fileName + ".txt");
	}
	
	// 파일 생성
	public void create() {
		try {
			if (file.createNewFile()) {
				System.out.println("파일 생성 성공!");
			} else {
				System.out.println("파일 생성 실패");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일에 저장
	public void dataSave(String arr[]) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < arr.length; i++) {
				pw.println(arr[i]);
			}
			pw.close();
			System.out.println("성공적으로 저장하였습니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일에 불러오기
public String[] dataLoad() {
		
		String arr[] = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str = "";
			int count = 0;
			// 데이터의 갯수를 카운터			
			while((str = br.readLine()) != null) {
				count++;
			}
			
			arr = new String[count];		
			// 파일의 처음 위치로 
			br = new BufferedReader(new FileReader(file));
			
			// 데이터 읽기
			int i = 0;
			while((str = br.readLine()) != null) {
				arr[i] = str;
				i++;
			}
			br.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return arr;
	}
}
