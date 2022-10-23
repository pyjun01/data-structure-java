import java.io.*;

public class CalculatorTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		// TODO : 아래 문장을 삭제하고 구현해라.
		System.out.println("<< command 함수에서 " + input + " 명령을 처리할 예정입니다 >>");

    // 1. input의 공백을 제거 한다.
    // 2. postfix expression으로 변환한다.
    // 3. 우선순위 별로 연산을 진행한다.
    /* 우선순위
     ( )
      ^
      - (unary)
      * / %
      + - (binary)
    */
	}
}
