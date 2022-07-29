package eu.gordaniil.rikexercisebe.controller;

import eu.gordaniil.rikexercisebe.domain.event.EventHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenValidEventDto_ShouldReturnSavedEventDtoAndStatusOk() throws Exception {
        final String expected = "{\"error\":\"Argument invalid exception\",\"exceptionData\":\"invalid value 'null' submitted for field 'date'. Message: 'date' cannot be null, \",\"correlationId\":\"-\"}";

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/rest/v1/events")
                                .contentType("application/json")
                                .content(EventHelper.eventDtoJsonString())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expected));
    }

    @Test
    public void givenValidEventId_ShouldThrowInternalServerErrorSinceEndpointDoesNotSupportPostAndReturnStatus5xx() throws Exception {
        final String expected = "{\"error\":\"Something went wrong\",\"exceptionData\":\"Please contact our support\",\"correlationId\":\"-\"}";

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/rest/v1/events/" + EventHelper.eventDto.getExtId())
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(expected));
    }

    @Test
    public void givenNonExistingEventId_ShouldThrowNotFoundExceptionAndStatus4xx() throws Exception {
        final String expected = "{\"error\":\"NOT_FOUND\",\"exceptionData\":\"'event' with param 'extId' and value '3f7c41d0-7a69-4f5f-8107-cc9d0183198c' not found\",\"correlationId\":\"-\"}";

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/rest/v1/events/" + EventHelper.eventDto.getExtId())
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(expected));
    }

}
