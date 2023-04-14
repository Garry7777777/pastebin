package com.skypro.pastebin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.json.JSONObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
@AutoConfigureMockMvc
public class PastebinControllerTest {

    @Value("${starting.pastebin.uri}")
    private  String uri;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getPastebin() throws Exception {
        mockMvc.perform(get("/c5de2882"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.title").value("title1"));
    }

    @Test
    void getPastebinNotFound() throws Exception {
        mockMvc.perform(get("/c5de-2882"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findPastebin() throws Exception {

        mockMvc.perform(get("/last"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].title").value("title3"));
    }

    @Test
    void findPastebinTitle() throws Exception {
        mockMvc.perform(get("/search?title=title1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].title").value("title1"));
    }

    @Test
    void findPastebinBody() throws Exception {
        mockMvc.perform(get("/search?body=body1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].body").value("body1"));
    }

    @Test
    void findPastebinTitleAndBody() throws Exception {
        mockMvc.perform(get("/search?title=title1&body=body1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].body").value("body1"));
    }

    @Test
    void findPastebinNotFound() throws Exception {
        mockMvc.perform(get("/search?body=c5de-2882"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void createPastebin() throws Exception {
        mockMvc.perform(post("/?Expiration=Never&Exposure=Public")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new JSONObject()
                                .put("title", "title4")
                                .put("body","body4")
                                .toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$.uri").exists());
    }
}