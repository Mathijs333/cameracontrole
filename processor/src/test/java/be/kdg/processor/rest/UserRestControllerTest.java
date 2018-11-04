package be.kdg.processor.rest;

import be.kdg.processor.dto.UserDTO;
import be.kdg.processor.model.User;
import be.kdg.processor.persistence.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/11/2018 22:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testCreate() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("mathijs");
        userDTO.setPassword("test");
        String jsonRequest = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().string(containsString("username")));
    }
    @Test
    public void testRead() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("mathijs");
        user.setPassword("test");
        userService.save(user);
        mockMvc.perform(get("/api/user/read/1"))
                .andDo(print())
                .andExpect(content().string(containsString("mathijs")));
    }
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/user/delete/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdate() throws Exception {

        User user = new User();
        user.setId(1);
        user.setUsername("mathijs");
        user.setPassword("test1");
        userService.save(user);
        UserDTO userTest = new UserDTO();
        userTest.setUsername("mathijs");
        userTest.setPassword("test1");
        String jsonRequest = objectMapper.writeValueAsString(userTest);
        mockMvc.perform(put("/api/user/update/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(content().string(containsString("test1")));
    }


}
