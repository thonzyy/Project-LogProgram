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
    private String code200Share, code403Share, code404Share, code500Share;
    private List<Integer> number;
    
    public Function2(Ui ui) {
    	this.ui = ui;
    }

    public void countHttpStatusCode() throws IOException {
        number = new ReadLogFile(ui).readFile();
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
        printResult();
        
    }
    
    public void countHttpStatusCode(int num1, int num2) throws IOException {
    	number = new ReadLogFile(ui).readFile(num1, num2);
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
    	printResult();
    	
    }

    public void calCode200Share() {
        code200Share = String.format("%4.2f", (code200 / (double) requestNum) * 100);
    }
    
    public void calCode403Share() {
    	code403Share = String.format("%4.2f", (code403 / (double) requestNum) * 100);
    }
    
    public void calCode404Share() {
    	code404Share = String.format("%4.2f", (code404 / (double) requestNum) * 100);
    }

    public void calCode500Share() {
        code500Share = String.format("%4.2f", (code500 / (double) requestNum) * 100);
    }

    public void printResult() {
        calCode200Share();
        calCode403Share();
        calCode404Share();
		calCode500Share();

		// 결과 출력
		System.out.println("Code 200 Count: " + code200Share + "%");
		System.out.println("Code 403 Count: " + code403Share + "%");
		System.out.println("Code 404 Count: " + code404Share + "%");
		System.out.println("Code 500 Share: " + code500Share + "%");
    }
}
