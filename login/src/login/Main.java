package login;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("로그인을 시작하지!");
		String id,pw;
		Scanner sc = new Scanner(System.in);
		Check check = new Check();
		for(;;) {
			System.out.print("목록은(4) 로그인을 할려면(1), 회원가입을 할려면(2), 회원삭제는(3) 입력하세요.");
			int menu = sc.nextInt();
			sc.nextLine(); //숫자 입력후 엔터값 없애기
			if(menu==1) {
				System.out.print("아이디를 입력해주세요:");
				id=sc.nextLine();
				System.out.print("패스워드를 입력해주세요:");
				pw=sc.nextLine();
				
				//객체(Check)의 메소드(login(string id, string pw):int (0:db접속오류,1:아이디가 틀리다.
				//2:패스워드 틀리다. 3:로그인성공)를 통해서 로그인 여부 확인 
				
				CheckType flag=check.login(id,pw);
				//int flag=check.login(id,pw);
						
				
				if(flag==CheckType.SUCCESS)
					System.out.println("정해놓은 값을 꺼내서 사용도 가능"+flag.value());
					System.out.println("로그인 성공");
				
			}else if(menu==2){
				System.out.println("회원가입 시작합니다.");
				System.out.print("아이디를 입력해주세요:");
				id=sc.nextLine();
				System.out.print("패스워드를 입력해주세요:");
				pw=sc.nextLine();
				check.signUp(id,pw);
				System.out.println("회원가입완료");
				
			}else if(menu==4){
				List<User> list=check.list();
				for(User temp  : list) {
					System.out.print(temp.id+"   ");
					System.out.println(temp.pw);
				}
			}else if(menu==10) {
				check.test(); //날짜 삽입테스트
			}
			
		}

		
		
	}

}
