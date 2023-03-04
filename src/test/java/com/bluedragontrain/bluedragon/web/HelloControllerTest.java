package com.bluedragontrain.bluedragon.web;


import com.bluedragontrain.bluedragon.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // JUnit 에 내장된 실행자 외에 다른 실행자 실행, 스프링 실행자
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
}) // 여러 스프링 어노테이션 중, Web 에 집중할 수 있는 어노테이션이다.
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈을 주입받는다.
    private MockMvc mvc; // 웹 API 테스트 할 때 사용, 스프링 MVC 테스트의 시작점

    @WithMockUser(roles = "USER")
    @Test
    public void Hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc 를 통해 /hello 주소로 GET 요청을 한다.
                .andExpect(status().isOk()) // HTTP Header 의 Status 를 검증(200, 404, 500 ...), 여기선 OK 즉, 200인지 아닌지 검증
                .andExpect(content().string(hello)); // Controller 에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount",
                String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath: JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount))); // $를 기준으로 필드명을 명시한다.
    }
}
