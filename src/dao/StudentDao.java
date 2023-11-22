package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import dto.StudentDto;

// Data Access Object: 데이터를 취급하는 클래스
public class StudentDao {
	// 학생 데이터 관리 배열
	private StudentDto student[];
	private int count;
	
	// 추가, 삭제, 검색, 수정 (CRUD)
	public StudentDao() { // 생성자는 딱 한 번 호출됨
		count = 0;
		
		student = new StudentDto[10]; // 변수만 생성
		// StudentDto student1, student2, student3 ... ;
		
		// dto를 생성
	}
	
	public void insert() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("학생 정보 입력입니다");

		System.out.print("이름 >> ");
		String name = sc.next();

		System.out.print("나이 >> ");
		int age = sc.nextInt();

		System.out.print("신장 >> ");
		double height = sc.nextDouble();

		System.out.print("주소 >> ");
		String address = sc.next();

		System.out.print("국어 >> ");
		int kor = sc.nextInt();

		System.out.print("영어 >> ");
		int eng = sc.nextInt();

		System.out.print("수학 >> ");
		int math = sc.nextInt();
		
		student[count] = new StudentDto(name, age, height, address, kor, eng, math);
		count++;
	}
	
	public int findTarget(String target) {
		int index = -1;
		for (int i = 0; i < student.length; i++) {
			StudentDto dto = student[i];
			if (dto != null) {
				if (target.equals(dto.getName())) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public void delete() {
		System.out.print("삭제하고 싶은 학생의 이름 >> ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		
		int index = findTarget(target);
		
		if(index == -1) {
			System.out.println("학생정보를 찾을 수 없습니다");
			return;
		} 
		
		student[index] = null;
		
//		student[index].setName("");
//		student[index].setAge(0);
//		student[index].setHeight(0);
//		student[index].setAddress("");
//		student[index].setKor(0);
//		student[index].setEng(0);
//		student[index].setMath(0);
		System.out.println(target+"의 정보를 삭제합니다.");
	}
	
	public void select() {
		System.out.print("검색할 학생의 이름 >> ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		
		int index = findTarget(target);
		
		if(index == -1) {
			System.out.println("학생정보를 찾을 수 없습니다");
			return;
		} 
		
		System.out.println(target+"의 정보입니다.");
		System.out.println(student[index].toString());
	}
	
	public void update() {
		System.out.print("수정할 학생의 이름 >> ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		
		int index = findTarget(target);
		
		if(index == -1) {
			System.out.println("학생정보를 찾을 수 없습니다");
			return;
		} 
		
		// 국, 영, 수 점수 수정
		System.out.print("국어점수 수정 >> ");
		int kor = sc.nextInt();
		student[index].setKor(kor);
		
		System.out.print("영어점수 수정 >> ");
		int eng = sc.nextInt();
		student[index].setEng(eng);
		
		System.out.print("수학점수 수정 >> ");
		int math = sc.nextInt();
		student[index].setMath(math);
		
		System.out.println(student[index].toString());
		System.out.println("수정이 완료되었습니다");
	}
	
	public void allData() {
		for (int i = 0; i < student.length; i++) {
			StudentDto dto = student[i];
			if(dto != null && !(dto.getName().equals(""))) {
				System.out.println(dto.toString());
			}
		}
	}
	
	public void save() {
		File file = new File("c:\\tmp\\students1122.txt");

		try {
			if (file.createNewFile()) {
				System.out.println("파일 생성 성공!");
			} else {
				System.out.println("파일 생성 실패");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < student.length; i++) {
				StudentDto dto = student[i];
				if(dto != null && !(dto.getName().equals(""))) {
					pw.write(dto.toString());
				}
			}
			System.out.println("파일을 저장하였습니다.");
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			File file = new File("c:\\tmp\\students1122.txt");
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			System.out.println("파일을 읽습니다...");
			String str = "";
			
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
