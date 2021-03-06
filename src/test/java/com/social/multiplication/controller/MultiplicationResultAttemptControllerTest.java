package com.social.multiplication.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.multiplication.controller.MultiplicationResultAttemptController.ResultResponse;
import com.social.multiplication.domain.Multiplication;
import com.social.multiplication.domain.MultiplicationResultAttempt;
import com.social.multiplication.domain.User;
import com.social.multiplication.service.MultiplicationService;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

	@MockBean
	private MultiplicationService multiplicationService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initField method below.
	private JacksonTester<MultiplicationResultAttempt> jsonResult;
	private JacksonTester<ResultResponse> jsonResponse;

	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void postResultResturnCorrect() throws Exception {
		genericParameterizedTest(true);
	}

	@Test
	public void postResultRetunNotCorrect() throws Exception {
		genericParameterizedTest(false);
	}

	void genericParameterizedTest(final boolean correct) throws Exception {
		// given (we're not testing here the service itself)
		given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(correct);

		User user = new User("John");
		Multiplication multiplication = new Multiplication(50, 70);
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500);

		// when
		MockHttpServletResponse response = mvc.perform(
				post("/results").contentType(MediaType.APPLICATION_JSON).content(jsonResult.write(attempt).getJson()))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new ResultResponse(correct)).getJson());
	}
}
