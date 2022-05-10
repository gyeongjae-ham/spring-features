package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    // 원래 Controller 안에서 String 반환이면 view를 찾게되므로, ResponseBody 애노테이션을 넣어주면 반환 String을 그대로 http 응답 메시지에 넣어준다
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    // query parameter 변수명이랑 같은 이름이면 ("username") 이 부분 생략 가능하다
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    // query parameter 변수명이랑 같은 이름이면 @RequestParam 부분 전체 생략 가능하다
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    // 기본형인 int 값은 null을 받을 수 없다 Integer로 받아야 한다
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    // 빈 문자의 경우에도 default 적용해준다
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = true, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // parameter를 Map, MultiValueMap으로 조회할 수 있다
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) { // 이렇게 돌려도 모델 객체를 생성하고 파라미터 값도 모두 들어가 있다!!
        // 모델의 파라미터 타입과 다른 요청이 들어오면 binding exception이 발생한다
        // 검증 처리 로직을 구현해줘야 한다
        // 검증과 예외 처리 과정이 controller에서 빡센 과정이다..;;
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // @Data lombok에 toString 있어서 예쁘게 만들어 준다

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { // @ModelAttribute 생략가능하다!!!!!!
        // 그럼 spring은 생략되어 있으면 뭘 기준으로 판단하냐?
        // String, int, Integer 같은 단순 타입 = @RequestParam
        // 그 외의 나머지는 @ModelAttribute(argument resolver로 지정해 둔 타입 제외)
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }
}
