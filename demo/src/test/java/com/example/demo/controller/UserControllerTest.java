package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class UserControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Test
//     public void testSubmitString() throws Exception {
//         String input = "test";
//         mockMvc.perform(post("/api/submit")
//                 .contentType(MediaType.TEXT_PLAIN)
//                 .content(input))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string(input + "!"));
//     }
// }
