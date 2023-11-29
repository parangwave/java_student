package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.StudentDto;
import file.FileIO;

// Data Access Object: 데이터를 취급하는 클래스
public class StudentDao implements StudentDaoInter {
	// 학생 데이터 관리 배열
//	private StudentDto student[];
	
	// 학생 데이터 관리 목록
	private List<StudentDto> list;
	
//	private int count;
	
	private FileIO fio;
	
	// 추가, 삭제, 검색, 수정 (CRUD)
	public StudentDao() { // 생성자는 딱 한 번 호출됨
		fio = new FileIO("student");
		fio.create();
		
//		count = 0;
		
//		student = new StudentDto[10]; // 변수만 생성
		// StudentDto student1, student2, student3 ... ;
		
		list = new ArrayList<StudentDto>();
		
		// dto를 생성
//		for (int i = 0; i < student.length; i++) {
//		student[i] = new StudentDto();
//		}
	}
	
	@Override
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
		
		StudentDto student = new StudentDto(name, age, height, address, kor, eng, math);
		list.add(student);
//		count++;
	}
	
	@Override
	public int findTarget(String target) {
		int index = -1;
		
		for (int i = 0; i < list.size(); i++) {
			StudentDto dto = list.get(i);
			if (target.equals(dto.getName())) {
				index = i;
				break;
			}
//			if (dto != null) {
//				if (target.equals(dto.getName())) {
//					index = i;
//					break;
//				}
//			}
		}
		return index;
	}
	
	@Override
	public void delete() {
		System.out.print("삭제하고 싶은 학생의 이름 >> ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		
		int index = findTarget(target);
		
		if(index == -1) {
			System.out.println("학생정보를 찾을 수 없습니다");
			return;
		} 
		
		list.remove(index);
		
//		student[index].setName("");
//		student[index].setAge(0);
//		student[index].setHeight(0);
//		student[index].setAddress("");
//		student[index].setKor(0);
//		student[index].setEng(0);
//		student[index].setMath(0);
		
		System.out.println(target + "의 정보를 삭제합니다.");
	}
	
	@Override
	public void select() {
		System.out.print("검색할 학생의 이름 >> ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		
		for (int i = 0; i < list.size(); i++) {
			StudentDto dto = list.get(i);
			if(target.equals(dto.getName())) {
				System.out.println(target+"의 정보입니다.");
				dto.print();
			}
//			if(dto != null && dto.getName().equals("") == false) {
//				if(target.equals(dto.getName())) {
//					System.out.println(target+"의 정보입니다.");
//					dto.print();
//				}
//			}
		}
	}
	
	@Override
	public void update() {
		System.out.print("수정할 학생의 이름 >> ");
		Scanner sc = new Scanner(System.in);
		String target = sc.next();
		
		int index = findTarget(target);
		
		if(index == -1) {
			System.out.println("학생정보를 찾을 수 없습니다");
			return;
		} 
		
		System.out.println("수정할 학생을 찾았습니다");
		
		// 국, 영, 수 점수 수정
		StudentDto student = list.get(index);
		
		System.out.print("국어점수 수정 >> ");
		int kor = sc.nextInt();
		student.setKor(kor);
		
		System.out.print("영어점수 수정 >> ");
		int eng = sc.nextInt();
		student.setEng(eng);
		
		System.out.print("수학점수 수정 >> ");
		int math = sc.nextInt();
		student.setMath(math);
		
		System.out.println(student.toString());
		System.out.println("성공적으로 수정이 완료되었습니다");
	}
	
	@Override
	public void allData() {
		for (int i = 0; i < list.size(); i++) {
			StudentDto dto = list.get(i);
			System.out.println(dto.toString());
//			if(dto != null && !(dto.getName().equals(""))) {
//				System.out.println(dto.toString());
//			}
		}
	}
	
	// 파일 저장
	@Override
	public void save() {
		// 실제로 삭제된 데이터를 제외한 (정상적인) 데이터가 몇 개?
//		int ci = 0;
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i) != null && list.get(i).getName().equals("") == false) {
//				ci++;
//			}
//		}
		
		// 배열
//		String arr[] = new String[ci];
//		int j = 0;
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i) != null && list.get(i).getName().equals("") == false) {
//				arr[j] = list.get(i).toString();
//				j++;
//			}
//		}
		
		String arr[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			StudentDto dto = list.get(i);
			arr[i] = dto.toString();
		}
		
		fio.dataSave(arr);
	}
	
	// 파일 읽기
	@Override
	public void load() {
		String arr[] = fio.dataLoad();
		
		// 읽을 데이터가 없을 때
		if (arr == null || arr.length == 0) {
//			count = 0; // 데이터가 없으니 데이터를 처음부터 넣어야하여 초기화함
			System.out.println("읽을 데이터가 없습니다");
			return;
		}
		
//		count = arr.length;
		
		// string -> student[]
		for (int i = 0; i < arr.length; i++) {
			// 문자열 자르기
			String split[] = arr[i].split("-");
			
			// 자른 문자열을 dto에 저장하기 위한 처리
			String name = split[0];
			int age = Integer.parseInt(split[1]);
			double height = Double.parseDouble(split[2]);
			String address = split[3];
			int kor = Integer.parseInt(split[4]);
			int eng = Integer.parseInt(split[5]);
			int math = Integer.parseInt(split[6]);
			
			StudentDto dto = new StudentDto(name, age, height, address, kor, eng, math);
			list.add(dto);
		}
		System.out.println("데이터로드 성공!");
	}
}
