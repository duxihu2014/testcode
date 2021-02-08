package test2222;



import java.io.BufferedReader;
import java.io.FileReader;

public class Test2 {
	
	public static void main(String[] args)throws Exception{
                //�ļ�����·���ĳ����Լ����ļ�·��
		FileReader fr=new FileReader("D:\\aaa.txt");
		//���Ի��ɹ���Ŀ¼�µ������ı��ļ�
		BufferedReader br=new BufferedReader(fr);
		String s  = "";
		while(br.readLine()!=null){
           s += br.readLine()+"\n";
			
		}
		br.close();
		System.out.println(s);
	}
	
}