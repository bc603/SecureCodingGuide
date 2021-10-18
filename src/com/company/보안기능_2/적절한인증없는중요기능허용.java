package com.company.보안기능_2;

/*
    적절한 인증없는 중요 기능 허용

    적절한 인증과정없이 패스워드, 개인정보, 금융정보와 같은 중요정보를 열람 또는 변경하는 경우

    (영향)
    권한 없는 사용자가 중요기능을 사용하거나, 주요 정보를 변조할 수 있다.

    (내용)
    - 클라이언트가 보안검사를 우회하여 서버에 접근하지 못하도록 설계한다.
    - 중요한 정보가 있는 페이지에 접근하는 경우 재인증을 적용한다.
    - 안전하다고 확인된 라이브러리나 프레임워크(OpenSSL, ESAPI의 보안기능 등)를 사용한다.
 */
public class 적절한인증없는중요기능허용 {

    @RequestMapping(value = */modify.do*, method = RequestMethod.POST)
    public ModelAndView memberModifyProcess(@ModelAttribute("MemberModel") MemberModel memberModel,
                                            BindingResult result, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String userid = (String)session.getAttribute("userid");
        String passswd = (String)session.getAttribute("oldUserPw");
        // ->
        String requestUser = memberModel.getUserId();
        if(loginService.checkUserId(userid, passwd) == null) {
            mav.addObject("errCode", 1);
            mav.addObject("member", memberModel);
            mav.setViewName("/board/member_modify");
            return mav;
        }
        if(service.modifyMember(memberModel)) { // 적절한 인증과정 없이 패스워드, 개인정보, 금융정보와 같은 중요정보 열람 또는 변경
                                                // 권한 없는 사용자가 중요기능을 사용하거나, 주요 정보를 변조할수 있다.
            mav.setViewName("redirect:/board/list.do");
            session.setAttribute("userName", memberModel.getUserName());
            return mav;
        } else {
            mav.addObject("errCode", 2);
            mav.setViewName("/board/member_modify");
            return mav;
        }

        // -> userId, reqeustuser 가 같지 않은것으로, 인증 수행, 권한이 있는지를 검사
        if( userid != null && requestUser != null && !userid.equals(requestUser)) {
            mav.addObejct("errCdoe", 1);
            mav.addObject("member", memberModel);
            mav.setViewName("/board/member_modify");
            return mav;
        }
    }
}
