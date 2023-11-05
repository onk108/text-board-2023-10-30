package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    int id = 0;
    List<Quotation> quotations = new ArrayList<>();
    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();


            if(cmd.equals("종료")) {
                break;
            } else if(cmd.equals("등록")) {
                actionWrite();
            } else if(cmd.equals("목록")) {
                actionList();
            } else if(cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            } else if(cmd.startsWith("수정?")) {
                actionModify(cmd);
            }
        }
    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if(id==0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

    void actionRemove(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if(id==0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }

    int getParamAsInt(String cmd, String paramname, int defauleValue) {
        String[] cmdBits = cmd.split("\\?",2);
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        for(int i=0; i<queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=",2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if(paramName.equals(paramname)) {
                try {
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    return defauleValue;
                }
            }
        }
        return defauleValue;
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------");

        if(quotations.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }

        for(int i=quotations.size()-1; i>=0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        id++;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }
}
