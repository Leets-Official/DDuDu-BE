package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.dto.AddItemRequest;
import DDuDu.DDuDu.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        itemRepository. deleteAll();
    }

    @DisplayName("투두 등록에 성공")
    @Test
    public void addArticle() throws Exception{
        //given
        final String url = "/items";
        final String content = "content";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        final Date createDate = dateFormat.parse("2023-11-09");
        final Date deadline = dateFormat.parse("2023-11-11");
        final boolean isCompleted = false;
        final boolean isRoutine = false;
        final AddItemRequest userRequest = new AddItemRequest(content, createDate, deadline, isCompleted, isRoutine);

        //JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Item> itemList = itemRepository.findAll();

        assertThat(itemList.size()).isEqualTo(1);
        assertThat(itemList.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("투두 목록 조회에 성공한다.")
    @Test
    public void findAllItems() throws Exception {
        // given
        final String url = "/items";
        final String content = "content";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        final Date createDate = dateFormat.parse("2023-11-09");
        final Date deadline = dateFormat.parse("2023-11-11");
        final boolean isCompleted = false;
        final boolean isRoutine = false;

        itemRepository.save(Item.builder()
                .content(content)
                .createDate(createDate)
                .deadline(deadline)
                .isCompleted(isCompleted)
                .isRoutine(isRoutine)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].createDate").value(createDate))
                .andExpect(jsonPath("$[0].deadline").value(deadline))
                .andExpect(jsonPath("$[0].isCompleted").value(isCompleted))
                .andExpect(jsonPath("$[0].isRoutine").value(isRoutine));
    }
}