<%
    // 0: 인증 가능 사용자 및 비밀번호 목록.
    String[] users = { "admin1234", "testadmin1"};
    String[] passwords = { "admin1234", "testadmin1"};
    // 1: form 으로부터 전달된 데이터를 변수에 저장.
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    // 2: 사용자 인증
    String redirectUrl = "/adminlogin"; // 인증 실패시 재요청 될 url 
    for (int i = 0; i < users.length; i++) {
        if (users[i].equals(id) && passwords[i].equals(pw)) {
            session.setAttribute("signedUser", id); // 인증되었음 세션에 남김
            redirectUrl = "/adminindex"; // 인증 성공 시 재요청 url
        }
    }
    response.sendRedirect(redirectUrl);
%>