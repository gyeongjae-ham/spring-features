package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j // log 찍을 수 있게 해주는 애노테이션
@RestController // 응답 값으로 view를 찾는게 아니라 문자 그대로 반환, 테스트 할 때는 view가 필요 없으므로
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,  // MultiValueMap 하나의 key에 여러 value를 받을 수 있다!!
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ) {
    log.info("request={}", request);
    log.info("response={}", response);
    log.info("httpMethod={}", httpMethod);
    log.info("locale={}", locale);
    log.info("headerMap={}", headerMap);
    log.info("header={}", host);
    log.info("myCookie={}", cookie);
    return "ok";
    }
}
