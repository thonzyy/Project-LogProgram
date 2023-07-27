package Login;


import java.io.IOException;
import java.util.List;

public class Function2 {
	
	private Ui ui;

    private int code200 = 0;
    private int code403 = 0;
    private int code404 = 0;
    private int code500 = 0;
    private int requestNum = 0;
    private String code403Share, code500Share;
    private List<Integer> number;
    
    public Function2(Ui ui) {
    	this.ui = ui;
    	new ReadLogFile(ui);
    }

    public void countHttpStatusCode() throws IOException {
        number = ReadLogFile.readFile();
        for (int num : number) {
            if (num == 200) {
                code200++;
            } else if (num == 404) {
                code404++;
            } else if (num == 403) {
                code403++;
            } else if (num == 500) {
                code500++;
            }
        }
        requestNum = number.size(); // 전체 요청 개수 설정
        
        ////////////////////
        Function2 f2 = new Function2(ui);
        try {
            f2.countHttpStatusCode();
            f2.calCode403Share();
            f2.calCode500Share();

            // 결과 출력
            System.out.println("Code 200 Count: " + f2.code200);
            System.out.println("Code 403 Count: " + f2.code403);
            System.out.println("Code 404 Count: " + f2.code404);
            System.out.println("Code 500 Count: " + f2.code500);
            System.out.println("Total Requests: " + f2.requestNum);
            System.out.println("Code 403 Share: " + f2.code403Share + "%");
            System.out.println("Code 500 Share: " + f2.code500Share + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /////////////////////
        
    }

    public void calCode403Share() {
        code403Share = String.format("%4.2f", (code403 / (double) requestNum) * 100);
    }

    public void calCode500Share() {
        code500Share = String.format("%4.2f", (code500 / (double) requestNum) * 100);
    }

//    public static void main(String[] args) {
//        Function2 f2 = new Function2();
//        try {
//            f2.countHttpStatusCode();
//            f2.calCode403Share();
//            f2.calCode500Share();
//
//            // 결과 출력
//            System.out.println("Code 200 Count: " + f2.code200);
//            System.out.println("Code 403 Count: " + f2.code403);
//            System.out.println("Code 404 Count: " + f2.code404);
//            System.out.println("Code 500 Count: " + f2.code500);
//            System.out.println("Total Requests: " + f2.requestNum);
//            System.out.println("Code 403 Share: " + f2.code403Share + "%");
//            System.out.println("Code 500 Share: " + f2.code500Share + "%");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
