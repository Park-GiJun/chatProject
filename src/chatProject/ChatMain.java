package chatProject;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatMain implements ActionListener, Runnable {
    private JFrame chatFrame; // 채팅 프레임
    private JButton cSend; // 메시지 전송 버튼
    private JLabel cMember; // 상대 정보
    private JTextArea cta; // 메시지 입력 필드
    private JTextPane ctp; // 메시지 출력 필드
    private JPanel cPanel; // 패널

    public ChatMain() throws BadLocationException {
        // 필드 및 프레임 생성, 입력 영역
        cPanel = new JPanel(); // 패널 생성
        cPanel.setVisible(true); // 패널을 화면에 표시
        cMember = new JLabel("이름(내선번호 0000)", SwingConstants.LEFT); // 상대 정보 (왼쪽 정렬)
        cMember.setBounds(202, 12, 25, 11);
        cPanel.setBackground(Color.WHITE); // 패널 배경 색상
        chatFrame = new JFrame(); // 채팅창 프레임
        chatFrame.setSize(610, 560);
        cta = new JTextArea(); // 채팅 입력 필드 (여러 줄 입력)
        cta.setEditable(true); // 입력 필드에서 내용 수정 가능
        cta.setBounds(190, 520, 610, 40);
        cSend = new JButton("SEND"); // 전송 버튼
        cSend.setBounds(718, 527, 55, 26);
        cSend.addActionListener(this);

        // 채팅 출력 영역
        ctp = new JTextPane(); // 서식 지정해서 출력하기
        ctp.setEditable(false); // 출력한 메시지 수정 불가능
        JScrollPane cScroll = new JScrollPane(ctp); // 스크롤
        cScroll.getVerticalScrollBar().setValue(0);
        StyledDocument csd = new DefaultStyledDocument(); // 텍스트 서식 지정 클래스
        Style style = csd.addStyle("textLR", null); // 스타일 생성
        boolean cSendMe = true; // 사용자가 보낸 메시지 = true
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT); // 사용자의 메시지 오른쪽 정렬
        if (!cSendMe) { // false면 왼쪽 정렬
            StyleConstants.setAlignment(style, StyleConstants.ALIGN_LEFT);
        }
        csd.insertString(csd.getLength(), ctp + "\n", style); // 서식에 맞춰 출력
        cta.setText(""); // 출력 후 입력창 초기화 (새로운 메시지 입력)
        cta.requestFocus(); // 입력 필드 포커스

        cPanel.add(cMember);
        cPanel.add(cSend);
        cPanel.add(ctp);
        cPanel.setVisible(true);
        chatFrame.add(cta);
        chatFrame.add(ctp);
        chatFrame.add(cScroll);
        chatFrame.setVisible(true);
    }

    
    public void svrSocket() {
        String svrAdress = "222.101.222.237"; // 서버 IP
        int svrPort = 8081; // 서버 포트
        try { // 특정 포트를 통한 실시간 양방향 통신 (Socket)
            Socket cSocket = new Socket(svrAdress, svrPort);
            InputStream input = cSocket.getInputStream(); // 데이터 읽기
            OutputStream output = cSocket.getOutputStream();

            cSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        try {
            ChatMain cm = new ChatMain();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
}
