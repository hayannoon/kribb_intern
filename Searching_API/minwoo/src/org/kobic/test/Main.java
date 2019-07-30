package org.kobic.test;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static String url_head = "http://fast.ntis.go.kr/apis/service/search/project?userKey=821614f489a22930b4971025776fb4b2e2b0cc3d&query=";
	public static String url_tail = "&displayCount=100&startPosition=1&sortby=-new";


	public static Scanner sc = new Scanner(System.in);
	private static boolean hasAnd = false;  //�˻�� And �����ڰ� �ִ��� ���θ� ������ ����
	private static boolean hasOr = false;	//�˻�� Or �����ڰ� �ִ��� ���θ� ������ ����
	static String input = null;

	
	
	//input���� �����ڰ� ����ִ��� Ȯ���Ѵ�.
	static void checkSearchingOperator(String input) {
		if (input.contains("+"))
			hasAnd = true;
		if (input.contains("|"))
			hasOr = true;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int howMany = 0; //��ū�� ���� ���� ����
		String output = ""; //��ȯ String ����
		String[] temp = new String[10]; //output�� ��������� �ӽ÷� ����ϴ� �迭

		System.out.println("검색어 입력 : ");
		input = sc.nextLine(); // ���ڿ� �Է�
		checkSearchingOperator(input); // ���ڿ� Ȯ��
		
		
		//�������� ������ ���� �˻�� �ڸ���, temp��� �迭�� �� ��ū���� �����Ѵ�.
		if (hasAnd) { // "+" ��ȣ�� ���� ���
			StringTokenizer tokens = new StringTokenizer(input, "+");
			for (int i = 0; tokens.hasMoreElements(); i++) {
				temp[i] = new String(tokens.nextToken());
				howMany++;
			}
		} else if (hasOr) {
			StringTokenizer tokens = new StringTokenizer(input, "|");
			for (int i = 0; tokens.hasMoreElements(); i++) {
				temp[i] = new String(tokens.nextToken());
				howMany++;
			}
			// �� ������ ������ �迭�� + Ȥ�� |�� �������� �߸� �������� ����ִ�.
		} else { //������ ���°��
			temp[0] = new String(input);
			howMany++;
		}
		
		
		
		// �˻����ʵ� �ȳ������ defalut �� bi �ʵ带 �־��ش�.
		for (int i = 0; i < howMany; i++) {
			if (!(temp[i].contains(":"))) {
				temp[i] = "bi:" + temp[i]; 
			}
		}
		// �� ������ ������ �� ��ū�� ��� : �� �����ϰ� �ȴ�.
		
		
		//�� ��ū�� ���鼭 �˻��� �ʵ尡 �ȵ� ��ū�� ã�Ƽ� ����Ʈ���� bi: �� �־��ָ鼭 ���� ������ش�.
		for (int i = 0; i < howMany; i++) {
			StringTokenizer tokens = new StringTokenizer(temp[i], ":");
			String tmp1 = tokens.nextToken();
			if ((!Field.isMember(tmp1))) {
				temp[i] = "bi:(" + tokens.nextToken().trim() + ")";
			} // Field�� �޴��� �ִ��� Ȯ���ϰ� ������ ��bƮ���� bi ����
			else {
				temp[i] = tmp1 + ":(" + tokens.nextToken().trim() + ")";
			}
		}
		// ������� �ϸ� temp�� �ϼ��� token���� �� ���ִ�.
		
		String query = "";
		if (hasAnd) { //And�� ���
			for (int i = 0; i < howMany; i++) {
				if (i != 0) {
					query = query.concat(" and "); // �� ó���� and ������ �ȵȴ�.
				}
				query = query.concat(temp[i]);
			}
		} else if (hasOr) { //or�� ���
			for (int i = 0; i < howMany; i++) {
				if (i != 0) {
					query = query.concat(" or "); // �� ó���� and ������ �ȵȴ�.
				}
				query = query.concat(temp[i]);
			}
		} else { //������ ���� ���
			query = query.concat(temp[0]);
		}
	
		

		
		
		try {
			query= URLEncoder.encode(query, "UTF-8");
			// encoding�ؼ� query�� ����
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//output file�� urlhead �����ϰ�
		output = new String(url_head);
		
		output = output.concat(query);
		output = output.concat(url_tail);
		
			
		output = output.replaceAll("  ", " ");
		//������ �ι����ٸ� �ٿ��ش�.
		
		
		

		NTISDataParser parser = new NTISDataParser();
		List<ResultModel> list = parser.getNtisData(output);
		
		for(ResultModel m : list) {
			System.out.println(m.getManager());
		}
		
	}

}